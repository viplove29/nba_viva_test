package serenitybase.steps.teststeps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.ActivityListPage;
import serenitybase.pages.mar.MarHomePage;

public class ActivityTestSteps {

    private ActivityListPage activityListPage;

    @Step
    public void expandCenter() {
        activityListPage.clickCenterSection();
    }
}
