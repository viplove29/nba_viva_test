package serenitybase.pages.vsso;

import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import serenitybase.helpers.Utilities;

@DefaultUrl("page:mar.url")
public class LoginPage extends PageObject {
  @FindBy(id = "contact_fields[firstname]")
  WebElementFacade firstNameTextBox;

  @FindBy(id = "contact_fields[lastname]")
  WebElementFacade lastNameTextBox;

  @FindBy(id = "contact_fields[email]")
  WebElementFacade emailTextBox;

  @FindBy(id = "contact_fields[zipcode]")
  WebElementFacade zipcodeTextBox;

  @FindBy(id = "onetrust-accept-btn-handler")
  WebElementFacade iAcceptButton;

  @FindBy(id = "formSubmit")
  WebElementFacade signUpButton;

  @FindBy(id = "recaptcha-anchor")
  WebElementFacade recaptchaAnchorCheckbox;

  @FindBy(xpath = "//div[@class='p-2 absolute right-3 hover:cursor-pointer']")
  WebElementFacade closeSignUpIcon;

  @FindBy(xpath = "//span[contains(text(),'Teams')]")
  WebElementFacade teamsHeader;

  @FindBy(xpath = "//img[@alt='Golden State Warriors']")
  WebElementFacade homePageIcon;

  public void enterDetailsToSignUpPopUp(
      String firstName, String lastName, String email, String zipcode) {
    if (closeSignUpIcon.isPresent()) {
      firstNameTextBox.sendKeys(firstName);
      lastNameTextBox.sendKeys(lastName);
      emailTextBox.sendKeys(email);
      zipcodeTextBox.sendKeys(zipcode);
      Utilities.simpleSleep(500);
      if (!recaptchaAnchorCheckbox.isPresent()) closeSignUpIcon.click();
      else signUpButton.click();
    }
  }

  public void clickOnAcceptCookiesIfExist() {
    if (iAcceptButton.isPresent()) iAcceptButton.click();
  }

  public List<String> getAllTeamList() {
    Actions action = new Actions(getDriver());
    action.moveToElement(teamsHeader).build().perform();
    Utilities.simpleSleep(1000);
    return getDriver().findElements(By.xpath("//ul[@id='teams']//li/a")).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public List<String> getAllNBAHomePageTabs() {
    return getDriver()
        .findElements(
            By.xpath("//nav[@aria-label='header-primary-menu']//ul[@role='menubar']/li/a"))
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public List<String> getTopNavigationBarTabsName() {
    return getDriver().findElements(By.xpath("//ul[@class='flex utility-font']/li/a")).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public void clickOnHomePageIcon() {
    homePageIcon.click();
  }

  public void clickOnDesiredTabInNBAHomepage(String nbaHomePage) {

    getDriver().findElement(By.xpath("(//span[contains(text()," + nbaHomePage + ")])[1]")).click();
  }

  public String getCurrentURL() {
    return getDriver().getCurrentUrl();
  }
}
