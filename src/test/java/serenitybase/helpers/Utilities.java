package serenitybase.helpers;

import static org.awaitility.Awaitility.await;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
  private static int downloadsCount;
  private static String mostRecentFile;
  private static final File downloads =
      new File(String.format("%s/Downloads/", System.getProperty("user.home")));

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
}
