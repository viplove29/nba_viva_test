package serenitybase.steps.stepdefinitions;


import io.cucumber.java.en.When;
import java.util.List;
import net.thucydides.core.annotations.Steps;
import org.jetbrains.annotations.NotNull;
import serenitybase.helpers.Utilities;
import serenitybase.steps.teststeps.ActivityTestSteps;
import serenitybase.steps.teststeps.SharedReportTestSteps;

public class ActivityListStepDefinitions {

    @Steps private ActivityTestSteps activityListTestSteps;


    @When("the user expands the center dropdown")
    public void the_user_generates_the_report_with_a_random_name() {

        activityListTestSteps.expandCenter();
        Utilities.simpleSleep(5000);
    }
}
