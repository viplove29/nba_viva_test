package serenitybase.pages.mar;

import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;

public class SharedReportPage extends ReportBasePage {

  @Override
  public void selectOptionUnderFilters(String option) {
    filterDropdown.click();
    ListOfWebElementFacades allFilterOptionList =
        findAll(
            "//ul[contains(@class, 'rpt-add-filter-dropdown')]//a[contains(text(), '"
                + option
                + "')]");
    for (WebElementFacade selectedFilterOption : allFilterOptionList) {
      try {
        if (selectedFilterOption.isClickable()) {
          selectedFilterOption.click();
          return;
        }
      } catch (Exception ex) {
        System.out.println("Could not find menu item");
      }
    }
    throw new RuntimeException("Drop down element not found");
  }

  public void selectOptionForSortColumns(String column) {
    sortDropdown.click();
    ListOfWebElementFacades sortOptions =
        findAll(
            "//ul[contains(@class, 'rpt-sortpanel-columns')]//a[contains(text(), '"
                + column
                + "')]");
    for (WebElementFacade sortColumn : sortOptions) {
      try {
        if (sortColumn.isClickable()) {
          sortColumn.click();
          return;
        }
      } catch (Exception ex) {
        System.out.println("Could not find menu item");
      }
    }
    throw new RuntimeException(column + " sort column not found");
  }
}
