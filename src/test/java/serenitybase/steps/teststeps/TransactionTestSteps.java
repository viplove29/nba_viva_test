package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.TransactionPage;

public class TransactionTestSteps {
  private TransactionPage transactionPage;

  @Step
  public void selectOptionUnderFilters(String option) {
    transactionPage.selectOptionUnderFilters(option);
  }

  @Step
  public void verifyCustomerNameColumnValues(String value) {
    List<Map<String, String>> rows = transactionPage.getReportGridDataAsMaps();
    Map<String, String> row = rows.get(0);
    String customerName = row.get("CUSTOMER NAME");
    assertThat(customerName.startsWith(value));
  }

  @Step
  public void extractReportValuesInTheGrid() {
    List<Map<String, String>> rows = transactionPage.getReportGridDataAsMaps();

    System.out.println("MAR Report Grid Data:");
    for (Map<String, String> row : rows) {
      System.out.println(row);
    }
  }
}
