package com.example.steps;

import com.example.LoginService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {
    private LoginService loginService;
    private boolean loginResult;

    @Before
    public void setup() {
        loginService = new LoginService();
    }

    @After
    public void tearDown() {
        loginService.logout();
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // In a real application, this would interact with a browser
        System.out.println("Navigating to login page...");
    }

    @When("I enter valid username {string} and password {string}")
    public void i_enter_valid_username_and_password(String username, String password) {
        loginResult = loginService.login(username, password);
    }

    @When("I enter invalid username {string} and password {string}")
    public void i_enter_invalid_username_and_password(String username, String password) {
        loginResult = loginService.login(username, password);
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        Assertions.assertTrue(loginService.isLoggedIn(), "User should be logged in");
    }

    @Then("I should see a welcome message")
    public void i_should_see_a_welcome_message() {
        Assertions.assertNotNull(loginService.getCurrentUser(), "Current user should be set");
        System.out.println("Welcome, " + loginService.getCurrentUser() + "!");
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        Assertions.assertFalse(loginResult, "Login should have failed");
        System.out.println("Error: Invalid username or password");
    }

    @Then("I should remain on the login page")
    public void i_should_remain_on_the_login_page() {
        Assertions.assertFalse(loginService.isLoggedIn(), "User should not be logged in");
    }

}
