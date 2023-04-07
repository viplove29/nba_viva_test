package serenitybase.steps.stepdefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import serenitybase.helpers.Utilities;
import serenitybase.steps.teststeps.HomePageTestSteps;

public class HomePageStepDefinitions {
  @Steps private HomePageTestSteps homePageTestSteps;

  @Given("the user selects {string} Report Template")
  public void the_user_selects_active_clients_report_template(String reportTemplate) {
    homePageTestSteps.selectReportTemplate(reportTemplate);
    Utilities.simpleSleep(5000);
  }

  @When("the user generates the report with a random name")
  public void the_user_generates_the_report_with_a_random_name() {
    Utilities.simpleSleep(5000);
    homePageTestSteps.generateReportWithRandomName();
  }

  @When("the user clicks on the Quick Actions {string} button")
  public void the_user_clicks_on_the_quick_actions_button(String reportType) {
    Utilities.setDownloadsCount();
    switch (reportType) {
      case "XLS":
        homePageTestSteps.excelQuickActions();
        Serenity.setSessionVariable("lastReportType").to(reportType);
        break;
      case "CSV":
        homePageTestSteps.csvQuickActions();
        Serenity.setSessionVariable("lastReportType").to(reportType);
        break;
      default:
        throw new IllegalArgumentException(
            String.format("Report type %s not supported", reportType));
    }
  }

  @When("the user navigates to the generated report")
  public void the_user_navigates_to_the_generated_report() {
    homePageTestSteps.navigateToGeneratedReport();
  }

  @When("the user selects {string} under Actions")
  public void the_user_selects_option_under_actions(String option) {
    homePageTestSteps.selectOptionUnderActions(option);
  }

  @Then("the user expands {string} filter")
  public void the_user_expands_filter(String filterName) {
    homePageTestSteps.expandFilter(filterName);
  }

  @Then("the user selects {string} from {string} category")
  public void the_user_selects_item_from_category(String itemName, String categoryName) {
    homePageTestSteps.selectItemFromCategory(itemName, categoryName);
  }

  @Then("the user selects {string} association option")
  public void the_user_selects_association_option(String optionName) {
    homePageTestSteps.selectBusinessUnitAssociationOption(optionName);
  }

  @ParameterType(
      "(Current (date|month($| to date| minus 1)|quarter($| to date| minus 1)|year($| to date| minus 1))|Through current date)")
  public String dateRange(String dateRange) {
    return dateRange;
  }

  @Then("the user selects {dateRange} date range from list")
  public void the_user_selects_date_range_from_list(String optionName) {
    homePageTestSteps.selectDateRangeFromList(optionName);
  }

  @Then("the user sets date range from {string} to {string}")
  public void the_user_sets_date_range_from_to(String from, String to) {
    homePageTestSteps.setDateRangeFromTo(from, to);
  }

  @Then("the user sets date range from {string} to today")
  public void the_user_sets_date_range_from_to_today(String from) {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String dateAsText = date.format(formatter);
    homePageTestSteps.setDateRangeFromTo(from, dateAsText);
  }

  @Then("the user sets the In-Force date to {string}")
  public void the_user_sets_the_in_force_date_range_to(String inForceDate) {
    homePageTestSteps.setInForceDateTo(inForceDate);
  }

  @Then(
      "the user selects {string} from {string} dropdown under Account Numbers\\/Sub-ledgers section")
  public void the_user_selects_value_from_dropdown(String value, String section) {
    homePageTestSteps.selectAccountNumbersSubLedgersFromDropdown(value, section);
  }

  @Then(
      "the user selects random value from {string} dropdown under Account Numbers\\/Sub-ledgers section")
  public void the_user_selects_random_value_from_dropdown(String section) {
    homePageTestSteps.selectRandomAccountNumbersSubLedgersFromDropdown(section);
  }

  @Then("the user verifies the {string} dropdown is displayed")
  public void the_user_verifies_the_dropdown_is_displayed(String section) {
    homePageTestSteps.verifyDropdownIsDisplayed(section);
  }

  @Then(
      "the user verifies all the options in the dropdown from the {string} category are in the {string} division")
  public void the_user_verifies_all_the_options_in_the_dropdown_are_in_the_division(
      String categoryName, String expectedDivision) {
    homePageTestSteps.verifyAllTheDropDownOptionsAreInSpecificDivision(
        expectedDivision, categoryName);
  }

  @When("the user selects the {string} option under the Status section")
  public void the_user_selects_option_under_the_status_section(String status) {
    homePageTestSteps.clickStatusRadioButton(status);
  }

  @When("the user clicks the {string} option under the {string} section")
  public void the_user_clicks_the_select_multiple_option_under_section(
      String radioButtonOption, String section) {
    homePageTestSteps.clickAllOrSelectMultipleRadioButton(radioButtonOption, section);
  }

  @When(
      "the user searches for {string} in the dropdown under the {string} section and selects the top option")
  public void the_user_searches_in_the_dropdown_under_section_and_selects_the_top_options(
      String options, String section) {
    homePageTestSteps.searchFromDropdownAndSelectTopOption(section, options);
  }

  @Then("the user verifies the dropdown value in the {string} section is set to {string}")
  public void the_user_verifies_the_dropdown_value_in_section_is_set_to_certain_text(
      String section, String expectedText) {
    homePageTestSteps.verifyTheDropdownValue(section, expectedText);
  }

  @Then(
      "the user searches for {string} in the {string} section dropdown and verifies all of the statuses are equal to {string}")
  public void the_user_verifies_all_of_the_statuses_in_dropdown_are_equal_to_expected_value(
      String option, String section, String expectedStatus) {
    homePageTestSteps.verifyActiveStatusInDropdownForSearchedItem(option, section, expectedStatus);
  }

  @Then(
      "the user searches for {string} in the {string} section dropdown and verifies the name does not show")
  public void the_user_searches_in_dropdown_and_verifies_the_string_does_not_appear_in_the_list(
      String search, String section) {
    homePageTestSteps.verifyDivisionDoesNotShowInDropdown(search, section);
  }

  @Then(
      "the user verifies all of the statuses in the {string} section dropdown are set to {string}")
  public void the_user_verifies_all_of_statuses_in_the_dropdown_are_set_to_string(
      String section, String expectedValue) {
    homePageTestSteps.verifyActiveStatusInDropdown(section, expectedValue);
  }

  @When("the user selects {string} from {string} category and closes the dropdown")
  public void the_user_selects_item_from_category_and_closes_the_dropdown(
      String itemName, String categoryName) {
    homePageTestSteps.selectItemFromCategoryAndCloseDropdown(itemName, categoryName);
  }

  @When("the user selects {string} from the company type dropdown")
  public void the_user_selects_from_company_type_dropdown(String type) {
    homePageTestSteps.selectItemFromCompanyTypeDropdown(type);
  }

  @When(
      "the user searches for {string} in the dropdown under the Company {string} section and selects the top option")
  public void the_user_searches_in_the_dropdown_under_company_section_and_selects_the_top_options(
      String options, String section) {
    homePageTestSteps.searchFromDropdownUnderCompany(options, section);
  }

  @Then("the user selects Not Billable Only option")
  public void the_user_selects_not_billable_only_option() {
    homePageTestSteps.clickNotBillableOnlyRadioButton();
  }
}
