package com.github.PiotrDuma.tests.stepdefs;

import com.github.PiotrDuma.web.page.email.InboxPage;
import com.github.PiotrDuma.web.page.login.LoginPage;
import com.github.PiotrDuma.web.window.MessageWindow;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailSendSuccessPath {

  LoginPage loginPage;
  InboxPage inboxPage;
  MessageWindow<InboxPage> messageWindow;
  String login;
  String password;
  String recipient;
  String subject;
  String message;

  @Given("I am on the login page")
  public void openLoginPage() {
    log.info("Opening login page");
    loginPage = new LoginPage()
        .openPage();
  }

  @When("I enter valid login {string} and password {string}")
  public void enterValidCredentials(String login, String password) {
    log.info(String.format("Enter login %s and password %s", login, password));
    this.login = login;
    this.password = password;
    loginPage
        .setUsername(login)
        .setPassword(password);
  }

  @When("I click login button")
  public void clickLoginButton() {
    log.info("Click login button");
    inboxPage = loginPage.clickSignInButton();
  }

  @Then("I should see inbox page")
  public void shouldLoginWithValidCredentials() {
    log.info(String.format("Test login with valid credentials: %s AND %s", login, password));

    assertThat(inboxPage.getEmailSpanText())
        .as("Check if login succeed by unique email selector")
        .isEqualTo(login);
  }


  @Given("I am logged in on my inbox page with credentials {string} and {string}")
  public void loginToInboxPage(String login, String password) {
    this.login = login;
    this.password = password;
    openLoginPage();
    enterValidCredentials(login, password);
    clickLoginButton();
  }

  @When("I click on new message button")
  public void clickOnNewMessageButton() {
    messageWindow = inboxPage.clickNewMessageButton();
  }

  @When("I create a new email with recipient {string}, subject {string}, and message {string}")
  public void createEmailWithValues(String recipient,
      String subject, String message) {
    this.recipient = recipient;
    this.subject = subject;
    this.message = message;
    messageWindow.setRecipient(recipient)
        .setSubject(subject)
        .openMessageEditorFrame()
        .clearEditorText()
        .setEditorMessageText(message)
        .closeFrame();
  }

  @Then("the recipient, subject, and message fields should be filled correctly")
  public void shouldFieldsBeFilledCorrectly() {
    assertThat(messageWindow.getRecipients())
        .as("Check if recipient list contains provided email")
        .contains(recipient);
    assertThat(messageWindow.getSubjectFieldValueAttribute())
        .as("Check if subject field is filled correctly")
        .isEqualTo(subject);
    assertThat(messageWindow.openMessageEditorFrame().getEditorMessageText())
        .as("Check if message field is filled correctly")
        .isEqualTo(message);
  }
}
