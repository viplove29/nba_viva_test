package serenitybase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import net.thucydides.core.annotations.Step;
import serenitybase.helpers.Utilities;
import serenitybase.pages.mar.AgencySelectionPage;
import serenitybase.pages.vsso.LoginPage;

public class LoginToMarTestSteps {
  private LoginPage loginPage;
  private AgencySelectionPage agencySelectionPage;

  @Step
  public void enterDetailsToSignUpPopUp(Map<String, String> userInfo) {
    loginPage.enterDetailsToSignUpPopUp(
        userInfo.get("First Name"),
        userInfo.get("Last Name"),
        userInfo.get("Email"),
        userInfo.get("ZipCode"));
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
    Utilities.simpleSleep(2000);
    assertThat(loginPage.getCurrentURL()).contains(urlText);
  }

  public void clickOnDesiredTabInNBAHomepage(String nbaHomePage) {
    loginPage.clickOnDesiredTabInNBAHomepage(nbaHomePage);
    {
    }
  }
}
