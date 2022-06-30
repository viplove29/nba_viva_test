package serenitybase.steps.stepdefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.thucydides.core.annotations.Steps;
import serenitybase.helpers.Utilities;
import serenitybase.steps.teststeps.HomePageTestSteps;

public class HomePageStepDefinitions {
  @Steps private HomePageTestSteps HomePageTestSteps;

  @Given("the user selects {string} Report Template")
  public void the_user_selects_active_clients_report_template(String reportTemplate) {
    HomePageTestSteps.selectReportTemplate(reportTemplate);
    Utilities.simpleSleep(5000);
  }

  @When("the user generates the report with a random name")
  public void the_user_generates_the_report_with_a_random_name() {
    Utilities.simpleSleep(5000);
    HomePageTestSteps.generateReportWithRandomName();
  }

  @When("the user clicks on the Quick Actions {string} button")
  public void the_user_clicks_on_the_quick_actions_button(String reportType) {
    switch (reportType) {
      case "XLS":
        HomePageTestSteps.excelQuickActions();
        break;
      case "CSV":
        HomePageTestSteps.csvQuickActions();
        break;
      default:
        throw new IllegalArgumentException(
            String.format("Report type %s not supported", reportType));
    }
  }

  @When("the user navigates to the generated report")
  public void the_user_navigates_to_the_generated_report() {
    HomePageTestSteps.navigateToGeneratedReport();
  }

  @When("the user selects {string} under Actions")
  public void the_user_selects_option_under_actions(String option) {
    HomePageTestSteps.selectOptionUnderActions(option);
  }

  @Then("the user expands {string} filter")
  public void the_user_expands_filter(String filterName) {
    HomePageTestSteps.expandFilter(filterName);
  }

  @Then("the user selects {string} from {string} category")
  public void the_user_selects_item_from_category(String itemName, String categoryName) {
    HomePageTestSteps.selectItemFromCategory(itemName, categoryName);
  }

  @Then("the user selects {string} association option")
  public void the_user_selects_association_option(String optionName) {
    HomePageTestSteps.selectBusinessUnitAssociationOption(optionName);
  }

  @ParameterType(
      "Current (date|month($| to date| minus 1)|quarter($| to date| minus 1)|year($| to date| minus 1))|Through current date")
  public String dateRange(String dateRange) {
    return dateRange;
  }

  @Then("the user selects {dateRange} date range from list")
  public void the_user_selects_date_range_from_list(String optionName) {
    HomePageTestSteps.selectDateRangeFromList(optionName);
  }

  @Then("the user sets date range from {string} to {string}")
  public void the_user_sets_date_range_from_to(String from, String to) {
    HomePageTestSteps.setDateRangeFromTo(from, to);
  }

  @Then("the user sets date range from {string} to today")
  public void the_user_sets_date_range_from_to_today(String from) {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String dateAsText = date.format(formatter);
    HomePageTestSteps.setDateRangeFromTo(from, dateAsText);
  }

  @Then(
      "the user selects {string} from {string} dropdown under Account Numbers\\/Sub-ledgers section")
  public void the_user_selects_value_from_dropdown(String value, String section) {
    HomePageTestSteps.selectAccountNumbersSubLedgersFromDropdown(value, section);
  }

  @Then(
      "the user selects random value from {string} dropdown under Account Numbers\\/Sub-ledgers section")
  public void the_user_selects_random_value_from_dropdown(String section) {
    HomePageTestSteps.selectRandomAccountNumbersSubLedgersFromDropdown(section);
  }

  @Then("the user verifies the {string} dropdown is displayed")
  public void the_user_verifies_the_dropdown_is_displayed(String section) {
    HomePageTestSteps.verifyDropdownIsDisplayed(section);
  }

  @Then(
      "the user verifies all the options in the dropdown from the {string} category are in the {string} division")
  public void the_user_verifies_all_the_options_in_the_dropdown_are_in_the_division(
      String categoryName, String expectedDivision) {
    HomePageTestSteps.verifyAllTheDropDownOptionsAreInSpecificDivision(
        expectedDivision, categoryName);
  }

  @When("the user selects the {string} option under the Status section")
  public void the_user_selects_option_under_the_status_section(String status) {
    HomePageTestSteps.clickStatusRadioButton(status);
  }
}
