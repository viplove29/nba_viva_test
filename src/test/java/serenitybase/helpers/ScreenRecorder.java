package serenitybase.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;

public class ScreenRecorder {
  private static String recordingUrl;

  private static String getServerUrl() {
    return System.getProperty("screenRecorder.url");
  }

  public static void startRecording() {
    try {
      URL startUrl = new URL(getServerUrl() + "/start");
      HttpURLConnection connection = (HttpURLConnection) startUrl.openConnection();
      connection.setRequestMethod("GET");
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(5000);
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      System.out.println(content);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void stopRecording() {
    try {
      URL startUrl = new URL(getServerUrl() + "/stop");
      HttpURLConnection connection = (HttpURLConnection) startUrl.openConnection();
      connection.setRequestMethod("GET");
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(50000);
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      //  Print in ANSI purple
      System.out.printf("Download recording: \u001B[35m%s\u001B[0m%n", content);
      recordingUrl = content.toString();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void writeMappingToFile(String fileName) {
    try {
      File urlMap = new File("recordings/S3Map.csv");
      FileUtils.writeStringToFile(
          urlMap,
          String.format("%s\t%s\n", fileName, recordingUrl),
          Charset.defaultCharset(),
          true);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void refreshCache() {
    try {
      Runtime.getRuntime()
          .exec(
              "aws storagegateway refresh-cache --file-share-arn arn:aws:storagegateway:us-east-1:065073025992:share/share-99B425F3");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
