package org.example.pages;

import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationPage {
    private final WebDriver driver;
    private String errorPasswordText;
    private String expectedErrorPasswordText = "Некорректный пароль";
    //поле Имя
    private final By nameField = By.xpath("//label[contains(text(), 'Имя')]/following-sibling::input");
    //поле Пароль
    private final By passwordField = By.cssSelector("input[name='Пароль']");
    //Поле Email
    private final By emailField = By.xpath("//label[contains(text(), 'Email')]/following-sibling::input");
    //Кнопка Зарегистрироваться
    private final By registrationButton = By.cssSelector("button[class*='type']");
    //Ошибка "Некорректный пароль"
    private final By errorPasswordMassage = By.cssSelector("p[class*='error']");
    //Ссылка Войти
    private final By logInLink = By.cssSelector("a[class^='Auth']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on the Registration button")
    public LoginPage clickOnRegistrationButton() {
        driver.findElement(registrationButton).click();
        return new LoginPage(driver);
    }

    @Step("Enter the user password")
    public void enterUserPassword(String userPassword) {
        driver.findElement(passwordField).sendKeys(userPassword);
    }

    @Step("Enter the user email")
    public void enterUserEmail(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
    }

    @Step("Enter the user name")
    public void enterUserName(String userName) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));

        driver.findElement(nameField).sendKeys(userName);
    }

    @Step("Check is error password text is correct and visible")
    public void checkErrorPasswordText() {
        errorPasswordText = driver.findElement(errorPasswordMassage).getText();
        assert driver.findElement(errorPasswordMassage).isDisplayed();
        assertEquals(expectedErrorPasswordText, errorPasswordText);
    }

    @Step("Click on LogIn link")
    public void clickOnLogInLink() {
        driver.findElement(logInLink).click();
    }
}
