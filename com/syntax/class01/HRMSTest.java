package com.syntax.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HRMSTest {

    WebDriver driver;
    @BeforeMethod

    public void openAndNavigate() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test (priority = 3)

    public void validAdminLogin() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        driver.findElement(By.id("btnLogin")).click();
        WebElement welcomeMessage = driver.findElement(By.id("welcome"));

        if(welcomeMessage.isDisplayed()) {
            System.out.println("Test Pass");
        } else {
            System.out.println("Test Fail");
        }
    }

    @Test (enabled = false)
    public void titleValidation() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Human Management System";

        if(expectedTitle.equals(actualTitle)) {
            System.out.println("Title is valid. Test Pass");
        } else {
            System.out.println("Title is not match. Test Fail");
        }
    }

    @Test (priority = 2)
    public void logoValidation() {
        WebElement logo = driver.findElement(By.cssSelector("img[src$='syntax.png']"));

        /*if(logo.isDisplayed()) {
            System.out.println("Logo is displayed. Test Pass");
        } else {
            System.out.println("Logo is not displayed. Test Fail");
        }*/

        Assert.assertTrue(!logo.isDisplayed(), "Logo is not displayed");

    }

    @Test (priority = 1)
    public void invalidCredentials() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin1");
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm1234");
        driver.findElement(By.id("btnLogin")).click();
        String errorMessageText = driver.findElement(By.id("spanMessage")).getText();
        String expectedErrorMessage = "Invalid Credentials";

       /* if(errorMessageText.equals(expectedErrorMessage)) {
            System.out.println("Error message “Invalid credentials” is displayed. Test Pass");
        } else {
            System.out.println("Error message is not present. Test Fail");
        }*/
        System.out.println("My code before the assertion");
        Assert.assertEquals(errorMessageText, expectedErrorMessage, "Not correct error message is displayed");
        System.out.println("My code after the assertion");
    }

    @AfterMethod

    public void closeBrowser() {
        driver.quit();
    }
}
