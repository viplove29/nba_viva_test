package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.jetbrains.annotations.NotNull;
import serenitybase.helpers.Utilities;
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

  @Then("the user clicks on the Sort symbol")
  public void the_user_clicks_on_the_sort_symbol() {
    sharedReportTestSteps.clickOnSortSymbol();
  }

  @Then("the user clicks on the Add Filters button")
  public void the_user_clicks_on_the_add_filters_button() {
    sharedReportTestSteps.clickOnAddFiltersButton();
  }

  @Then("the user selects {string} under Filters")
  public void the_user_selects_option_under_filters(String option) {
    sharedReportTestSteps.selectOptionUnderFilters(option);
  }

  @Then("the user selects {string} under drop down")
  public void the_user_selects_option_under_drop_down(String option) {
    sharedReportTestSteps.selectOptionForSortColumns(option);
  }

  @Then("the user selects {string} under Sort")
  public void the_user_selects_option_under_sort(String option) {
    sharedReportTestSteps.selectOptionUnderSort(option);
  }

  @Then("the user clicks on the Cancel button")
  public void the_user_clicks_on_the_cancel_button() {
    Utilities.simpleSleep(500);
    sharedReportTestSteps.clickOnCancelButton();
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
      String columnName, String value) throws AWTException {
    sharedReportTestSteps.verifyColumnValuesAreEqualTo(columnName, value);
  }

  @Then("the user selects {string} tab")
  public void the_user_selects_tab(String tabName) {
    sharedReportTestSteps.selectTab(tabName);
  }

  @Then("the user verifies the {string} tab exists")
  public void the_user_verifies_the_tab_exists(String tabName) {
    sharedReportTestSteps.selectTab(tabName);
  }

  @Then("the user verifies the following columns are displayed in tab")
  public void the_user_verifies_tab_contains_the_following_columns(List<String> columnNames) {
    for (String columnName : columnNames) {
      sharedReportTestSteps.verifyColumnIsDisplayedInTab(columnName);
    }
  }

  @Then(
      "the user verifies the following columns are displayed in the report by scrolling to the right")
  public void
      the_user_verifies_the_following_columns_are_displayed_in_the_report_by_scrolling_to_the_right(
          List<String> columnNames) {
    sharedReportTestSteps.validateTheListOfColumnsAreDisplayedInTheGrid(columnNames);
  }

  @Then("the user verifies the following columns are not displayed in tab")
  public void the_user_verifies_tab_does_not_contain_the_following_columns(
      List<String> columnNames) {
    for (String columnName : columnNames) {
      sharedReportTestSteps.verifyColumnIsNotDisplayedInTab(columnName);
    }
  }

  @When("the user extracts all the report values in the grid")
  public void the_user_extracts_all_the_report_values_in_the_grid() throws AWTException {
    sharedReportTestSteps.extractReportValuesInTheGrid();
  }

  @When("the user verifies that all the {string} values on the Report start with {string}")
  public void the_user_verifies_that_all_the_customer_name_values_start_with(
      String columnName, String value) throws AWTException {
    sharedReportTestSteps.verifyColumnValuesStartWith(columnName, value);
  }

  @When("the user presses the templates action menu")
  public void the_user_presses_the_templates_action_menu() {
    sharedReportTestSteps.clickTemplatesActionMenu();
  }

  @When("the user selects the delete menu item")
  public void the_user_selects_the_delete_menu_item() {
    sharedReportTestSteps.clickOnDeleteMenuItem();
    Serenity.setSessionVariable("createdTemplate").to(null);
  }

  @When("the user verifies that the values of {string} are in ascending order")
  public void the_user_verifies_that_the_dataset_values_are_in_ascending_order(String columnName)
      throws AWTException {
    Utilities.simpleSleep(3000);
    sharedReportTestSteps.verifyDatasetColumnValuesAreInOrder(columnName, "ascending");
  }

  @When("the user verifies that the values of {string} are in descending order")
  public void the_user_verifies_that_the_dataset_values_are_in_descending_order(String columnName)
      throws AWTException {
    Utilities.simpleSleep(3000);
    sharedReportTestSteps.verifyDatasetColumnValuesAreInOrder(columnName, "descending");
  }

  @When("the user verifies that the values of {string} are in ascending date order")
  public void the_user_verifies_that_the_dataset_values_are_in_ascending_date_order(
      String columnName) throws AWTException {
    Utilities.simpleSleep(3000);
    sharedReportTestSteps.verifyDatasetColumnValuesAreInDateOrder(columnName, "ascending");
  }

  @When("the user verifies that the values of {string} are in descending date order")
  public void the_user_verifies_that_the_dataset_values_are_in_descending_date_order(
      String columnName) throws AWTException {
    Utilities.simpleSleep(3000);
    sharedReportTestSteps.verifyDatasetColumnValuesAreInDateOrder(columnName, "descending");
  }

  @When("the user stores the report grid to {string}")
  public void the_user_stores_the_report_grid_to(String value) throws AWTException {
    sharedReportTestSteps.extractReportValuesInTheGridToVariable(value);
  }

  @When("the user clicks the add summary tab")
  public void the_user_clicks_the_add_summary_tab() {
    Utilities.simpleSleep(1000);
    sharedReportTestSteps.clickOnAddSummaryTab();
  }

  @When("the user clicks the cancel button")
  public void the_user_clicks_the_cancel_button() {
    sharedReportTestSteps.clickOnCancelButton();
  }

  @When("the user clicks the next page button")
  public void the_user_clicks_the_next_page_button() {
    sharedReportTestSteps.clickOnNextPageButton();
  }

  @When("the user clicks the action button")
  public void the_user_clicks_the_action_button() {
    Utilities.simpleSleep(1000);
    sharedReportTestSteps.selectActionButton();
  }

  @When("the user clicks the Save as a New Template menu item")
  public void the_user_clicks_the_save_as_a_new_template() {
    Utilities.simpleSleep(1000);
    sharedReportTestSteps.saveAsANewTemplate();
  }

  @When("the user names the template {string}")
  public void the_user_names_the_template(@NotNull String name) {
    Utilities.simpleSleep(500);
    sharedReportTestSteps.enterTemplateName(name);
    Serenity.setSessionVariable("createdTemplate").to(name);
  }

  @When("the user saves the template")
  public void the_user_saves_the_template() {
    Utilities.simpleSleep(500);
    sharedReportTestSteps.saveNewTemplate();
  }

  @When("the user clicks the Ok button")
  public void the_user_clicks_the_ok_button() {
    Utilities.simpleSleep(500);
    sharedReportTestSteps.clickOnOkButton();
  }

  @When("the user search for the template named {string}")
  public void the_user_saves_the_template(String templateName) {
    sharedReportTestSteps.searchForTemplate(templateName);
    Utilities.simpleSleep(500);
  }

  @When("the user presses the back arrow")
  public void the_user_presses_the_back_button() {
    Utilities.simpleSleep(500);
    sharedReportTestSteps.pressBackArrow();
  }

  @When("the user sets the Filter to range from {string} to today")
  public void the_user_sets_the_filter_to_range_from_to_today(String from) {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String dateAsText = date.format(formatter);
    sharedReportTestSteps.setFilterValueToRangeFromTo(from, dateAsText);
  }

  @When("the user scrolls the current tab horizontally to the end")
  public void the_user_scrolls_the_current_tab_horizontally_to_the_end() {
    sharedReportTestSteps.scrollTheCurrentTabHorizontallyToTheEnd();
  }

  @When("the user selects the following columns to show using the hide show icon")
  public void the_user_selects_the_following_columns_to_show_using_the_hide_show_icon(
      List<String> options) {
    sharedReportTestSteps.selectMultipleOptionUnderHideShowIcon(options, true);
  }
}
