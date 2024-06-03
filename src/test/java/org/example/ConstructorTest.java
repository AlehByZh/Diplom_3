package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.pages.MainPage;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ConstructorTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    @DisplayName("Checking the constructor Souse tab")
    @Test
    public void testConstructorTabSouse() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickOnSouseTab();
        main.checkSouseTabIsSelected();
    }

    @DisplayName("Checking the constructor Ingredients tab")
    @Test
    public void testConstructorTabIngredients() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickOnIngredientsTab();
        main.checkIngredientTabIsSelected();
    }

    @DisplayName("Checking the constructor Bun tab")
    @Test
    public void testConstructorTabBun() {
        WebDriver driver = driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickOnSouseTab();
        main.clickOnBunTab();
        main.checkBunTabIsSelected();
    }
}
