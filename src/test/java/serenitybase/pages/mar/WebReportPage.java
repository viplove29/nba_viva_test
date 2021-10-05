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

  @FindBy(id = "f")
  private WebElementFacade filterSymbol;

  @FindBy(id = "drop_columns")
  private WebElementFacade filterDropdown;

  @FindBy(id = "drop_filteroperators")
  private WebElementFacade filterPresetDropDown;

  @FindBy(id = "Active CustomerInactive")
  private WebElementFacade activeCustomerColumn;

  @FindBy(xpath = "//input[@type='text'][1]")
  private WebElementFacade valueTextBox;

  @FindBy(xpath = ".//span[text()=' Add Filters']")
  private WebElementFacade addFiltersButton;

  @FindBy(xpath = ".//button[text()='Apply']")
  private WebElementFacade applyButton;

  @FindBy(xpath = ".//a[contains(text(), 'Active Customer')]")
  private WebElementFacade activeCustomerFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Branch')]")
  private WebElementFacade branchFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Equal to')]")
  private WebElementFacade equalToValue;

  @FindBy(xpath = ".//a[contains(text(), 'Starts with')]")
  private WebElementFacade startsWithValue;

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

  public boolean isDivisionDisplayed() {
    return divisionDetailView.isDisplayed();
  }

  public boolean isBranchDisplayed() {
    return branchDetailView.isDisplayed();
  }

  public boolean isDepartmentDisplayed() {
    return departmentDetailView.isDisplayed();
  }

  public boolean isGroupDisplayed() {
    return groupDetailView.isDisplayed();
  }

  public void clickOnFilterSymbol() {
    filterSymbol.click();
  }

  public void clickOnAddFiltersButton() {
    addFiltersButton.click();
  }

  public void selectOptionUnderFilters(String option) {
    filterDropdown.click();
    switch (option) {
      case "Active Customer":
        activeCustomerFilter.click();
        break;
      case "Branch":
        branchFilter.click();
        break;
      default:
        throw new IllegalArgumentException(String.format("%s filter option not supported", option));
    }
  }

  public void clickOnApply() {
    applyButton.click();
  }

  public void setFilterPresetToStartWith() {
    filterPresetDropDown.click();
    startsWithValue.click();
  }

  public void setFilterStartsWith(String value) {
    valueTextBox.click();
    valueTextBox.sendKeys(value);
  }

  public String getActiveCustomerColumnValue() {
    return activeCustomerColumn.getText();
  }
}
