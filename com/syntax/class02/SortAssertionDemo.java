package com.syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class SortAssertionDemo {

    // As an admin I should be able to login into HRMS
           // logo is displayed
           // user is successfully logged in

    WebDriver driver;
    @BeforeMethod

    public void openAndNavigate() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod

    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void logoAndValidLogin() {
        //verifying that log is displayed
        WebElement element = driver.findElement(By.xpath("//div[@id = 'divLogo']//img"));
        // creating an object of soft assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(!element.isDisplayed(), "Logo is not displayed");

        // entering valid credentials to login
        String username = "Admin";
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        driver.findElement(By.id("btnLogin")).click();

        // validating that we are logged in

        WebElement messageWelcome = driver.findElement(By.cssSelector("a#welcome"));
        softAssert.assertTrue(!messageWelcome.isDisplayed(), "Welcome message is not displayed");
        softAssert.assertEquals(messageWelcome.getText(), "Welcomes " + username, "Welcome text is not matching");

        System.out.println("End of the test case");
        softAssert.assertAll();
    }
}
