package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.When;
import java.util.List;
import net.thucydides.core.annotations.Steps;
import org.jetbrains.annotations.NotNull;
import serenitybase.helpers.Utilities;
import serenitybase.steps.teststeps.SharedReportTestSteps;

public class AddSummaryViewStepDefinitions {
  @Steps private SharedReportTestSteps sharedReportTestSteps;

  @When("the user selects the following checkboxes on the summary page")
  public void the_user_selects_the_following_checkboxes_on_the_summary_page(
      @NotNull List<String> labels) {
    for (String label : labels) {
      sharedReportTestSteps.selectSummaryCheckbox(label);
    }
  }

  @When("the user names the summary {string}")
  public void the_user_names_the_summary(@NotNull String name) {
    Utilities.simpleSleep(500);
    sharedReportTestSteps.enterSummaryName(name);
  }

  @When("the user clicks the add summary button")
  public void the_user_clicks_the_add_summary_button() {
    Utilities.simpleSleep(1000);
    sharedReportTestSteps.clickOnAddSummaryButton();
  }
}
