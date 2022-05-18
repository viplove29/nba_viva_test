package serenitybase.pages.mar;

public class SharedReportPage extends ReportBasePage {

  @Override
  public void selectOptionUnderFilters(String option) {
    filterDropdown.click();
    findBy(".//a[contains(text(), '" + option + "')]").click();
  }
}
