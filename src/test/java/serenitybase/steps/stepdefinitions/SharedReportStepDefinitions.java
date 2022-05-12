package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import serenitybase.steps.teststeps.SharedReportTestSteps;

public class SharedReportStepDefinitions {
  @Steps private SharedReportTestSteps sharedReportTestSteps;

  @Then("the excel report has the same number of rows as the web report")
  public void the_excel_report_has_the_same_number_of_rows_as_the_web_report() {
    sharedReportTestSteps.verifyNumberOfRows();
  }

  @Then("the report is {string} if applicable")
  public void the_report_has_permission(String permission) {
    if (StringUtils.isNotBlank(permission)) {
      sharedReportTestSteps.verifySheetPermissions(permission);
    }
  }

  @Then("the report is a {string}")
  public void the_report_has_file_format(String fileFormat) {
    sharedReportTestSteps.verifyReportHasFileFormat(fileFormat);
  }

  @Then("the report has the same columns as the web report")
  public void the_report_has_the_same_columns_as_the_web_report() {
    sharedReportTestSteps.verifyColumns();
  }

  @Then("the report has a {string} if applicable")
  public void the_report_has_a_cover_sheet_if_applicable(String includeCoverSheet) {
    if (StringUtils.isNotBlank(includeCoverSheet)) {
      sharedReportTestSteps.verifyCoverSheet(Boolean.parseBoolean(includeCoverSheet));
    }
  }

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

  @Then("the user verifies that all the Active Customer values are equal to {string}")
  public void the_user_verifies_that_all_the_active_customer_values_are_equal_to(String value) {
    sharedReportTestSteps.verifyActiveCustomerColumnValue(value);
  }

  @Then("the user verifies that all the Customer Type values are equal to {string}")
  public void the_user_verifies_that_all_the_customer_type_values_are_equal_to(String value) {
    sharedReportTestSteps.verifyCustomerTypeColumnValue(value);
  }

  @Then("the user selects {string} tab")
  public void the_user_selects_tab(String tabName) {
    sharedReportTestSteps.selectTab(tabName);
  }

  @Then("the user verifies the following columns are displayed in tab")
  public void the_user_verifies_tab_contains_the_following_columns(List<String> columnNames) {
    for (String columnName : columnNames) {
      sharedReportTestSteps.verifyColumnsAreDisplayedInTab(columnName);
    }
  }

  @Then("the user verifies that all the Current Personnel Type values are equal to {string}")
  public void the_user_verifies_that_all_the_current_personnel_type_values_are_equal_to(
      String value) {
    sharedReportTestSteps.verifyCurrentPersonnelTypeColumnValue(value);
  }
}
