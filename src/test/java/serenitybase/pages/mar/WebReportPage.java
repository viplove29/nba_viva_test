package serenitybase.pages.mar;

import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class WebReportPage extends PageObject {
  @FindBy(xpath = "//span[@ng-show='grid.options.totalItems > 0']//b[2]")
  private WebElementFacade totalResults;

  @FindBy(id = "shc")
  private WebElementFacade showHideIconButton;

  @FindBy(id = "chDivision")
  private WebElementFacade divisionDetailView;

  @FindBy(id = "chBranch")
  private WebElementFacade branchDetailView;

  @FindBy(id = "chDepartment")
  private WebElementFacade departmentDetailView;

  @FindBy(id = "chGroup")
  private WebElementFacade groupDetailView;

  public int getNumberOfResults() {
    return Integer.parseInt(totalResults.getText());
  }

  public List<String> getReportHeaders() {
    return findAll("//i[@class='rpt-glyphicon fa fa-eye']/ancestor::a").stream()
        .map(e -> e.getAttribute("innerText"))
        .collect(Collectors.toList());
  }

  public void selectOptionUnderHideShowIcon(String option) {
    showHideIconButton.click();
    find(String.format("//*[text()='%s']", option)).click();
  }

  public boolean verifyDivisionIsDisplayed() {
    return divisionDetailView.isDisplayed();
  }

  public boolean verifyBranchIsDisplayed() {
    return branchDetailView.isDisplayed();
  }

  public boolean verifyDepartmentIsDisplayed() {
    return departmentDetailView.isDisplayed();
  }

  public boolean verifyGroupIsDisplayed() {
    return groupDetailView.isDisplayed();
  }
}
