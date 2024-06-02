package org.example.pages;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    //кнопка Личный кабинет
    private final By accountButton = By.xpath("//a[@href='/account']");
    //Иконки ингредиентов
    private final By ingredientIcon = By.cssSelector("a[class^='BurgerIngredient']");
    //Кнопка "Войти в аккаунт"
    private final By logInButton = By.cssSelector("button[class*='button']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on Account button")
    public LoginPage clickOnAccountButton() {
        driver.findElement(accountButton).click();
        return new LoginPage(driver);
    }

    @Step("Open main page")
    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    @Step("Check is more then 2 ingredients is visible")
    public void checkIngredientIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(ingredientIcon, 2));
        assert driver.findElement(ingredientIcon).isDisplayed();
    }

    @Step("Click on LogIn button on main page")
    public LoginPage clickOnLogInButton() {
        driver.findElement(logInButton).click();
        return new LoginPage(driver);
    }
}
