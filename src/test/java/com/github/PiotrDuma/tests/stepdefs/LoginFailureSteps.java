package com.github.PiotrDuma.tests.stepdefs;

import com.github.PiotrDuma.web.page.login.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginFailureSteps {
  LoginPage loginPage;

  @Given("User's on the login page")
  public void openPage() {
    log.info("Opening login page");
    loginPage = new LoginPage()
        .openPage();
  }

  @When("User enters login: {string} and password: {string} values")
  public void enterInvalidCredentials(String username, String password) {
    log.info(String.format("Test login with invalid username: %s AND %s", username, password));
    loginPage.setUsername(username)
        .setPassword(password);
  }

  @Then("User should see invalid credentials error message")
  public void shouldShownErrorMessage() {
    assertThat(loginPage.isErrorMessageDisplayed())
        .as("Should show error message, case: invalid credentials")
        .isTrue();
  }

  @When("User enters empty login and {string} password")
  public void setEmptyLoginAndValidPassword(String password) {
    log.info(String.format("Test login with invalid username: %s AND %s", "", password));
    loginPage.setUsername("")
        .setPassword(password);
  }

  @When("User clicks button to login")
  public void clickLoginButton() {
    log.info("Click login button");
    loginPage.clickSignInButton();
  }

  @Then("blank username error message appears")
  public void shouldShowBlankUsernameErrorMessage() {
    assertThat(loginPage.isLoginMessageDisplayed())
        .as("Should show error message, case: blank username")
        .isTrue();
  }

  @When("User enter {string} login and empty password")
  public void i_enter_login_and_empty_password(String username) {
    log.info(String.format("Test login with invalid username: %s AND %s", username, ""));
    loginPage.setUsername(username)
        .setPassword("");
  }

  @Then("blank password error message appears")
  public void shouldShowBlankPasswordErrorMessage() {
    assertThat(loginPage.isPasswordMessageDisplayed())
        .as("Should show error message, case: blank password")
        .isTrue();
  }
}
