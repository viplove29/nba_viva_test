package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.UnbilledPoliciesTestSteps;

public class UnbilledPoliciesStepDefinitions {

  @Steps private UnbilledPoliciesTestSteps unbilledPolicyTestSteps;

  @Then("the user clicks on the Filter symbol on the Unbilled Policy report")
  public void the_user_clicks_on_the_filter_symbol() {
    unbilledPolicyTestSteps.clickOnFilterSymbol();
  }

  @Then("the user clicks on the Add Filters button on the Unbilled Policy Report")
  public void the_user_clicks_on_the_add_filters_button() {
    unbilledPolicyTestSteps.clickOnAddFiltersButton();
  }

  @Then("the user selects {string} under Filters on the Unbilled Policy Report")
  public void the_user_selects_option_under_filters(String option) {
    unbilledPolicyTestSteps.selectOptionUnderFilters(option);
  }

  @Then("the user sets the Filter to start with {string} on the Unbilled Policy Report")
  public void the_user_sets_the_filter_to_start_with_value(String value) {
    unbilledPolicyTestSteps.setFilterValueToStartWith(value);
  }

  @Then("the user clicks on the Apply button on the Unbilled Policy Report")
  public void the_user_clicks_on_the_apply_button() {
    unbilledPolicyTestSteps.clickOnApply();
  }

  @When(
      "the user verifies that all the Policy Number values on the Unbilled Policy start with {string}")
  public void the_user_verifies_that_all_the_policy_number_values_start_with(String value) {
    unbilledPolicyTestSteps.verifyPolicyNumberColumnValue(value);
  }
}
