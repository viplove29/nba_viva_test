package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.InvalidArgumentException;
import serenitybase.helpers.Utilities;
import serenitybase.pages.mar.ReportBasePage;
import serenitybase.pages.mar.SharedReportPage;
import serenitybase.pages.mar.TransactionPage;
import serenitybase.pages.reports.CsvReport;
import serenitybase.pages.reports.ExcelReport;

public class SharedReportTestSteps {
  private ReportBasePage sharedReportPage;

  SharedReportTestSteps() {
    String reportPageType = Serenity.sessionVariableCalled("reportType");
    if (reportPageType == null || reportPageType.isEmpty()) {
      throw new InvalidArgumentException("Report Type Session Variable was not set");
    }
    switch (reportPageType) {
      case "Transaction":
        sharedReportPage = new TransactionPage();
        break;
      default:
        sharedReportPage = new SharedReportPage();
    }
  }

  @Step
  public void selectTab(String tabName) {
    sharedReportPage.selectTab(tabName);
  }

  @Step
  public void verifyNumberOfRows() {
    ExcelReport excelReport = new ExcelReport();
    assertThat(excelReport.getLastRowNum("Report Data"))
        .isEqualTo(sharedReportPage.getNumberOfResults());
  }

  @Step
  public void verifyReportHasFileFormat(String fileFormat) {
    Utilities.waitForDownload();
    assertThat(Utilities.getExtension(Utilities.getMostRecentFile()))
        .isEqualToIgnoringCase(fileFormat.equals("Excel") ? "XLSX" : "CSV");
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
  public void selectOptionUnderHideShowIcon(String option) {
    sharedReportPage.selectOptionUnderHideShowIcon(option);
  }

  @Step
  public void verifyPoliciesAreDisplayed(String policies) {
    boolean isElementDisplayed;
    switch (policies) {
      case ("Division"):
        isElementDisplayed = sharedReportPage.isDivisionDisplayed();
        break;
      case ("Branch"):
        isElementDisplayed = sharedReportPage.isBranchDisplayed();
        break;
      case ("Department"):
        isElementDisplayed = sharedReportPage.isDepartmentDisplayed();
        break;
      case ("Group"):
        isElementDisplayed = sharedReportPage.isGroupDisplayed();
        break;
      default:
        throw new IllegalArgumentException(String.format("'%s' is not a valid policy.", policies));
    }
    assertThat(isElementDisplayed)
        .withFailMessage(String.format("The policy '%s' was not displayed", policies))
        .isTrue();
  }

  @Step
  public void clickOnFilterSymbol() {
    sharedReportPage.clickOnFilterSymbol();
  }

  @Step
  public void clickOnAddFiltersButton() {
    sharedReportPage.clickOnAddFiltersButton();
  }

  @Step
  public void selectOptionUnderFilters(String option) {
    sharedReportPage.selectOptionUnderFilters(option);
  }

  @Step
  public void clickOnApply() {
    sharedReportPage.clickOnApply();
  }

  @Step
  public void setFilterValueToStartWith(String value) {
    sharedReportPage.setFilterPresetToStartWith();
    sharedReportPage.setFilterStartsWith(value);
  }

  @Step
  public void setFilterValueToEqualTo(String value) {
    sharedReportPage.setFilterPresetToEqualTo();
    sharedReportPage.setFilterEqualTo(value);
  }

  @Step
  public void verifyActiveCustomerColumnValue(String value) {
    assertThat(sharedReportPage.getActiveCustomerColumnValue()).isEqualTo(value);
  }

  @Step
  public void verifyCustomerTypeColumnValue(String value) {
    assertThat(sharedReportPage.getCustomerTypeColumnValue()).isEqualTo(value);
  }

  @Step
  public void verifyColumnIsDisplayedInTab(String columnName) {
    assertThat(sharedReportPage.validateColumnIsDisplayedInTab(columnName)).isTrue();
  }

  @Step
  public void verifyCurrentPersonnelTypeColumnValue(String value) {
    assertThat(sharedReportPage.getCurrentPersonnelTypeColumnValue()).isEqualTo(value);
  }

  @Step
  public void extractReportValuesInTheGrid() {
    List<Map<String, String>> rows = sharedReportPage.getReportGridDataAsMaps();

    System.out.println("MAR Report Grid Data:");
    for (Map<String, String> row : rows) {
      System.out.println(row);
    }
  }

  @Step
  public void verifyColumnValuesStartWith(String columnName, String value) {
    List<Map<String, String>> gridData = sharedReportPage.getReportGridDataAsMaps();
    for (Map<String, String> rowData : gridData) {
      assertThat(rowData.get(columnName.toUpperCase()).startsWith(value));
    }
  }
}