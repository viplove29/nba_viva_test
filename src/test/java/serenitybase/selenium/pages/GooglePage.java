package serenitybase.selenium.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class GooglePage extends PageObject {

  @FindBy(id = "gbqfbb")
  private WebElementFacade luckyButton;

  public void clickLuckyButton() {
    luckyButton.waitUntilEnabled();
    luckyButton.click();
  }
}
