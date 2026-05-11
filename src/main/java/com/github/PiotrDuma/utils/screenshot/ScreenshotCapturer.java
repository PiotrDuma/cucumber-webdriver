package com.github.PiotrDuma.utils.screenshot;

import com.github.PiotrDuma.webdriver.SingletonWebDriverFactory;
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
public class ScreenshotCapturer {
  static final String TIMESTAMP_FORMAT = "yyyy_MM_dd-HH_mm_ss";
  static final String SCREENSHOT_PATH = "screenshots/";

  public void capture(String prefixName) {

    File file = getScreenshotAsFile();
    String destinationPath = getFilePath(prefixName);
    try {
      FileUtils.copyFile(file, new File(destinationPath));
      log.info(String.format("Screenshot saved: %s", destinationPath));
    } catch (
        IOException e) {
      log.error("Screenshot save failure.");
      throw new RuntimeException(e);
    }
  }

  private static File getScreenshotAsFile() {
    WebDriver webDriver = SingletonWebDriverFactory.getWebDriver();
    TakesScreenshot screenshot = (TakesScreenshot) webDriver;
    return screenshot.getScreenshotAs(OutputType.FILE);
  }

  private static String getFilePath(String prefixName) {
    return new StringBuilder(SCREENSHOT_PATH)
        .append(prefixName)
        .append("_")
        .append(getTimestamp())
        .append(".png")
        .toString();
  }

  private static String getTimestamp() {
    return new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());
  }
}
