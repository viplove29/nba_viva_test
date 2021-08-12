/**
 * The is the base page for all other Benefit Point pages. It covers any common elements that are
 * available on any page as well as most of the supporting methods that might be used for things on
 * any page. This is done so that all of the child pages can inherit the methods instead of having
 * to implement them individually by page.
 *
 * @author AJ Johnson
 * @version 1.1.0
 */
package serenitybase.selenium.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import serenitybase.Utilities;

public class BasePage extends PageObject {

  /**
   * Standard Page Object constructor model, just shipping the driver object up to {@link
   * PageObject}.
   *
   * @param driver Optional override for location of properties file
   */
  public BasePage(WebDriver driver) {
    super(driver);
  }

  public boolean amOnCertPage() {
    boolean onCertPage = false;
    if (getElementThatMayNotBePresent(By.id("overridelink")) != null) {
      onCertPage = true;
    }
    return onCertPage;
  }

  public void clickAcceptCert() {
    find(By.id("overridelink")).click();
  }

  /**
   * Attempts to find an element that may not be present when an exception is not the desired
   * outcome, for instance when testing for the presence of something where the test should fail if
   * it is not present or when using it to help to wait for something to appear.
   *
   * @param by The By locator for the element to be found
   * @return Returns the element if found, null if not
   */
  public WebElementFacade getElementThatMayNotBePresent(By by) {
    WebElementFacade element;
    try {
      element = find(by);
      if (!element.isDisplayed() && element.isEnabled()) {
        element = null;
      }
    } catch (NoSuchElementException e) {
      element = null;
    }

    return element;
  }

  /**
   * Attempts to find an element that may not be present when an exception is not the desired
   * outcome, for instance when testing for the presence of something where the test should fail if
   * it is not present or when using it to help to wait for something to appear. This version goes
   * off of an existing element instead of going after the entire DOM.
   *
   * @param context The element to look within
   * @param by The By locator for the element to be found
   * @return Returns the element if found, null if not
   */
  public WebElementFacade getElementThatMayNotBePresent(WebElementFacade context, By by) {
    WebElementFacade element;
    try {
      element = context.find(by);
      if (!element.isDisplayed() && element.isEnabled()) {
        element = null;
      }
    } catch (NoSuchElementException e) {
      element = null;
    }

    return element;
  }

  /**
   * Navigates to a specific url. Used at the beginning of all scenarios.
   *
   * @param url Returns String of the url to be navigated to, requires full url
   */
  public void goToUrl(String url) {
    getDriver().get(url);

    // To account for IE's habit of saying the page isn't safe.
    if (InternetExplorerDriver.class.isAssignableFrom(getDriverClass()) && amOnCertPage()) {
      clickAcceptCert();
    }
  }

  /**
   * Gets the current page's url from the browser. This will use the browser window currently in
   * focus.
   *
   * @return Returns String of the url the browser is currently on
   */
  public String getCurrentUrl() {
    return getDriver().getCurrentUrl();
  }

  /** Through Selenium Actions it emulates the "page down" key */
  public void pageDown() {
    Actions actions = new Actions(getDriver());
    actions.sendKeys(Keys.PAGE_DOWN).perform();
  }

  /** Through Selenium Actions it emulates the "page up" key */
  public void pageUp() {
    Actions actions = new Actions(getDriver());
    actions.sendKeys(Keys.PAGE_UP).perform();
  }

  /** Through Selenium Actions it emulates the "esc" key */
  public void hitEscape() {
    Actions action = new Actions(getDriver());
    action.sendKeys(Keys.ESCAPE).perform();
  }

  /**
   * Given two known elements it will drag the first and drop it on the second.
   *
   * @param grab The element to be picked up and dragged
   * @param drop The element the dragged item is to be dropped on
   */
  public void dragAndDrop(WebElementFacade grab, WebElementFacade drop) {
    Actions builder = new Actions(getDriver());
    Action dragAndDrop = builder.clickAndHold(grab).moveToElement(drop).release(drop).build();
  }

  /**
   * Selenium identifies windows by something it calls a handle. This gets the handle of the window
   * currently in focus.
   *
   * @return Returns the String value of the window handle
   */
  private String getCurrentWindowHandle() {
    return getDriver().getWindowHandle();
  }

  /**
   * Takes the current handle and stores it in Serenity's persistent hashmap so that it can be
   * referenced later. It then gets the handle for the recent popup window and tells Selenium to
   * switch focus to that window. This is generally used when the popup did not automatically gain
   * focus.
   */
  public void switchToMostRecentPopUpWindow() {
    Serenity.setSessionVariable("mainHandle").to(getCurrentWindowHandle());

    String popUpHandle = null;
    Set<String> handles = getDriver().getWindowHandles();
    Iterator<String> iterator = handles.iterator();

    while (iterator.hasNext()) {
      popUpHandle = iterator.next();
    }

    getDriver().switchTo().window(popUpHandle);

    // This is to handle IE's habit of saying the site is insecure.
    if (amOnCertPage()) {
      clickAcceptCert();
    }
  }

  /**
   * Takes the current handle and stores it in Serenity's session map so that it can be referenced
   * later. Creates a list of the currently available handles and, based on the order in which the
   * popups/tabs/windows were opened, it finds the correct window handle and brings that one into
   * focus.
   *
   * @param openingOrder The nth window where n is the order in which the window was opened i.e.:
   *     first popup is 1, second popup is 2, etc
   */
  public void switchToPopUpWindow(int openingOrder) {
    Serenity.setSessionVariable("mainHandle").to(getCurrentWindowHandle());

    ArrayList<String> handlesList = new ArrayList<>(getDriver().getWindowHandles());
    String popUpHandle = handlesList.get(openingOrder);

    getDriver().switchTo().window(popUpHandle);

    // This is to handle IE's habit of saying the site is insecure.
    if (amOnCertPage()) {
      clickAcceptCert();
    }
  }

  /**
   * Gets the window handle for the main window from the Serenity hashmap and then switches to that
   * window. This method requires that the switch away from the main window was done either by
   * {@link BasePage#switchToPopUpWindow(int)} or {@link BasePage#switchToMostRecentPopUpWindow()}.
   */
  public void switchToMainWindow() {
    String mainHandle = Serenity.sessionVariableCalled("mainHandle");
    getDriver().switchTo().window(mainHandle);
  }

  /**
   * This method is there when an element may or may not be present or may not always be immediately
   * clickable. This keeps trying for a while before going ahead and throwing the normal exception
   * if it could not click correctly.
   *
   * @param by By for finding the element to be clicked
   */
  public void retryClick(By by) {
    boolean clicked = false;
    WebElementFacade element;
    for (int i = 0; i < 50; i++) {

      element = getElementThatMayNotBePresent(by);
      if (element == null) {
        Utilities.simpleSleep(100);
        continue;
      } else {
        try {
          element.click();
          clicked = true;
          break;
        } catch (ElementNotInteractableException
            | StaleElementReferenceException
            | NoSuchElementException e) {
          // Swallow the exception for now.  It will get thrown later if this loop fails.
        }
      }
    }

    // If it wasn't successfully clicked, go ahead and try this last time in order to get the
    // exception
    if (!clicked) {
      find(by).click();
    }
  }

  /**
   * This method is there when an element may or may not be present or may not always be immediately
   * clickable. This keeps trying for a while before going ahead and throwing the normal exception
   * if it could not click correctly.
   *
   * @param element Element to be clicked
   */
  public void retryClick(WebElementFacade element) {
    boolean clicked = false;
    for (int i = 0; i < 50; i++) {

      try {
        element.click();
        clicked = true;
        break;
      } catch (ElementNotInteractableException
          | StaleElementReferenceException
          | NoSuchElementException
          | ElementShouldBeEnabledException e) {
        // Swallow the exception for now.  It will get thrown later if this loop fails.
      }
    }

    // If it wasn't successfully clicked, go ahead and try this last time in order to get the
    // exception
    if (!clicked) {
      element.click();
    }
  }

  /**
   * This method acquires the page's title as displayed in the containing tab.
   *
   * @return Value of the displayed title
   */
  public String getTitle() {
    return getDriver().getTitle();
  }

  public void maximizeWindowSize() {
    getDriver().manage().window().maximize();
  }

  public void setWindowWidthAndHeight(int width, int height) {
    getDriver().manage().window().setSize(new Dimension(width, height));
  }

  protected Class<? extends WebDriver> getDriverClass() {
    WebDriverFacade driver = (WebDriverFacade) getDriver();
    return driver.getDriverClass();
  }

  /** Closes the browser */
  public void closeBrowser() {
    getDriver().close();
  }
}
