package serenitybase.pages.mar;

import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

public class ExportOptionsModal extends PageObject {
  @FindBy(xpath = "//input[@ng-model='exportAs']")
  private List<WebElementFacade> fileFormatGroup;

  @FindBy(xpath = "//input[@ng-model='Addons']")
  private List<WebElementFacade> addonsGroup;

  @FindBy(xpath = "//input[@ng-model='exportVisibleColumnsOnly']")
  private List<WebElementFacade> columnsGroup;

  @FindBy(xpath = "//input[@ng-model='exportFilteredData']")
  private List<WebElementFacade> filtersGroup;

  @FindBy(xpath = "//input[@ng-model='IsCoverIncluded']")
  private WebElementFacade includeCoverSheetCheckbox;

  @FindBy(xpath = "//button[@id='sls']//span")
  private WebElementFacade exportButton;

  public void selectFileFormat(String option) {
    selectRadioOption(fileFormatGroup, option.toLowerCase());
  }

  public void selectPermissions(String option) {
    selectRadioOption(addonsGroup, option);
  }

  public void selectColumns(String option) {
    selectRadioOption(columnsGroup, String.valueOf(option.equals("Visible") ? 1 : 0));
  }

  public void selectFilters(String option) {
    selectRadioOption(filtersGroup, String.valueOf(option.equals("Export Filtered Data") ? 1 : 0));
  }

  public void includeCoverSheet(String include) {
    if (Boolean.parseBoolean(include)) {
      //    Selenium is unable to target the checkbox for some reason
      JavascriptExecutor executor = (JavascriptExecutor) getDriver();
      executor.executeScript("document.getElementById('rpt-incudecover-check-excel').click()");
    }
  }

  public void export() {
    exportButton.click();
  }

  private void selectRadioOption(List<WebElementFacade> group, String value) {
    if (value == null) return;
    //  Use JS to click because the input is covered by the label
    JavascriptExecutor executor = (JavascriptExecutor) getDriver();
    group.stream()
        .filter(radioButton -> value.equals(radioButton.getAttribute("value")))
        .findFirst()
        .ifPresent(e -> executor.executeScript("arguments[0].click()", e));
  }
}
