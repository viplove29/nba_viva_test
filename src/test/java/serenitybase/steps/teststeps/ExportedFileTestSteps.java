package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.thucydides.core.annotations.Step;
import serenitybase.helpers.Utilities;
import serenitybase.pages.mar.ReportBasePage;
import serenitybase.pages.reports.CsvReport;
import serenitybase.pages.reports.ExcelReport;

public class ExportedFileTestSteps {
  private ReportBasePage sharedReportPage;

  @Step
  public void verifyReportHasFileFormat(String fileFormat) {
    Utilities.waitForDownload();
    assertThat(Utilities.getExtension(Utilities.getMostRecentFile()))
        .isEqualToIgnoringCase(fileFormat.equals("Excel") ? "XLSX" : "CSV");
  }

  @Step
  public void verifyCoverSheet(boolean includeCoverSheet) {
    ExcelReport excelReport = new ExcelReport();
    if (includeCoverSheet) {
      assertThat(excelReport.getReportSheetNames()).contains("Cover Page");
    } else {
      assertThat(excelReport.getReportSheetNames()).doesNotContain("Cover Page");
    }
  }

  @Step
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

  @Step
  public void verifyNumberOfRows() {
    ExcelReport excelReport = new ExcelReport();
    assertThat(excelReport.getLastRowNum("Report Data"))
        .isEqualTo(sharedReportPage.getNumberOfResults());
  }

  @Step
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
    assertThat(headers).containsAll(sharedReportPage.getReportHeaders());
  }

  @Step
  public void verifyDataUnderHeaderForGeneratedReport(String header, String expectedValue) {
    Utilities.waitForDownload();
    String extension = Utilities.getExtension(Utilities.getMostRecentFile());
    String[] filePath = Utilities.getMostRecentFile().split("[\\\\/]");
    String name = filePath[filePath.length - 1];
    if (extension.equalsIgnoreCase("CSV")) {
      CsvReport csvReport = new CsvReport();
      List<String> columnValues;
      columnValues = csvReport.getValuesInColumnUnderHeader(header, name);
      for (String columnValue : columnValues) {
        assertThat(columnValue).isEqualTo(expectedValue);
      }
    } else if (extension.equalsIgnoreCase("XLSX")) {
      ExcelReport excelReport = new ExcelReport();
      assertThat(
              excelReport.validateValuesInColumnUnderHeader(expectedValue, header, "Report Data"))
          .isTrue();
    } else {
      throw new IllegalArgumentException(String.format("File type %s not supported", extension));
    }
  }

  @Step
  public void verifyDataUnderHeaderForGeneratedReportForStringList(
      String header, List<String> expectedValues) {
    Utilities.waitForDownload();
    String extension = Utilities.getExtension(Utilities.getMostRecentFile());
    String[] filePath = Utilities.getMostRecentFile().split("[\\\\/]");
    String name = filePath[filePath.length - 1];
    String regex = String.join("|", expectedValues);
    if (extension.equalsIgnoreCase("CSV")) {
      CsvReport csvReport = new CsvReport();
      List<String> columnValues;
      columnValues = csvReport.getValuesInColumnUnderHeader(header, name);
      for (String columnValue : columnValues) {
        assertThat(columnValue.matches(regex)).isTrue();
      }
    } else if (extension.equalsIgnoreCase("XLSX")) {
      ExcelReport excelReport = new ExcelReport();
      List<String> columnValues;
      columnValues = excelReport.getValuesInColumnUnderHeader(header, "Report Data");
      for (String columnValue : columnValues) {
        assertThat(columnValue.matches(regex)).isTrue();
      }
    } else {
      throw new IllegalArgumentException(String.format("File type %s not supported", extension));
    }
  }
}
