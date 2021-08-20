package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import serenitybase.helpers.Utilities;
import serenitybase.pages.mar.MarHomePage;
import serenitybase.pages.mar.WebReportPage;
import serenitybase.pages.reports.CsvReport;
import serenitybase.pages.reports.ExcelReport;

public class ReportTemplateTestSteps {
  private MarHomePage marHomePage;
  private WebReportPage webReportPage;
  private String reportName;

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
  }

  public void verifyNumberOfRows() {
    ExcelReport excelReport = new ExcelReport();
    assertThat(excelReport.getLastRowNum("Report Data"))
        .isEqualTo(webReportPage.getNumberOfResults());
  }

  public void selectOptionUnderActions(String option) {
    marHomePage.selectOptionUnderActions(option);
  }

  public void verifyReportHasFileFormat(String fileFormat) {
    Utilities.waitForDownload();
    assertThat(Utilities.getExtension(Utilities.getMostRecentFile()))
        .isEqualToIgnoringCase(fileFormat.equals("Excel") ? "XLSX" : "CSV");
  }

  public void verifyColumns() {
    List<String> headers;
    String extension = Utilities.getExtension(Utilities.getMostRecentFile());
    if (extension.equalsIgnoreCase("CSV")) {
      CsvReport csvReport = new CsvReport();
      headers = csvReport.getCsvReportHeaders();
    } else if (extension.equalsIgnoreCase("XLSX")) {
      ExcelReport excelReport = new ExcelReport();
      headers = excelReport.getReportHeaders();
    } else {
      throw new IllegalArgumentException(String.format("File type %s not supported", extension));
    }
    assertThat(headers).containsAll(webReportPage.getReportHeaders());
  }

  public void verifyCoverSheet(boolean includeCoverSheet) {
    ExcelReport excelReport = new ExcelReport();
    if (includeCoverSheet) {
      assertThat(excelReport.getReportSheetNames()).contains("Cover Page");
    } else {
      assertThat(excelReport.getReportSheetNames()).doesNotContain("Cover Page");
    }
  }

  public void verifySheetPermissions(String permission) {
    ExcelReport excelReport = new ExcelReport();
    switch (permission) {
      case "Editable":
        assertThat(excelReport.isSheetProtected("Report Data")).isFalse();
        break;
      case "Locked":
        assertThat(excelReport.isSheetProtected("Locked Report Data")).isTrue();
        break;
      case "Both":
        assertThat(excelReport.isSheetProtected("Report Data")).isFalse();
        assertThat(excelReport.isSheetProtected("Locked Report Data")).isTrue();
        break;
      default:
        throw new IllegalArgumentException(String.format("%s not supported", permission));
    }
  }
}
