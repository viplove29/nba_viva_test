package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.MarHomePage;

public class GeneralLedgerTestSteps {
  private MarHomePage marHomePage;

  @Step
  public void verifyNumbersDropdownOptionsAreDisplayed(List<String> options) {
    assertThat(options).containsExactlyInAnyOrderElementsOf(marHomePage.getNumbersDropdownValues());
  }

  @Step
  public void verifySubLedgersDropdownOptionsAreDisplayed(List<String> options) {
    assertThat(options)
        .containsExactlyInAnyOrderElementsOf(marHomePage.getSubLedgersDropdownValues());
  }

  @Step
  public void verifyBeginningGreaterThanEndingErrorMessage(String errorMessage) {
    assertThat(errorMessage).isEqualTo(marHomePage.getBeginningGreaterThanEndingErrorMessage());
  }
}
