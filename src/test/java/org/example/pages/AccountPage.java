package org.example.pages;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private final WebDriver driver;
    //Кнопка Выход
    private By logOutButton;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on LogOut button")
    public void clickOnLogOutButton() {
        logOutButton = By.cssSelector("button[class*='Account']");
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
        driver.findElement(logOutButton).click();
    }
}
