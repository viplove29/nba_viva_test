package serenitybase.pages.mar;

import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class WebReportPage extends PageObject {
  @FindBy(xpath = "//span[@ng-show='grid.options.totalItems > 0']//b[2]")
  private WebElementFacade totalResults;

  public int getNumberOfResults() {
    return Integer.parseInt(totalResults.getText());
  }

  public List<String> getReportHeaders() {
    return findAll("//i[@class='rpt-glyphicon fa fa-eye']/ancestor::a").stream()
        .map(e -> e.getAttribute("innerText"))
        .collect(Collectors.toList());
  }
}
