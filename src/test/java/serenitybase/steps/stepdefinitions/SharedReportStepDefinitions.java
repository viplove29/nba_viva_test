package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.SharedReportTestSteps;

public class SharedReportStepDefinitions {
  @Steps private SharedReportTestSteps sharedReportTestSteps;

  @When("the user selects {string} under the hide show icon")
  public void the_user_selects_option_under_show_hide_icon(String option) {
    sharedReportTestSteps.selectOptionUnderHideShowIcon(option);
  }

  @Then("the user verifies the {string} are displayed in the detail view tab")
  public void the_user_verifies_the_policies_are_displayed_in_the_detail_view_tab(String policies) {
    sharedReportTestSteps.verifyPoliciesAreDisplayed(policies);
  }

  @Then("the user clicks on the Filter symbol")
  public void the_user_clicks_on_the_filter_symbol() {
    sharedReportTestSteps.clickOnFilterSymbol();
  }

  @Then("the user clicks on the Add Filters button")
  public void the_user_clicks_on_the_add_filters_button() {
    sharedReportTestSteps.clickOnAddFiltersButton();
  }

  @Then("the user selects {string} under Filters")
  public void the_user_selects_option_under_filters(String option) {
    sharedReportTestSteps.selectOptionUnderFilters(option);
  }

  @Then("the user clicks on the Apply button")
  public void the_user_clicks_on_the_apply_button() {
    sharedReportTestSteps.clickOnApply();
  }

  @Then("the user sets the Filter to start with {string}")
  public void the_user_sets_the_filter_to_start_with_value(String value) {
    sharedReportTestSteps.setFilterValueToStartWith(value);
  }

  @Then("the user sets the Filter to equal to {string}")
  public void the_user_sets_the_filter_to_equal_to_value(String value) {
    sharedReportTestSteps.setFilterValueToEqualTo(value);
  }

  @Then("the user verifies that all the {string} values on the Report are equal to {string}")
  public void the_user_verifies_that_all_the_customer_type_values_are_equal_to(
      String columnName, String value) {
    sharedReportTestSteps.verifyColumnValuesAreEqualTo(columnName, value);
  }

  @Then("the user selects {string} tab")
  public void the_user_selects_tab(String tabName) {
    sharedReportTestSteps.selectTab(tabName);
  }

  @Then("the user verifies the following columns are displayed in tab")
  public void the_user_verifies_tab_contains_the_following_columns(List<String> columnNames) {
    for (String columnName : columnNames) {
      sharedReportTestSteps.verifyColumnIsDisplayedInTab(columnName);
    }
  }

  @When("the user extracts all the report values in the grid")
  public void the_user_extracts_all_the_report_values_in_the_grid() {
    sharedReportTestSteps.extractReportValuesInTheGrid();
  }

  @When("the user verifies that all the {string} values on the Report start with {string}")
  public void the_user_verifies_that_all_the_customer_name_values_start_with(
      String columnName, String value) {
    sharedReportTestSteps.verifyColumnValuesStartWith(columnName, value);
  }
}
