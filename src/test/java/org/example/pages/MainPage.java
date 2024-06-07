package org.example.pages;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    //Вкладка Соусы в конструкторе
    private final By souseTab = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    //Вкладка Начинки в конструкторе
    private final By ingredientsTab = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");
    //Вкладка Булки в конструкторе
    private final By bunTab = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");

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

    @Step("Click on the Souse tab")
    public void clickOnSouseTab() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(souseTab));
        driver.findElement(souseTab).click();
    }

    @Step("Check is Souse tab selected")
    public void checkSouseTabIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(souseTab, "class", "current"));
        WebElement souseTabElement = driver.findElement(souseTab);
        assert souseTabElement.getAttribute("class").contains("current");
    }

    @Step("Click on Ingredient tab")
    public void clickOnIngredientsTab() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(ingredientsTab));
        driver.findElement(ingredientsTab).click();
    }

    @Step("Check is Souse tab selected")
    public void checkIngredientTabIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(ingredientsTab, "class", "current"));
        WebElement ingredientTabElement = driver.findElement(ingredientsTab);
        assert ingredientTabElement.getAttribute("class").contains("current");
    }

    @Step("Click on Bun tab")
    public void clickOnBunTab() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(bunTab));
        driver.findElement(bunTab).click();
    }

    @Step("Check is Bun tab selected")
    public void checkBunTabIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(bunTab, "class", "current"));
        WebElement bunTabElement = driver.findElement(bunTab);
        assert bunTabElement.getAttribute("class").contains("current");
    }
}
