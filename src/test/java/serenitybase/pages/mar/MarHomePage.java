package serenitybase.pages.mar;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MarHomePage extends BasePage {
  @FindBy(id = "rua")
  private WebElementFacade reportNameTextBox;

  @FindBy(id = "gr")
  private WebElementFacade runReportButton;

  @FindBy(xpath = "//div[@class='rpt-split-view rpt-split-view-right']")
  private WebElementFacade reportTemplatesContainer;

  @FindBy(id = "agdd")
  private WebElementFacade actionsButton;

  @FindBy(xpath = "//div[@class='rpt-modal-container ng-scope']")
  private WebElementFacade reportSetupContainer;

  @FindBy(name = "division-option-multiple")
  private WebElementFacade divisionSelectMultipleOption;

  @FindBy(name = "branch-option-multiple")
  private WebElementFacade branchSelectMultipleOption;

  @FindBy(name = "department-option-multiple")
  private WebElementFacade departmentSelectMultipleOption;

  @FindBy(name = "group-option-multiple")
  private WebElementFacade groupSelectMultipleOption;

  @FindBy(name = "association-option-customer")
  private WebElementFacade customerOption;

  @FindBy(name = "association-option-policy")
  private WebElementFacade policyOption;

  @FindBy(id = "drop_columnvalues")
  private WebElementFacade dropDownButton;

  @FindBy(id = "divDDG1")
  private WebElementFacade divisionDropdown;

  @FindBy(id = "branchDDG1")
  private WebElementFacade branchDropdown;

  @FindBy(id = "deptDDG1")
  private WebElementFacade departmentDropdown;

  @FindBy(id = "grpDDG1")
  private WebElementFacade groupDropdown;

  private WebElementFacade getReportRow(String reportName) {
    return find(String.format("//tr[@id='%s']", reportName));
  }

  public void selectReportTemplate(String reportTemplate) {
    reportTemplatesContainer.findBy(String.format(".//span[text()='%s']", reportTemplate)).click();
  }

  public void setReportName(String reportName) {
    typeInto(reportNameTextBox, reportName);
  }

  public void runReport() {
    runReportButton.click();
  }

  public void waitForReportToComplete(String reportName) {
    withTimeoutOf(Duration.ofMinutes(5)).waitFor(getReportRow(reportName));
  }

  public void clickExcelQuickActions(String reportName) {
    getReportRow(reportName).findBy(".//img[contains(@ng-src,'excel')]").click();
  }

  public void clickCsvQuickActions(String reportName) {
    getReportRow(reportName).findBy(".//img[contains(@ng-src,'csv')]").click();
  }

  public void clickOnReport(String reportName) {
    getReportRow(reportName).findBy(String.format(".//span[text()='%s']", reportName)).click();
  }

  public void selectOptionUnderActions(String option) {
    actionsButton.click();
    find(String.format("//*[text()='%s']", option)).click();
  }

  public void expandFilter(String filterName) {
    List<WebElement> filterModulesList =
        reportSetupContainer.findElements(By.className("rpt-gen-agingar-caret"));
    for (WebElement filter : filterModulesList) {
      boolean isFilterExpanded = filter.getAttribute("class").contains("fa-caret-down");
      if (!isFilterExpanded
          && filter
              .findElement(By.xpath("ancestor::a"))
              .getText()
              .trim()
              .equalsIgnoreCase(filterName)) {
        filter.click();
      }
    }
  }

  public void selectItemFromCategory(String itemName, String categoryName) {
    clickOnSelectMultipleForCategory(categoryName);
    dropDownButton.click();
    WebElementFacade categoryDropdown = getCategoryDropdown(categoryName);
    categoryDropdown.find(By.xpath(".//a[contains(text(),'Clear')]")).click();
    int itemIndex = searchForItemIndexInCategoryDropdown(itemName, categoryName, categoryDropdown);
    selectItemCheckboxByIndex(itemIndex, categoryDropdown);
  }

  public int searchForItemIndexInCategoryDropdown(
      String itemName, String categoryName, WebElementFacade categoryDropdown) {
    List<String> dropdownValues = getItemNamesFromCategoryDropdown(categoryName, categoryDropdown);
    int itemIndex = 0;
    for (int i = 0; i < dropdownValues.size(); i++) {
      if (dropdownValues.get(i).equals(itemName)) {
        itemIndex = i;
      }
    }
    return itemIndex;
  }

  public List<String> getItemNamesFromCategoryDropdown(
      String categoryName, WebElementFacade categoryDropdown) {
    List<WebElement> dropdownList =
        categoryDropdown
            .findElement(By.className("ui-grid-render-container-body"))
            .findElement(By.className("rpt-ui-grid-canvas"))
            .findElements(By.xpath(String.format(".//div[contains(@id, '%s')]", categoryName)));
    List<String> dropdownValues = new ArrayList<>();
    dropdownList.forEach(option -> dropdownValues.add(option.getText()));
    return dropdownValues;
  }

  public void selectItemCheckboxByIndex(int itemIndex, WebElementFacade categoryDropdown) {
    List<WebElement> dropdownCheckboxes =
        categoryDropdown
            .findElement(By.className("ui-grid-render-container-left"))
            .findElements(By.className("ui-grid-row"));
    WebElement checkBox = dropdownCheckboxes.get(itemIndex);
    if (!checkBox.isDisplayed()) {
      scrollToElement(checkBox);
    }
    checkBox.findElement(By.className("ui-grid-selection-row-header-buttons")).click();
  }

  public void clickOnSelectMultipleForCategory(String categoryName) {
    switch (categoryName) {
      case "Division":
        divisionSelectMultipleOption.click();
        break;
      case "Branch":
        branchSelectMultipleOption.click();
        break;
      case "Department":
        departmentSelectMultipleOption.click();
        break;
      case "Group":
        groupSelectMultipleOption.click();
        break;
      default:
        throw new IllegalArgumentException(
            String.format("%s category not supported", categoryName));
    }
  }

  public void selectBusinessUnitAssociationOption(String optionName) {
    switch (optionName) {
      case "Customer":
        customerOption.click();
        break;
      case "Policy":
        policyOption.click();
        break;
      default:
        throw new IllegalArgumentException(String.format("%s option not supported", optionName));
    }
  }

  public WebElementFacade getCategoryDropdown(String categoryName) {
    switch (categoryName) {
      case "Division":
        return divisionDropdown;
      case "Branch":
        return branchDropdown;
      case "Department":
        return departmentDropdown;
      case "Group":
        return groupDropdown;
      default:
        throw new IllegalArgumentException(
            String.format("%s Business Unit category not supported", categoryName));
    }
  }
}
