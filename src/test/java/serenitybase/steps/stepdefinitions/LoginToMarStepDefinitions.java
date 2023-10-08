package serenitybase.steps.stepdefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import net.thucydides.core.annotations.Steps;
import serenitybase.steps.teststeps.LoginToMarTestSteps;

public class LoginToMarStepDefinitions {
  @Steps LoginToMarTestSteps loginToMarTestSteps;

  @ParameterType("SuperAdmin|AgencyAdmin|RegularUser")
  public String user(String user) {
    return user;
  }

  @Given("the user opens the NBA warrior home page")
  public void the_user_opens_the_nba_warrior_homepage() {
    loginToMarTestSteps.openHomePage();
  }

  @When("the user enters below details to sign up pop up if exists")
  public void the_user_enters_below_details_to_sign_up_pop_up_if_exists(
      Map<String, String> userInfo) {
    loginToMarTestSteps.enterDetailsToSignUpPopUp(userInfo);
  }

  @When("the user accepts the cookies if exists")
  public void the_user_accepts_the_cookies_if_exists() {
    loginToMarTestSteps.clickOnAcceptCookiesIfExist();
  }

  @When("^the user verifies below NBA teams location$")
  public void the_user_verifies_below_NBA_teams_location(List<String> nBATeamLocation) {
    loginToMarTestSteps.verifyNBATeamLocation(nBATeamLocation);
  }

  @When("^the user navigates back to homepage$")
  public void the_user_navigates_back_to_homepage() {
    loginToMarTestSteps.clickOnHomePageIcon();
  }

  @Then("the user verifies that below tabs are displayed in the NBA homepage")
  public void the_user_verifies_that_below_tabs_are_displayed_in_the_n_b_a_homepage(
      List<String> nbaHomePageTabs) {
    loginToMarTestSteps.verifyNBAHomePageTabs(nbaHomePageTabs);
  }

  @Then(
      "the user verifies that below tabs are displayed in the top navigation bar of NBA warrior homepage")
  public void
      the_user_verifies_that_below_tabs_are_displayed_in_the_top_navigation_bar_of_n_b_a_warrior_homepage(
          List<String> nbaHomePageTopNavigationTabs) {
    loginToMarTestSteps.verifyTopNavigationBarNBAHomePageTabsTitle(nbaHomePageTopNavigationTabs);
  }

  @And(
      "^the user clicks on '(Tickets|Schedule|Team|Shop|Chase Center|My Warriors Account)' tab of NBA warrior homepage$")
  public void the_user_clicks_on_desired_tab_of_n_b_a_warrior_homepage(String tabName) {
    loginToMarTestSteps.clickOnDesiredTabInNBAHomepage(tabName);
  }

  @Then(
      "^the user verifies that '(Tickets|Schedule|Roster|Shop|Chase Center|My Warriors Account)' page is loaded$")
  public void the_user_verifies_that_schedule_page_is_loaded(String pageTitle) {
    loginToMarTestSteps.verifyPageUrlText(pageTitle.toLowerCase());
  }

  @Then("the user verifies the number of broken link in the NBA warrior homepage")
  public void the_user_verifies_the_number_of_broken_link_in_the_n_b_a_warrior_homepage() {
    loginToMarTestSteps.getNumberOfBrokenLink();
  }

  @And("the user closes the sign up pop up if exists")
  public void the_user_closes_the_sign_up_pop_up_if_exists() {
    loginToMarTestSteps.closeSignUpPopUpIfExists();
  }

  @Then(
      "the user verifies that NBA Home page should have total {int} teams city added in the Teams dropdown")
  public void
      the_user_verifies_that_n_b_a_home_page_should_have_total_teams_city_added_in_the_teams_dropdown(
          int nbaTeamCityCount) {
    loginToMarTestSteps.verifyNBATeamLocationCount(nbaTeamCityCount);
  }

  @And("the user saves current items count and clicks on Load More button")
  public void the_user_saves_current_items_count_and_clicks_on_load_more_button() {
    loginToMarTestSteps.saveCurrentItemsCountInNBAHomePage();
    loginToMarTestSteps.clickOnLoadMoreButton();
  }

  @Then(
      "the user verifies that {int} more new items are loaded in the bottom of NBA warrior homepage")
  public void
      the_user_verifies_that_more_new_items_are_loaded_in_the_bottom_of_n_b_a_warrior_homepage(
          int newItemsCount) {
    loginToMarTestSteps.verifyNewlyLoadedItemsCount(newItemsCount);
  }
}
