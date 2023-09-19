package serenitybase.steps.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.ActivityTestSteps;

public class ActivityListStepDefinitions {

  @Steps private ActivityTestSteps activityListTestSteps;

  @When("the user expands the center section")
  public void the_user_expands_center_section() {

    activityListTestSteps.expandCenter();
  }

  @When("the user expands the customer business section")
  public void the_user_expands_the_customer_business_section() {

    activityListTestSteps.expandCustomerBusinessSection();
  }

  @When("the user selects division multiple")
  public void the_user_selects_division_multiple() {

    activityListTestSteps.expandCustomerBusinessSectionDivisionMultiple();
  }

  @When("the user selects division all")
  public void the_user_selects_division_all() {

    activityListTestSteps.expandCustomerBusinessSectionDivisionAll();
  }

  @When("the user expands policy section")
  public void the_user_expands_policy_section() {

    activityListTestSteps.expandPolicySection();
  }

  @When("the user expands transaction type section")
  public void the_user_expands_transaction_type_section() {

    activityListTestSteps.expandTransactionTypeSection();
  }

  @When("the user expands transaction type section option multiple")
  public void the_user_expands_transaction_type_section_option_multiple() {

    activityListTestSteps.expandTransactionTypeSectionOptionMultiple();
  }

  @When("the user expands transaction type section option all")
  public void the_user_expands_transaction_type_section_option_all() {

    activityListTestSteps.expandTransactionTypeSectionOptionAll();
  }

  @When("the user expands type of business section")
  public void the_user_expands_type_of_business_section() {

    activityListTestSteps.expandTypeOfBusinessSection();
  }

  @When("the user presses the type of business option multiple")
  public void the_user_presses_the_type_of_business_option_multiple() {

    activityListTestSteps.expandTypeOfBusinessOptionMultiple();
  }

  @When("the user expands company section")
  public void the_user_expands_company_section() {

    activityListTestSteps.clickCompanySection();
  }

  @When("the user expands date selection section")
  public void the_user_expands_date_section() {
    activityListTestSteps.clickDateSection();
  }

  @When("the user expands action type section")
  public void the_user_expands_action_type_section() {
    activityListTestSteps.expandActionTypeSection();
  }

  @When("the user expands entered by section")
  public void the_user_expands_entered_by_section() {
    activityListTestSteps.expandEnteredBySection();
  }

  @When("the user expands additional filters section")
  public void the_user_expands_additional_filters_section() {
    activityListTestSteps.expandAdditionalFiltersSection();
  }

  @When("the user clicks the division select multiple button")
  public void the_user_clicks_the_division_select_multiple_button() {
    activityListTestSteps.clickDivisionOptionMultiple();
  }

  @When("the user clicks the division select multiple dropdown button")
  public void the_user_clicks_the_division_select_multiple_dropdown_button() {
    activityListTestSteps.clickDivisionOptionMultipleDropDown();
  }

  @When("the user clicks the second dropdown item")
  public void the_user_clicks_the_second_dropdown_item() {
    activityListTestSteps.clickSecondDropdownItem();
  }

  @When("the user enters {string} into number of days")
  public void the_user_enters_into_number_of_days(String numberOfDays) {
    activityListTestSteps.enterNumberOfDays(numberOfDays);
  }

  @Then("verify the number of days is {string}")
  public void verify_the_number_of_days(String numberOfDays) {
    String days;
    days = activityListTestSteps.getNumberOfDays();
    assertThat(numberOfDays).isEqualTo(days);
  }
}
