package serenitybase.pages.mar;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.commons.lang3.StringUtils;
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

  @FindBy(xpath = ".//span[text()=' Edit']")
  protected WebElementFacade editFiltersButton;

  @FindBy(xpath = ".//button[text()='Apply']")
  protected WebElementFacade applyButton;

  @FindBy(xpath = ".//span[text()='OK']")
  protected WebElementFacade okButton;

  @FindBy(xpath = ".//a[text()='Delete']")
  protected WebElementFacade deleteMenuItem;

  @FindBy(id = "slc")
  protected WebElementFacade cancelButton;

  @FindBy(xpath = "//div[contains(@ng-click, 'paginationApi.nextPage()')]")
  protected WebElementFacade nextPageButton;

  @FindBy(xpath = ".//a[contains(text(), 'Starts with')]")
  protected WebElementFacade startsWithValue;

  @FindBy(xpath = ".//a[contains(text(), 'Equal to')]")
  protected WebElementFacade equalToValue;

  @FindBy(xpath = ".//a[contains(text(), 'Range')]")
  protected WebElementFacade rangeValue;

  @FindBy(xpath = "//date-popup[contains(@control, 'minControl')]//input")
  protected WebElementFacade rangeFrom;

  @FindBy(xpath = "//date-popup[contains(@control, 'maxControl')]//input")
  protected WebElementFacade rangeTo;

  @FindBy(
      xpath = "//div[contains(@class, 'active')]//div[contains(@class, 'rpt-grid-header-text')]")
  private List<WebElementFacade> reportGridHeaders;

  @FindBy(xpath = "//div[contains(@class, 'active')]//div[contains(@class, 'ui-grid-row')]")
  private List<WebElementFacade> reportGridRows;

  @FindBy(id = "vwadd-summary")
  protected WebElementFacade summaryPage;

  @FindBy(xpath = ".//div[contains(@class,'rpt-grid-header-text')]/span")
  protected List<WebElementFacade> displayedColumns;

  private static String TABLE_CELL_XPATH =
      ".//div[contains(@class, 'rpt-ui-grid-cell-content')] | .//a[contains(@class, 'ng-binding')]";
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
    int scrollX = element.getLocation().x + (element.getRect().width / 2);
    String script =
        String.format(
            "document.getElementById('%s').getElementsByClassName('ui-grid-viewport')[%d].scrollLeft = %d",
            activeTabContent.getAttribute("id"), viewportIndex, scrollX);
    JavascriptExecutor executor = ((JavascriptExecutor) getDriver());
    executor.executeScript(script);
    scrollToElement(element);
  }

  public void scrollTheCurrentTabHorizontallyToTheEnd() {
    WebElementFacade activeTabContent =
        find(By.id(Serenity.sessionVariableCalled("activeTabContentId")));
    int viewportIndex = DETAIL_VIEW_TAB_VIEWPORT_INDEX;
    if (!activeTabContent.getAttribute("id").equals(DETAIL_VIEW_TAB_ID)) {
      viewportIndex = TAB_VIEWPORT_INDEX;
    }
    int tabWidth = activeTabContent.getSize().width;
    String script =
        String.format(
            "document.getElementById('%s').getElementsByClassName('ui-grid-viewport')[%d].scrollLeft = %d",
            activeTabContent.getAttribute("id"), viewportIndex, tabWidth);
    JavascriptExecutor executor = ((JavascriptExecutor) getDriver());
    executor.executeScript(script);
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

    // Wait till first element is not empty or up to 10 seconds
    int count = 0;
    do {
      WebElement firstElement = reportGridRows.get(0).findElement(By.xpath(TABLE_CELL_XPATH));
      if (!firstElement.getText().isEmpty()) {
        break;
      } else {
        Utilities.simpleSleep(1000);
        count++;
      }
    } while (count < 10);

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
    int counter = 0;
    do {
      try {
        okButton.click();
        return;
      } catch (Exception e) {
        counter++;
        Utilities.simpleSleep(2000);
      }
    } while (counter < 5);
    System.out.println("No OK button to click after 5 tries");
  }

  public void clickOnCancelButton() {
    cancelButton.click();
  }

  public void clickOnNextPageButton() {
    nextPageButton.click();
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
    try {
      addFiltersButton.click();
    } catch (Exception e) {
      editFiltersButton.click();
    }
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
    showHideIconButton.click();
  }

  public void selectMultipleOptionsUnderHideShowIcon(List<String> options, Boolean show) {
    showHideIconButton.click();
    if (show) {
      selectMultipleOptionsToShowOrHide(
          options, String.format("./ancestor::li[contains(@class, 'rmedMenuItem')]"));
    } else {
      selectMultipleOptionsToShowOrHide(
          options, String.format("./ancestor::li[contains(@class, 'rmMenuItem')]"));
    }
    showHideIconButton.click();
  }

  private void selectMultipleOptionsToShowOrHide(List<String> options, String listItemXpath) {
    options.forEach(
        (option) -> {
          WebElement listOptionElement = find(String.format("//*[text()='%s']", option));
          if (listOptionElement.findElements(By.xpath(listItemXpath)).size() > 0) {
            listOptionElement.click();
          }
        });
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

  public void setFilterPresetToRange() {
    filterPresetDropDown.click();
    rangeValue.click();
  }

  public void setFilterRangeFromAndTo(String from, String to) {
    rangeFrom.click();
    rangeFrom.sendKeys(from);
    rangeTo.click();
    rangeTo.sendKeys(to);
  }

  private WebElement findColumnByColumnNameInTab(WebElement element, String value) {
    return element.findElement(By.xpath(String.format(".//*[@id='ch%s']", value)));
  }

  public boolean validateColumnIsDisplayedInTab(String columnName) {
    try {
      WebElement activeTab = find(By.id(Serenity.sessionVariableCalled("activeTabContentId")));
      WebElement column = findColumnByColumnNameInTab(activeTab, columnName);
      horizontalScroll(column);
      return column.isDisplayed();
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public List<String> getAllColumnsDisplayedInCurrentTabByUsingHorizontalScroll() {
    Set<String> allDisplayedColumns = new HashSet<String>();
    WebElement activeTab = find(By.id(Serenity.sessionVariableCalled("activeTabContentId")));
    int viewportIndex = DETAIL_VIEW_TAB_VIEWPORT_INDEX;
    if (!activeTab.getAttribute("id").equals(DETAIL_VIEW_TAB_ID)) {
      viewportIndex = TAB_VIEWPORT_INDEX;
    }
    JavascriptExecutor executor = ((JavascriptExecutor) getDriver());
    String scrollWidthScript =
        String.format(
            "return document.getElementById('%s').getElementsByClassName('ui-grid-viewport')[%d].scrollWidth;",
            activeTab.getAttribute("id"), viewportIndex);
    int scrollWidth = Long.valueOf((long) executor.executeScript(scrollWidthScript)).intValue();
    String clientWidthScript =
        String.format(
            "return document.getElementById('%s').getElementsByClassName('ui-grid-viewport')[%d].clientWidth;",
            activeTab.getAttribute("id"), viewportIndex);
    int clientWidth = Long.valueOf((long) executor.executeScript(clientWidthScript)).intValue();
    int scrollLength = clientWidth / 3;
    do {
      List<String> displayedColumnsList =
          reportGridHeaders.stream()
              .map(
                  colElement -> {
                    return colElement.getText().trim();
                  })
              .collect(Collectors.toList());
      allDisplayedColumns.addAll(displayedColumnsList);
      scrollLength = scrollLength + 400;
      String scrollScript =
          String.format(
              "document.getElementById('%s').getElementsByClassName('ui-grid-viewport')[%d].scrollLeft = %d;",
              activeTab.getAttribute("id"), viewportIndex, scrollLength);
      executor.executeScript(scrollScript);
    } while (scrollLength <= scrollWidth);
    return allDisplayedColumns.stream()
        .filter(StringUtils::isNotBlank)
        .collect(Collectors.toList());
  }

  public boolean validateColumnIsNotDisplayedInTab(String columnName) {
    WebElement activeTab = find(By.id(Serenity.sessionVariableCalled("activeTabContentId")));
    List<WebElement> columns =
        activeTab.findElements(By.xpath(String.format(".//*[@id='ch%s']", columnName)));
    if (columns.size() == 0) {
      return true;
    } else {
      return false;
    }
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
