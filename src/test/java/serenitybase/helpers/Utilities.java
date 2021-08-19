package serenitybase.helpers;

import static org.awaitility.Awaitility.await;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Utilities {
  public static int downloadsCount;

  public static void simpleSleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

  public static void waitForDownload() {
    await().atMost(90, TimeUnit.SECONDS).until(Utilities::hasFileBeenDownloaded);
    //  This wait is required to select the new file
    simpleSleep(1000);
  }

  public static void openMostRecentExcelFile() {
    openMostRecentFile("xlsx");
  }

  public static void openMostRecentCsvFile() {
    openMostRecentFile("csv");
  }

  private static void openMostRecentFile(String extension) {
    String home = System.getProperty("user.home");
    try {
      List<File> files =
          Arrays.stream(
                  Objects.requireNonNull(
                      new File(String.format("%s/Downloads/", home))
                          .listFiles(
                              (dir, name) ->
                                  name.toLowerCase().endsWith(String.format(".%s", extension)))))
              .collect(Collectors.toList());
      Optional<File> mostRecentExcelFile =
          Arrays.stream(
                  Objects.requireNonNull(
                      new File(String.format("%s/Downloads/", home))
                          .listFiles((dir, name) -> name.toLowerCase().endsWith(".xlsx"))))
              .max(Comparator.comparingLong(File::lastModified));
      if (mostRecentExcelFile.isPresent()) {
        Desktop.getDesktop().open(mostRecentExcelFile.get());
      } else {
        throw new RuntimeException(String.format("No %s files found", extension));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static int countDownloads() {
    return Objects.requireNonNull(
            new File(String.format("%s/Downloads/", System.getProperty("user.home"))).listFiles())
        .length;
  }

  public static void setDownloadsCount() {
    downloadsCount =
        Objects.requireNonNull(
                new File(String.format("%s/Downloads/", System.getProperty("user.home")))
                    .listFiles())
            .length;
  }

  public static boolean hasFileBeenDownloaded() {
    return countDownloads() > downloadsCount;
  }

  public static String getDownloadsPath() {
    try {
      String machineName = InetAddress.getLocalHost().getHostName();
      String downloadsPath;
      if (machineName.contains("EC2AMAZ") && new File("D:").exists()) {
        downloadsPath =
            Objects.requireNonNull(
                Objects.requireNonNull(new File("D:/Users").listFiles(File::isDirectory))[0]
                    .listFiles(x -> x.getName().equals("Downloads")))[0]
                .getPath();
      } else {
        String home = System.getProperty("user.home");
        downloadsPath = new File(String.format("%s/Downloads/", home)).getPath();
      }
      return downloadsPath;
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }
}
