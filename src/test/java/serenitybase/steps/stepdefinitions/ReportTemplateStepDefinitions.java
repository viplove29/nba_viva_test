package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.ReportTemplateTestSteps;

public class ReportTemplateStepDefinitions {
  @Steps private ReportTemplateTestSteps reportTemplateTestSteps;

  @Given("the user selects {string} Report Template")
  public void the_user_selects_active_clients_report_template(String reportTemplate) {
    reportTemplateTestSteps.selectReportTemplate(reportTemplate);
  }

  @When("the user generates the report with a random name")
  public void the_user_generates_the_report_with_a_random_name() {
    reportTemplateTestSteps.generateReportWithRandomName();
  }

  @When("the user clicks on the Quick Actions {string} button")
  public void the_user_clicks_on_the_quick_actions_button(String reportType) {
    switch (reportType) {
      case "XLS":
        reportTemplateTestSteps.excelQuickActions();
        break;
      case "CSV":
        reportTemplateTestSteps.csvQuickActions();
        break;
      default:
        throw new IllegalArgumentException(
            String.format("Report type %s not supported", reportType));
    }
  }

  @When("the user navigates to the generated report")
  public void the_user_navigates_to_the_generated_report() {
    reportTemplateTestSteps.navigateToGeneratedReport();
  }

  @When("the user opens the downloaded excel report")
  public void the_user_opens_the_downloaded_excel_report() {
    reportTemplateTestSteps.openDownloadedExcelReport();
  }

  @Then("the excel report has the same number of rows as the web report")
  public void the_excel_report_has_the_same_number_of_rows_as_the_web_report() {
    reportTemplateTestSteps.verifyNumberOfRows();
  }
}
