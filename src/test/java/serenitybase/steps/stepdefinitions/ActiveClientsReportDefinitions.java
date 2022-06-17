package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.When;
import java.util.List;
import net.thucydides.core.annotations.Steps;
import org.jetbrains.annotations.NotNull;
import serenitybase.steps.teststeps.SharedReportTestSteps;

public class ActiveClientsReportDefinitions {
  @Steps private SharedReportTestSteps sharedReportTestSteps;

  @When("the user clicks the following checkboxes on the Active Client summary page")
  public void the_user_verifies_tab_contains_the_following_columns(@NotNull List<String> labels) {
    for (String label : labels) {
      switch (label) {
        case "Branch":
          sharedReportTestSteps.clickOnSummaryCheckbox("checkbox2");
        case "Customer Executive":
          sharedReportTestSteps.clickOnSummaryCheckbox("checkbox4");
        case "Customer":
          sharedReportTestSteps.clickOnSummaryCheckbox("checkbox6");
        case "Policy Count":
          sharedReportTestSteps.clickOnSummaryCheckbox("summary1");
      }
    }
  }
}
