package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegistrationPage;
import org.example.pages.RestorePasswordPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AuthorizationTest {
    private UserClient client = new UserClient();
    private String email;
    private String password;
    private String accessToken;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void creatUser() {
        User createdUser = client.createUser();
        email = createdUser.getEmail();
        password = createdUser.getPassword();
    }

    @DisplayName("Authorization happy path use LogIn main page button")
    @Test
    public void testLogInForMainPage() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        LoginPage login = main.clickOnLogInButton();
        login.enterUserEmail(email);
        login.enterUserPassword(password);
        login.clickOnLoginButton();
        main.checkIngredientIsVisible();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
    }

    @DisplayName("Authorization happy path use Account button")
    @Test
    public void testLogInForAccountPage() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        LoginPage login = main.clickOnAccountButton();
        login.enterUserEmail(email);
        login.enterUserPassword(password);
        login.clickOnLoginButton();
        main.checkIngredientIsVisible();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
    }

    @DisplayName("Authorization happy path use LogIn link on registration page")
    @Test
    public void testLogInForRegistrationPage() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        LoginPage login = main.clickOnAccountButton();
        RegistrationPage registration = login.clickOnRegistrationLink();
        registration.clickOnLogInLink();
        login.enterUserEmail(email);
        login.enterUserPassword(password);
        login.clickOnLoginButton();
        main.checkIngredientIsVisible();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
    }

    @DisplayName("Authorization happy path use LogIn link on restore password page")
    @Test
    public void testLogInForRestorePasswordPage() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        LoginPage login = main.clickOnLogInButton();
        RestorePasswordPage restore = login.clickOnRestorePassLink();
        restore.clickOnLogInButtonLink();
        login.enterUserEmail(email);
        login.enterUserPassword(password);
        login.clickOnLoginButton();
        main.checkIngredientIsVisible();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
    }

    @After
    public void deleteUser() {
        if (accessToken != null && !accessToken.isEmpty()) {
            client.deleteCourier(accessToken);
        }
    }
}
