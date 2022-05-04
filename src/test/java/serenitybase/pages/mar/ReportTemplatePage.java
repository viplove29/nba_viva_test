package serenitybase.pages.mar;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class ReportTemplatePage extends ReportBasePage {

  @FindBy(id = "Customer TypeMaster")
  private WebElementFacade masterTypeColumn;

  @FindBy(id = "Current Personnel TypeRep")
  private WebElementFacade currentPersonnelTypeColumn;

  @FindBy(xpath = ".//a[contains(text(), 'Active Customer')]")
  private WebElementFacade activeCustomerFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Branch')]")
  private WebElementFacade branchFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Customer Type')]")
  private WebElementFacade customerTypeFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Current Personnel Type')]")
  private WebElementFacade currentPersonnelTypeFilter;


  public boolean isDivisionDisplayed() {
    return divisionDetailView.isDisplayed();
  }

  public boolean isBranchDisplayed() {
    return branchDetailView.isDisplayed();
  }

  public boolean isDepartmentDisplayed() {
    return departmentDetailView.isDisplayed();
  }

  public boolean isGroupDisplayed() {
    return groupDetailView.isDisplayed();
  }

  public void clickOnFilterSymbol() {
    filterSymbol.click();
  }

  public void clickOnAddFiltersButton() {
    addFiltersButton.click();
  }

  public void selectOptionUnderFilters(String option) {
    filterDropdown.click();
    switch (option) {
      case "Active Customer":
        activeCustomerFilter.click();
        break;
      case "Branch":
        branchFilter.click();
        break;
      case "Customer Type":
        customerTypeFilter.click();
        break;
      case "Current Personnel Type":
        currentPersonnelTypeFilter.click();
        break;
      default:
        throw new IllegalArgumentException(String.format("%s filter option not supported", option));
    }
  }

  public String getActiveCustomerColumnValue() {
    return activeCustomerColumn.getText();
  }

  public String getCustomerTypeColumnValue() {
    horizontalScroll(masterTypeColumn);
    return masterTypeColumn.getText();
  }

  public String getCurrentPersonnelTypeColumnValue() {
    horizontalScroll(currentPersonnelTypeColumn);
    return currentPersonnelTypeColumn.getText();
  }


}
