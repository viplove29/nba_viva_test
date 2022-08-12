package serenitybase.helpers;

import static org.awaitility.Awaitility.await;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities extends PageObject {
  private static int downloadsCount;
  private static String mostRecentFile;
  private static final File downloads =
      new File(String.format("%s/Downloads/", System.getProperty("user.home")));
  private static WebDriver driver;

  public WebDriver getDriver() {
    if (driver == null) {
      driver = Serenity.getDriver();
    }
    return driver;
  }

  public void scrollToElement(WebElement element) {
    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
  }

  public static void setActiveTabContentIdSessionVariable() {
    String activeTabContentId =
        ThucydidesWebDriverSupport.getDriver()
            .findElement(By.cssSelector(".rpt-tab-content.active"))
            .getAttribute("id");
    Serenity.setSessionVariable("activeTabContentId").to(activeTabContentId);
  }

  public static void setReportTypeSessionVariable(WebElement reportRow) {
    String reportType =
        reportRow.findElement(By.xpath("//div[@class='rpt-recent-table-line']//span[2]")).getText();
    Serenity.setSessionVariable("reportType").to(reportType);
  }

  public static void simpleSleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

  public static void waitForDownload() {
    await().atMost(90, TimeUnit.SECONDS).until(Utilities::hasFileBeenDownloaded);
    //  This wait is required to select the new file
    simpleSleep(1000);
    setMostRecentFile();
  }

  public static int countDownloads() {
    return Objects.requireNonNull(downloads.listFiles()).length;
  }

  public static void setDownloadsCount() {
    downloadsCount = Objects.requireNonNull(downloads.listFiles()).length;
  }

  public static boolean hasFileBeenDownloaded() {
    return countDownloads() > downloadsCount;
  }

  public static String getMostRecentFile() {
    return mostRecentFile;
  }

  public static void setMostRecentFile() {
    mostRecentFile =
        Arrays.stream(Objects.requireNonNull(downloads.listFiles()))
            .max(Comparator.comparingLong(File::lastModified))
            .get()
            .getPath();
  }

  public static String getExtension(String fileName) {
    String extension = FilenameUtils.getExtension(fileName);
    if (StringUtils.isBlank(extension)) {
      throw new IllegalArgumentException(
          String.format("Could not parse extension from %s", fileName));
    }
    return extension;
  }

  public static void waitForActiveTabLoadingSpinner() {
    boolean attemptedRefresh = false;
    int retries = 0;
    int maxRetries = 100;
    WebDriverWait wait = new WebDriverWait(ThucydidesWebDriverSupport.getDriver(), 1);
    try {
      String activeTabContentId = Serenity.sessionVariableCalled("activeTabContentId");
      String spinnerPath =
          String.format("//div[@id='%s']//i[contains(@class,'fa-spinner')]", activeTabContentId);
      WebElement loadingSpinner =
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(spinnerPath)));
      while (loadingSpinner.isDisplayed()) {
        simpleSleep(500);
        retries++;
        if (retries > maxRetries && loadingSpinner.isDisplayed()) {
          if (!attemptedRefresh) {
            ThucydidesWebDriverSupport.getDriver().navigate().refresh();
            attemptedRefresh = true;
            retries = 0;
          } else break;
        }
      }
    } catch (Exception ex) {
      System.out.println("Active tab loading spinner no longer found");
    }
  }

  public static void waitForHomePageLoadingSpinners() {
    int retries = 0;
    int maxRetries = 100;
    WebDriverWait wait = new WebDriverWait(ThucydidesWebDriverSupport.getDriver(), 5);

    String leftLoadingStatusXPath =
        "//div[@recent-report-left-view]//div[@http-loading-status]//span";
    WebElement leftLoadingStatus =
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(leftLoadingStatusXPath)));
    while (leftLoadingStatus.isDisplayed()) {
      simpleSleep(1000);
      retries++;
      if (!leftLoadingStatus.isDisplayed()) {
        break;
      }
      if (retries > maxRetries) {
        throw new RuntimeException("Left Loading Status Spinner showing after 100 seconds");
      }
    }
    retries = 0;
    String rightLoadingStatusXPath =
        "//div[@report-list-right-view]//div[@http-loading-status]//span";
    WebElement rightLoadingStatus =
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(rightLoadingStatusXPath)));
    while (rightLoadingStatus.isDisplayed()) {
      simpleSleep(1000);
      retries++;
      if (!rightLoadingStatus.isDisplayed()) {
        break;
      }
      if (retries > maxRetries) {
        throw new RuntimeException("Right Loading Status showing after 100 seconds");
      }
    }
  }

  public static void waitForReportPageLoadingSpinners() {
    int retries = 0;
    int maxRetries = 100;
    int spinnerCount =
        ThucydidesWebDriverSupport.getDriver()
            .findElements(By.xpath("//i[contains(@class,'fa-spinner')]"))
            .size();
    for (int i = 0; i < spinnerCount; i++) {
      while (getSpinnerDisplayState(i)) {
        simpleSleep(1000);
        retries++;
        if (retries > maxRetries) {
          throw new RuntimeException(
              "Report Page Loading Spinners still showing after 100 seconds");
        }
      }
    }
  }

  // Used to get Spinner display state since they throw Stale Element Exceptions a lot
  private static boolean getSpinnerDisplayState(int spinnerNum) {
    List<WebElement> spinners =
        ThucydidesWebDriverSupport.getDriver()
            .findElements(By.xpath("//i[contains(@class,'fa-spinner')]"));
    try {
      return spinners.get(spinnerNum).isDisplayed();
    } catch (StaleElementReferenceException e) {
      List<WebElement> retrySpinners =
          ThucydidesWebDriverSupport.getDriver()
              .findElements(By.xpath("//i[contains(@class,'fa-spinner')]"));
      return retrySpinners.get(spinnerNum).isDisplayed();
    }
  }

  public static boolean checkEnvironmentVersion(String requiredVersion, String environmentVersion) {
    int requiredMajorVersion = Integer.parseInt(requiredVersion.substring(0, 2));
    int envMajorVersion = Integer.parseInt(environmentVersion.substring(0, 2));
    if (envMajorVersion > requiredMajorVersion) {
      return true;
    } else if (envMajorVersion < requiredMajorVersion) {
      return false;
    } else {
      // Handle envMajorVersion == requiredMajorVersion
      int requiredMinorVersion = Integer.parseInt(requiredVersion.substring(3, 4));
      int envMinorVersion = Integer.parseInt(environmentVersion.substring(3, 4));
      return envMinorVersion >= requiredMinorVersion;
    }
  }
}
