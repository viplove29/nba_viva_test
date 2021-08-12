/**
 * The Serenity/Cucumber Scenario Steps class that handles connecting the scenario steps in the
 * feature files and the test driving code for commonly used test steps that aren't specific to any
 * particular testing area. It does not perform any test logic, though it can perform setup logic
 * like getting values from a properties file.
 *
 * @author AJ Johnson
 * @author Charlie Mitchell
 * @version 1.0.0
 */
package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.CommonTestSteps;

public class CommonStepDefinitions {

  @Steps CommonTestSteps commonTestSteps;

  @Given("I navigate to {string}")
  public void i_navigate_to_the_url(String url) {
    commonTestSteps.openPage(url);
  }

  @Given("^I wait for '([^']*?)' seconds$")
  public void i_wait_for_seconds_seconds(int seconds) {
    commonTestSteps.waitForSeconds(seconds);
  }

  @Then("^the expected status code of '([^']*?)' matches the response status code$")
  public void the_expected_status_code_matches_the_response_status_code(int expectedStatusCode) {
    commonTestSteps.verifyStatusCode(expectedStatusCode);
  }

  @Given("^I maximize the window$")
  public void i_maximize_the_window() {
    commonTestSteps.maximizeWindow();
  }

  @Given("I set the window width to {int} and the height to be {int}")
  public void i_set_the_window_width_to_be_width_and_the_height_to_be_height(
      int width, int height) {
    commonTestSteps.setWindowSize(width, height);
  }

  @When("^I close the browser$")
  public void i_close_the_browser() {
    commonTestSteps.closeBrowser();
  }
}
