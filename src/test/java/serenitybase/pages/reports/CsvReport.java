package serenitybase.pages.reports;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.primitives.Ints;
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import serenitybase.helpers.Utilities;

public class CsvReport {
  private static final int HEADER_ROW_NUMBER = 0;

  public CsvReport() {
    Utilities.waitForDownload();
  }

  public List<String> getCsvReportHeaders() {
    try {
      String absolutePath = Utilities.getMostRecentFile();
      BOMInputStream bomStream = new BOMInputStream(new FileInputStream(absolutePath));
      return Arrays.asList(new CSVReader(new InputStreamReader(bomStream)).readNext());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<String> getColumnsRangeValuesForFirstCustomerRow(
      String reportFile, String customer, String firstColumn, String lastColumn) {
    List<String> customerReferralData = new ArrayList<>();
    Integer rowNumber = null;
    List<String> headers = new ArrayList<>();
    try {
      String absolutePath = Utilities.getMostRecentFile();
      BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
      CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withTrim());
      List<CSVRecord> records = csvParser.getRecords();

      // Columns headers are taken
      CSVRecord headerRow = records.get(HEADER_ROW_NUMBER);
      int columnsNumber = headerRow.size();
      for (int i = 0; i < columnsNumber; i++) {
        String name = headerRow.get(i);
        headers.add(name);
      }

      // Row number of needed customer is found
      records.remove(0);
      records.remove(0);
      records.remove(0);
      int columnIndex = 0;
      //  TODO: Update this switch for specific MAR reports
      switch (reportFile) {
        case "ReportPolicyBookBus":
          columnIndex = 6;
          break;
        case "ReportProdAnalysis":
          columnIndex = 1;
          break;
        case "ReportCustList":
          columnIndex = 7;
          break;
      }
      for (CSVRecord record : records) {
        if (record.get(columnIndex).equals(customer)) {
          rowNumber = Ints.checkedCast(record.getRecordNumber());
          break;
        }
      }
      if (rowNumber == null) {
        throw new RuntimeException("Could Not Find customer name in csv report: " + customer);
      }

      CSVRecord dataRow = records.get(rowNumber - 4);
      for (int i = headers.indexOf(firstColumn); i < headers.indexOf(lastColumn) + 1; i++) {
        String data = dataRow.get(i);
        customerReferralData.add(data);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return customerReferralData;
  }

  public List<String> getValuesInColumnUnderHeader(String headerName, String reportFile) {
    List<String> values = new ArrayList<>();
    List<String> headers = new ArrayList<>();
    try {
      String absolutePath = Utilities.getMostRecentFile();
      BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
      CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withTrim());
      List<CSVRecord> records = csvParser.getRecords();

      CSVRecord headerRow = records.get(HEADER_ROW_NUMBER);
      IntStream.range(0, headerRow.size()).forEach(idx -> headers.add(headerRow.get(idx)));
      int columnNum = headers.indexOf(headerName);
      if (columnNum == -1) {
        return Collections.emptyList();
      }

      List<CSVRecord> valueRecordsList = records.subList(HEADER_ROW_NUMBER + 1, records.size());
      valueRecordsList.forEach(
          valuesRecords -> {
            values.add(valuesRecords.get(columnNum));
          });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return values;
  }

  public String getAccessColumnValueFromSpecifiedRow(int rowNumber, String reportFile) {
    String desiredValue = "";
    try {
      String absolutePath = Utilities.getMostRecentFile();
      BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
      CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withTrim());
      List<CSVRecord> records = csvParser.getRecords();
      CSVRecord desiredRow = records.get(rowNumber);
      desiredValue = desiredRow.get(5);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return desiredValue;
  }

  public boolean getValueImmediatelyUnderHeader(
      String reportName, String valueToValidate, String headerName) {
    int column = 0;
    try {
      String absolutePath = Utilities.getMostRecentFile();

      BufferedReader readerForHeaders = new BufferedReader(new FileReader(absolutePath));
      CSVParser csvParserForHeaders = new CSVParser(readerForHeaders, CSVFormat.DEFAULT.withTrim());
      CSVRecord headerRow = csvParserForHeaders.getRecords().get(0);

      // Making a second BufferedReader was necessary in order to get another row of values
      BufferedReader readerForValues = new BufferedReader(new FileReader(absolutePath));
      CSVParser csvParserForValues = new CSVParser(readerForValues, CSVFormat.DEFAULT.withTrim());
      CSVRecord valueRow = csvParserForValues.getRecords().get(1);

      int columnsNumber = headerRow.size();
      for (int i = 0; i < columnsNumber; i++) {
        // when the "LOB" header is found, record column number
        if (headerRow.get(i).equals(headerName)) {
          column = i;
        }
      }
      // go down one row, go to the saved column number and check the value
      return valueRow.get(column).equals(valueToValidate);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }
}
