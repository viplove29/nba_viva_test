package serenitybase.pages.mar;

import java.time.Duration;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class AgencySelectionPage extends PageObject {
  @FindBy(css = ".loginbutton")
  private WebElementFacade loginButton;

  public void selectAgency(String agency) {
    withTimeoutOf(Duration.ofSeconds(90))
        .find(String.format("//span[@title='%s']", agency))
        .click();
    loginButton.click();
  }

  public String getCurrentURL() {
    return getDriver().getCurrentUrl();
  }

  public void backup() {
    getDriver().navigate().back();
  }

  public boolean onResubmitPage() {
    return getDriver().getPageSource().contains("Confirm Form Resubmission");
  }

  public void refresh() {
    getDriver().navigate().refresh();
  }
}
