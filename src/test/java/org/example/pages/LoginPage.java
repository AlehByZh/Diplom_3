package org.example.pages;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    //ссылка Зарегистрироваться
    private final By registrationLink = By.cssSelector("p[class$='mb-4'] a[class^='Auth']");
    //кнопка Войти
    private final By loginButton = By.xpath("//button[contains(@class, 'button_') and contains(text(), 'Войти')]");
    //поле Пароль
    private final By passwordField = By.cssSelector("input[name='Пароль']");
    //поле Email
    private final By emailField = By.cssSelector("input[name='name']");
    //Ссылка Восстановить пароль
    private final By restorePasswordLink = By.xpath("//a[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on the Registration link")
    public RegistrationPage clickOnRegistrationLink() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationLink));

        driver.findElement(registrationLink).click();
        return new RegistrationPage(driver);
    }

    @Step("Check is Login button is visible")
    public void checkIsDisplayedLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        assert driver.findElement(loginButton).isDisplayed();
    }

    @Step("Click on the Login button")
    public MainPage clickOnLoginButton() {
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }

    @Step("Enter the user password")
    public void enterUserPassword(String userPassword) {
        driver.findElement(passwordField).sendKeys(userPassword);
    }

    @Step("Enter the user email")
    public void enterUserEmail(String userEmail) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(userEmail);
    }

    @Step("Click on Restore password link")
    public RestorePasswordPage clickOnRestorePassLink() {
        driver.findElement(restorePasswordLink).click();
        return new RestorePasswordPage(driver);
    }
}
