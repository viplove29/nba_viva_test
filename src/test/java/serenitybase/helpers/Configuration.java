package serenitybase.helpers;

import static net.serenitybdd.core.environment.WebDriverConfiguredEnvironment.getEnvironmentVariables;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.environment.UndefinedEnvironmentVariableException;
import net.thucydides.core.util.EnvironmentVariables;

public class Configuration {
  private static final EnvironmentVariables envVar = getEnvironmentVariables();

  public static String MAR_SUPERADMIN = getProperty("superAdmin.user");
  public static String MAR_SUPERADMIN_PASSWORD = getProperty("superAdmin.password");
  public static String MAR_AGENCYADMIN = getProperty("agencyAdmin.user");
  public static String MAR_AGENCYADMIN_PASSWORD = getProperty("agencyAdmin.password");
  public static String MAR_REGULARUSER = getProperty("regularUser.user");
  public static String MAR_REGULARUSER_PASSWORD = getProperty("regularUser.password");

  private static String getProperty(String property) {
    String value = EnvironmentSpecificConfiguration.from(envVar).getProperty(property);
    if (value == null || value.equals("")) {
      throw new UndefinedEnvironmentVariableException(
          String.format(
              "Property %s is not configured in serenity.conf in %s",
              property, System.getProperties().getProperty("environment")));
    }
    return value;
  }

  public static String getVssoUrl() {
    return getProperty("vsso.url");
  }

  public static String getNbaUrl() {
    return getProperty("nba.url");
  }

  public static String getAmsUrl() {
    return getProperty("ams.url");
  }

  public static String getUser() {
    return getProperty("user");
  }

  public static String getPassword() {
    return getProperty("password");
  }

  public static String getAgency() {
    return getProperty("agency");
  }

  public static String getAppiumLogLevel() {
    String logLevel = System.getProperty("appium.log.level");
    return logLevel == null ? "debug" : logLevel.toLowerCase();
  }

  public static String getEnvironment() {
    return System.getProperty("environment");
  }

  public static String getVersion() {
    return getProperty("version");
  }
}
