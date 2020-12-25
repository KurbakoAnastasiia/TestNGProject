package com.syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class HW {

    /*TC 1: HRMS Add Employee:
Open chrome browser
Go to “http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login”
Login into the application
Click on Add Employee
Verify labels: Full Name, Employee Id, Photograph are displayed
Provide Employee First and Last Name
Add a picture to the profile
Verify Employee has been added successfully
Close the browser*/

    WebDriver driver;
    String url = "http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login";
    @BeforeClass
    public void openBrowserAndNavigate() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void login() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        driver.findElement(By.id("btnLogin")).click();
        WebElement welcomeMessage = driver.findElement(By.id("welcome"));
        Assert.assertTrue(welcomeMessage.isDisplayed(), "Welcome message is not displayed");
    }

    @Test (dependsOnMethods = "login")
    public void addEmployeeVerification() throws InterruptedException {
        driver.findElement(By.xpath("//b[text() = 'PIM']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text() = 'Add Employee']")).click();
        Thread.sleep(2000);
        WebElement fullName = driver.findElement(By.xpath("//label[text() = 'Full Name']"));
        WebElement employeeID = driver.findElement(By.xpath("//label[text() = 'Employee Id']"));
        WebElement photograph = driver.findElement(By.xpath("//label[text() = 'Photograph']"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(fullName.isDisplayed(), "Fullname is not displayed");
        softAssert.assertTrue(employeeID.isDisplayed(), "Employee ID is not displayed");
        softAssert.assertTrue(photograph.isDisplayed(), "Photograph is not displayed");
    }

    @Test (dependsOnMethods = "addEmployeeVerification")
    public void addNewEmployee() {
        driver.findElement(By.id("firstName")).sendKeys("Vladimir");
        driver.findElement(By.id("lastName")).sendKeys("Ivanov");
        driver.findElement(By.id("photofile")).sendKeys("/Users/agameganon/Desktop/PhotoTask.jpg");
        driver.findElement(By.id("btnSave")).click();
        WebElement personalInfp = driver.findElement(By.xpath("//h1[text() = 'Personal Details']"));
        Assert.assertTrue(personalInfp.isDisplayed(), "Employee has not been added");
    }

}
