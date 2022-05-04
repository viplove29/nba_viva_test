package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.TransactionTestSteps;

public class TransactionStepDefinitions {

  @Steps private TransactionTestSteps transactionTestSteps;

  @When("the user clicks on the Filter symbol of the Transaction report")
  public void the_user_clicks_on_the_filter_symbol() {
    transactionTestSteps.clickOnFilterSymbol();
  }

  @When("the user clicks on the Add Filters button of the Transaction Report")
  public void the_user_clicks_on_the_add_filters_button() {
    transactionTestSteps.clickOnAddFiltersButton();
  }

  @When("the user selects {string} under Filters of the Transaction Report")
  public void the_user_selects_option_under_filters(String option) {
    transactionTestSteps.selectOptionUnderFilters(option);
  }

  @When("the user sets the Filter to start with {string} of the Transaction Report")
  public void the_user_sets_the_filter_to_start_with_value(String value) {
    transactionTestSteps.setFilterValueToStartWith(value);
  }

  @When("the user clicks on the Apply button of the Transaction Report")
  public void the_user_clicks_on_the_apply_button() {
    transactionTestSteps.clickOnApply();
  }

  @When(
      "the user verifies that all the Customer Name values on the Transaction Report start with {string}")
  public void the_user_verifies_that_all_the_customer_name_values_start_with(String value) {
    transactionTestSteps.verifyCustomerNameColumnValues(value);
  }

  @When("the user extracts all the report values in the grid")
  public void the_user_extracts_all_the_report_values_in_the_grid() {
    transactionTestSteps.extractReportValuesInTheGrid();
  }
}
