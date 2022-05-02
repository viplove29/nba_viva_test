package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.TransactionTestSteps;

public class TransactionStepDefinitions {

  @Steps private TransactionTestSteps transactionTestSteps;

  @Then("the user clicks on the Filter symbol of the Transaction report")
  public void the_user_clicks_on_the_filter_symbol() {
    transactionTestSteps.clickOnFilterSymbol();
  }

  @Then("the user clicks on the Add Filters button of the Transaction Report")
  public void the_user_clicks_on_the_add_filters_button() {
    transactionTestSteps.clickOnAddFiltersButton();
  }

  @Then("^the user selects \"([^\"]*)\" under Filters of the Transaction Report$")
  public void the_user_selects_option_under_filters(String option) {
    transactionTestSteps.selectOptionUnderFilters(option);
  }

  @Then("^the user sets the Filter to start with \"([^\"]*)\" of the Transaction Report$")
  public void the_user_sets_the_filter_to_start_with_value(String value) {
    transactionTestSteps.setFilterValueToStartWith(value);
  }

  @Then("the user clicks on the Apply button of the Transaction Report")
  public void the_user_clicks_on_the_apply_button() {
    transactionTestSteps.clickOnApply();
  }

  @Then(
      "^the user verifies that all the Customer Name values on the Transaction Report are equal to \"([^\"]*)\"$")
  public void the_user_verifies_that_all_the_policy_number_values_are_equal_to(String value) {
    transactionTestSteps.verifyPolicyNumberColumnValue(value);
  }
}
