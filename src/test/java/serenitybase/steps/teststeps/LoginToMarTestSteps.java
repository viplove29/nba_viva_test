package serenitybase.steps.teststeps;

import serenitybase.helpers.Configuration;
import serenitybase.helpers.Utilities;
import serenitybase.pages.mar.AgencySelectionPage;
import serenitybase.pages.vsso.LoginPage;

public class LoginToMarTestSteps {
  private LoginPage loginPage;
  private AgencySelectionPage agencySelectionPage;

  public void logIn(String email, String password) {
    loginPage.open();
    loginPage.logIn(email, password);
  }

  public void selectAgency() {
    agencySelectionPage.selectAgency(Configuration.getAgency());
  }

  public void waitForAgencyPage() {
    int counter = 0;
    do {
      String onURL = agencySelectionPage.getCurrentURL();
      // Workaround for when VSSO login sends user to wrong page
      if (onURL.contains("/Error")) {
        System.out.println("Got Error Page - Selecting Back Button");
        agencySelectionPage.backup();
      } else if (onURL.contains("/SelectAgency")) {
        return;
      }
      Utilities.simpleSleep(2000);
      counter++;
    } while (counter < 100);
    throw new RuntimeException("Login Error - did not go to Select Agency page in 200 seconds");
  }
}
