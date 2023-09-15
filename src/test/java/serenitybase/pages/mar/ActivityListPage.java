package serenitybase.pages.mar;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActivityListPage extends PageObject {

    @FindBy(xpath = "//a[text()[contains(.,'Center')]]")
    protected WebElementFacade centerSection;


    public void clickCenterSection() {
         centerSection.click();
    }
}
