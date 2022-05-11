package serenitybase.steps.teststeps;

import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.BrokerAgedAccountsReceivablePage;

public class BrokerAgedAccountsReceivableTestSteps {
  private BrokerAgedAccountsReceivablePage brokerAgedAccountsReceivablePage;

  @Step
  public void selectOptionUnderFilters(String option) {
    brokerAgedAccountsReceivablePage.selectOptionUnderFilters(option);
  }
}
