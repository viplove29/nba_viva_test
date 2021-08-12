package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.GoogleTestSteps;

public class GoogleStepDefinitions {

  @Steps GoogleTestSteps googleTestSteps;

  @Then("I click the Im Feeling Lucky button")
  public void i_click_the_Im_feeling_luck_button() {
    googleTestSteps.clickImFeelingLucky();
  }
}
