package serenitybase.pages.mar;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.List;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import serenitybase.helpers.Utilities;

public class BasePage extends PageObject {

  @FindBy(id = "shc")
  public WebElementFacade showHideIconButton;

  @FindBy(xpath = "//span[@ng-show='grid.options.totalItems > 0']//b[2]")
  public WebElementFacade totalResults;

  @FindBy(id = "chDivision")
  public WebElementFacade divisionDetailView;

  @FindBy(id = "chBranch")
  public WebElementFacade branchDetailView;

  @FindBy(id = "chDepartment")
  public WebElementFacade departmentDetailView;

  @FindBy(id = "chGroup")
  public WebElementFacade groupDetailView;

  @FindBy(id = "f")
  public WebElementFacade filterSymbol;

  @FindBy(id = "drop_columns")
  public WebElementFacade filterDropdown;

  @FindBy(id = "drop_filteroperators")
  public WebElementFacade filterPresetDropDown;

  @FindBy(id = "drop_columnvalues")
  public WebElementFacade dropdownMenuButton;

  @FindBy(id = "c802")
  public WebElementFacade customerTypeDropdownMenu;

  @FindBy(xpath = "//input[@type='text'][1]")
  public WebElementFacade valueTextBox;

  @FindBy(id = "Active CustomerInactive")
  public WebElementFacade activeCustomerColumn;

  @FindBy(xpath = ".//span[text()=' Add Filters']")
  public WebElementFacade addFiltersButton;

  @FindBy(xpath = ".//button[text()='Apply']")
  public WebElementFacade applyButton;

  @FindBy(xpath = ".//a[contains(text(), 'Starts with')]")
  public WebElementFacade startsWithValue;

  @FindBy(xpath = ".//a[contains(text(), 'Equal to')]")
  public WebElementFacade equalToValue;

  @FindBy(
      xpath = "//div[contains(@class, 'active')]//div[contains(@class, 'rpt-grid-header-text')]")
  private List<WebElementFacade> reportGridHeaders;

  @FindBy(xpath = "//div[contains(@class, 'active')]//div[contains(@class, 'ui-grid-row')]")
  private List<WebElementFacade> reportGridRows;

  private static String TABLE_CELL_XPATH = ".//div[contains(@class, 'rpt-ui-grid-cell-content')]";
  protected static final String DETAIL_VIEW_TAB_ID = "vw1";
  protected static final int DETAIL_VIEW_TAB_VIEWPORT_INDEX = 0;
  protected static final int TAB_VIEWPORT_INDEX = 1;

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
}
