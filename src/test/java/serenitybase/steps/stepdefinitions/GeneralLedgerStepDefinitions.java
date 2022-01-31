package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import java.util.List;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.GeneralLedgerTestSteps;

public class GeneralLedgerStepDefinitions {
  @Steps private GeneralLedgerTestSteps generalLedgerTestSteps;

  @Then("the user verifies the following options are displayed in Numbers dropdown")
  public void the_user_verifies_the_following_options_are_displayed_in_numbers_dropdown(
      List<String> options) {
    generalLedgerTestSteps.verifyNumbersDropdownOptionsAreDisplayed(options);
  }

  @Then("the user verifies the following options are displayed in Sub-ledgers dropdown")
  public void the_user_verifies_the_following_options_are_displayed_in_subledgers_dropdown(
      List<String> options) {
    generalLedgerTestSteps.verifySubLedgersDropdownOptionsAreDisplayed(options);
  }

  @Then(
      "^the user verifies an error message with \"([^\"]*)\" text displays when selecting Beginning number greater than Ending number$")
  public void
      the_user_verifies_an_error_message_with_text_displays_when_selecting_beginning_greater_than_ending(
          String errorMessage) {
    generalLedgerTestSteps.verifyBeginningGreaterThanEndingErrorMessage(errorMessage);
  }
}
