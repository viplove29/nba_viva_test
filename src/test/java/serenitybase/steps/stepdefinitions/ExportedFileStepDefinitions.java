package serenitybase.steps.stepdefinitions;

import io.cucumber.java.en.Then;
import java.util.List;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import serenitybase.steps.teststeps.ExportedFileTestSteps;

public class ExportedFileStepDefinitions {
  @Steps private ExportedFileTestSteps exportedFileTestSteps;

  @Then("the excel report has the same number of rows as the web report")
  public void the_excel_report_has_the_same_number_of_rows_as_the_web_report() {
    exportedFileTestSteps.verifyNumberOfRows();
  }

  @Then("the exported report has {string} permission if applicable")
  public void the_exported_report_has_permission(String permission) {
    if (StringUtils.isNotBlank(permission)) {
      exportedFileTestSteps.verifySheetPermissions(permission);
    }
  }

  @Then("the exported report is a {string} file format")
  public void the_exported_report_has_file_format(String fileFormat) {
    exportedFileTestSteps.verifyReportHasFileFormat(fileFormat);
  }

  @Then("the exported report has the same columns as the web report")
  public void the_exported_report_has_the_same_columns_as_the_web_report() {
    exportedFileTestSteps.verifyColumns();
  }

  @Then("the exported report has a {string} cover sheet if applicable")
  public void the_exported_report_has_a_cover_sheet_if_applicable(String includeCoverSheet) {
    if (StringUtils.isNotBlank(includeCoverSheet)) {
      exportedFileTestSteps.verifyCoverSheet(Boolean.parseBoolean(includeCoverSheet));
    }
  }

  @Then(
      "the user verifies all of the data under the {string} header in the file from the generated report is set to {string}")
  public void the_user_verifies_the_data_under_header_in_exported_file_from_generated_report(
      String header, String expectedValue) {
    exportedFileTestSteps.verifyDataUnderHeaderForGeneratedReport(header, expectedValue);
  }

  @Then(
      "the user verifies the data under the {string} header in the file from the generated report has these values")
  public void
      the_user_verifies_the_data_under_header_in_exported_file_from_generated_report_for_list_of_strings(
          String header, List<String> expectedValues) {
    exportedFileTestSteps.verifyDataUnderHeaderForGeneratedReportForStringList(
        header, expectedValues);
  }
}
