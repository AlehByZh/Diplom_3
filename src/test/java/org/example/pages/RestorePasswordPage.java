package org.example.pages;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePasswordPage {
    private final WebDriver driver;
    //Кнопка Восстановить
    private final By restoreButton = By.xpath("//button[contains(@class, 'button_button__33qZ0')]");
    //Ссылка Войти
    private final By logInLink = By.cssSelector("a[class^='Auth']");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on LogIn link")
    public void clickOnLogInButtonLink() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(restoreButton));
        driver.findElement(logInLink).click();
    }
}
