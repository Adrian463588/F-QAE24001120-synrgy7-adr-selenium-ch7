import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.HomePage;
import org.example.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTestPOM {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeTest
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(); // Inisialisasi driver

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        //wait implicit for 1 minute
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLogin();

        // Assertion 1: Validate URL dashboard
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URL does not match after successful login.");
        System.out.println("Dashboard URL is: " + driver.getCurrentUrl());

        // Assertion 2: Validate inventory display
        Assert.assertTrue(homePage.isInventoryDisplayed(), "Inventory is not displayed.");
        System.out.println("Inventory is displayed.");

        // Assertion 3: Validate page title
        Assert.assertEquals(loginPage.getPageTitle(), "Products", "Page title does not match.");
        System.out.println("Page title is: " + loginPage.getPageTitle());
    }

    @Test(priority = 2)
    public void testFailedLogin() {
        driver.get("https://www.saucedemo.com/");

        loginPage.inputUsername("locked_out_user");
        loginPage.inputPassword("wrong_password");
        loginPage.clickLogin();

        // Assertion 1: Validate error message
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service", "Error message does not match.");
        System.out.println("Error message is: " + loginPage.getErrorMessage());
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Pastikan driver berhenti hanya jika tidak null
        }
    }
}
