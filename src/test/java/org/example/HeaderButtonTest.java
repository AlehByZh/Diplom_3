package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.pages.AccountPage;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HeaderButtonTest {
    private WebDriver driver;
    private MainPage main;
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
        driver = driverRule.getDriver();
        main = new MainPage(driver);
        main.open();
    }

    @DisplayName("Check move from Account to Logo")
    @Test
    public void moveFromAccountToLogoTest() {
        LoginPage login = main.clickOnLogInButton();
        login.enterUserEmail(email);
        login.enterUserPassword(password);
        login.clickOnLoginButton();
        main.checkIngredientIsVisible();
        main.clickOnAccountButton();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");

        AccountPage account = new AccountPage(driver);
        account.clickOnLogo();
        main.checkIngredientIsVisible();
    }

    @DisplayName("Check move from Account to Constructor")
    @Test
    public void moveFromAccountToConstructorTest() {
        LoginPage login = main.clickOnLogInButton();
        login.enterUserEmail(email);
        login.enterUserPassword(password);
        login.clickOnLoginButton();
        main.checkIngredientIsVisible();
        main.clickOnAccountButton();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");

        AccountPage account = new AccountPage(driver);
        account.clickOnConstructor();
        main.checkIngredientIsVisible();
    }

    @After
    public void deleteUser() {
        if (accessToken != null && !accessToken.isEmpty()) {
            client.deleteCourier(accessToken);
        }
    }
}
