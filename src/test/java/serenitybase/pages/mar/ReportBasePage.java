package serenitybase.pages.mar;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import serenitybase.helpers.Utilities;

public class ReportBasePage extends PageObject {

  @FindBy(id = "shc")
  protected WebElementFacade showHideIconButton;

  @FindBy(id = "templates_action_menu")
  protected WebElementFacade templatesActionMenu;

  @FindBy(id = "rptSearch")
  protected WebElementFacade templateSearchText;

  @FindBy(id = "ar")
  protected WebElementFacade backArrow;

  @FindBy(id = "agdd")
  protected WebElementFacade actionsButton;

  @FindBy(id = "sln")
  protected WebElementFacade saveTemplateName;

  @FindBy(id = "sls")
  protected WebElementFacade saveTemplateButton;

  @FindBy(id = "vn")
  protected WebElementFacade summaryName;

  @FindBy(id = "agsnt")
  protected WebElementFacade saveAsANewTemplate;

  @FindBy(id = "rpt-add-summary-tab")
  protected WebElementFacade addSummaryTab;

  @FindBy(id = "asas")
  protected WebElementFacade addSummaryButton;

  @FindBy(xpath = "//span[@ng-show='grid.options.totalItems > 0']//b[2]")
  protected WebElementFacade totalResults;

  @FindBy(id = "f")
  protected WebElementFacade filterSymbol;

  @FindBy(id = "sortColDropdown")
  protected WebElementFacade sortColumnDropDown;

  @FindBy(id = "drop_columns")
  protected WebElementFacade filterDropdown;

  @FindBy(id = "drop_directions")
  protected WebElementFacade sortDropDirections;

  @FindBy(id = "drop_filteroperators")
  protected WebElementFacade filterPresetDropDown;

  @FindBy(id = "drop_columnvalues")
  protected WebElementFacade dropdownMenuButton;

  @FindBy(id = "c802")
  protected WebElementFacade customerTypeDropdownMenu;

  @FindBy(xpath = "//input[@type='text'][1]")
  protected WebElementFacade valueTextBox;

  @FindBy(xpath = ".//span[text()=' Add Filters']")
  protected WebElementFacade addFiltersButton;

  @FindBy(xpath = ".//button[text()='Apply']")
  protected WebElementFacade applyButton;

  @FindBy(xpath = ".//span[text()='OK']")
  protected WebElementFacade okButton;

  @FindBy(xpath = ".//a[text()='Delete']")
  protected WebElementFacade deleteMenuItem;

  @FindBy(id = "slc")
  protected WebElementFacade cancelButton;

  @FindBy(xpath = ".//a[contains(text(), 'Starts with')]")
  protected WebElementFacade startsWithValue;

  @FindBy(xpath = ".//a[contains(text(), 'Equal to')]")
  protected WebElementFacade equalToValue;

  @FindBy(
      xpath = "//div[contains(@class, 'active')]//div[contains(@class, 'rpt-grid-header-text')]")
  private List<WebElementFacade> reportGridHeaders;

  @FindBy(xpath = "//div[contains(@class, 'active')]//div[contains(@class, 'ui-grid-row')]")
  private List<WebElementFacade> reportGridRows;

  @FindBy(id = "vwadd-summary")
  protected WebElementFacade summaryPage;

  private static String TABLE_CELL_XPATH = ".//div[contains(@class, 'rpt-ui-grid-cell-content')]";
  protected static final String DETAIL_VIEW_TAB_ID = "vw1";
  protected static final int DETAIL_VIEW_TAB_VIEWPORT_INDEX = 0;
  protected static final int TAB_VIEWPORT_INDEX = 1;

  protected WebElementFacade getColumnWebElementFacade(String columnName) {
    return findBy("#ch" + columnName);
  }

  public void scrollToElement(WebElement element) {
    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
  }

  public void horizontalScroll(WebElement element) {
    WebElementFacade activeTabContent =
        find(By.id(Serenity.sessionVariableCalled("activeTabContentId")));
    int viewportIndex = DETAIL_VIEW_TAB_VIEWPORT_INDEX;
    if (!activeTabContent.getAttribute("id").equals(DETAIL_VIEW_TAB_ID)) {
      viewportIndex = TAB_VIEWPORT_INDEX;
    }
    int scrollX = element.getLocation().x;
    String script =
        String.format(
            "document.getElementById('%s').getElementsByClassName('ui-grid-viewport')[%d].scrollLeft = %d",
            activeTabContent.getAttribute("id"), viewportIndex, scrollX);
    JavascriptExecutor executor = ((JavascriptExecutor) getDriver());
    executor.executeScript(script);
    scrollToElement(element);
  }

  public void selectOptionUnderFilters(String option) {
    throw new RuntimeException("Select Option under filters not implemented in ReportBasePage");
  }

  public List<Map<String, String>> getReportGridDataAsMaps() {
    Instant start = Instant.now();

    // Shrink the web page, so all cells are in the DOM
    JavascriptExecutor executor = ((JavascriptExecutor) getDriver());
    executor.executeScript("document.body.style.zoom = '50%'");
    Utilities.simpleSleep(200);

    // Get Header strings
    List<String> headerStrings = new ArrayList<>();
    for (WebElement gridHeader : reportGridHeaders) {
      headerStrings.add(gridHeader.getText());
    }

    // Get strings for Cells in each Row
    List<List<String>> rowStrings = new ArrayList<>();
    for (WebElement gridRow : reportGridRows) {
      List<WebElement> cellElements = gridRow.findElements(By.xpath(TABLE_CELL_XPATH));
      List<String> rowString = new ArrayList<>();
      for (WebElement cellElement : cellElements) {
        rowString.add(cellElement.getText());
      }
      rowStrings.add(rowString);
    }

    executor.executeScript("document.body.style.zoom = '100%'");

    List<Map<String, String>> rows = new ArrayList<>();
    for (List<String> cellStrings : rowStrings) {
      if (cellStrings.size() != headerStrings.size()) {
        System.out.println("Header Strings - size - " + headerStrings.size());
        System.out.println("Cell Strings - size - " + cellStrings.size());
        throw new RuntimeException("The following grid text could not be parsed:\n" + cellStrings);
      }
      // Create map using ordered list of headers and ordered list of values for current row, then
      // add to list of row maps
      Map<String, String> row = new HashMap<>();
      for (int i = 0; i < headerStrings.size(); i++) {
        row.put(headerStrings.get(i), cellStrings.get(i));
      }
      rows.add(row);
    }

    Instant end = Instant.now();
    System.out.println("Time taken: " + Duration.between(start, end).toMillis() + " milliseconds");
    return rows;
  }

  public void clickOnApply() {
    applyButton.click();
  }

  public void clickOnOkButton() {
    okButton.click();
  }

  public void clickOnCancelButton() {
    cancelButton.click();
  }

  public void clickOnBackArrow() {
    backArrow.click();
  }

  public void clickOnTemplatesActionMenu() {
    templatesActionMenu.click();
  }

  public void setFilterPresetToStartWith() {
    filterPresetDropDown.click();
    startsWithValue.click();
  }

  public void setFilterStartsWith(String value) {
    valueTextBox.click();
    valueTextBox.sendKeys(value);
  }

  public void clickOnFilterSymbol() {
    filterSymbol.click();
  }

  public void clickOnSortSymbol() {
    sortColumnDropDown.click();
  }

  public void clickOnAddFiltersButton() {
    addFiltersButton.click();
  }

  public void clickOnDeleteMenuItem() {
    deleteMenuItem.click();
  }

  public void clickOnAddSummaryTab() {
    addSummaryTab.click();
  }

  public void clickOnAddSummaryButton() {
    addSummaryButton.click();
  }

  public void selectSummaryCheckbox(String label) {
    List<WebElementFacade> checkBoxes =
        summaryPage.thenFindAll("//label[contains(text(), " + "'" + label + "')]");
    for (WebElementFacade checkBox : checkBoxes) {
      if (!checkBox.isSelected() && checkBox.getText().equals(label)) {
        checkBox.click();
      }
    }
  }

  public void setSummaryName(String label) {
    summaryName.typeAndEnter(label);
  }

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
    Utilities.setActiveTabContentIdSessionVariable();
    Utilities.waitForActiveTabLoadingSpinner();
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

  private WebElement findColumnByColumnNameInTab(WebElement element, String value) {
    return element.findElement(By.xpath(String.format(".//*[@id='ch%s']", value)));
  }

  public boolean validateColumnIsDisplayedInTab(String columnName) {
    WebElement activeTab = find(By.id(Serenity.sessionVariableCalled("activeTabContentId")));
    WebElement column = findColumnByColumnNameInTab(activeTab, columnName);
    horizontalScroll(column);
    return column.isDisplayed();
  }

  public boolean isColumnDisplayed(String columnName) {
    return getColumnWebElementFacade(columnName).isDisplayed();
  }

  public void selectOptionUnderSort(String option) {
    sortDropDirections.click();
    Utilities.simpleSleep(1000);
    findBy(".//a[contains(text(), '" + option + "')]").click();
  }

  public void selectActionButton() {
    actionsButton.click();
  }

  public void saveAsANewTemplate() {
    saveAsANewTemplate.click();
  }

  public void saveNewTemplate() {
    saveTemplateButton.click();
    Utilities.simpleSleep(1000);
  }

  public void enterANewTemplateName(String name) {
    saveTemplateName.typeAndEnter(name);
  }

  public void searchForTemplate(String name) {
    templateSearchText.typeAndEnter(name);
  }
}
