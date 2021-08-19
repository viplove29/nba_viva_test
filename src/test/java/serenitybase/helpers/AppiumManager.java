package serenitybase.helpers;

import static java.util.concurrent.TimeUnit.SECONDS;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.windows.WindowsDriver;
import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumManager {
  private static AppiumDriverLocalService appium;
  private static WindowsDriver<WebElement> session;

  public static void startAppium() {
    URL appiumURL = getAppiumURL();
    String logLevel = Configuration.getAppiumLogLevel();
    AppiumServiceBuilder builder =
        new AppiumServiceBuilder()
            .withIPAddress(appiumURL.getHost())
            .usingPort(appiumURL.getPort())
            .withArgument(GeneralServerFlag.LOG_LEVEL, logLevel)
            .withArgument(GeneralServerFlag.SESSION_OVERRIDE);
    appium = AppiumDriverLocalService.buildService(builder);
    appium.start();
  }

  private static URL getAppiumURL() {
    try {
      return new URL("http://127.0.0.1:4723/wd/hub");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void stopAppium() {
    if (appium != null) {
      appium.stop();
    }
  }

  private static WindowsDriver<WebElement> createWindowsDriver() {
    DesiredCapabilities appCapabilities = new DesiredCapabilities();
    appCapabilities.setCapability("app", "Root");
    appCapabilities.setCapability("platformName", "Windows");
    appCapabilities.setCapability("deviceName", "WindowsPC");
    appCapabilities.setCapability("newCommandTimeout", 1800);
    return session = new WindowsDriver<>(getAppiumURL(), appCapabilities);
  }

  public static WindowsDriver<WebElement> getSession() {
    if (session == null) {
      session = createWindowsDriver();
      session.manage().timeouts().implicitlyWait(60, SECONDS);
    }
    return session;
  }
}
