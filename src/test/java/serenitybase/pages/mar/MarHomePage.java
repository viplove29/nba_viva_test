package serenitybase.pages.mar;

import java.time.Duration;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import serenitybase.helpers.Utilities;

public class MarHomePage extends PageObject {
  @FindBy(id = "rua")
  private WebElementFacade reportNameTextBox;

  @FindBy(id = "gr")
  private WebElementFacade runReportButton;

  @FindBy(xpath = "//div[@class='rpt-split-view rpt-split-view-right']")
  private WebElementFacade reportTemplatesContainer;

  private WebElementFacade getReportRow(String reportName) {
    return find(String.format("//tr[@id='%s']", reportName));
  }

  public void selectReportTemplate(String reportTemplate) {
    reportTemplatesContainer.findBy(String.format(".//span[text()='%s']", reportTemplate)).click();
  }

  public void setReportName(String reportName) {
    typeInto(reportNameTextBox, reportName);
  }

  public void runReport() {
    runReportButton.click();
  }

  public void waitForReportToComplete(String reportName) {
    withTimeoutOf(Duration.ofMinutes(5)).waitFor(getReportRow(reportName));
  }

  public void clickExcelQuickActions(String reportName) {
    Utilities.setDownloadsCount();
    getReportRow(reportName).findBy(".//img[contains(@ng-src,'excel')]").click();
  }

  public void clickCsvQuickActions(String reportName) {
    getReportRow(reportName).findBy(".//img[contains(@ng-src,'csv')]").click();
  }

  public void clickOnReport(String reportName) {
    getReportRow(reportName).findBy(String.format(".//span[text()='%s']", reportName)).click();
  }
}
