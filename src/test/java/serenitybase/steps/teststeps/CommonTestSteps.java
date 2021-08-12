package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import serenitybase.Utilities;
import serenitybase.selenium.pages.BasePage;

/**
 * The Serenity/Cucumber Test Steps class that handles the actual test logic. It does not handle the
 * driving logic that PageObjects or similar do and generally doesn't interact with their specific
 * objects such as WebElementFacade or RestAssured's Response, though it can, such as in the case of
 * RestAssured RequestSpecification objects. This class handles steps common to all aspects of
 * Benefit Point.
 *
 * @author AJ Johnson
 * @version 1.0.0
 */
public class CommonTestSteps extends ScenarioSteps {

  BasePage basePage;

  @Step
  public void openPage(String url) {
    basePage.goToUrl(url);
  }

  @Step
  public void waitForSeconds(int seconds) {
    Utilities.simpleSleep(seconds * 1000);
  }

  @Step
  public void verifyStatusCode(int expectedStatusCode) {
    Response response = Serenity.sessionVariableCalled("response");
    assertThat(response.getStatusCode()).isEqualTo(expectedStatusCode);
  }

  @Step
  public void maximizeWindow() {
    basePage.maximizeWindowSize();
  }

  @Step
  public void setWindowSize(int width, int height) {
    basePage.setWindowWidthAndHeight(width, height);
  }

  @Step("Close the browser")
  public void closeBrowser() {
    basePage.closeBrowser();
  }
}
