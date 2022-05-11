package serenitybase.pages.mar;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class BrokerAgedAccountsReceivablePage extends ReportBasePage {

  @FindBy(xpath = ".//a[contains(text(), 'Aging Category')]")
  private WebElementFacade agingCategory;

  @FindBy(xpath = ".//a[contains(text(), 'Broker Name')]")
  private WebElementFacade brokerName;

  @FindBy(xpath = ".//a[contains(text(), 'Invoice Executive')]")
  private WebElementFacade invoiceExecutive;

  @FindBy(xpath = ".//a[contains(text(), 'Total Invoice Balance')]")
  private WebElementFacade totalInviceBalance;

  public void selectOptionUnderFilters(String option) {
    filterDropdown.click();
    switch (option) {
      case "Aging Category":
        agingCategory.click();
        break;
      case "Broker Name":
        brokerName.click();
        break;
      case "Invoice Executive":
        invoiceExecutive.click();
        break;
      case "Total Invoice Balance":
        totalInviceBalance.click();
        break;
      default:
        throw new IllegalArgumentException(String.format("%s filter option not supported", option));
    }
  }
}
