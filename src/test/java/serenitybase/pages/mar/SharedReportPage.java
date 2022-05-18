package serenitybase.pages.mar;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SharedReportPage extends ReportBasePage {
  // TODO This class can be renamed to something associated with Personnel Extract

  @FindBy(xpath = ".//a[contains(text(), 'Active Customer')]")
  private WebElementFacade activeCustomerFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Branch')]")
  private WebElementFacade branchFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Customer Type')]")
  private WebElementFacade customerTypeFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Current Personnel Type')]")
  private WebElementFacade currentPersonnelTypeFilter;

  @Override
  public void selectOptionUnderFilters(String option) {
    filterDropdown.click();
    WebElement filterOption =
        getDriver().findElement(By.xpath(".//a[contains(text(), '" + option + "')]"));
    filterOption.click();
  }
}
