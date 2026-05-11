package com.github.PiotrDuma.tests.stepdefs;

import com.github.PiotrDuma.utils.screenshot.ScreenshotCapturer;
import com.github.PiotrDuma.webdriver.SingletonWebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

  @After
  public void teadDown(Scenario scenario) {
    ScreenshotCapturer capturer = new ScreenshotCapturer();
    try {
      if (scenario.isFailed()) {
        capturer.capture(scenario.getName());
      }
    } finally {
      closeDriver();
    }
  }

  private static void closeDriver() {
    SingletonWebDriverFactory.closeDriver();
  }
}
