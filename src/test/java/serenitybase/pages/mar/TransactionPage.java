package serenitybase.pages.mar;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class TransactionPage extends BasePage {


  @FindBy(xpath = ".//a[contains(text(), 'Customer Name')]")
  private WebElementFacade customerName;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Id')]")
  private WebElementFacade policyId;

  @FindBy(xpath = ".//a[contains(text(), 'Bill Method')]")
  private WebElementFacade billMethod;

  @FindBy(xpath = ".//a[contains(text(), 'Billed Premium')]")
  private WebElementFacade billedPremium;

  @FindBy(xpath = ".//a[contains(text(), 'Business With Agency')]")
  private WebElementFacade businessWithAgency;

  @FindBy(xpath = ".//a[contains(text(), 'Cancel Reason')]")
  private WebElementFacade cancelReason;

  @FindBy(xpath = ".//a[contains(text(), 'Company Type')]")
  private WebElementFacade companyType;

  @FindBy(xpath = ".//a[contains(text(), 'Company Underwriter')]")
  private WebElementFacade companyUnderwriter;

  @FindBy(xpath = ".//a[contains(text(), 'Customer Added Date')]")
  private WebElementFacade customerAddedDate;

  @FindBy(xpath = ".//a[contains(text(), 'Customer Number')]")
  private WebElementFacade customerNumber;

  @FindBy(xpath = ".//a[contains(text(), 'Estimated Revenue')]")
  private WebElementFacade estimatedRevenue;

  @FindBy(xpath = ".//a[contains(text(), 'Estimated Revenue Percent')]")
  private WebElementFacade estimatedRevenuePercent;

  @FindBy(xpath = ".//a[contains(text(), 'Line of Business')]")
  private WebElementFacade lineOfBusiness;

  @FindBy(xpath = ".//a[contains(text(), 'Parent Company')]")
  private WebElementFacade parentCompany;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Branch')]")
  private WebElementFacade policyBranch;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Broker')]")
  private WebElementFacade policyBroker;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Department')]")
  private WebElementFacade policyDepartment;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Division')]")
  private WebElementFacade policyDivision;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Effective Date')]")
  private WebElementFacade policyEffectiveDate;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Expiration Date')]")
  private WebElementFacade policyExpirationDate;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Full Term Premium')]")
  private WebElementFacade policyFullTermPremium;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Group')]")
  private WebElementFacade policyGroup;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Notation')]")
  private WebElementFacade policyNotation;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Number')]")
  private WebElementFacade policyNumber;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Primary Executive')]")
  private WebElementFacade policyPrimaryExecutive;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Primary Representative')]")
  private WebElementFacade policyPrimaryRepresentative;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Status')]")
  private WebElementFacade policyStatus;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Type')]")
  private WebElementFacade policyType;

  @FindBy(xpath = ".//a[contains(text(), 'Premium')]")
  private WebElementFacade premium;

  @FindBy(xpath = ".//a[contains(text(), 'Processed By')]")
  private WebElementFacade processedBy;

  @FindBy(xpath = ".//a[contains(text(), 'Total Cost')]")
  private WebElementFacade totalCost;

  @FindBy(xpath = ".//a[contains(text(), 'Transaction Description')]")
  private WebElementFacade transactionDescription;

  @FindBy(xpath = ".//a[contains(text(), 'Transaction Effective Date')]")
  private WebElementFacade transactionEffectiveDate;

  @FindBy(xpath = ".//a[contains(text(), 'Transaction Premium')]")
  private WebElementFacade transactionPremium;

  @FindBy(xpath = ".//a[contains(text(), 'Transaction Source')]")
  private WebElementFacade transactionSource;

  @FindBy(xpath = ".//a[contains(text(), 'Transaction Type')]")
  private WebElementFacade transactionType;

  @FindBy(xpath = ".//a[contains(text(), 'Writing Company')]")
  private WebElementFacade writingCompany;

  @FindBy(xpath = ".//a[contains(text(), 'Total Billed Premium')]")
  private WebElementFacade totalBilledPremium;

  @FindBy(xpath = ".//a[contains(text(), 'Total Premium')]")
  private WebElementFacade totalPremium;

  @FindBy(xpath = ".//a[contains(text(), 'Transaction Count')]")
  private WebElementFacade transactionCount;

  @FindBy(id = "chPolicy Number")
  private WebElementFacade policyNumberColumn;

  public TransactionPage() {}

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
      case "Policy Id":
        policyId.click();
        break;
      case "Bill Method":
        billMethod.click();
        break;
      case "Billed Premium":
        billedPremium.click();
        break;
      case "Business With Agency":
        businessWithAgency.click();
        break;
      case "Cancel Reason":
        cancelReason.click();
        break;
      case "Company Type":
        companyType.click();
        break;
      case "Company Underwriter":
        companyUnderwriter.click();
        break;
      case "Customer Added Date":
        customerAddedDate.click();
        break;
      case "Customer Number":
        customerNumber.click();
        break;
      case "Estimated Revenue":
        estimatedRevenue.click();
        break;
      case "Estimated Revenue Percent":
        estimatedRevenuePercent.click();
        break;
      case "Line of Business":
        lineOfBusiness.click();
        break;
      case "Parent Company":
        parentCompany.click();
        break;
      case "Policy Branch":
        policyBranch.click();
        break;
      case "Policy Broker":
        policyBroker.click();
        break;
      case "Policy Department":
        policyDepartment.click();
        break;
      case "Policy Division":
        policyDivision.click();
        break;
      case "Policy Effective Date":
        policyEffectiveDate.click();
        break;
      case "Policy Expiration Date":
        policyExpirationDate.click();
        break;
      case "Policy Full Term Premium":
        policyFullTermPremium.click();
        break;
      case "Policy Group":
        policyGroup.click();
        break;
      case "Policy Notation":
        policyNotation.click();
        break;
      case "Policy Number":
        policyNumber.click();
        break;
      case "Policy Primary Executive":
        policyPrimaryExecutive.click();
        break;
      case "Policy Primary Representative":
        policyPrimaryRepresentative.click();
        break;
      case "Policy Status":
        policyStatus.click();
        break;
      case "Policy Type":
        policyType.click();
        break;
      case "Premium":
        premium.click();
        break;
      case "Processed By":
        processedBy.click();
        break;
      case "Transaction Description":
        transactionDescription.click();
        break;
      case "Transaction Effective Date":
        transactionEffectiveDate.click();
        break;
      case "Transaction Premium":
        transactionPremium.click();
        break;
      case "Transaction Source":
        transactionSource.click();
        break;
      case "Transaction Type":
        transactionType.click();
        break;
      case "Writing Company":
        writingCompany.click();
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

}
