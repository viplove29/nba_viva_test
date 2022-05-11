package serenitybase.steps.teststeps;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.UnbilledPoliciesPage;

public class UnbilledPoliciesTestSteps {
  private UnbilledPoliciesPage unbilledPoliciesPage;

  @Step
  public void selectOptionUnderFilters(String option) {
    unbilledPoliciesPage.selectOptionUnderFilters(option);
  }

  @Step
  public void verifyColumnValue(String columnName, String value) {
    List<Map<String, String>> rows = unbilledPoliciesPage.getReportGridDataAsMaps();
    Map<String, String> row = rows.get(0);
    String gridValue = row.get(columnName.toUpperCase(Locale.ROOT));
    assert (gridValue.startsWith(value));
  }
}
