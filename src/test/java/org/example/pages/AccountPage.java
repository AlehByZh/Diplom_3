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
    //Кнопка Профиль
    private final By profileButton = By.xpath("//a[contains(@href, 'profile')]");
    //Логотип StellarBurgers
    private final By logoButton = By.cssSelector("[class*=\"logo\"]");
    //Кнопка Конструктор
    private final By constructorButton = By.xpath("//*[text() = 'Конструктор']");
    //Кнопка Выход
    private By logOutButton = By.cssSelector("button[class*='Account']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on LogOut button")
    public void clickOnLogOutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
        driver.findElement(logOutButton).click();
    }

    @Step("Check is Profile button is visible")
    public void checkIsProfileButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        assert driver.findElement(profileButton).isDisplayed();
    }

    @Step("Click on logo")
    public void clickOnLogo() {
        driver.findElement(logoButton).click();
    }

    public void clickOnConstructor() {
        driver.findElement(constructorButton).click();
    }
}
