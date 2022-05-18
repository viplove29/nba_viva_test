package serenitybase.steps.teststeps;

import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.UnbilledPoliciesPage;

public class UnbilledPoliciesTestSteps {
  private UnbilledPoliciesPage unbilledPoliciesPage;

  @Step
  public void selectOptionUnderFilters(String option) {
    unbilledPoliciesPage.selectOptionUnderFilters(option);
  }
}
