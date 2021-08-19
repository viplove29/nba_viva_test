package serenitybase.pages.vsso;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("page:mar.url")
public class LoginPage extends PageObject {
  @FindBy(id = "username")
  WebElementFacade emailTextBox;

  @FindBy(id = "password")
  WebElementFacade passwordTextBox;

  @FindBy(xpath = "//a[@title='Log In']")
  WebElementFacade loginButton;

  public void logIn(String email, String password) {
    typeInto(emailTextBox, email);
    typeInto(passwordTextBox, password);
    loginButton.click();
  }
}
