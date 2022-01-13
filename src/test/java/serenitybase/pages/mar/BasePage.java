package serenitybase.pages.mar;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class BasePage extends PageObject {

  public void scrollToElement(WebElement element) {
    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
  }
}
