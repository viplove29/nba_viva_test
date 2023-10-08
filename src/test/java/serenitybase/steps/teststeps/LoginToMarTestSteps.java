package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import serenitybase.helpers.Utilities;
import serenitybase.pages.vsso.LoginPage;

public class LoginToMarTestSteps {
  private LoginPage loginPage;

  @Step
  public void enterDetailsToSignUpPopUp(Map<String, String> userInfo) {
    loginPage.enterDetailsToSignUpPopUp(
        userInfo.get("First Name"),
        userInfo.get("Last Name"),
        userInfo.get("Email"),
        userInfo.get("ZipCode"));
  }

  @Step
  public void closeSignUpPopUpIfExists() {
    loginPage.closeSignUpPopUpIfExists();
  }

  public void clickOnAcceptCookiesIfExist() {
    loginPage.clickOnAcceptCookiesIfExist();
  }

  @Step
  public void openHomePage() {
    loginPage.open();
  }

  @Step
  public void verifyNBATeamLocation(List<String> nBATeamLocation) {
    System.out.println(loginPage.getAllTeamList());
    assertThat(loginPage.getAllTeamList()).containsAll(nBATeamLocation);
  }

  @Step
  public void verifyNBATeamLocationCount(int count) {
    System.out.println(loginPage.getAllTeamListCount());
    assertThat(loginPage.getAllTeamListCount()).isEqualTo(count);
  }

  @Step
  public void clickOnHomePageIcon() {
    loginPage.clickOnHomePageIcon();
  }

  @Step
  public void verifyNBAHomePageTabs(List<String> nbaHomePageTabs) {
    System.out.println(loginPage.getAllNBAHomePageTabs());
    assertThat(loginPage.getAllNBAHomePageTabs()).containsAll(nbaHomePageTabs);
  }

  @Step
  public void verifyTopNavigationBarNBAHomePageTabsTitle(
      List<String> nbaHomePageTopNavigationTabs) {
    System.out.println(loginPage.getTopNavigationBarTabsName());
    assertThat(loginPage.getTopNavigationBarTabsName()).containsAll(nbaHomePageTopNavigationTabs);
  }

  @Step
  public void verifyPageUrlText(String urlText) {
    System.out.println(loginPage.getCurrentURL());
    Utilities.simpleSleep(5000);
    assertThat(loginPage.getCurrentURL()).contains(urlText);
  }

  @Step
  public void clickOnDesiredTabInNBAHomepage(String nbaHomePage) {
    System.out.println("clicking on link");
    Utilities.simpleSleep(2000);
    loginPage.clickOnDesiredTabInNBAHomepage(nbaHomePage);
  }

  @Step
  public void getNumberOfBrokenLink() {
    System.out.println("trying to get broken links now");
    Utilities.simpleSleep(1000);
    loginPage.getNumberOfBrokenLink();
  }

  @Step
  public void saveCurrentItemsCountInNBAHomePage() {
    int currentArticleCount = loginPage.getCurrentLoadedArticlesCount();
    System.out.println("Saves count : " + loginPage.getCurrentLoadedArticlesCount());
    Serenity.setSessionVariable("currentArticleCount").to(currentArticleCount);
  }

  @Step
  public void clickOnLoadMoreButton() {
    loginPage.clickOnLoadMoreButton();
  }

  @Step
  public void verifyNewlyLoadedItemsCount(int count) {
    System.out.println("Latest count : " + loginPage.getCurrentLoadedArticlesCount());
    Utilities.simpleSleep(1000);
    int latestCount = loginPage.getCurrentLoadedArticlesCount();
    System.out.println("Previous count before clicking Load More : " + Serenity.sessionVariableCalled("currentArticleCount"));
    int previousCount = Serenity.sessionVariableCalled("currentArticleCount");
    int newlyAddedItemsCount = latestCount - previousCount;
    assertThat(newlyAddedItemsCount).isEqualTo(count);
  }
}
