package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.BrokerAgedAccountsReceivableTestSteps;

public class BrokerAgedAccountsReceivableStepDefinitions {

  @Steps private BrokerAgedAccountsReceivableTestSteps brokerAgedAccountsReceivableTestSteps;

  @Then("the user selects {string} under Filters on the Broker Aged Accounts Receivable Report")
  public void the_user_selects_option_under_filters(String option) {
    brokerAgedAccountsReceivableTestSteps.selectOptionUnderFilters(option);
  }
}
