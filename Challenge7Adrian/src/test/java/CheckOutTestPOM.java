import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.CheckoutPage;
import org.example.HomePage;
import org.example.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckOutTestPOM {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    CheckoutPage checkoutPage;

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
        checkoutPage = new CheckoutPage(driver);

        // Login
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLogin();

        // Verify login success
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Login failed or title does not match.");
    }

    @Test
    public void testCheckOut() throws InterruptedException {
        // Add items to cart
        homePage.addItemToCart("add-to-cart-sauce-labs-backpack");
        homePage.addItemToCart("add-to-cart-sauce-labs-bike-light");

        // Proceed to checkout
        homePage.clickCart();
//        Thread.sleep(15000);  // Wait for 15 seconds

        //Assert Validate Cart Page URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html", "URL does not match after successful login.");
        System.out.println("Cart Page URL is: " + driver.getCurrentUrl());

        checkoutPage.proceedToCheckout();
//        Thread.sleep(15000);  // Wait for 15 seconds
        //Assert Validate Checkout Page URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html", "URL does not match after successful login.");
        System.out.println("Checkout Page URL is: " + driver.getCurrentUrl());

        checkoutPage.fillCheckoutInformation("John", "Doe", "12345");

        checkoutPage.clickContinue();
//        Thread.sleep(15000);  // Wait for 15 seconds

        //Assert Validate Checkout Page URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html", "URL does not match after successful login.");
        System.out.println("Checkout Page URL is: " + driver.getCurrentUrl());


        checkoutPage.clickFinish();


        // Assert Validate Checkout Complete
        Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout not completed successfully");
        System.out.println("Checkout is completed successfully");

        /// Assert Validate Image
        Assert.assertTrue(checkoutPage.isCompletedImageDisplayed(), "Image is not displayed");
        System.out.println("Image is displayed");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Pastikan driver berhenti hanya jika tidak null
        }
    }
}
