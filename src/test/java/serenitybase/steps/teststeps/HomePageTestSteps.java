package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
  public void setInForceDateTo(String inForceDate) {
    marHomePage.setInForceDateTo(inForceDate);
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

  @Step
  public void verifyAllTheDropDownOptionsAreInSpecificDivision(
      String expectedDivision, String categoryName) {
    List<String> dropdownDivisions = marHomePage.getDivisionsInDropdown(categoryName);
    assertThat(dropdownDivisions.size()).isGreaterThan(0);
    for (int i = 0; i < dropdownDivisions.size(); i++) {
      assertThat(dropdownDivisions.get(i)).isEqualTo(expectedDivision);
    }
  }

  @Step
  public void clickStatusRadioButton(String status) {
    marHomePage.clickStatusRadioButton(status);
  }

  @Step
  public void clickAllOrSelectMultipleRadioButton(String radioButtonOption, String section) {
    marHomePage.clickAllOrSelectMultipleRadioButton(radioButtonOption, section);
  }

  @Step
  public void searchFromDropdownAndSelectTopOption(String section, String options) {
    marHomePage.searchPersonnelDropdownElement(section, options);
  }

  @Step
  public void verifyTheDropdownValue(String section, String expectedText) {
    assertThat(marHomePage.getDropdownValueText(section)).isEqualTo(expectedText);
  }

  @Step
  public void verifyActiveStatusInDropdownForSearchedItem(
      String option, String categoryName, String expectedStatus) {
    List<String> statusList =
        marHomePage.getActiveStatusFromDropdownForSearchedItem(option, categoryName);
    if (expectedStatus.equalsIgnoreCase("active")) {
      assertThat(statusList).doesNotContain("X");
    } else if (expectedStatus.equalsIgnoreCase("inactive")) {
      assertThat(statusList).containsOnly("X");
    } else {
      throw new IllegalArgumentException(
          String.format("Status type %s not supported", expectedStatus));
    }
  }

  @Step
  public void verifyDivisionDoesNotShowInDropdown(String value, String categoryName) {
    List<String> dropdownDivisions = marHomePage.getDivisionsInDropdown(categoryName);
    assertThat(dropdownDivisions).doesNotContain(value);
  }

  @Step
  public void verifyActiveStatusInDropdown(String categoryName, String expectedStatus) {
    List<String> statusList = marHomePage.getActiveStatusFromDropdownForAllItems(categoryName);
    if (expectedStatus.equalsIgnoreCase("active")) {
      assertThat(statusList).doesNotContain("X");
    } else if (expectedStatus.equalsIgnoreCase("inactive")) {
      assertThat(statusList).containsOnly("X");
    } else {
      throw new IllegalArgumentException(
          String.format("Status type %s not supported", expectedStatus));
    }
  }

  @Step
  public void selectItemFromCategoryAndCloseDropdown(String itemName, String categoryName) {
    marHomePage.selectItemFromCategoryAndCloseDropdown(itemName, categoryName);
  }

  @Step
  public void selectItemFromCompanyTypeDropdown(String type) {
    marHomePage.selectFromCompanyTypeDropdown(type);
  }

  @Step
  public void searchFromDropdownUnderCompany(String options, String section) {
    marHomePage.searchCompanyDropdown(section, options);
  }

  @Step
  public void clickNotBillableOnlyRadioButton() {
    marHomePage.clickNotBillableOnlyRadioButton();
  }
}
