Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid username "validUser" and password "validPass123"
    Then I should be redirected to the dashboard
    Then I should see a welcome message

  Scenario: Failed login with invalid credentials
    Given I am on the login page
    When I enter invalid username "invalidUser" and password "wrongPass"
    Then I should see an error message
    Then I should remain on the login page