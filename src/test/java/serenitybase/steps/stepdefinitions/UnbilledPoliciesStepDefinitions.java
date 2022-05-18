package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.SharedReportTestSteps;
import serenitybase.steps.teststeps.UnbilledPoliciesTestSteps;

public class UnbilledPoliciesStepDefinitions {

  @Steps private UnbilledPoliciesTestSteps unbilledPolicyTestSteps;
  @Steps private SharedReportTestSteps sharedReportTestSteps;

  @Then("the user selects {string} under Filters on the Unbilled Policy Report")
  public void the_user_selects_option_under_filters(String option) {
    unbilledPolicyTestSteps.selectOptionUnderFilters(option);
  }

  @Then("the user verifies that all the {string} values start with {string}")
  public void the_user_verifies_that_all_the_values_start_with(String columnName, String value) {
    sharedReportTestSteps.verifyColumnValuesStartWith(columnName, value);
  }
}
