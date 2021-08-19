package serenitybase.pages.mar;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class WebReportPage extends PageObject {
  @FindBy(xpath = "//span[@ng-show='grid.options.totalItems > 0']//b[2]")
  private WebElementFacade totalResults;

  public int getNumberOfResults() {
    return Integer.parseInt(totalResults.getText());
  }
}
