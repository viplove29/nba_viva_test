package serenitybase.pages.mar;

import java.time.Duration;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class AgencySelectionPage extends PageObject {
  @FindBy(css = ".loginbutton")
  private WebElementFacade loginButton;

  public void selectAgency(String agency) {
    withTimeoutOf(Duration.ofSeconds(40))
        .find(String.format("//span[@title='%s']", agency))
        .click();
    loginButton.click();
  }
}
