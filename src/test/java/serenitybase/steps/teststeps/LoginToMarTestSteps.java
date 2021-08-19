package serenitybase.steps.teststeps;

import serenitybase.helpers.Configuration;
import serenitybase.pages.mar.AgencySelectionPage;
import serenitybase.pages.vsso.LoginPage;

public class LoginToMarTestSteps {
  private LoginPage loginPage;
  private AgencySelectionPage agencySelectionPage;

  public void logIn() {
    loginPage.open();
    loginPage.logIn(Configuration.getUser(), Configuration.getPassword());
  }

  public void selectAgency() {
    agencySelectionPage.selectAgency(Configuration.getAgency());
  }
}
