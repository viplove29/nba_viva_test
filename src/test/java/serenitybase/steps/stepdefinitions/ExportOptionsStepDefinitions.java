package serenitybase.steps.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.ExportOptionsTestSteps;

public class ExportOptionsStepDefinitions {
  @Steps private ExportOptionsTestSteps exportOptionsTestSteps;

  @When("use user exports the report with the following options")
  public void use_user_exports_the_report_with_the_following_options(DataTable options) {
    exportOptionsTestSteps.selectExportOptions(options.asList());
  }
}
