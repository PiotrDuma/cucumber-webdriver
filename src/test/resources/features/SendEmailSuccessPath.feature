Feature: Send email success path

  @critical_path
  Scenario: Successful login
    Given User is on the login page
    When User enters valid login "test_user_epam_1234@proton.me" and password "<a1b2c3d4>"
    And User clicks login button
    Then User is on the inbox page

  @critical_path
  Scenario: Open message window and fill text fields
    Given User is logged in on my inbox page with credentials "test_user_epam_1234@proton.me" and "<a1b2c3d4>"
    When User clicks on new message button
    And User creates a new email with recipient "test_user_epam_1234@proton.me", subject "SUBJECT 12345", and message "MESSAGE 12345"
    Then the recipient, subject, and message fields are filled correctly