package serenitybase.steps.teststeps;

import java.util.List;
import net.serenitybdd.core.Serenity;
import serenitybase.pages.mar.ExportOptionsModal;

public class ExportOptionsTestSteps {
  private ExportOptionsModal exportOptionsModal;

  public void selectExportOptions(List<String> options) {
    exportOptionsModal.selectFileFormat(options.get(0));
    if (options.get(0).toLowerCase().equalsIgnoreCase("excel")) {
      Serenity.setSessionVariable("lastReportType").to("XLS");
    } else if (options.get(0).toLowerCase().equalsIgnoreCase("csv")) {
      Serenity.setSessionVariable("lastReportType").to("CSV");
    }
    exportOptionsModal.selectPermissions(options.get(1));
    exportOptionsModal.includeCoverSheet(options.get(2));
    exportOptionsModal.selectColumns(options.get(3));
    exportOptionsModal.selectFilters(options.get(4));
    exportOptionsModal.export();
  }
}
