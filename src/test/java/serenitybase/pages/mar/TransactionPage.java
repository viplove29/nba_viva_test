package serenitybase.pages.mar;

import java.util.Arrays;
import java.util.List;

public class TransactionPage extends ReportBasePage {

  private final List<String> filterOptions =
      Arrays.asList(
          "Customer Name",
          "Policy Id",
          "Bill Method",
          "Billed Premium",
          "Business With Agency",
          "Cancel Reason",
          "Company Type",
          "Company Underwriter",
          "Customer Added Date",
          "Customer Number",
          "Estimated Revenue",
          "Estimated Revenue Percent",
          "Line of Business",
          "Parent Company",
          "Policy Branch",
          "Policy Broker",
          "Policy Department",
          "Policy Division",
          "Policy Effective Date",
          "Policy Expiration Date",
          "Policy Full Term Premium",
          "Policy Group",
          "Policy Notation",
          "Policy Number",
          "Policy Primary Executive",
          "Policy Primary Representative",
          "Policy Status",
          "Policy Type",
          "Premium",
          "Processed By",
          "Total Cost",
          "Transaction Description",
          "Transaction Effective Date",
          "Transaction Premium",
          "Transaction Source",
          "Transaction Type",
          "Writing Company",
          "Total Billed Premium",
          "Total Premium",
          "Transaction Count");

  public TransactionPage() {}

  @Override
  public void selectOptionUnderFilters(String option) {
    if (!filterOptions.contains(option)) {
      throw new IllegalArgumentException(String.format("%s filter option not supported", option));
    }
    filterDropdown.click();
    findBy(".//a[contains(text(), '" + option + "')]").click();
  }
}
