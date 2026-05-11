package com.github.PiotrDuma.tests.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.github.PiotrDuma.tests.stepdefs",
//    tags = "@critical_path",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

  @DataProvider(parallel = true)
  @Override
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
