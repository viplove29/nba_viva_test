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
    sharedReportPage.clickOnCancelButton();
  }

  @Step
  public void clickOnAddSummaryTab() {
    sharedReportPage.clickOnAddSummaryTab();
  }

  @Step
  public void clickOnAddSummaryButton() {
    sharedReportPage.clickOnAddSummaryButton();
  }

  @Step
  public void clickOnOkButton() {
    sharedReportPage.clickOnOkButton();
  }

  @Step
  public void selectSummaryCheckbox(String label) {
    sharedReportPage.selectSummaryCheckbox(label);
  }

  @Step
  public void enterSummaryName(String label) {
    sharedReportPage.setSummaryName(label);
  }

  @Step
  public void enterTemplateName(String label) {
    sharedReportPage.enterANewTemplateName(label);
  }

  @Step
  public void selectActionButton() {
    sharedReportPage.selectActionButton();
  }

  @Step
  public void saveAsANewTemplate() {
    sharedReportPage.saveAsANewTemplate();
  }

  @Step
  public void saveNewTemplate() {
    sharedReportPage.saveNewTemplate();
  }

  @Step
  public void pressBackArrow() {
    sharedReportPage.clickOnBackArrow();
  }

  @Step
  public void clickTemplatesActionMenu() {
    sharedReportPage.clickOnTemplatesActionMenu();
  }

  @Step
  public void clickOnDeleteMenuItem() {
    sharedReportPage.clickOnDeleteMenuItem();
  }

  @Step
  public void searchForTemplate(String templateName) {
    sharedReportPage.searchForTemplate(templateName);
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
      assertThat(Ordering.from(String.CASE_INSENSITIVE_ORDER).isOrdered(columns))
          .as("Column data is not in ascending order: '%s'", columns)
          .isTrue();
    } else {
      assertThat(Ordering.from(String.CASE_INSENSITIVE_ORDER).reverse().isOrdered(columns))
          .as("Column data is not in descending order: '%s'", columns)
          .isTrue();
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
