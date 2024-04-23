package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void beforeTest() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterClass
    public void afterTest() {
        driver.close();
    }

    @Test(dataProvider = "logins")
    public void loginTest(String login, String caption) {
        driver.get("https://MyStudioURL.com");
        driver.findElement(By.id("login")).sendKeys(login);
        driver.findElement(By.id("pwd")).sendKeys("Mystudio123");
        driver.findElement(By.id("sbmt")).click();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(text(),'" + caption + "')]")).isEmpty());
    }

    @DataProvider(name = "logins")
    public static Object[][] loginsData() {
        return new Object[][]{
                {"Manager@mystudio.academy", "Analytics"},
                {"Staff@mystudio.academy", "Customers"}
        };
    }

    /*
    You didn't ask but here is working test
    So you can verify solution is actually working
    Run from IDE or `gradlew test`
     */
    @Test
    public void googleSearch() {
        driver.get("https://www.google.com.ua/");
        driver.findElement(By.name("q")).sendKeys("MyStudio.io");
        new Actions(driver).keyDown(Keys.ENTER)
                .perform();
        wait.until(e -> driver.findElement(By.id("rcnt")).isDisplayed());
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(text(),'MyStudio.io')]")).isEmpty());
    }
}
