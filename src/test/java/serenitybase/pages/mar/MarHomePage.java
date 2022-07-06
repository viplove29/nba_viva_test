package serenitybase.pages.mar;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import serenitybase.helpers.Utilities;

public class MarHomePage extends PageObject {
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

  @FindBy(xpath = "//div[@class='radio']/input[1]")
  private WebElementFacade fromListRadioButton;

  @FindBy(xpath = "//div[@class='radio']/input[2]")
  private WebElementFacade selectDatesRadioButton;

  @FindBy(xpath = "//p[contains(text(),'From List')]/following-sibling::div[1]")
  private WebElementFacade fromListDropdown;

  @FindBy(xpath = "//span[contains(text(),'From')]/following::input")
  private WebElementFacade dateFromInput;

  @FindBy(xpath = "//span[contains(text(),'To')]/following::input")
  private WebElementFacade dateToInput;

  @FindBy(xpath = "//label[contains(text(),'Account Numbers/Sub-ledgers:')]")
  private WebElementFacade accountNumbersSubLedgersSection;

  @FindBy(xpath = "//p[contains(text(),'Numbers:')]")
  private WebElementFacade numbersSection;

  @FindBy(xpath = "//p[contains(text(),'Beginning:')]")
  private WebElementFacade beginningSection;

  @FindBy(xpath = "//p[contains(text(),'Ending:')]")
  private WebElementFacade endingSection;

  @FindBy(xpath = "//p[contains(text(),'Specific Number:')]")
  private WebElementFacade specificNumberSection;

  @FindBy(xpath = "//p[contains(text(),'Sub-ledgers:')]")
  private WebElementFacade subLedgersSection;

  @FindBy(xpath = "//p[contains(text(),'Broker:')]")
  private WebElementFacade brokerSection;

  @FindBy(xpath = "//p[contains(text(),'Company:')]")
  private WebElementFacade companySection;

  @FindBy(xpath = "//p[contains(text(),'Employee:')]")
  private WebElementFacade employeeSection;

  @FindBy(xpath = "//p[contains(text(),'Vendor:')]")
  private WebElementFacade vendorSection;

  @FindBy(xpath = "//p[contains(text(),'Numbers:')]//following::select[1]")
  private WebElementFacade numbersDropdown;

  @FindBy(xpath = "//p[contains(text(),'Sub-ledgers:')]//following::select[1]")
  private WebElementFacade subLedgersDropdown;

  @FindBy(xpath = "//input[@placeholder='Search']")
  private WebElementFacade dropdownSearchBox;

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
    WebElement reportRow = getReportRow(reportName);
    Utilities.setReportTypeSessionVariable(reportRow);
    reportRow.findElement(By.xpath(String.format(".//span[text()='%s']", reportName))).click();
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
      new Utilities().scrollToElement(checkBox);
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

  public void selectFromListOption() {
    fromListRadioButton.click();
  }

  public void clickFromListDropdown() {
    fromListDropdown.click();
  }

  public void selectDateRangeOptionFromDropdown(String optionName) {
    List<WebElement> dateRangeOptions = fromListDropdown.findElements(By.tagName("option"));

    WebElement selectedOption =
        dateRangeOptions.stream()
            .filter(x -> x.getText().contains(optionName))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("No date range found " + optionName));
    selectedOption.click();
  }

  public void selectSelectDatesOption() {
    selectDatesRadioButton.click();
  }

  public void setDateFrom(String from) {
    typeInto(dateFromInput, from);
  }

  public void setDateTo(String to) {
    typeInto(dateToInput, to);
  }

  public void selectValueFromDropdown(String option, String sectionName) {
    WebElement section = getAccountNumbersSubLedgersSection(sectionName);
    WebElement dropdown = section.findElement(By.xpath(".//following::select[1]"));
    dropdown.click();
    selectOptionFromDropdown(dropdown, option);
  }

  public void selectRandomValueFromDropdown(String sectionName) {
    WebElement section = getAccountNumbersSubLedgersSection(sectionName);
    WebElement dropdown = section.findElement(By.xpath(".//following::select[1]"));
    dropdown.click();

    Random random = new Random();
    List<WebElement> dropdownOptions = dropdown.findElements(By.tagName("option"));
    int index = random.nextInt(dropdownOptions.size());
    selectOptionFromDropdown(dropdown, dropdownOptions.get(index).getText());
  }

  public WebElement getAccountNumbersSubLedgersSection(String section) {
    switch (section) {
      case "Numbers":
        return numbersSection;
      case "Beginning":
        return beginningSection;
      case "Ending":
        return endingSection;
      case "Specific Number":
        return specificNumberSection;
      case "Sub-ledgers":
        return subLedgersSection;
      case "Vendor":
        return vendorSection;
      case "Broker":
        return brokerSection;
      case "Company":
        return companySection;
      case "Employee":
        return employeeSection;
      default:
        throw new IllegalArgumentException(
            String.format("%s section in Account Numbers/Sub-ledgers not supported", section));
    }
  }

  public void selectOptionFromDropdown(WebElement dropdown, String option) {
    List<WebElement> dropdownOptions = dropdown.findElements(By.tagName("option"));

    WebElement selectedOption =
        dropdownOptions.stream()
            .filter(x -> x.getText().contains(option))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("No option found " + option));
    selectedOption.click();
  }

  public List<String> getNumbersDropdownValues() {
    return getDropdownOptions(numbersDropdown);
  }

  public List<String> getSubLedgersDropdownValues() {
    return getDropdownOptions(subLedgersDropdown);
  }

  public List<String> getDropdownOptions(WebElement dropdown) {
    dropdown.click();
    List<WebElement> dropdownOptions = dropdown.findElements(By.tagName("option"));
    List<String> dropdownValues = new ArrayList<>();
    dropdownOptions.forEach(
        option -> {
          dropdownValues.add(option.getText());
        });
    return dropdownValues;
  }

  public String getBeginningGreaterThanEndingErrorMessage() {
    return accountNumbersSubLedgersSection
        .findElement(By.xpath(".//following::span[contains(@class,'ng-scope')]"))
        .getText();
  }

  public boolean isDropdownDisplayed(String sectionName) {
    WebElement section = getAccountNumbersSubLedgersSection(sectionName);
    return section.findElement(By.xpath(".//following::select[1]")).isDisplayed();
  }

  public void waitForDropdownDisplayed(String sectionName) {
    WebElement section = getAccountNumbersSubLedgersSection(sectionName);
    int count = 0;
    while (count < 30) {
      if (section.findElement(By.xpath(".//following::select[1]")).isDisplayed()) {
        return;
      }
      Utilities.simpleSleep(500);
    }
    return;
  }

  public List<String> getDivisionsInDropdown(String categoryName) {
    clickOnSelectMultipleForCategory(categoryName);
    getDriver()
        .findElement(
            By.xpath(
                "//div[@ng-if=\""
                    + categoryName.toLowerCase()
                    + "Option === 'multiple'\"]//div//a[@id='drop_columnvalues']"))
        .click();

    List<String> divisionList;
    WebElementFacade categoryDropdown = getCategoryDropdown(categoryName);

    divisionList = getDivisionFromCategoryDropdown(categoryName, categoryDropdown);
    getDriver()
        .findElement(
            By.xpath(
                "//div[@ng-if=\""
                    + categoryName.toLowerCase()
                    + "Option === 'multiple'\"]//div//a[@id='drop_columnvalues']"))
        .click();

    return divisionList;
  }

  private List<String> getDivisionFromCategoryDropdown(
      String categoryName, WebElementFacade categoryDropdown) {
    List<WebElement> dropdownList =
        categoryDropdown
            .findElement(By.className("ui-grid-render-container-body"))
            .findElement(By.className("rpt-ui-grid-canvas"))
            .findElements(By.xpath(".//div[contains(@id, 'Division')]"));
    List<String> dropdownValues = new ArrayList<>();
    dropdownList.forEach(option -> dropdownValues.add(option.getText()));
    return dropdownValues;
  }

  public void clickStatusRadioButton(String status) {
    getDriver()
        .findElement(By.xpath("//input[@name='status-option-" + status.toLowerCase() + "']"))
        .click();
  }

  public void clickAllOrSelectMultipleRadioButton(String radioButtonOption, String section) {
    if (radioButtonOption.equals("Select Multiple")) {
      getDriver().findElement(By.xpath("//input[@name='" + section + "-option-multiple']")).click();
    } else {
      getDriver()
          .findElement(
              By.xpath(
                  "//input[@name='"
                      + section
                      + "-option-"
                      + radioButtonOption.toLowerCase()
                      + "']"))
          .click();
    }
  }

  public void searchPersonnelDropdownElement(String section, String options) {
    getDriver()
        .findElement(
            By.xpath(
                "//multi_select_dropdown[@model='"
                    + section
                    + "Model']//a[@id='drop_columnvalues']"))
        .click();
    dropdownSearchBox.clear();
    dropdownSearchBox.sendKeys(options);

    getDriver().findElement(By.xpath("//input[@id='" + section + "Id_checkbox0']")).click();
    getDriver()
        .findElement(
            By.xpath(
                "//multi_select_dropdown[@model='"
                    + section
                    + "Model']//a[@id='drop_columnvalues']"))
        .click();
  }

  public String getDropdownValueText(String section) {
    return getDriver()
        .findElement(
            By.xpath("//multi_select_dropdown[@model='" + section + "Model']//input[@id='fveq']"))
        .getAttribute("value");
  }
}
