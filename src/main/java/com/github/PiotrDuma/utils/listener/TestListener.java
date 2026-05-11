package com.github.PiotrDuma.utils.listener;

import com.github.PiotrDuma.utils.screenshot.ScreenshotCapturer;
import com.github.PiotrDuma.webdriver.SingletonWebDriverFactory;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestListener implements ITestListener {

  static final String TIMESTAMP_FORMAT = "yyyy_MM_dd-HH_mm_ss";
  static final String SCREENSHOT_PATH = "screenshots/";

  @Override
  public void onTestSuccess(ITestResult result) {
    closeDriver();
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    closeDriver();
  }

  @Override
  public void onTestFailure(ITestResult result) {
    ScreenshotCapturer capturer = new ScreenshotCapturer();
    try {
      capturer.capture(result.getName());
    } finally {
      closeDriver();
    }
  }

  private static void closeDriver() {
    SingletonWebDriverFactory.closeDriver();
  }
}
