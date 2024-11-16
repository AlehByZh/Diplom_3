package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    protected void after() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void initDriver() {
        if ("yandex".equals(System.getProperty("browser"))) {
            initYandex();
        } else {
            initChrome();
        }
    }

    private void initYandex() {
        WebDriverManager.chromedriver().driverVersion(System.getProperty("driver.version")).setup();
        var options = new ChromeOptions();
        options.setBinary(System.getProperty("webdriver.yandex.bin"));
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    private void initChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }
}

