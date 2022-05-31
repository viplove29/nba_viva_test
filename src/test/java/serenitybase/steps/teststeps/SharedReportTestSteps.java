package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Ordering;
import java.util.*;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.*;

public class SharedReportTestSteps {
  private SharedReportPage sharedReportPage;

  @Step
  public void selectTab(String tabName) {
    sharedReportPage.selectTab(tabName);
  }

  @Step
  public void selectOptionUnderHideShowIcon(String option) {
    sharedReportPage.selectOptionUnderHideShowIcon(option);
  }

  @Step
  public void verifyPoliciesAreDisplayed(String policies) {
    boolean isElementDisplayed;
    switch (policies) {
      case ("Division"):
        isElementDisplayed = sharedReportPage.isColumnDisplayed("Division");
        break;
      case ("Branch"):
        isElementDisplayed = sharedReportPage.isColumnDisplayed("Branch");
        break;
      case ("Department"):
        isElementDisplayed = sharedReportPage.isColumnDisplayed("Department");
        break;
      case ("Group"):
        isElementDisplayed = sharedReportPage.isColumnDisplayed("Group");
        break;
      default:
        throw new IllegalArgumentException(String.format("'%s' is not a valid policy.", policies));
    }
    assertThat(isElementDisplayed)
        .withFailMessage(String.format("The policy '%s' was not displayed", policies))
        .isTrue();
  }

  @Step
  public void clickOnFilterSymbol() {
    sharedReportPage.clickOnFilterSymbol();
  }

  @Step
  public void clickOnSortSymbol() {
    sharedReportPage.clickOnSortSymbol();
  }

  @Step
  public void clickOnAddFiltersButton() {
    sharedReportPage.clickOnAddFiltersButton();
  }

  @Step
  public void selectOptionUnderFilters(String option) {
    sharedReportPage.selectOptionUnderFilters(option);
  }

  @Step
  public void selectOptionUnderSort(String option) {
    sharedReportPage.selectOptionUnderSort(option);
  }

  @Step
  public void clickOnApply() {
    sharedReportPage.clickOnApply();
  }

  @Step
  public void clickOnCancel() {
    sharedReportPage.clickOnCancel();
  }

  @Step
  public void setFilterValueToStartWith(String value) {
    sharedReportPage.setFilterPresetToStartWith();
    sharedReportPage.setFilterStartsWith(value);
  }

  @Step
  public void setFilterValueToEqualTo(String value) {
    sharedReportPage.setFilterPresetToEqualTo();
    sharedReportPage.setFilterEqualTo(value);
  }

  @Step
  public void verifyColumnValuesAreEqualTo(String columnName, String value) {
    List<Map<String, String>> gridData = sharedReportPage.getReportGridDataAsMaps();
    for (Map<String, String> rowData : gridData) {
      assertThat(rowData.get(columnName.toUpperCase()).equals(value));
    }
  }

  @Step
  public void verifyColumnIsDisplayedInTab(String columnName) {
    assertThat(sharedReportPage.validateColumnIsDisplayedInTab(columnName)).isTrue();
  }

  @Step
  public void extractReportValuesInTheGrid() {
    List<Map<String, String>> rows = sharedReportPage.getReportGridDataAsMaps();

    System.out.println("MAR Report Grid Data:");
    for (Map<String, String> row : rows) {
      System.out.println(row);
    }
  }

  @Step
  public void extractReportValuesInTheGridToVariable(String variableName) {
    List<Map<String, String>> rows = sharedReportPage.getReportGridDataAsMaps();
    Serenity.setSessionVariable(variableName).to(rows);
  }

  @Step
  public void clickOnCancelButton() {
    sharedReportPage.clickOnCancel();
  }

  @Step
  public void verifyDatasetColumnValuesAreInOrder(String columnName, String order) {
    List<Map<String, String>> dataset = sharedReportPage.getReportGridDataAsMaps();
    ArrayList<String> columns = new ArrayList<>();
    dataset.forEach(
        map -> {
          columns.add(map.get(columnName.toUpperCase(Locale.ROOT)));
        });
    if (order.equals("ascending")) {
      assertThat(Ordering.natural().isOrdered(columns));
    } else {
      assertThat(Ordering.natural().reverse().isOrdered(columns));
    }
  }

  @Step
  public void verifyColumnValuesStartWith(String columnName, String value) {
    List<Map<String, String>> gridData = sharedReportPage.getReportGridDataAsMaps();
    for (Map<String, String> rowData : gridData) {
      assertThat(rowData.get(columnName.toUpperCase()).startsWith(value));
    }
  }
}
