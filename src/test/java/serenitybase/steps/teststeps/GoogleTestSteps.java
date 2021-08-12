package serenitybase.steps.teststeps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import serenitybase.selenium.pages.GooglePage;

public class GoogleTestSteps extends ScenarioSteps {

  GooglePage googlePage;

  @Step
  public void clickImFeelingLucky() {
    googlePage.clickLuckyButton();
  }
}
