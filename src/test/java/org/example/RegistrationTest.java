package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RegistrationTest {
    private WebDriver driver;
    private MainPage main;
    private final UserClient client = new UserClient();
    private String accessToken;
    private String userName = "Pietro";
    private String userEmail = "pietroMaximoff@gog.com";
    private String userPassword = "123456";
    private String userMimWrongPassword = "12345";

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        driver = driverRule.getDriver();
        main = new MainPage(driver);
        main.open();
    }

    @DisplayName("Registration user happy path")
    @Test
    public void testRegistration() {
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
