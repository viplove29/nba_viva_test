package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import serenitybase.helpers.Utilities;
import serenitybase.pages.mar.MarHomePage;
import serenitybase.pages.mar.WebReportPage;
import serenitybase.pages.reports.ExcelReport;

public class ReportTemplateTestSteps {
  private MarHomePage marHomePage;
  private WebReportPage webReportPage;
  private String reportName;
  private int webResults;

  public void selectReportTemplate(String reportTemplate) {
    marHomePage.selectReportTemplate(reportTemplate);
  }

  public void generateReportWithRandomName() {
    reportName = UUID.randomUUID().toString();
    marHomePage.setReportName(reportName);
    marHomePage.runReport();
    marHomePage.waitForReportToComplete(reportName);
  }

  public void excelQuickActions() {
    marHomePage.clickExcelQuickActions(reportName);
  }

  public void csvQuickActions() {
    marHomePage.clickCsvQuickActions(reportName);
  }

  public void navigateToGeneratedReport() {
    marHomePage.clickOnReport(reportName);
    webResults = webReportPage.getNumberOfResults();
  }

  public void openDownloadedExcelReport() {
    Utilities.waitForDownload();
    Utilities.openMostRecentExcelFile();
  }

  public void openDownloadedCsvReport() {
    Utilities.waitForDownload();
    Utilities.openMostRecentCsvFile();
  }

  public void verifyNumberOfRows() {
    ExcelReport excelReport = new ExcelReport();
    assertThat(webResults).isEqualTo(excelReport.getLastRowNum("Report Data"));
  }
}
