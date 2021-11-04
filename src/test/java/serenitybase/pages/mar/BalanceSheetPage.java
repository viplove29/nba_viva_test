package serenitybase.pages.mar;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class BalanceSheetPage extends PageObject {
  @FindBy(id = "chDivision")
  private WebElementFacade divisionColumn;

  @FindBy(id = "chLevel")
  private WebElementFacade levelColumn;

  @FindBy(id = "chAccount")
  private WebElementFacade accountColumn;

  @FindBy(id = "chDescription")
  private WebElementFacade descriptionColumn;

  @FindBy(id = "chAmount")
  private WebElementFacade amountColumn;

  @FindBy(id = "chCompare Amount")
  private WebElementFacade compareAmountColumn;

  public boolean verifyColumnsAreDisplayedWithValues(String columnValue) {
    switch (columnValue) {
      case "Division":
        return divisionColumn.isDisplayed();
      case "Level":
        return levelColumn.isDisplayed();
      case "Account":
        return accountColumn.isDisplayed();
      case "Description":
        return descriptionColumn.isDisplayed();
      case "Amount":
        return amountColumn.isDisplayed();
      case "Compare Amount":
        return compareAmountColumn.isDisplayed();
      default:
        throw new IllegalArgumentException(
            String.format("%s column option not supported", columnValue));
    }
  }
}
