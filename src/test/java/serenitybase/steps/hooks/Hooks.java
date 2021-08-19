package serenitybase.steps.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import serenitybase.helpers.AppiumManager;
import serenitybase.helpers.ScreenRecorder;

public class Hooks {
  @Before
  public void before() {
    AppiumManager.startAppium();
    AppiumManager.getSession();
    if (System.getProperty("screenRecorder.url") != null) ScreenRecorder.startRecording();
  }

  @After
  public void after() {
    AppiumManager.stopAppium();
    killProcess("EXCEL.EXE");
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
      System.out.println("Exception during taskKill");
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
