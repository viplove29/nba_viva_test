package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.LoginToMarTestSteps;

public class LoginToMarStepDefinitions {
  @Steps LoginToMarTestSteps loginToMarTestSteps;

  @Given("the user logs into MAR")
  public void the_user_logs_into_Mar() {
    loginToMarTestSteps.logIn();
    loginToMarTestSteps.selectAgency();
  }
}
