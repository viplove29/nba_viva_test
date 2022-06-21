package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.When;
import java.util.List;
import net.thucydides.core.annotations.Steps;
import org.jetbrains.annotations.NotNull;
import serenitybase.steps.teststeps.SharedReportTestSteps;

public class ActiveClientsReportStepDefinitions {
  @Steps private SharedReportTestSteps sharedReportTestSteps;

  @When("the user clicks the following checkboxes on the Active Client summary page")
  public void the_user_clicks_the_following_checkboxes_on_the_active_client_summary_page(@NotNull List<String> labels) {
    for (String label : labels) {
      switch (label) {
        case "Branch":
          sharedReportTestSteps.clickOnSummaryCheckbox("checkbox2");
          break;
        case "Customer Executive":
          sharedReportTestSteps.clickOnSummaryCheckbox("checkbox4");
          break;
        case "Customer":
          sharedReportTestSteps.clickOnSummaryCheckbox("checkbox6");
          break;
        case "Policy Count":
          sharedReportTestSteps.clickOnSummaryCheckbox("summary1");
          break;
        default:
          throw new IllegalArgumentException(String.format("'%s' is not valid.", label));
      }
    }
  }
}
