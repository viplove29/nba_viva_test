package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import serenitybase.helpers.Utilities;
import serenitybase.pages.vsso.NbaHomePage;

public class NbaTestSteps {
  private NbaHomePage nbaHomePage;

  @Step
  public void enterDetailsToSignUpPopUp(Map<String, String> userInfo) {
    nbaHomePage.enterDetailsToSignUpPopUp(
        userInfo.get("First Name"),
        userInfo.get("Last Name"),
        userInfo.get("Email"),
        userInfo.get("ZipCode"));
  }

  @Step
  public void verifyEnteredDetailsOfSignUpPopUp(Map<String, String> userInfo) {
    assertThat(nbaHomePage.getFirstNameText()).isEqualTo(userInfo.get("First Name"));
    assertThat(nbaHomePage.getLastNameText()).isEqualTo(userInfo.get("Last Name"));
    assertThat(nbaHomePage.getEmailText()).isEqualTo(userInfo.get("Email"));
    assertThat(nbaHomePage.getZipCodeText()).isEqualTo(userInfo.get("ZipCode"));
  }

  @Step
  public void verifyNumberOfScheduleMatchesToBePlayed(int number) {
    assertThat(nbaHomePage.getScheduledMatchCount()).isEqualTo(number);
  }

  @Step
  public void verifyNumberOfPlayers(int number) {
    assertThat(nbaHomePage.getPlayersCount()).isEqualTo(number);
  }

  @Step
  public void closeSignUpPopUpIfExists() {
    nbaHomePage.closeSignUpPopUpIfExists();
  }

  public void clickOnAcceptCookiesIfExist() {
    nbaHomePage.clickOnAcceptCookiesIfExist();
  }

  @Step
  public void openHomePage() {
    nbaHomePage.open();
  }

  @Step
  public void verifyNBATeamLocation(List<String> nBATeamLocation) {
    System.out.println(nbaHomePage.getAllTeamList());
    assertThat(nbaHomePage.getAllTeamList()).containsAll(nBATeamLocation);
  }

  @Step
  public void verifyNBATeamLocationCount(int count) {
    System.out.println(nbaHomePage.getAllTeamListCount());
    assertThat(nbaHomePage.getAllTeamListCount()).isEqualTo(count);
  }

  @Step
  public void clickOnHomePageIcon() {
    nbaHomePage.clickOnHomePageIcon();
  }

  @Step
  public void verifyNBAHomePageTabs(List<String> nbaHomePageTabs) {
    System.out.println(nbaHomePage.getAllNBAHomePageTabs());
    assertThat(nbaHomePage.getAllNBAHomePageTabs()).containsAll(nbaHomePageTabs);
  }

  @Step
  public void verifyNBAMatchesDateAndTime(List<String> nbaMatchScheduleDateAndTime) {
    System.out.println(nbaHomePage.getAllScheduledMatchDateAndTime());
    assertThat(nbaHomePage.getAllScheduledMatchDateAndTime())
        .containsAll(nbaMatchScheduleDateAndTime);
  }

  @Step
  public void verifyNBAPlayersList(List<String> nbaPlayers) {
    System.out.println(nbaHomePage.getAllNBAPlayersList());
    assertThat(nbaHomePage.getAllNBAPlayersList()).containsAll(nbaPlayers);
  }

  @Step
  public void verifyTopNavigationBarNBAHomePageTabsTitle(
      List<String> nbaHomePageTopNavigationTabs) {
    System.out.println(nbaHomePage.getTopNavigationBarTabsName());
    assertThat(nbaHomePage.getTopNavigationBarTabsName()).containsAll(nbaHomePageTopNavigationTabs);
  }

  @Step
  public void verifyPageUrlText(String urlText) {
    System.out.println(nbaHomePage.getCurrentURL());
    Utilities.simpleSleep(5000);
    assertThat(nbaHomePage.getCurrentURL()).contains(urlText);
  }

  @Step
  public void clickOnDesiredTabInNBAHomepage(String nbaHomePage) {
    System.out.println("clicking on link");
    Utilities.simpleSleep(2000);
    this.nbaHomePage.clickOnDesiredTabInNBAHomepage(nbaHomePage);
  }

  @Step
  public void getNumberOfBrokenLink() {
    System.out.println("Trying to get broken links now");
    Utilities.simpleSleep(1000);
    nbaHomePage.getNumberOfBrokenLink();
  }

  @Step
  public void saveCurrentItemsCountInNBAHomePage() {
    int currentArticleCount = nbaHomePage.getCurrentLoadedArticlesCount();
    System.out.println("Saved count : " + nbaHomePage.getCurrentLoadedArticlesCount());
    Serenity.setSessionVariable("currentArticleCount").to(currentArticleCount);
  }

  @Step
  public void clickOnLoadMoreButton() {
    nbaHomePage.clickOnLoadMoreButton();
  }

  @Step
  public void verifyNewlyLoadedItemsCount(int count) {
    System.out.println("Latest count : " + nbaHomePage.getCurrentLoadedArticlesCount());
    Utilities.simpleSleep(1000);
    int latestCount = nbaHomePage.getCurrentLoadedArticlesCount();
    System.out.println(
        "Previous count before clicking Load More : "
            + Serenity.sessionVariableCalled("currentArticleCount"));
    int previousCount = Serenity.sessionVariableCalled("currentArticleCount");
    int newlyAddedItemsCount = latestCount - previousCount;
    assertThat(newlyAddedItemsCount).isEqualTo(count);
  }
}
