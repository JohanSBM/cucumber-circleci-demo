Feature: Login functionality
  As a user
  I want to log into the system
  So that I can access my account

  Scenario: Successful login
    Given I am on the login page
    When I enter valid username "user" and password "password"
    Then I should be redirected to the dashboard
    And I should see a welcome message

  Scenario: Failed login
    Given I am on the login page
    When I enter invalid username "wronguser" and password "wrongpass"
    Then I should see an error message
    And I should remain on the login page
    
  Scenario: Admin login
    Given I am on the login page
    When I enter valid username "admin" and password "admin123"
    Then I should be redirected to the dashboard
    And I should see a welcome message