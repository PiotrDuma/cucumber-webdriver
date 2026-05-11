Feature: Login failure

  Scenario Outline: Invalid credentials
    Given I'm on the login page
    When I enter login: "<username>" and password: "<password>" values
    And I click button to login
    Then I should see invalid credentials error message

    Examples:
    | username                               | password   |
    | test_user_epam_1234_invalid@proton.me  | <a1b2c3d4> |
    | test_user_epam_1234@proton.me          | <12345678> |

  Scenario: Blank username failure
    Given I'm on the login page
    When I enter empty login and "<a1b2c3d4>" password
    And I click button to login
    Then blank username error message should be shown

  Scenario: Blank password failure
    Given I'm on the login page
    When I enter "test_user_epam_1234@proton.me" login and empty password
    And I click button to login
    Then blank password error message should be shown