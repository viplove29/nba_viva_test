package serenitybase.steps.stepdefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import serenitybase.helpers.Configuration;
import serenitybase.helpers.Utilities;
import serenitybase.steps.teststeps.LoginToMarTestSteps;

public class LoginToMarStepDefinitions {
  @Steps LoginToMarTestSteps loginToMarTestSteps;

  @ParameterType("SuperAdmin|AgencyAdmin|RegularUser")
  public String user(String user) {
    return user;
  }

  @Given("the user logs into MAR as \"{user}\"")
  public void the_user_logs_into_Mar(String userRole) {
    switch (userRole) {
      case "SuperAdmin":
        loginToMarTestSteps.logIn(
            Configuration.MAR_SUPERADMIN, Configuration.MAR_SUPERADMIN_PASSWORD);
        loginToMarTestSteps.waitForAgencyPage();
        loginToMarTestSteps.selectAgency();
        break;
      case "AgencyAdmin":
        loginToMarTestSteps.logIn(
            Configuration.MAR_AGENCYADMIN, Configuration.MAR_AGENCYADMIN_PASSWORD);
        loginToMarTestSteps.waitForAgencyPage();
        loginToMarTestSteps.selectAgency();
        break;
      case "RegularUser":
        loginToMarTestSteps.logIn(
            Configuration.MAR_REGULARUSER, Configuration.MAR_REGULARUSER_PASSWORD);
        loginToMarTestSteps.waitForAgencyPage();
        loginToMarTestSteps.selectAgency();
        break;
      default:
        throw new IllegalArgumentException(String.format("%s role not supported", userRole));
    }
    Utilities.waitForHomePageLoadingSpinners();
  }
}
