/**
 * This utility is primarily responsible for providing common methods that aren't specific to any
 * testing tool.
 *
 * @author AJ Johnson
 * @version 0.1.0
 */
package serenitybase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import net.serenitybdd.core.Serenity;

/**
 * This test utility primarily handles the role of interacting with the properties file. It should
 * also be used to handle anything that is universal to all testing and exists outside of the test
 * logic and outside of the other test tool structures like Page Objects or RestAssured structures.
 *
 * @author AJ Johnson
 * @version 1.0.6
 */
public class Utilities {

  private static String defaultPropertiesFilePath = "/test.properties";

  /**
   * Version of getProperty that does not contain an override of the properties file location.
   *
   * @param key Key to be searched for
   * @return Value if one is available
   * @throws IOException Exception thrown because of accessing a properties file
   */
  public static String getProperty(String key) throws IOException {
    return getProperty(key, null);
  }

  /**
   * Takes in the key value for the parameters file and finds the related value if one exists. Also
   * checks to see that the value was returned. It does this here because a null return will
   * absolutely break the test calling it and here we can access the key that was used with ease.
   *
   * @param key Key to be searched for
   * @param locationOverride Optional override for location of properties file
   * @return Returns the value for given key
   * @throws IOException Exception thrown because of accessing a properties file
   */
  public static String getProperty(String key, String locationOverride) throws IOException {
    Properties properties;

    // load properties file
    properties = new Properties();
    String propertiesFilePath = defaultPropertiesFilePath;
    if (locationOverride != null) {
      propertiesFilePath = locationOverride;
    }
    InputStream in = properties.getClass().getResourceAsStream(propertiesFilePath);
    properties.load(in);

    if (key.charAt(0) == '~') {
      return key.substring(1, key.length());
    }
    String value = properties.getProperty(key);
    if (value == null) {
      throw new NullPointerException(
          "The key provided of " + key + " was not present and " + "so returned a null value.");
    }
    return value;
  }

  /**
   * An example of what can be done in a utilities class to support other coding work. Creates a
   * random number to be used as an ID for elements that need something identifying to avoid logic
   * collisions and then saves it off into the session map as well as returning it. If a unique ID
   * has already been set in this test, it will return that provided. This is primarily so that a
   * value set in the Given steps of the test will always have access to the same ID in the
   * verification step.
   *
   * @return Unique number between 0 and 999999 as a String
   */
  public static String getUniqueId() {
    String uniqueId = Serenity.sessionVariableCalled("uniqueId");
    if (uniqueId == null) {
      Random random = new Random();
      uniqueId = Integer.toString(random.nextInt(1000000));
      Serenity.setSessionVariable("uniqueId").to(uniqueId);
    }
    return uniqueId;
  }

  /**
   * Used to allow use of a sleep without always having to enter the try/catch block or throw the
   * InterruptedException. Convenience method.
   *
   * @param millis The time to be slept in milliseconds
   */
  public static void simpleSleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
      ie.printStackTrace();
    }
  }
}
