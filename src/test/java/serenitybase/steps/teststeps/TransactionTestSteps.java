package serenitybase.steps.teststeps;


import static org.assertj.core.api.Assertions.assertThat;
import net.thucydides.core.annotations.Step;
import serenitybase.pages.mar.TransactionPage;

public class TransactionTestSteps {
    private TransactionPage transactionPage;

    @Step
    public void clickOnFilterSymbol() {
        transactionPage.clickOnFilterSymbol();
    }

    @Step
    public void clickOnAddFiltersButton() {
        transactionPage.clickOnAddFiltersButton();
    }

    @Step
    public void selectOptionUnderFilters(String option) {
        transactionPage.selectOptionUnderFilters(option);
    }

    @Step
    public void setFilterValueToStartWith(String value) {
        transactionPage.setFilterPresetToStartWith();
        transactionPage.setFilterStartsWith(value);
    }

    @Step
    public void clickOnApply() {
        transactionPage.clickOnApply();
    }

    @Step
    public void verifyPolicyNumberColumnValue(String value) {
        assertThat(transactionPage.getPolicyNumberColumnValue() == value);
    }
}
