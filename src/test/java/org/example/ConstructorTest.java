package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.pages.MainPage;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ConstructorTest {
    private MainPage main;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        WebDriver driver = driverRule.getDriver();
        main = new MainPage(driver);
        main.open();
    }

    @DisplayName("Checking the constructor Souse tab")
    @Test
    public void testConstructorTabSouse() {
        main.clickOnSouseTab();
        main.checkSouseTabIsSelected();
    }

    @DisplayName("Checking the constructor Ingredients tab")
    @Test
    public void testConstructorTabIngredients() {
        main.clickOnIngredientsTab();
        main.checkIngredientTabIsSelected();
    }

    @DisplayName("Checking the constructor Bun tab")
    @Test
    public void testConstructorTabBun() {
        main.clickOnSouseTab();
        main.clickOnBunTab();
        main.checkBunTabIsSelected();
    }
}
