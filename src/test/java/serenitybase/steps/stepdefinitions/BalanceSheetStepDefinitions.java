package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import java.util.List;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.BalanceSheetTestSteps;

public class BalanceSheetStepDefinitions {
  @Steps private BalanceSheetTestSteps balanceSheetTestSteps;

  @Then("the user verifies the following columns are displayed:")
  public void the_user_verifies_the_following_columns_are_displayed(List<String> columnValues) {
    for (String columnValue : columnValues) {
      balanceSheetTestSteps.verifyColumnsAreDisplayed(columnValue);
    }
  }
}
