package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import serenitybase.helpers.Utilities;
import serenitybase.pages.mar.MarHomePage;
import serenitybase.pages.mar.WebReportPage;
import serenitybase.pages.reports.CsvReport;
import serenitybase.pages.reports.ExcelReport;

public class ReportTemplateTestSteps {
  private MarHomePage marHomePage;
  private WebReportPage webReportPage;
  private String reportName;

  @Step
  public void selectReportTemplate(String reportTemplate) {
    marHomePage.selectReportTemplate(reportTemplate);
  }

  @Step
  public void generateReportWithRandomName() {
    reportName = UUID.randomUUID().toString();
    marHomePage.setReportName(reportName);
    marHomePage.runReport();
    marHomePage.waitForReportToComplete(reportName);
  }

  @Step
  public void expandFilter(String filterName) {
    marHomePage.expandFilter(filterName);
  }

  @Step
  public void selectItemFromCategory(String itemName, String categoryName) {
    marHomePage.selectItemFromCategory(itemName, categoryName);
  }

  @Step
  public void selectBusinessUnitAssociationOption(String optionName) {
    marHomePage.selectBusinessUnitAssociationOption(optionName);
  }

  @Step
  public void selectTab(String tabName) {
    webReportPage.selectTab(tabName);
  }

  @Step
  public void excelQuickActions() {
    marHomePage.clickExcelQuickActions(reportName);
  }

  @Step
  public void csvQuickActions() {
    marHomePage.clickCsvQuickActions(reportName);
  }

  @Step
  @Screenshots(disabled = true)
  public void navigateToGeneratedReport() {
    marHomePage.clickOnReport(reportName);
  }

  @Step
  public void verifyNumberOfRows() {
    ExcelReport excelReport = new ExcelReport();
    assertThat(excelReport.getLastRowNum("Report Data"))
        .isEqualTo(webReportPage.getNumberOfResults());
  }

  @Step
  public void selectOptionUnderActions(String option) {
    marHomePage.selectOptionUnderActions(option);
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
    assertThat(headers).containsAll(webReportPage.getReportHeaders());
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
    webReportPage.selectOptionUnderHideShowIcon(option);
  }

  @Step
  public void verifyPoliciesAreDisplayed(String policies) {
    boolean isElementDisplayed;
    switch (policies) {
      case ("Division"):
        isElementDisplayed = webReportPage.isDivisionDisplayed();
        break;
      case ("Branch"):
        isElementDisplayed = webReportPage.isBranchDisplayed();
        break;
      case ("Department"):
        isElementDisplayed = webReportPage.isDepartmentDisplayed();
        break;
      case ("Group"):
        isElementDisplayed = webReportPage.isGroupDisplayed();
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
    webReportPage.clickOnFilterSymbol();
  }

  @Step
  public void clickOnAddFiltersButton() {
    webReportPage.clickOnAddFiltersButton();
  }

  @Step
  public void selectOptionUnderFilters(String option) {
    webReportPage.selectOptionUnderFilters(option);
  }

  @Step
  public void clickOnApply() {
    webReportPage.clickOnApply();
  }

  @Step
  public void setFilterValueToStartWith(String value) {
    webReportPage.setFilterPresetToStartWith();
    webReportPage.setFilterStartsWith(value);
  }

  @Step
  public void setFilterValueToEqualTo(String value) {
    webReportPage.setFilterPresetToEqualTo();
    webReportPage.setFilterEqualTo(value);
  }

  @Step
  public void verifyActiveCustomerColumnValue(String value) {
    assertThat(webReportPage.getActiveCustomerColumnValue()).isEqualTo(value);
  }

  @Step
  public void verifyCustomerTypeColumnValue(String value) {
    assertThat(webReportPage.getCustomerTypeColumnValue()).isEqualTo(value);
  }

  @Step
  public void verifyColumnsAreDisplayedInTab(String columnName) {
    assertThat(webReportPage.validateColumnsAreDisplayedInTab(columnName)).isTrue();
  }

  @Step
  public void selectDateRangeFromList(String optionName) {
    marHomePage.selectFromListOption();
    marHomePage.clickFromListDropdown();
    marHomePage.selectDateRangeOptionFromDropdown(optionName);
  }

  @Step
  public void setDateRangeFromTo(String from, String to) {
    marHomePage.selectSelectDatesOption();
    marHomePage.setDateFrom(from);
    marHomePage.setDateTo(to);
  }
}
