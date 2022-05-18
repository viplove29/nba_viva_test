package serenitybase.pages.mar;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SharedReportPage extends ReportBasePage {
  // TODO This class can be renamed to something associated with Personnel Extract
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

  @FindBy(id = "Active CustomerInactive")
  protected WebElementFacade activeCustomerColumn;

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

  @Override
  public void selectOptionUnderFilters(String option) {
    filterDropdown.click();
    WebElement filterOption =
        getDriver().findElement(By.xpath(".//a[contains(text(), '" + option + "')]"));
    filterOption.click();
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
