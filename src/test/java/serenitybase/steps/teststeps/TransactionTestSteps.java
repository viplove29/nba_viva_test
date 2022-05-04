package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.TransactionPage;
import java.util.Map;
import java.util.List;


public class TransactionTestSteps {
  private TransactionPage transactionPage;

  @Step
  public void clickOnFilterSymbol() {
    transactionPage.clickOnFilterSymbol();
  }

  @Step
  public void clickOnAddFiltersButton() {
    transactionPage.clickOnAddFiltersButton();
  }

  @Step
  public void selectOptionUnderFilters(String option) {
    transactionPage.selectOptionUnderFilters(option);
  }

  @Step
  public void setFilterValueToStartWith(String value) {
    transactionPage.setFilterPresetToStartWith();
    transactionPage.setFilterStartsWith(value);
  }

  @Step
  public void clickOnApply() {
    transactionPage.clickOnApply();
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
