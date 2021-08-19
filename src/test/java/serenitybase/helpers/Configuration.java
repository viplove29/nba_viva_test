package serenitybase.helpers;

import static net.serenitybdd.core.environment.WebDriverConfiguredEnvironment.getEnvironmentVariables;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.environment.UndefinedEnvironmentVariableException;
import net.thucydides.core.util.EnvironmentVariables;

public class Configuration {
  private static final EnvironmentVariables envVar = getEnvironmentVariables();

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

  public static String getMarUrl() {
    return getProperty("mar.url");
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
}
