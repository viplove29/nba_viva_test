package serenitybase.steps.teststeps;

import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.ActivityListPage;

public class ActivityTestSteps {

  private ActivityListPage activityListPage;

  @Step
  public void expandCenter() {
    activityListPage.clickCenterSection();
  }

  @Step
  public void expandCustomerBusinessSection() {
    activityListPage.clickCustomerBusinessSection();
  }

  @Step
  public void expandCustomerBusinessSectionDivisionMultiple() {
    activityListPage.clickDivisionMultiple();
  }

  @Step
  public void expandCustomerBusinessSectionDivisionAll() {
    activityListPage.clickDivisionAll();
  }

  @Step
  public void expandPolicySection() {
    activityListPage.clickPolicySection();
  }

  @Step
  public void expandTransactionTypeSection() {
    activityListPage.clickTransactionType();
  }

  @Step
  public void expandTransactionTypeSectionOptionMultiple() {
    activityListPage.clickTransactionTypeOptionMultiple();
  }

  @Step
  public void expandTransactionTypeSectionOptionAll() {
    activityListPage.clickTransactionTypeOptionAll();
  }

  @Step
  public void expandTypeOfBusinessSection() {
    activityListPage.clickTypeOfBusinessSection();
  }

  @Step
  public void expandTypeOfBusinessOptionMultiple() {
    activityListPage.clickTypeOfBusinessOptionMultiple();
  }

  @Step
  public void clickCompanySection() {
    activityListPage.clickCompanySection();
  }

  @Step
  public void clickDateSection() {
    activityListPage.clickDateSection();
  }

  @Step
  public void expandActionTypeSection() {
    activityListPage.clickActionTypeSection();
  }

  @Step
  public void expandEnteredBySection() {
    activityListPage.clickEnteredBySection();
  }

  @Step
  public void expandAdditionalFiltersSection() {
    activityListPage.clickAdditionalFiltersSection();
  }

  @Step
  public void clickDivisionOptionMultipleDropDown() {
    activityListPage.clickDivisionOptionMultipleDropDown();
  }

  @Step
  public void clickDivisionOptionMultiple() {
    activityListPage.clickDivisionOptionMultiple();
  }

  @Step
  public void clickSecondDropdownItem() {
    activityListPage.clickSecondDropdownItem();
  }

  @Step
  public void enterNumberOfDays(String numberOfDays) {
    activityListPage.enterIntoInputNumberOfDays(numberOfDays);
  }

  @Step
  public String getNumberOfDays() {
    return activityListPage.getInputNumberOfDays();
  }
}
