package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.StringUtils;
import serenitybase.helpers.Utilities;
import serenitybase.pages.mar.MarHomePage;

public class HomePageTestSteps {
  private MarHomePage marHomePage;
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
    Utilities.setActiveTabContentIdSessionVariable();
  }

  @Step
  public void selectOptionUnderActions(String option) {
    marHomePage.selectOptionUnderActions(option);
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
      marHomePage.waitForDropdownDisplayed(section);
      assertThat(marHomePage.isDropdownDisplayed(section)).isTrue();
    }
  }
}
