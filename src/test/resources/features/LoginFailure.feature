Feature: Login failure

  Scenario Outline: Usernvalid credentials
    Given User's on the login page
    When User enters login: "<username>" and password: "<password>" values
    And User clicks button to login
    Then User should see invalid credentials error message

    Examples:
    | username                               | password   |
    | test_user_epam_1234_invalid@proton.me  | <a1b2c3d4> |
    | test_user_epam_1234@proton.me          | <12345678> |

  Scenario: Blank username failure
    Given User's on the login page
    When User enters empty login and "<a1b2c3d4>" password
    And User clicks button to login
    Then blank username error message appears

  Scenario: Blank password failure
    Given User's on the login page
    When User enter "test_user_epam_1234@proton.me" login and empty password
    And User clicks button to login
    Then blank password error message appears