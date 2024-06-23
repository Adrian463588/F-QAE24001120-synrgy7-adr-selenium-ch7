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

public class SortingTestPOM {

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

        // Login
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLogin();

        // Verify login success
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Login failed or title does not match.");

    }

    @Test
    public void testSortingHighToLow() {
        // Assert URL after login
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Current URL does not match expected URL.");
        System.out.println("Dashboard URL is: " + driver.getCurrentUrl());

        // Assert page title after login
        Assert.assertEquals(homePage.getPageTitle(), "Products", "Page title does not match expected.");
        System.out.println("Page title is: " + homePage.getPageTitle());

        // Sorting the products from high to low
        homePage.selectSortOption("Price (high to low)");

        // Get the price of the first item
        double firstItemPrice = homePage.getItemPrice(1);

        // Get the price of the second item
        double secondItemPrice = homePage.getItemPrice(2);

        // Assert that the first item price is larger than the second item price
        Assert.assertTrue(firstItemPrice >= secondItemPrice, "The first item price is not larger than or equal to the second item price.");

        System.out.println("Products are sorted from high to low");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Pastikan driver berhenti hanya jika tidak null
        }
    }
}
