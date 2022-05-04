package serenitybase.pages.mar;

import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import serenitybase.helpers.Utilities;

public class WebReportPage extends BasePage {

  @FindBy(id = "Customer TypeMaster")
  private WebElementFacade masterTypeColumn;

  @FindBy(id = "Current Personnel TypeRep")
  private WebElementFacade currentPersonnelTypeColumn;

  @FindBy(xpath = ".//a[contains(text(), 'Active Customer')]")
  private WebElementFacade activeCustomerFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Branch')]")
  private WebElementFacade branchFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Customer Type')]")
  private WebElementFacade customerTypeFilter;

  @FindBy(xpath = ".//a[contains(text(), 'Current Personnel Type')]")
  private WebElementFacade currentPersonnelTypeFilter;

  public int getNumberOfResults() {
    return Integer.parseInt(totalResults.getText());
  }

  public List<String> getReportHeaders() {
    String activeTabContentId = Serenity.sessionVariableCalled("activeTabContentId");
    WebElementFacade activeTabContent = find(By.id(activeTabContentId));
    return activeTabContent
        .findElements(By.xpath(".//i[@class='rpt-glyphicon fa fa-eye']/ancestor::a"))
        .stream()
        .map(e -> e.getAttribute("innerText"))
        .collect(Collectors.toList());
  }

  public void selectOptionUnderHideShowIcon(String option) {
    showHideIconButton.click();
    find(String.format("//*[text()='%s']", option)).click();
  }

  public void selectTab(String tabName) {
    find(String.format("//*[text()='%s']", tabName)).click();
    String activeTabContentId = find(By.cssSelector(".rpt-tab-content.active")).getAttribute("id");
    Serenity.setSessionVariable("activeTabContentId").to(activeTabContentId);
    Utilities.waitForActiveTabLoadingSpinner();
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
      case "Customer Type":
        customerTypeFilter.click();
        break;
      case "Current Personnel Type":
        currentPersonnelTypeFilter.click();
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

  public void setFilterPresetToEqualTo() {
    filterPresetDropDown.click();
    equalToValue.click();
  }

  public void setFilterEqualTo(String value) {
    dropdownMenuButton.click();
    customerTypeDropdownMenu
        .findElement(
            By.xpath(
                String.format("//label[contains(text(), '%s')]/preceding-sibling::input", value)))
        .click();
    if (dropdownMenuButton.getAttribute("aria-expanded").contains("true")) {
      dropdownMenuButton.click();
    }
  }

  public String getActiveCustomerColumnValue() {
    return activeCustomerColumn.getText();
  }

  public String getCustomerTypeColumnValue() {
    horizontalScroll(masterTypeColumn);
    return masterTypeColumn.getText();
  }

  public String getCurrentPersonnelTypeColumnValue() {
    horizontalScroll(currentPersonnelTypeColumn);
    return currentPersonnelTypeColumn.getText();
  }

  public boolean validateColumnsAreDisplayedInTab(String columnName) {
    WebElement activeTab = find(By.id(Serenity.sessionVariableCalled("activeTabContentId")));
    WebElement column = findColumnByColumnNameInTab(activeTab, columnName);
    horizontalScroll(column);
    return column.isDisplayed();
  }

  public WebElement findColumnByColumnNameInTab(WebElement element, String value) {
    return element.findElement(By.xpath(String.format(".//*[@id='ch%s']", value)));
  }
}
