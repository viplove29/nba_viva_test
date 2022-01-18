package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import serenitybase.steps.teststeps.ReportTemplateTestSteps;

public class ReportTemplateStepDefinitions {
  @Steps private ReportTemplateTestSteps reportTemplateTestSteps;

  @Given("the user selects {string} Report Template")
  public void the_user_selects_active_clients_report_template(String reportTemplate) {
    reportTemplateTestSteps.selectReportTemplate(reportTemplate);
  }

  @When("the user generates the report with a random name")
  public void the_user_generates_the_report_with_a_random_name() {
    reportTemplateTestSteps.generateReportWithRandomName();
  }

  @When("the user clicks on the Quick Actions {string} button")
  public void the_user_clicks_on_the_quick_actions_button(String reportType) {
    switch (reportType) {
      case "XLS":
        reportTemplateTestSteps.excelQuickActions();
        break;
      case "CSV":
        reportTemplateTestSteps.csvQuickActions();
        break;
      default:
        throw new IllegalArgumentException(
            String.format("Report type %s not supported", reportType));
    }
  }

  @When("the user navigates to the generated report")
  public void the_user_navigates_to_the_generated_report() {
    reportTemplateTestSteps.navigateToGeneratedReport();
  }

  @Then("the excel report has the same number of rows as the web report")
  public void the_excel_report_has_the_same_number_of_rows_as_the_web_report() {
    reportTemplateTestSteps.verifyNumberOfRows();
  }

  @When("the user selects {string} under Actions")
  public void the_user_selects_option_under_actions(String option) {
    reportTemplateTestSteps.selectOptionUnderActions(option);
  }

  @Then("the report is {string} if applicable")
  public void the_report_has_permission(String permission) {
    if (StringUtils.isNotBlank(permission)) {
      reportTemplateTestSteps.verifySheetPermissions(permission);
    }
  }

  @Then("the report is a {string}")
  public void the_report_has_file_format(String fileFormat) {
    reportTemplateTestSteps.verifyReportHasFileFormat(fileFormat);
  }

  @Then("the report has the same columns as the web report")
  public void the_report_has_the_same_columns_as_the_web_report() {
    reportTemplateTestSteps.verifyColumns();
  }

  @Then("the report has a {string} if applicable")
  public void the_report_has_a_cover_sheet_if_applicable(String includeCoverSheet) {
    if (StringUtils.isNotBlank(includeCoverSheet)) {
      reportTemplateTestSteps.verifyCoverSheet(Boolean.parseBoolean(includeCoverSheet));
    }
  }

  @When("the user selects {string} under the hide show icon")
  public void the_user_selects_option_under_show_hide_icon(String option) {
    reportTemplateTestSteps.selectOptionUnderHideShowIcon(option);
  }

  @Then("the user verifies the {string} are displayed in the detail view tab")
  public void the_user_verifies_the_policies_are_displayed_in_the_detail_view_tab(String policies) {
    reportTemplateTestSteps.verifyPoliciesAreDisplayed(policies);
  }

  @Then("the user clicks on the Filter symbol")
  public void the_user_clicks_on_the_filter_symbol() {
    reportTemplateTestSteps.clickOnFilterSymbol();
  }

  @Then("the user clicks on the Add Filters button")
  public void the_user_clicks_on_the_add_filters_button() {
    reportTemplateTestSteps.clickOnAddFiltersButton();
  }

  @Then("the user selects {string} under Filters")
  public void the_user_selects_option_under_filters(String option) {
    reportTemplateTestSteps.selectOptionUnderFilters(option);
  }

  @Then("the user clicks on the Apply button")
  public void the_user_clicks_on_the_apply_button() {
    reportTemplateTestSteps.clickOnApply();
  }

  @Then("the user sets the Filter to start with {string}")
  public void the_user_sets_the_filter_to_start_with_value(String value) {
    reportTemplateTestSteps.setFilterValueToStartWith(value);
  }

  @Then("the user verifies that all the Active Customer values are equal to {string}")
  public void the_user_verifies_that_all_the_active_customer_values_are_equal_to(String value) {
    reportTemplateTestSteps.verifyActiveCustomerColumnValue(value);
  }

  @Then(
      "^the user selects \"(Current date|Through current date|Current year|Current year to date|Current year minus 1|Current month|Current month to date|Current month minus 1|Current quarter|Current quarter to date|Current quarter minus 1)\" date range from list$")
  public void the_user_selects_date_range_from_list(String optionName) {
    reportTemplateTestSteps.selectDateRangeFromList(optionName);
  }

  @Then("^the user sets date range from \"([^\"]*)\" to \"([^\"]*)\"$")
  public void the_user_sets_date_range_from_to(String from, String to) {
    reportTemplateTestSteps.setDateRangeFromTo(from, to);
  }
}
