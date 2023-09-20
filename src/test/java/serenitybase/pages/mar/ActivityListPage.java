package serenitybase.pages.mar;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class ActivityListPage extends PageObject {

  @FindBy(xpath = "//a[text()[contains(.,'Center')]]")
  protected WebElementFacade centerSection;

  @FindBy(xpath = "//a[text()[contains(.,'Action Type')]]")
  protected WebElementFacade actionTypeSection;

  @FindBy(xpath = "//a[text()[contains(.,'Entered By')]]")
  protected WebElementFacade enteredBySection;

  @FindBy(xpath = "//a[text()[contains(.,'Additional Filters')]]")
  protected WebElementFacade additionalFiltersSection;

  @FindBy(xpath = "//a[text()[contains(.,'Policy')]]")
  protected WebElementFacade policySection;

  @FindBy(xpath = "//a[text()[contains(.,'Transaction Type')]]")
  protected WebElementFacade transactionTypeSection;

  @FindBy(xpath = "//a[text()[contains(.,'Type of Business')]]")
  protected WebElementFacade typeOfBusinessSection;

  @FindBy(xpath = "//a[text()[contains(.,'Company')]]")
  protected WebElementFacade companySection;

  @FindBy(xpath = "//a[text()[contains(.,'Date Selection')]]")
  protected WebElementFacade dateSelection;

  @FindBy(name = "TransactionType-option-multiple")
  private WebElementFacade transactionTypeOptionMultiple;

  @FindBy(name = "TransactionType-option-all")
  private WebElementFacade transactionTypeOptionAll;

  @FindBy(xpath = "//a[text()[contains(.,'Customer Business Unit')]]")
  protected WebElementFacade customerBusinessSection;

  @FindBy(name = "division-option-multiple")
  private WebElementFacade divisionMultiple;

  @FindBy(name = "division-option-all")
  private WebElementFacade divisionAll;

  @FindBy(name = "TypeOfBusiness-option-multiple")
  private WebElementFacade typeOfBusinessMultiple;

  @FindBy(name = "activity-additional-filters-appended")
  private WebElementFacade activityAdditionalFiltersAppended;

  @FindBy(name = "activity-additional-filters-attachments")
  private WebElementFacade activityAdditionalFiltersAttachments;

  @FindBy(name = "division-option-multiple")
  private WebElementFacade divisionOptionMultiple;

  @FindBy(id = "divDDG1_drop_columnvalues")
  private WebElementFacade divDDG1DropColumnvalues;

  @FindBy(id = "selectionRowHeaderCol_2")
  private WebElementFacade selectionRowHeaderCol2;

  @FindBy(name = "ActivityDateSelection-input-number-of-days")
  private WebElementFacade inputNumberOfDays;

  public void clickCenterSection() {
    centerSection.click();
  }

  public void clickSecondDropdownItem() {
    selectionRowHeaderCol2.click();
  }

  public void clickDivisionOptionMultipleDropDown() {
    divDDG1DropColumnvalues.click();
  }

  public void clickDivisionOptionMultiple() {
    divisionOptionMultiple.click();
  }

  public void clickPolicySection() {
    policySection.click();
  }

  public void clickCustomerBusinessSection() {
    customerBusinessSection.click();
  }

  public void enterIntoInputNumberOfDays(String numberOfDays) {
    inputNumberOfDays.sendKeys(numberOfDays);
  }

  public String getInputNumberOfDays() {
    return inputNumberOfDays.getValue();
  }

  public void clickDivisionMultiple() {
    divisionMultiple.click();
  }

  public void clickDivisionAll() {
    divisionAll.click();
  }

  public void clickTransactionType() {
    transactionTypeSection.click();
  }

  public void clickTransactionTypeOptionMultiple() {
    transactionTypeOptionMultiple.click();
  }

  public void clickTransactionTypeOptionAll() {
    transactionTypeOptionAll.click();
  }

  public void clickTypeOfBusinessSection() {
    typeOfBusinessSection.click();
  }

  public void clickTypeOfBusinessOptionMultiple() {
    typeOfBusinessMultiple.click();
  }

  public void clickCompanySection() {
    companySection.click();
  }

  public void clickDateSection() {
    dateSelection.click();
  }

  public void clickActionTypeSection() {
    actionTypeSection.click();
  }

  public void clickEnteredBySection() {
    enteredBySection.click();
  }

  public void clickAdditionalFiltersSection() {
    additionalFiltersSection.click();
  }
}
