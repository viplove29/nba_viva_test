package serenitybase.pages.mar;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class UnbilledPoliciesPage extends ReportBasePage {

  @FindBy(xpath = ".//a[contains(text(), 'Additional Executive 1')]")
  private WebElementFacade additionalExecutive1;

  @FindBy(xpath = ".//a[contains(text(), 'Additional Executive 2')]")
  private WebElementFacade additionalExecutive2;

  @FindBy(xpath = ".//a[contains(text(), 'Additional Representative 1')]")
  private WebElementFacade additionalRepresentative1;

  @FindBy(xpath = ".//a[contains(text(), 'Additional Representative 2')]")
  private WebElementFacade additionalRepresentative2;

  @FindBy(xpath = ".//a[contains(text(), 'Bill Method')]")
  private WebElementFacade billMethod;

  @FindBy(xpath = ".//a[contains(text(), 'Billed Premium/Non Premium')]")
  private WebElementFacade billedPremiumNonPremium;

  @FindBy(xpath = ".//a[contains(text(), 'Branch')]")
  private WebElementFacade branch;

  @FindBy(xpath = ".//a[contains(text(), 'Company Type')]")
  private WebElementFacade companyType;

  @FindBy(xpath = ".//a[contains(text(), 'Current Broker Name')]")
  private WebElementFacade currentBrokerName;

  @FindBy(xpath = ".//a[contains(text(), 'Customer Firm Name')]")
  private WebElementFacade customerFirmName;

  @FindBy(xpath = ".//a[contains(text(), 'Customer First Name')]")
  private WebElementFacade customerFirstName;

  @FindBy(xpath = ".//a[contains(text(), 'Customer Last Name')]")
  private WebElementFacade customerLastName;

  @FindBy(xpath = ".//a[contains(text(), 'Customer Name')]")
  private WebElementFacade customerName;

  @FindBy(xpath = ".//a[contains(text(), 'Customer Number')]")
  private WebElementFacade customerNumber;

  @FindBy(xpath = ".//a[contains(text(), 'Department')]")
  private WebElementFacade department;

  @FindBy(xpath = ".//a[contains(text(), 'Division')]")
  private WebElementFacade division;

  @FindBy(xpath = ".//a[contains(text(), 'Estimated Revenue')]")
  private WebElementFacade estimatedRevenue;

  @FindBy(xpath = ".//a[contains(text(), 'Estimated Revenue Percent')]")
  private WebElementFacade estimatedRevenuePercent;

  @FindBy(xpath = ".//a[contains(text(), 'Group')]")
  private WebElementFacade group;

  @FindBy(xpath = ".//a[contains(text(), 'How Billed')]")
  private WebElementFacade howBilled;

  @FindBy(xpath = ".//a[contains(text(), 'Line of Business/Non Premium')]")
  private WebElementFacade lineOfBusinessNonPremium;

  @FindBy(xpath = ".//a[contains(text(), 'Non Premium')]")
  private WebElementFacade nonPremium;

  @FindBy(xpath = ".//a[contains(text(), 'Original Executive')]")
  private WebElementFacade originalExecutive;

  @FindBy(xpath = ".//a[contains(text(), 'Original Representative')]")
  private WebElementFacade originalRepresentative;

  @FindBy(xpath = ".//a[contains(text(), 'Parent Company')]")
  private WebElementFacade parentCompany;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Expiration Date')]")
  private WebElementFacade policyExpirationDate;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Id')]")
  private WebElementFacade policyId;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Number')]")
  private WebElementFacade policyNumber;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Primary Executive')]")
  private WebElementFacade policyPrimaryExecutive;

  @FindBy(xpath = ".//a[contains(text(), 'Policy Primary Representative')]")
  private WebElementFacade policyPrimaryRepresentative;

  @FindBy(xpath = ".//a[contains(text(), 'Premium')]")
  private WebElementFacade premium;

  @FindBy(xpath = ".//a[contains(text(), 'Transaction Effective Date')]")
  private WebElementFacade transationEffectiveDate;

  @FindBy(xpath = ".//a[contains(text(), 'Transaction Source')]")
  private WebElementFacade transactionSource;

  @FindBy(xpath = ".//a[contains(text(), 'Type of Business')]")
  private WebElementFacade typeOfBusiness;

  @FindBy(xpath = ".//a[contains(text(), 'Unbilled Agency Commission')]")
  private WebElementFacade unbilledAgencyCommission;

  public UnbilledPoliciesPage() {}

  public void selectOptionUnderFilters(String option) {
    filterDropdown.click();
    switch (option) {
      case "Line of Business/Non Premium":
        lineOfBusinessNonPremium.click();
        break;
      case "Branch":
        branch.click();
        break;

      case "Additional Executive 1":
        additionalExecutive1.click();
        break;
      case "Additional Executive 2":
        additionalExecutive2.click();
        break;
      case "Additional Representative 1":
        additionalRepresentative1.click();
        break;
      case "Additional Representative 2":
        additionalRepresentative2.click();
        break;
      case "Division":
        division.click();
        break;
      case "Policy Number":
        policyNumber.click();
        break;
      case "Bill Method":
        billMethod.click();
        break;

      case "Billed Premium/Non Premium":
        billedPremiumNonPremium.click();
        break;
      case "Company Type":
        companyType.click();
        break;
      case "Current Broker Name":
        currentBrokerName.click();
        break;
      case "Customer Firm Name":
        customerFirmName.click();
        break;
      case "Customer First Name":
        customerFirstName.click();
        break;
      case "Customer Last Name":
        customerLastName.click();
        break;
      case "Customer Name":
        customerName.click();
        break;
      case "Customer Number":
        customerNumber.click();
        break;
      case "Department":
        department.click();
        break;
      case "Estimated Revenue":
        estimatedRevenue.click();
        break;
      case "Estimated Revenue Percent":
        estimatedRevenuePercent.click();
        break;
      case "Group":
        group.click();
        break;
      case "How Billed":
        howBilled.click();
        break;
      case "Non Premium":
        nonPremium.click();
        break;
      case "Original Executive":
        originalExecutive.click();
        break;
      case "Original Representative":
        originalRepresentative.click();
        break;
      case "Parent Company":
        parentCompany.click();
        break;
      case "Policy Expiration Date":
        policyExpirationDate.click();
        break;
      case "Policy Id":
        policyId.click();
        break;
      case "Policy Primary Executive":
        policyPrimaryExecutive.click();
        break;
      case "Policy Primary Representative":
        policyPrimaryRepresentative.click();
        break;
      case "Premium":
        premium.click();
        break;
      case "Transaction Effective Date":
        premium.click();
        break;
      case "Transaction Source":
        transactionSource.click();
        break;
      case "Type of Business":
        typeOfBusiness.click();
        break;
      case "Unbilled Agency Commission":
        unbilledAgencyCommission.click();
        break;
      default:
        throw new IllegalArgumentException(String.format("%s filter option not supported", option));
    }
  }
}
