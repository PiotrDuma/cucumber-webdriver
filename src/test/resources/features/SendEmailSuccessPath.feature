Feature: Send email success path

  @critical_path
  Scenario: Successful login
    Given I am on the login page
    When I enter valid login "test_user_epam_1234@proton.me" and password "<a1b2c3d4>"
    And I click login button
    Then I should see inbox page

  @critical_path
  Scenario: Open message window and fill text fields
    Given I am logged in on my inbox page with credentials "test_user_epam_1234@proton.me" and "<a1b2c3d4>"
    When I click on new message button
    And I create a new email with recipient "test_user_epam_1234@proton.me", subject "SUBJECT 12345", and message "MESSAGE 12345"
    Then the recipient, subject, and message fields should be filled correctly