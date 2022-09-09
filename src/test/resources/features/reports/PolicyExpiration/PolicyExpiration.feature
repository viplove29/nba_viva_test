Feature: Policy Expiration

  As A MAR user,
  I want the ability to use generate a Policy Expiration report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Policy Expiration" Report Template


  @TC-343
  @sync-38702524
  @PolicyExpiration
  Scenario: "Policy Expiration" report Filters option functions properly
    And the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "107"
    And the user clicks on the Apply button
    Then the user verifies that all the "Customer Name" values on the Report start with "107"

  @TC-342
  @sync-38702522
  @PolicyExpiration
  Scenario: "Policy Expiration" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Customer Name" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Customer Name" are in ascending order
    And the user clicks the cancel button


  @TC-346
  @sync-38702533
  @PolicyExpiration
  Scenario: Add Policy Expiration summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Total Cost          |
      | Policy Branch       |
      | Writing Company     |
      | Company Underwriter |
    And the user names the summary "policyExpirationSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "policyExpirationSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "policyExpirationSummaryTemplate"
    And the user selects "policyExpirationSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "policyExpirationSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "policyExpirationSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

  @TC-345
  @sync-38702532
  @PolicyExpiration
  Scenario: Policy Expiration Check Columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                              |
      | Renewal Contact Name                       |
      | Renewal Contact Phone Number - Residential |
      | Renewal Contact Phone Number - Business    |
      | Renewal Contact Email                      |
      | Policy Number                              |
      | Policy Effective Date                      |
      | Policy Expiration Date                     |
      | Parent Company                             |
      | Primary Executive                          |
      | Primary Representative                     |
      | Policy Status                              |
      | Policy Type LOB                            |
      | Bill Method                                |
      | Business With Agency                       |
      | Total Cost                                 |
      | Policy Full Term Premium                   |
      | Line Of Business                           |
    And the user selects "Policy Type LOB" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Policy Type LOB |

  @TC-575
  @sync-56127671
  @Version-22R2
  @PolicyExpiration
  @SmokeTest
  Scenario: Verify Functionality of BU Filters in Policy Expiration Report in MAR
    And the user expands "Business Unit" filter
    And the user selects "Division One" from "Division" category and closes the dropdown
    And the user selects the "All" option under the Status section
    Then the user verifies all the options in the dropdown from the "Branch" category are in the "Division One" division
    Then the user verifies all the options in the dropdown from the "Department" category are in the "Division One" division
    Then the user verifies all the options in the dropdown from the "Group" category are in the "Division One" division

  @TC-576
  @sync-56127692
  @Version-22R2
  @PolicyExpiration
  Scenario: Data Validation Using New BU Filters for the Policy Expiration Report in MAR
    And the user expands "Business Unit" filter
    And the user selects "Division One" from "Division" category and closes the dropdown
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "Division One"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "Division One"

  @TC-580
  @sync-56156226
  @PolicyExpiration
  Scenario: Verify Functionality of Company Filters in Policy Expiration Report in MAR
    And the user expands "Company" filter
    And the user clicks the "Select Multiple" option under the "Written" section
    And the user searches for "AFCO" in the dropdown under the Company "Written" section and selects the top option
    Then the user verifies the dropdown value in the "Written" section is set to "AFCO - AFCO - AFCO"
    And the user searches for "10194 - 10194 - Progressive" in the dropdown under the Company "Written" section and selects the top option
    Then the user verifies the dropdown value in the "Written" section is set to "2 items"
    And the user clicks the "Select Multiple" option under the "Parent" section
    And the user searches for "Progressive" in the dropdown under the Company "Parent" section and selects the top option
    Then the user verifies the dropdown value in the "Parent" section is set to "Progressive"
    And the user searches for "AFCO" in the dropdown under the Company "Parent" section and selects the top option
    Then the user verifies the dropdown value in the "Parent" section is set to "2 items"
    And the user selects "Insurance" from the company type dropdown
    And the user clicks the "Select Multiple" option under the "Written" section
    And the user searches for "ABC Insurance - ABC - ABC Insurance" in the dropdown under the Company "Written" section and selects the top option
    Then the user verifies the dropdown value in the "Written" section is set to "ABC Insurance - ABC - ABC Insurance"
    And the user searches for "10194 - 10194 - Progressive" in the dropdown under the Company "Written" section and selects the top option
    Then the user verifies the dropdown value in the "Written" section is set to "2 items"
    And the user clicks the "Select Multiple" option under the "Parent" section
    And the user searches for "Progressive" in the dropdown under the Company "Parent" section and selects the top option
    Then the user verifies the dropdown value in the "Parent" section is set to "Progressive"
    And the user searches for "ABC Insurance" in the dropdown under the Company "Parent" section and selects the top option
    Then the user verifies the dropdown value in the "Parent" section is set to "2 items"
    And the user selects "Brokerage" from the company type dropdown
    And the user clicks the "Select Multiple" option under the "Written" section
    And the user searches for "Brokerage 1 - BROK1 - Brokerage 1" in the dropdown under the Company "Written" section and selects the top option
    Then the user verifies the dropdown value in the "Written" section is set to "Brokerage 1 - BROK1 - Brokerage 1"
    And the user searches for "BrokerageParent - BrokeP - BrokerageParent" in the dropdown under the Company "Written" section and selects the top option
    Then the user verifies the dropdown value in the "Written" section is set to "2 items"
    And the user clicks the "Select Multiple" option under the "Parent" section
    And the user searches for "Brokerage 1" in the dropdown under the Company "Parent" section and selects the top option
    Then the user verifies the dropdown value in the "Parent" section is set to "Brokerage 1"
    And the user searches for "BrokerageParent" in the dropdown under the Company "Parent" section and selects the top option
    Then the user verifies the dropdown value in the "Parent" section is set to "2 items"

  @TC-577
  @sync-56127732
  @Version-22R2
  @PolicyExpiration
  Scenario: Verify Functionality of Active/Inactive/All BU Filters in Policy Expiration Report in MAR
    And the user expands "Business Unit" filter
    Then the user searches for "JJ Inactive" in the "Division" section dropdown and verifies all of the statuses are equal to "Inactive"
    Then the user searches for "JJ Active" in the "Division" section dropdown and verifies all of the statuses are equal to "Active"
    And the user selects the "Active" option under the Status section
    Then the user searches for "JJ Inactive" in the "Division" section dropdown and verifies the name does not show
    Then the user searches for "JJ Active" in the "Division" section dropdown and verifies all of the statuses are equal to "Active"
    And the user selects "JJ Active" from "Division" category
    And the user selects the "Active" option under the Status section
    Then the user verifies all of the statuses in the "Branch" section dropdown are set to "Active"
    Then the user verifies all of the statuses in the "Department" section dropdown are set to "Active"
    Then the user verifies all of the statuses in the "Group" section dropdown are set to "Active"
    And the user selects the "Inactive" option under the Status section
    Then the user searches for "JJ Active" in the "Division" section dropdown and verifies the name does not show
    Then the user searches for "JJ Inactive" in the "Division" section dropdown and verifies all of the statuses are equal to "Inactive"
    And the user selects "JJ Inactive" from "Division" category
    And the user selects the "Inactive" option under the Status section
    Then the user verifies all of the statuses in the "Branch" section dropdown are set to "Inactive"
    Then the user verifies all of the statuses in the "Department" section dropdown are set to "Inactive"
    Then the user verifies all of the statuses in the "Group" section dropdown are set to "Inactive"
    And the user selects the "All" option under the Status section
    And the user selects "JJ Active" from "Division" category
    And the user selects the "All" option under the Status section
    Then the user verifies all of the statuses in the "Branch" section dropdown are set to "Active"
    Then the user verifies all of the statuses in the "Department" section dropdown are set to "Active"
    Then the user verifies all of the statuses in the "Group" section dropdown are set to "Active"

  @TC-578
  @sync-56127754
  @Version-22R2
  @PolicyExpiration
  Scenario: Data Validation Using New BU Active/Inactive Filters - Policy Expiration (MAR)
    And the user selects Current quarter to date date range from list
    And the user expands "Business Unit" filter
    And the user selects the "All" option under the Status section
    And the user selects "JJ Active" from "Division" category and closes the dropdown
    And the user selects "JJ Active 1" from "Branch" category and closes the dropdown
    And the user selects "JJ Active 1" from "Department" category and closes the dropdown
    And the user selects "JJ Active 1" from "Group" category and closes the dropdown
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "JJ Active"
    Then the user verifies all of the data under the "Branch" header in the file from the generated report is set to "JJ Active 1"
    Then the user verifies all of the data under the "Department" header in the file from the generated report is set to "JJ Active 1"
    Then the user verifies all of the data under the "Group" header in the file from the generated report is set to "JJ Active 1"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "JJ Active"
    Then the user verifies all of the data under the "Branch" header in the file from the generated report is set to "JJ Active 1"
    Then the user verifies all of the data under the "Department" header in the file from the generated report is set to "JJ Active 1"
    Then the user verifies all of the data under the "Group" header in the file from the generated report is set to "JJ Active 1"
    And the user selects "Policy Expiration" Report Template
    And the user expands "Business Unit" filter
    And the user selects the "Inactive" option under the Status section
    And the user selects "JJ Inactive" from "Division" category and closes the dropdown
    And the user selects "JJ Inactive 1" from "Branch" category and closes the dropdown
    And the user selects "JJ Inactive 1" from "Department" category and closes the dropdown
    And the user selects "JJ Inactive 1" from "Group" category and closes the dropdown
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "JJ Inactive"
    Then the user verifies all of the data under the "Branch" header in the file from the generated report is set to "JJ Inactive 1"
    Then the user verifies all of the data under the "Department" header in the file from the generated report is set to "JJ Inactive 1"
    Then the user verifies all of the data under the "Group" header in the file from the generated report is set to "JJ Inactive 1"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "JJ Inactive"
    Then the user verifies all of the data under the "Branch" header in the file from the generated report is set to "JJ Inactive 1"
    Then the user verifies all of the data under the "Department" header in the file from the generated report is set to "JJ Inactive 1"
    Then the user verifies all of the data under the "Group" header in the file from the generated report is set to "JJ Inactive 1"

  @TC-581
  @sync-56168910
  @Version-22R2
  @PolicyExpiration
  Scenario: Data Validation Using New Company Filters for the Policy Expiration Report in MAR
    And the user selects Current quarter to date date range from list
    And the user expands "Company" filter
    And the user clicks the "Select Multiple" option under the "Parent" section
    And the user searches for "Progressive" in the dropdown under the Company "Parent" section and selects the top option
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Parent Company" header in the file from the generated report is set to "Progressive"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Parent Company" header in the file from the generated report is set to "Progressive"
    And the user selects "Policy Expiration" Report Template
    And the user selects Current quarter to date date range from list
    And the user expands "Company" filter
    And the user selects "Brokerage" from the company type dropdown
    And the user clicks the "Select Multiple" option under the "Parent" section
    And the user searches for "JJ Brokerage 1" in the dropdown under the Company "Parent" section and selects the top option
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Parent Company" header in the file from the generated report is set to "JJ Brokerage 1"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Parent Company" header in the file from the generated report is set to "JJ Brokerage 1"

  @TC-573
  @sync-56127390
  @Version-22R2
  @PolicyExpiration
  Scenario: Verify Functionality of Personnel Filters in Policy Expiration Report in MAR
    And the user expands "Personnel" filter
    And the user clicks the "Select Multiple" option under the "Executive" section
    And the user searches for "Jenkins ,Josh" in the dropdown under the "Executive" section and selects the top option
    Then the user verifies the dropdown value in the "Executive" section is set to "Jenkins ,Josh"
    And the user searches for "!Aleks" in the dropdown under the "Executive" section and selects the top option
    Then the user verifies the dropdown value in the "Executive" section is set to "2 items"
    And the user clicks the "All" option under the "Executive" section
    And the user clicks the "Select Multiple" option under the "Representative" section
    And the user searches for "CSR" in the dropdown under the "Representative" section and selects the top option
    Then the user verifies the dropdown value in the "Representative" section is set to "CSR"
    And the user searches for "!Aleks" in the dropdown under the "Representative" section and selects the top option
    Then the user verifies the dropdown value in the "Representative" section is set to "2 items"
    And the user clicks the "All" option under the "Representative" section
    And the user clicks the "Select Multiple" option under the "Broker" section
    And the user searches for "BCD Broker" in the dropdown under the "Broker" section and selects the top option
    Then the user verifies the dropdown value in the "Broker" section is set to "BCD Broker"
    And the user searches for "ABC Broker" in the dropdown under the "Broker" section and selects the top option
    Then the user verifies the dropdown value in the "Broker" section is set to "2 items"

  @TC-574
  @sync-56127414
  @Version-22R2
  @PolicyExpiration
  Scenario: Data Validation Using New Personnel Filters for the Policy Expiration Report in MAR
    And the user sets date range from "01/01/2010" to today
    And the user expands "Personnel" filter
    And the user clicks the "Select Multiple" option under the "Executive" section
    And the user searches for "Jenkins ,Josh" in the dropdown under the "Executive" section and selects the top option
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Customer Executive" header in the file from the generated report is set to "Jenkins, Josh"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Customer Executive" header in the file from the generated report is set to "Jenkins, Josh"
    And the user selects "Policy Expiration" Report Template
    And the user sets date range from "01/01/2010" to today
    And the user expands "Personnel" filter
    And the user clicks the "Select Multiple" option under the "Executive" section
    And the user searches for "Jenkins ,Josh" in the dropdown under the "Executive" section and selects the top option
    And the user searches for "!Aleks" in the dropdown under the "Executive" section and selects the top option
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies the data under the "Customer Executive" header in the file from the generated report has these values
      | Jenkins, Josh   |
      | !Aleks          |
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies the data under the "Customer Executive" header in the file from the generated report has these values
      | Jenkins, Josh   |
      | !Aleks          |
    And the user selects "Policy Expiration" Report Template
    And the user sets date range from "01/01/2010" to today
    And the user expands "Personnel" filter
    And the user clicks the "Select Multiple" option under the "Representative" section
    And the user searches for "CSR" in the dropdown under the "Representative" section and selects the top option
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Customer Representative" header in the file from the generated report is set to "CSR -CSR"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Customer Representative" header in the file from the generated report is set to "CSR -CSR"
    And the user selects "Policy Expiration" Report Template
    And the user sets date range from "01/01/2010" to today
    And the user expands "Personnel" filter
    And the user clicks the "Select Multiple" option under the "Broker" section
    And the user searches for "BCD Broker" in the dropdown under the "Broker" section and selects the top option
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Broker Name" header in the file from the generated report is set to "BCD Broker"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Broker Name" header in the file from the generated report is set to "BCD Broker"

