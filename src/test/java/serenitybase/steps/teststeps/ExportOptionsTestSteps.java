package serenitybase.steps.teststeps;

import java.util.List;
import serenitybase.pages.mar.ExportOptionsModal;

public class ExportOptionsTestSteps {
  private ExportOptionsModal exportOptionsModal;

  public void selectExportOptions(List<String> options) {
    exportOptionsModal.selectFileFormat(options.get(0));
    exportOptionsModal.selectPermissions(options.get(1));
    exportOptionsModal.includeCoverSheet(options.get(2));
    exportOptionsModal.selectColumns(options.get(3));
    exportOptionsModal.selectFilters(options.get(4));
    exportOptionsModal.export();
  }
}
