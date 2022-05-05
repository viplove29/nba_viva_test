package serenitybase.steps.teststeps;

import java.util.List;
import java.util.Map;
import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.UnbilledPoliciesPage;

public class UnbilledPoliciesTestSteps {
  private UnbilledPoliciesPage unbilledPoliciesPage;

  @Step
  public void clickOnFilterSymbol() {
    unbilledPoliciesPage.clickOnFilterSymbol();
  }

  @Step
  public void clickOnAddFiltersButton() {
    unbilledPoliciesPage.clickOnAddFiltersButton();
  }

  @Step
  public void selectOptionUnderFilters(String option) {
    unbilledPoliciesPage.selectOptionUnderFilters(option);
  }

  @Step
  public void setFilterValueToStartWith(String value) {
    unbilledPoliciesPage.setFilterPresetToStartWith();
    unbilledPoliciesPage.setFilterStartsWith(value);
  }

  @Step
  public void clickOnApply() {
    unbilledPoliciesPage.clickOnApply();
  }

  @Step
  public void verifyPolicyNumberColumnValue(String value) {
    List<Map<String, String>> rows = unbilledPoliciesPage.getReportGridDataAsMaps();
    Map<String, String> row = rows.get(0);
    String customerName = row.get("POLICY NUMBER");
    assert (customerName.startsWith(value));
  }
}
