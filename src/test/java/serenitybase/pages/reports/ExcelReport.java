package serenitybase.pages.reports;

import static serenitybase.helpers.Utilities.getMostRecentFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Streams;
import serenitybase.helpers.Utilities;

public class ExcelReport extends PageObject {
  private static final double THRESHOLD = .0001;

  public ExcelReport() {
    Utilities.waitForDownload();
  }

  /**
   * This method uses Apache POI lib, due to that Appium is very unstable if it used as Excel parser
   *
   * @return List of report headers(from the first row of the report)
   */
  public java.util.List<String> getReportHeaders() {
    Workbook workbook = getCurrentReportWorkbook();
    java.util.List<String> headers = new ArrayList<>();
    Iterator<Sheet> sheetIterator = workbook.sheetIterator();
    while (sheetIterator.hasNext()) {
      Row row = sheetIterator.next().getRow(0);
      row.forEach(r -> headers.add(r.getStringCellValue()));
    }
    return headers;
  }

  public java.util.List<String> getReportHeaders(String sheetName) {
    java.util.List<String> headers = new ArrayList<>();
    Sheet sheet = setExcelFile(sheetName);
    Row row = sheet.getRow(0);
    row.forEach(r -> headers.add(r.getStringCellValue()));
    return headers.stream().map(StringUtils::normalizeSpace).collect(Collectors.toList());
  }

  public String getReportPath() {
    return getMostRecentFile();
  }

  public XSSFSheet setExcelFile(String SheetName) {
    try {
      String reportPath = getReportPath();
      int count = 0;
      OPCPackage excelFile = null;
      do {
        try {
          excelFile = OPCPackage.open(reportPath);
          break;
        } catch (NotOfficeXmlFileException e) {
          Utilities.simpleSleep(1000);
          count++;
          if (count == 5) {
            throw new RuntimeException(
                "Opening file Error: " + reportPath + " Message: " + e.getMessage());
          }
        }
      } while (count < 5);
      XSSFWorkbook excelTargetBook = new XSSFWorkbook(excelFile);
      return excelTargetBook.getSheet(SheetName);
    } catch (InvalidFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public java.util.List<String> getValuesInColumnUnderHeader(String headerName, String sheetName) {
    java.util.List<String> headers = new ArrayList<>();
    Sheet sheet = setExcelFile(sheetName);
    Row headerRow = sheet.getRow(0);
    headerRow.forEach(r -> headers.add(r.getStringCellValue()));

    int column = headers.indexOf(headerName);
    if (column == -1) {
      return Collections.emptyList();
    }

    java.util.List<String> values = new ArrayList<>();
    Iterator<Row> rowIterator = sheet.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      if (row.getRowNum() == 0) {
        continue;
      }
      Cell cell = row.getCell(column);
      if (cell != null && !cell.getStringCellValue().trim().isEmpty()) {
        values.add(cell.getStringCellValue());
      }
    }
    return values;
  }

  public java.util.List<String> getValuesInColumnUnderSubHeader(
      String subHeaderName, String sheetName) {
    java.util.List<String> headers = new ArrayList<>();
    Sheet sheet = setExcelFile(sheetName);
    Row headerRow = sheet.getRow(1);
    headerRow.forEach(r -> headers.add(r.getStringCellValue()));

    int column = headers.indexOf(subHeaderName);
    if (column == -1) {
      return Collections.emptyList();
    }

    java.util.List<String> values = new ArrayList<>();
    Iterator<Row> rowIterator = sheet.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      if (row.getRowNum() == 0 || row.getRowNum() == 1) {
        continue;
      }
      String cell = row.getCell(column).toString();
      if (cell != null) {
        values.add(cell);
      }
    }
    return values;
  }

  public java.util.List<Double> getNumericOrNullValuesInColumnUnderHeader(
      String headerName, String sheetName) {
    Sheet sheet = setExcelFile(sheetName);
    java.util.List<String> headers = getReportHeaders(sheetName);
    FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();

    int column = headers.indexOf(headerName);
    if (column == -1) {
      return Collections.emptyList();
    }
    java.util.List<Double> values = new ArrayList<>();
    Iterator<Row> rowIterator = sheet.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      if (row.getRowNum() == 0) {
        continue;
      }
      Cell cell = row.getCell(column);
      if (cell == null) {
        values.add(null);
      } else if (cell.getCellType() == CellType.NUMERIC) {
        values.add(cell.getNumericCellValue());
      } else if (cell.getCellType() == CellType.FORMULA
          && cell.getCachedFormulaResultType() == CellType.NUMERIC) {
        evaluator.evaluateFormulaCell(cell);
        values.add(cell.getNumericCellValue());
      }
    }
    return values;
  }

  public java.util.List<CellType> getCellTypesUnderColumnHeader(
      String headerName, String sheetName) {
    Sheet sheet = setExcelFile(sheetName);
    java.util.List<String> headers = getReportHeaders(sheetName);
    int column = headers.indexOf(headerName);
    if (column == -1) {
      throw new RuntimeException("Column with header '" + headerName + "' is absent");
    }
    java.util.List<CellType> cellTypes = new ArrayList<>();
    Iterator<Row> rowIterator = sheet.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      if (row.getRowNum() == 0) {
        continue;
      }
      Cell cell = row.getCell(column);
      if (cell == null) {
        cellTypes.add(null);
      } else {
        cellTypes.add(cell.getCellType());
      }
    }
    return cellTypes;
  }

  public java.util.List<Map<String, String>> getMapOfRows(String sheetName) {
    Sheet sheet = setExcelFile(sheetName);
    java.util.List<String> headers = getReportHeaders(sheetName);
    FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
    evaluator.evaluateAll();
    return Streams.stream(sheet)
        .map(this::getValuesFromAllCellsInRow)
        .filter(CollectionUtils::isNotEmpty)
        .map(cellsValues -> combineTwoListsIntoMap(headers, cellsValues))
        .collect(Collectors.toList());
  }

  private Map<String, String> combineTwoListsIntoMap(
      java.util.List<String> keys, java.util.List<String> values) {
    if (keys.size() <= values.size()) {
      return IntStream.range(0, keys.size())
          .boxed()
          .collect(Collectors.toMap(keys::get, values::get, (value1, value2) -> value1));
    } else {
      return Collections.emptyMap();
    }
  }

  private java.util.List<String> getValuesFromAllCellsInRow(Row row) {
    return Streams.stream(row).map(this::getCellValue).collect(Collectors.toList());
  }

  private String getCellValue(Cell cell) {
    boolean isFormula = cell.getCellType().equals(CellType.FORMULA);
    return isFormula ? getValueFromFormula(cell) : cell.toString();
  }

  private String getValueFromFormula(Cell cell) {
    return String.format("%.1f", cell.getNumericCellValue());
  }

  public boolean validateValuesInColumnUnderHeader(
      String expectedValue, String headerName, String sheetName) {
    java.util.List<String> values = getValuesInColumnUnderHeader(headerName, sheetName);
    return values.stream().allMatch(t -> t.equals(expectedValue));
  }

  public boolean validateValueExistsInColumnUnderHeader(
      String expectedValue, String headerName, String sheetName) {
    java.util.List<String> values = getValuesInColumnUnderHeader(headerName, sheetName);
    return values.contains(expectedValue);
  }

  protected Workbook getCurrentReportWorkbook() {
    String reportPath = getReportPath();
    //    closeExcelFile();
    Utilities.simpleSleep(200);
    return getWorkbookForReport(reportPath);
  }

  protected String getReportFileName(String windowTitle) {
    // This pattern match report name from window title
    Pattern pattern = Pattern.compile("^(.*) - Excel");
    Matcher matcher = pattern.matcher(windowTitle);
    if (!matcher.find()) {
      throw new RuntimeException(
          "Can't get the report file name from the window title: " + windowTitle);
    }
    return matcher.group(1);
  }

  private Workbook getWorkbookForReport(String reportPath) {
    try {
      return WorkbookFactory.create(new File(reportPath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected int getRowsInSheetSatisfyingMethod(
      Workbook workbook, String sheetName, Function<Row, Boolean> rowValidationFunction) {
    int numberOfRows = 0;
    Sheet sheet = workbook.getSheet(sheetName);
    Iterator<Row> rows = sheet.rowIterator();
    while (rows.hasNext()) {
      Row row = rows.next();
      if (rowValidationFunction.apply(row)) {
        numberOfRows++;
      }
    }
    return numberOfRows;
  }

  protected boolean columnIsValueInRow(Row row, int columnIndex, String value) {
    Cell column = row.getCell(columnIndex);
    if (column != null) {
      return column.toString().equals(value);
    }
    return false;
  }

  public boolean verifyMonthsAndYearInHeaders() {
    java.util.List<String> requiredHeaders = new ArrayList<>();
    String[] months = new DateFormatSymbols().getMonths();
    java.util.List<String> monthList = Arrays.asList(months);
    int monthValue = monthList.indexOf(Serenity.sessionVariableCalled("reportMonth").toString());
    int yearValue = Integer.parseInt(Serenity.sessionVariableCalled("reportYear").toString());
    for (int i = 0; i < 12; i++) {
      if (monthValue < 0) {
        monthValue = monthValue + 12;
        yearValue = yearValue - 1;
      }
      String header = "All Actual Billed Revenue " + monthList.get(monthValue) + " " + yearValue;
      requiredHeaders.add(header);
      monthValue--;
    }
    java.util.List<String> headers = getReportHeaders();
    return headers.containsAll(requiredHeaders);
  }

  public boolean validateListOfValuesInColumnUnderHeader(
      java.util.List<String> expectedValues, String headerName, String sheetName) {
    java.util.List<String> headers = new ArrayList<>();
    Sheet sheet = setExcelFile(sheetName);
    Row headerRow = sheet.getRow(0);
    headerRow.forEach(r -> headers.add(r.getStringCellValue()));

    int column = headers.indexOf(headerName);
    if (column == -1) {
      return false;
    }

    Iterator<Row> rowIterator = sheet.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      if (row.getRowNum() == 0) {
        continue;
      }
      Cell cell = row.getCell(column);
      if (cell != null && !cell.getStringCellValue().trim().isEmpty()) {
        if (!expectedValues.contains(cell.getStringCellValue())) {
          return false;
        }
      }
    }
    return true;
  }

  public String getRowAndColumnValue(int rowNum, int columnNum, String sheetName) {
    Sheet sheet = setExcelFile(sheetName);
    Row row = sheet.getRow(rowNum);
    Cell cell = row.getCell(columnNum);
    return cell.getStringCellValue();
  }

  public boolean rowAndColumnValueIsEqualTo(
      int rowNum, int columnNum, String sheetName, String rowValue) {
    return getRowAndColumnValue(rowNum, columnNum, sheetName).contentEquals(rowValue);
  }

  public int getRowAndColumnNumberValue(int rowNum, int columnNum, String sheetName) {
    Sheet sheet = setExcelFile(sheetName);
    Row row = sheet.getRow(rowNum);
    Cell cell = row.getCell(columnNum);
    if (cell.getCellType() == CellType.STRING) {
      String cellString = cell.getStringCellValue().replace(",", "");
      return Integer.parseInt(cellString);
    } else if (cell.getCellType() == CellType.NUMERIC) {
      return (int) cell.getNumericCellValue();
    } else if (cell.getCellType() == CellType.FORMULA
        && cell.getCachedFormulaResultType() == CellType.NUMERIC) {
      FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
      evaluator.evaluateFormulaCell(cell);
      return (int) cell.getNumericCellValue();
    } else if (cell.getCellType() == CellType.FORMULA
        && cell.getCachedFormulaResultType() == CellType.STRING) {
      FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
      evaluator.evaluateFormulaCell(cell);
      String cellString = cell.getStringCellValue().replace(",", "");
      return Integer.parseInt(cellString);
    } else {
      throw new RuntimeException("Cell format is not supported to obtain the value from it");
    }
  }

  public boolean rowAndColumnValueContains(
      int rowNum, int columnNum, String sheetName, String rowValue) {
    Sheet sheet = setExcelFile(sheetName);
    Row row = sheet.getRow(rowNum);
    Cell cell = row.getCell(columnNum);
    return cell.getStringCellValue().contains(rowValue);
  }

  public boolean validateCorrespondingSetsOfValuesInColumnsUnderTwoHeaders(
      Map.Entry<String, String> headerEntry, Map<String, Double> expectedValues, String sheetName) {
    java.util.List<String> headers = new ArrayList<>();
    Sheet sheet = setExcelFile(sheetName);
    Row headerRow = sheet.getRow(0);
    headerRow.forEach(r -> headers.add(r.getStringCellValue()));

    int columnOne = headers.indexOf(headerEntry.getKey());
    int columnTwo = headers.indexOf(headerEntry.getValue());
    if (columnOne == -1 || columnTwo == -1) {
      System.out.println("All column headers were not found");
      return false;
    }

    Iterator<Row> rowIterator = sheet.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      if (row.getRowNum() == 0) {
        continue;
      }
      Cell cellOne = row.getCell(columnOne);
      Cell cellTwo = row.getCell(columnTwo);
      if (cellOne != null && !cellOne.getStringCellValue().trim().isEmpty()) {
        String cellOneKey = cellOne.getStringCellValue();
        if (expectedValues.containsKey(cellOneKey)) {
          Double cellTwoValue = cellTwo.getNumericCellValue();
          Double expectedDouble = expectedValues.get(cellOneKey);
          if (Math.abs(expectedDouble - cellTwoValue) >= THRESHOLD) {
            return false;
          }
        } else {
          System.out.println("First Column contained value not in data table");
          return false;
        }
      }
    }
    return true;
  }

  public boolean isSheetProtected(String sheetName) {
    return setExcelFile(sheetName).getProtect();
  }

  public int getRowForNewPolicy(String sheetName) {
    java.util.List<String> policies = getValuesInColumnUnderHeader("Policy Number", sheetName);
    return policies.indexOf(Serenity.sessionVariableCalled("newPolicyName")) + 1;
  }

  public java.util.List<Integer> getRowsForNewPolicy(String sheetName) {
    java.util.List<String> policies = getValuesInColumnUnderHeader("Policy Number", sheetName);
    String policyName = Serenity.sessionVariableCalled("newPolicyName");
    java.util.List<Integer> policyRows = new ArrayList<>();
    IntStream.range(0, policies.size())
        .forEach(
            idx -> {
              if (policyName.equals(policies.get(idx))) {
                policyRows.add(idx + 1);
              }
            });
    return policyRows;
  }

  public java.util.List<Integer> getRowsForCustomerName(String sheetName, String customer) {
    java.util.List<String> customers = getValuesInColumnUnderHeader("Customer", sheetName);
    java.util.List<Integer> customerRows = new ArrayList<>();
    IntStream.range(0, customers.size())
        .forEach(
            idx -> {
              if (customer.equals(customers.get(idx))) {
                customerRows.add(idx + 1);
              }
            });
    return customerRows;
  }

  public int getColumnForHeader(String header, String sheetName) {
    java.util.List<String> headers = getReportHeaders(sheetName);
    return headers.indexOf(header);
  }

  public String getCellFormulaUnderColumnHeader(String columnHeader, int rowNum, String sheetName) {
    Sheet sheet = setExcelFile(sheetName);
    Row row = sheet.getRow(rowNum);
    Cell cell = row.getCell(getColumnForHeader(columnHeader, sheetName));
    return cell.getCellFormula();
  }

  public int getNumberOfRowsWithValueUnderColumn(String sheet, String value, String column) {
    java.util.List<Map<String, String>> rows = getMapOfRows(sheet);
    java.util.List<Map<String, String>> newRows =
        rows.stream().filter(row -> row.containsKey(column)).collect(Collectors.toList());
    return (int)
        IntStream.range(0, newRows.size())
            .filter(i -> newRows.get(i).get(column).equals(value))
            .count();
  }

  public String getCellValueOfRowWithSpecifiedLOB(
      String cellHeader, String desiredLineOfBusiness, String policy, String sheet) {
    java.util.List<Map<String, String>> rows = getMapOfRows(sheet);
    String lineOfBusinessColumnHeader = "Line of Business / Charge Type";
    java.util.List<Map<String, String>> newRows =
        rows.stream()
            .filter(
                row ->
                    row.containsKey("Policy Number")
                        && row.get("Policy Number").equalsIgnoreCase(policy))
            .collect(Collectors.toList());
    return newRows.stream()
        .filter(row -> row.get(lineOfBusinessColumnHeader).equals(desiredLineOfBusiness))
        .findFirst()
        .map(row -> row.get(cellHeader))
        .get();
  }

  public java.util.List<String> getEffectiveDatesOfPolicies() {
    return getValuesInColumnUnderHeader("Effective Date", "Revenue Recognition P&C-(Edit)");
  }

  public String getReportParameterFromCoverSheet(String reportParameter) {
    DataFormatter dataFormatter = new DataFormatter();
    HashMap<String, String> coverSheet = new HashMap<>();
    Sheet sheet = setExcelFile("Cover Sheet");
    for (int i = 0; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);
      coverSheet.put(
          dataFormatter.formatCellValue(row.getCell(0)),
          dataFormatter.formatCellValue(row.getCell(1)));
    }
    return coverSheet.get(reportParameter);
  }

  public boolean canColumnCanBeResized(String sheetName, int columnNum) {
    Sheet sheet = setExcelFile(sheetName);
    int newWidth = sheet.getColumnWidth(columnNum) * 2;
    sheet.setColumnWidth(columnNum, newWidth);
    int actualWidth = sheet.getColumnWidth(columnNum);
    return newWidth == actualWidth && 0 != actualWidth;
  }

  public boolean canRowCanBeResized(String sheetName, int rowNum) {
    Sheet sheet = setExcelFile(sheetName);
    short newHeight = (short) (sheet.getRow(0).getHeight() + 10);
    sheet.getRow(rowNum).setHeight(newHeight);
    float actualHeight = sheet.getRow(rowNum).getHeight();
    return newHeight == actualHeight && 0 != actualHeight;
  }

  public boolean isColumnAndRowAllowWrapText(String sheetName, int rowNum, int columnNum) {
    Sheet sheet = setExcelFile(sheetName);
    sheet.getColumnStyle(columnNum).setWrapText(true);
    return sheet.getColumnStyle(columnNum).getWrapText();
  }

  public java.util.List<String> getReportSheetNames() {
    try {
      java.util.List<String> sheetNames = new ArrayList<String>();
      String reportPath = getReportPath();
      FileInputStream ExcelFile = new FileInputStream(reportPath);
      XSSFWorkbook workbook = new XSSFWorkbook(ExcelFile);
      int numberOfSheets = workbook.getNumberOfSheets();
      for (int i = 0; i < numberOfSheets; i++) {
        sheetNames.add(workbook.getSheetName(i));
      }
      return sheetNames;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public java.util.List<String> getCellValuesForTheRow(String sheetName, int rowNumber) {
    List<String> values = new ArrayList<>();
    Sheet sheet = setExcelFile(sheetName);
    Row row = sheet.getRow(rowNumber);
    for (Cell cell : row) {
      if (cell.getCellType() == CellType.STRING) {
        values.add(cell.getStringCellValue());
      } else if (cell.getCellType() == CellType.NUMERIC) {
        values.add(
            NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(cell.getNumericCellValue())
                .replace(",", ""));
      } else {
        System.out.println("Cell format is not supported to obtain the value from it");
      }
    }
    return values;
  }

  public int getLastRowNum(String sheetName) {
    Sheet sheet = setExcelFile(sheetName);
    return sheet.getLastRowNum();
  }

  public String isDesiredRowAndColumnContainsPositiveValue(
      String subHeaderName, String sheetName, int numberOfRow) {
    return getValuesInColumnUnderSubHeader(subHeaderName, sheetName).get(numberOfRow);
  }
}
