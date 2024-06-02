package org.example;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegistrationPage;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private final UserClient client = new UserClient();
    private String accessToken;
    private String userName = "Pietro";
    private String userEmail = "pietroMaximoff@gog.com";
    private String userPassword = "123456";
    private String userMimWrongPassword = "12345";

    @Rule
    public DriverRule driverRule = new DriverRule();

    @DisplayName("Registration user happy path")
    @Test
    public void testRegistration() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        LoginPage login = main.clickOnAccountButton();
        RegistrationPage registration = login.clickOnRegistrationLink();
        registration.enterUserName(userName);
        registration.enterUserEmail(userEmail);
        registration.enterUserPassword(userPassword);
        registration.clickOnRegistrationButton();
        login.checkIsDisplayedLoginPage();

        login.enterUserEmail(userEmail);
        login.enterUserPassword(userPassword);
        login.clickOnLoginButton();
        main.checkIngredientIsVisible();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
    }

    @DisplayName("Check error text for min password symbols")
    @Test
    public void testMinPasswordSymbols() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        LoginPage login = main.clickOnAccountButton();
        RegistrationPage registration = login.clickOnRegistrationLink();
        registration.enterUserName(userName);
        registration.enterUserEmail(userEmail);
        registration.enterUserPassword(userMimWrongPassword);
        registration.clickOnRegistrationButton();
        registration.checkErrorPasswordText();
    }

    @After
    public void deleteUser() {
        if (accessToken != null && !accessToken.isEmpty()) {
            client.deleteCourier(accessToken);
        }
    }
}
