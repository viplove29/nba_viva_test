package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.StringUtils;
import serenitybase.helpers.Utilities;
import serenitybase.pages.mar.MarHomePage;
import serenitybase.pages.mar.ReportTemplatePage;
import serenitybase.pages.reports.CsvReport;
import serenitybase.pages.reports.ExcelReport;

public class ReportTemplateTestSteps {
  private MarHomePage marHomePage;
  private ReportTemplatePage reportTemplatePage;
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
    reportTemplatePage.selectTab(tabName);
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
    Utilities.waitForReportPageLoadingSpinners();
  }

  @Step
  public void verifyNumberOfRows() {
    ExcelReport excelReport = new ExcelReport();
    assertThat(excelReport.getLastRowNum("Report Data"))
        .isEqualTo(reportTemplatePage.getNumberOfResults());
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
    assertThat(headers).containsAll(reportTemplatePage.getReportHeaders());
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
    reportTemplatePage.selectOptionUnderHideShowIcon(option);
  }

  @Step
  public void verifyPoliciesAreDisplayed(String policies) {
    boolean isElementDisplayed;
    switch (policies) {
      case ("Division"):
        isElementDisplayed = reportTemplatePage.isDivisionDisplayed();
        break;
      case ("Branch"):
        isElementDisplayed = reportTemplatePage.isBranchDisplayed();
        break;
      case ("Department"):
        isElementDisplayed = reportTemplatePage.isDepartmentDisplayed();
        break;
      case ("Group"):
        isElementDisplayed = reportTemplatePage.isGroupDisplayed();
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
    reportTemplatePage.clickOnFilterSymbol();
  }

  @Step
  public void clickOnAddFiltersButton() {
    reportTemplatePage.clickOnAddFiltersButton();
  }

  @Step
  public void selectOptionUnderFilters(String option) {
    reportTemplatePage.selectOptionUnderFilters(option);
  }

  @Step
  public void clickOnApply() {
    reportTemplatePage.clickOnApply();
  }

  @Step
  public void setFilterValueToStartWith(String value) {
    reportTemplatePage.setFilterPresetToStartWith();
    reportTemplatePage.setFilterStartsWith(value);
  }

  @Step
  public void setFilterValueToEqualTo(String value) {
    reportTemplatePage.setFilterPresetToEqualTo();
    reportTemplatePage.setFilterEqualTo(value);
  }

  @Step
  public void verifyActiveCustomerColumnValue(String value) {
    assertThat(reportTemplatePage.getActiveCustomerColumnValue()).isEqualTo(value);
  }

  @Step
  public void verifyCustomerTypeColumnValue(String value) {
    assertThat(reportTemplatePage.getCustomerTypeColumnValue()).isEqualTo(value);
  }

  @Step
  public void verifyColumnsAreDisplayedInTab(String columnName) {
    assertThat(reportTemplatePage.validateColumnsAreDisplayedInTab(columnName)).isTrue();
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

  @Step
  public void selectAccountNumbersSubLedgersFromDropdown(String option, String section) {
    marHomePage.selectValueFromDropdown(option, section);
  }

  @Step
  public void selectRandomAccountNumbersSubLedgersFromDropdown(String section) {
    marHomePage.selectRandomValueFromDropdown(section);
  }

  @Step
  public void verifyDropdownIsDisplayed(String section) {
    if (StringUtils.isNotBlank(section)) {
      assertThat(marHomePage.isDropdownDisplayed(section)).isTrue();
    }
  }

  @Step
  public void verifyCurrentPersonnelTypeColumnValue(String value) {
    assertThat(reportTemplatePage.getCurrentPersonnelTypeColumnValue()).isEqualTo(value);
  }
}
