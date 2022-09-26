package serenitybase.steps.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assume;
import serenitybase.helpers.AppiumManager;
import serenitybase.helpers.Configuration;
import serenitybase.helpers.ScreenRecorder;
import serenitybase.helpers.Utilities;

public class Hooks {

  public static Scenario scenario;

  @Before
  public void before() {
    AppiumManager.startAppium();
    AppiumManager.getSession();
    if (System.getProperty("screenRecorder.url") != null) ScreenRecorder.startRecording();
    Utilities.setDownloadsCount();
  }

  @Before(order = 0)
  public void skipTestBasedOnVersionTag(Scenario scenario) {
    Hooks.scenario = scenario;
    List<String> versionTags =
        scenario.getSourceTagNames().stream()
            .filter(name -> name.startsWith("@Version"))
            .collect(Collectors.toList());
    if (versionTags.size() == 0) {
      System.out.println("Scenario without version - will be run in all MAR environments");
      return;
    } else if (versionTags.size() > 1) {
      throw new RuntimeException("Multiple Version Tags per Scenario are not supported");
    }
    String version = versionTags.get(0).substring(versionTags.get(0).lastIndexOf("-") + 1);
    if (version.length() != 4 || !version.matches("^\\d{2}[R]\\d")) {
      throw new RuntimeException("Version Tag not in correct format: " + versionTags.get(0));
    }
    String envVersion = Configuration.getVersion();
    if (!envVersion.equalsIgnoreCase("main")) {
      boolean check = Utilities.checkEnvironmentVersion(version, envVersion);
      Assume.assumeTrue(
          String.format(
              "Skipping scenario due to version, requires %s test environment is %s",
              version, envVersion),
          check);
    } else {
      return;
    }
  }

  @After
  public void after() {
    AppiumManager.stopAppium();
  }

  @After
  public void stop_screen_recording(Scenario scenario) {
    if (System.getProperty("screenRecorder.url") != null) {
      ScreenRecorder.stopRecording();
      if (System.getenv("CI_PIPELINE_SOURCE").equals("merge_request_event")) {
        ScreenRecorder.refreshCache();
      }
      ScreenRecorder.writeMappingToFile(scenario.getName());
    }
  }

  private void killProcess(String processName) {
    try {
      if (isRunning(processName)) {
        Runtime.getRuntime().exec("taskkill /F /IM " + processName);
      }
    } catch (Exception e) {
      System.out.println("Exception during taskKill of process - " + processName);
    }
  }

  private boolean isRunning(String processName) throws Exception {
    Process listTasksProcess = Runtime.getRuntime().exec("tasklist");
    BufferedReader tasksListReader =
        new BufferedReader(new InputStreamReader(listTasksProcess.getInputStream()));

    String tasksLine;
    while ((tasksLine = tasksListReader.readLine()) != null) {
      if (tasksLine.contains(processName)) {
        return true;
      }
    }
    return false;
  }
}
