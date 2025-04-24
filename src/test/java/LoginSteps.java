package com.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private String currentPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://your-app-url.com/login");
        currentPage = "login";
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='username' or @name='username']")));
    }

    @When("I enter valid username {string} and password {string}")
    public void enter_valid_credentials(String username, String password) {
        enterCredentials(username, password);
    }

    @When("I enter invalid username {string} and password {string}")
    public void enter_invalid_credentials(String username, String password) {
        enterCredentials(username, password);
    }

    private void enterCredentials(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='username' or @name='username']")));
        WebElement passwordField = driver.findElement(
                By.xpath("//input[@id='password' or @name='password']"));
        WebElement loginButton = driver.findElement(
                By.xpath("//button[@id='login-btn' or contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @Then("I should be redirected to the dashboard")
    public void verify_redirect_to_dashboard() {
        wait.until(ExpectedConditions.urlContains("dashboard"));
        currentPage = "dashboard";
    }

    @Then("I should see a welcome message")
    public void verify_welcome_message() {
        WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Welcome') or @id='welcome-message']")));
        assertTrue(welcomeMessage.isDisplayed());
    }

    @Then("I should see an error message")
    public void verify_error_message() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(@class,'error') or contains(text(),'error')]")));
        assertTrue(errorMessage.isDisplayed());
    }

    @Then("I should remain on the login page")
    public void verify_stay_on_login_page() {
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}