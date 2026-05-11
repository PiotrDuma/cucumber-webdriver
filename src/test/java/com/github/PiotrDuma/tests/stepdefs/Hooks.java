package com.github.PiotrDuma.tests.stepdefs;

import com.github.PiotrDuma.webdriver.SingletonWebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Slf4j
public class Hooks {

  static final String TIMESTAMP_FORMAT = "yyyy_MM_dd-HH_mm_ss";
  static final String SCREENSHOT_PATH = "screenshots/";

  @After
  public void teadDown(Scenario scenario) {

    try {
      if (scenario.isFailed()) {
        takeScreenshot(scenario.getName());
      }
    } finally {
      closeDriver();
    }
  }

  private void takeScreenshot(String scenarioName){
    try {
      File file = getScreenshotAsFile();
      String destinationPath = getFilePath(scenarioName);
      FileUtils.copyFile(file, new File(destinationPath));
      log.info(String.format("Screenshot saved: %s", destinationPath));
    } catch (IOException e) {
      log.error("Screenshot save failure.");
      throw new RuntimeException(e);
    }
  }

  private static File getScreenshotAsFile() {
    WebDriver webDriver = SingletonWebDriverFactory.getWebDriver();
    TakesScreenshot screenshot = (TakesScreenshot) webDriver;
    return screenshot.getScreenshotAs(OutputType.FILE);
  }

  private static String getFilePath(String resultName) {
    return new StringBuilder(SCREENSHOT_PATH)
        .append(resultName)
        .append("_")
        .append(getTimestamp())
        .append(".png")
        .toString();
  }

  private static String getTimestamp() {
    return new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());
  }

  private static void closeDriver() {
    SingletonWebDriverFactory.closeDriver();
  }
}
