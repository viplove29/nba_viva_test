package serenitybase.helpers;

import static org.awaitility.Awaitility.await;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class Utilities {
  private static int downloadsCount;
  private static String mostRecentFile;
  private static final File downloads =
      new File(String.format("%s/Downloads/", System.getProperty("user.home")));

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
    setMostRecentFile();
  }

  public static int countDownloads() {
    return Objects.requireNonNull(downloads.listFiles()).length;
  }

  public static void setDownloadsCount() {
    downloadsCount = Objects.requireNonNull(downloads.listFiles()).length;
  }

  public static boolean hasFileBeenDownloaded() {
    return countDownloads() > downloadsCount;
  }

  public static String getMostRecentFile() {
    return mostRecentFile;
  }

  public static void setMostRecentFile() {
    mostRecentFile =
        Arrays.stream(Objects.requireNonNull(downloads.listFiles()))
            .max(Comparator.comparingLong(File::lastModified))
            .get()
            .getPath();
  }

  public static String getExtension(String fileName) {
    String extension = FilenameUtils.getExtension(fileName);
    if (StringUtils.isBlank(extension)) {
      throw new IllegalArgumentException(
          String.format("Could not parse extension from %s", fileName));
    }
    return extension;
  }
}
