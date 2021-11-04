package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.BalanceSheetPage;

public class BalanceSheetTestSteps {
  private BalanceSheetPage balanceSheetPage;

  @Step
  public void verifyColumnsAreDisplayed(String columnValue) {
    assertThat(balanceSheetPage.verifyColumnsAreDisplayedWithValues(columnValue)).isTrue();
  }
}
