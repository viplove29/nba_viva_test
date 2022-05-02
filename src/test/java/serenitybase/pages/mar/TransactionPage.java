package serenitybase.pages.mar;

import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import serenitybase.helpers.Utilities;

public class TransactionPage  extends BasePage {

    @FindBy(id = "shc")
    private WebElementFacade showHideIconButton;

    @FindBy(id = "chDivision")
    private WebElementFacade divisionDetailView;

    @FindBy(id = "chBranch")
    private WebElementFacade branchDetailView;

    @FindBy(id = "chDepartment")
    private WebElementFacade departmentDetailView;

    @FindBy(id = "chGroup")
    private WebElementFacade groupDetailView;

    @FindBy(id = "f")
    private WebElementFacade filterSymbol;

    @FindBy(id = "drop_columns")
    private WebElementFacade filterDropdown;

    @FindBy(id = "drop_filteroperators")
    private WebElementFacade filterPresetDropDown;

    @FindBy(xpath = ".//span[text()=' Add Filters']")
    private WebElementFacade addFiltersButton;

    @FindBy(xpath = ".//button[text()='Apply']")
    private WebElementFacade applyButton;

    @FindBy(xpath = ".//a[contains(text(), 'Starts with')]")
    private WebElementFacade startsWithValue;

    @FindBy(xpath = "//input[@type='text'][1]")
    private WebElementFacade valueTextBox;

    @FindBy(xpath = ".//a[contains(text(), 'Equal to')]")
    private WebElementFacade equalToValue;

    @FindBy(id = "drop_columnvalues")
    private WebElementFacade dropdownMenuButton;

    @FindBy(id = "c802")
    private WebElementFacade customerTypeDropdownMenu;

    @FindBy(id = "Active CustomerInactive")
    private WebElementFacade activeCustomerColumn;

    @FindBy(id = "Customer TypeMaster")
    private WebElementFacade masterTypeColumn;

    @FindBy(id = "Current Personnel TypeRep")
    private WebElementFacade currentPersonnelTypeColumn;

    @FindBy(xpath = ".//a[contains(text(), 'Customer Name')]")
    private WebElementFacade customerName;

    @FindBy(xpath = ".//a[contains(text(), 'Policy Count')]")
    private WebElementFacade policyCount;

    @FindBy(xpath = ".//a[contains(text(), 'Policy Id')]")
    private WebElementFacade policyId;

    @FindBy(xpath = ".//a[contains(text(), 'Total Billed Premium')]")
    private WebElementFacade totalBilledPremium;

    @FindBy(xpath = ".//a[contains(text(), 'Total Premium')]")
    private WebElementFacade totalPremium;

    @FindBy(xpath = ".//a[contains(text(), 'Transaction Count')]")
    private WebElementFacade transactionCount;

    @FindBy(id = "chDivision")
    private WebElementFacade divisionColumn;

    @FindBy(id = "chPolicy Number")
    private WebElementFacade policyNumberColumn;

    @FindBy(xpath = "//span[@ng-show='grid.options.totalItems > 0']//b[2]")
    private WebElementFacade totalResults;

    protected static final String DETAIL_VIEW_TAB_ID = "vw1";
    protected static final int DETAIL_VIEW_TAB_VIEWPORT_INDEX = 0;
    protected static final int TAB_VIEWPORT_INDEX = 1;

    public TransactionPage() {}

    public int getNumberOfResults() {
        return Integer.parseInt(totalResults.getText());
    }

    public List<String> getReportHeaders() {
        String activeTabContentId = Serenity.sessionVariableCalled("activeTabContentId");
        WebElementFacade activeTabContent = find(By.id(activeTabContentId));
        return activeTabContent
                .findElements(By.xpath(".//i[@class='rpt-glyphicon fa fa-eye']/ancestor::a"))
                .stream()
                .map(e -> e.getAttribute("innerText"))
                .collect(Collectors.toList());
    }

    public void selectOptionUnderHideShowIcon(String option) {
        showHideIconButton.click();
        find(String.format("//*[text()='%s']", option)).click();
    }

    public void selectTab(String tabName) {
        find(String.format("//*[text()='%s']", tabName)).click();
        String activeTabContentId = find(By.cssSelector(".rpt-tab-content.active")).getAttribute("id");
        Serenity.setSessionVariable("activeTabContentId").to(activeTabContentId);
        Utilities.waitForActiveTabLoadingSpinner();
    }

    public boolean isDivisionDisplayed() {
        return divisionDetailView.isDisplayed();
    }

    public boolean isBranchDisplayed() {
        return branchDetailView.isDisplayed();
    }

    public boolean isDepartmentDisplayed() {
        return departmentDetailView.isDisplayed();
    }

    public boolean isGroupDisplayed() {
        return groupDetailView.isDisplayed();
    }

    public void clickOnFilterSymbol() {
        filterSymbol.click();
    }

    public void clickOnAddFiltersButton() {
        addFiltersButton.click();
    }

    public void selectOptionUnderFilters(String option) {
        filterDropdown.click();
        switch (option) {
            case "Customer Name":
                customerName.click();
                break;
            case "Policy Count":
                policyCount.click();
                break;
            case "Policy Id":
                policyId.click();
                break;
            case "Total Billed Premium":
                totalBilledPremium.click();
                break;
            case "Total Premium":
                totalPremium.click();
                break;
            case "Transaction Count":
                transactionCount.click();
                break;

            default:
                throw new IllegalArgumentException(String.format("%s filter option not supported", option));
        }
    }

    public void clickOnApply() {
        applyButton.click();
    }

    public void setFilterPresetToStartWith() {
        filterPresetDropDown.click();
        startsWithValue.click();
    }

    public void setFilterStartsWith(String value) {
        valueTextBox.click();
        valueTextBox.sendKeys(value);
    }

    public void setFilterPresetToEqualTo() {
        filterPresetDropDown.click();
        equalToValue.click();
    }

    public void setFilterEqualTo(String value) {
        dropdownMenuButton.click();
        customerTypeDropdownMenu
                .findElement(
                        By.xpath(
                                String.format("//label[contains(text(), '%s')]/preceding-sibling::input", value)))
                .click();
        if (dropdownMenuButton.getAttribute("aria-expanded").contains("true")) {
            dropdownMenuButton.click();
        }
    }

    public String getActiveCustomerColumnValue() {
        return activeCustomerColumn.getText();
    }

    public String getCustomerTypeColumnValue() {
        horizontalScroll(masterTypeColumn);
        return masterTypeColumn.getText();
    }

    public String getPolicyNumberColumnValue() {
        horizontalScroll(policyNumberColumn);
        return policyNumberColumn.getText();
    }

    public String getDivisionColumnValue() {
        horizontalScroll(divisionColumn);
        return divisionColumn.getText();
    }

    public boolean validateColumnsAreDisplayedInTab(String columnName) {
        WebElement activeTab = find(By.id(Serenity.sessionVariableCalled("activeTabContentId")));
        WebElement column = findColumnByColumnNameInTab(activeTab, columnName);
        horizontalScroll(column);
        return column.isDisplayed();
    }

    public WebElement findColumnByColumnNameInTab(WebElement element, String value) {
        return element.findElement(By.xpath(String.format(".//*[@id='ch%s']", value)));
    }

    public void horizontalScroll(WebElement element) {
        WebElementFacade activeTabContent =
                find(By.id(Serenity.sessionVariableCalled("activeTabContentId")));
        int viewportIndex = DETAIL_VIEW_TAB_VIEWPORT_INDEX;
        if (!activeTabContent.getAttribute("id").equals(DETAIL_VIEW_TAB_ID)) {
            viewportIndex = TAB_VIEWPORT_INDEX;
        }
        int scrollX = element.getLocation().x;
        String script =
                String.format(
                        "document.getElementById('%s').getElementsByClassName('ui-grid-viewport')[%d].scrollLeft = %d",
                        activeTabContent.getAttribute("id"), viewportIndex, scrollX);
        JavascriptExecutor executor = ((JavascriptExecutor) getDriver());
        executor.executeScript(script);
        scrollToElement(element);
    }
}
