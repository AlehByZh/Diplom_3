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

public class AccountButtonTest {
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

    @DisplayName("Check is Account button move to Account page")
    @Test
    public void accountButtonMoveToAccountPage() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        LoginPage login = main.clickOnLogInButton();
        login.enterUserEmail(email);
        login.enterUserPassword(password);
        login.clickOnLoginButton();
        main.checkIngredientIsVisible();
        main.clickOnAccountButton();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");

        AccountPage account = new AccountPage(driver);
        account.checkIsProfileButtonVisible();
    }

    @After
    public void deleteUser() {
        if (accessToken != null && !accessToken.isEmpty()) {
            client.deleteCourier(accessToken);
        }
    }
}
