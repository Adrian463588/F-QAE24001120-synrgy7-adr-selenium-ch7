import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;



//Abaikan class ini tidak ada POM
@Test
public class Sorting {

        @Test
        public void sortSuccess() {
            WebDriverManager.firefoxdriver().setup();
            WebDriver driver = new FirefoxDriver();

            //set the browser fullscreen
            driver.manage().window().maximize();

            //navigate to the website
            driver.get("https://saucedemo.com/");

            //implicit wait
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

            System.out.println("Navigated to the website");

            //input username
            driver.findElement(By.id("user-name")).sendKeys("standard_user");

            //input password
            driver.findElement(By.id("password")).sendKeys("secret_sauce");

            //click login button
            driver.findElement(By.id("login-button")).click();

            //get the title of the website
            String title = driver.getTitle();


            //Assert the title
            Assert.assertEquals(title, "Swag Labs");

            Assert.assertEquals(driver.getTitle(), "Swag Labs");

            System.out.println("Title is: " + title);

            //assertion2 : get the current url

            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
            System.out.println("URL is: " + driver.getCurrentUrl());

            //assert text dashboard
            Assert.assertEquals(driver.findElement(By.className("title")).getText(), "Products");

            // Sorting the products from high to low
            driver.findElement(By.className("product_sort_container")).click();
            driver.findElement(By.xpath("//option[contains(text(),'Price (high to low)')]")).click();

            // Get the price of the first item
            String firstItemPriceString = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[1]")).getText();
            double firstItemPrice = Double.parseDouble(firstItemPriceString.replace("$", ""));

            // Get the price of the second item
            String secondItemPriceString = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[2]")).getText();
            double secondItemPrice = Double.parseDouble(secondItemPriceString.replace("$", ""));

            // Assert that the first item price is larger than the second item price
            Assert.assertTrue(firstItemPrice > secondItemPrice, "The first item price is not larger than the second item price");

            System.out.println("Products are sorted from high to low");


            //close the browser
            driver.quit();

        }





}
