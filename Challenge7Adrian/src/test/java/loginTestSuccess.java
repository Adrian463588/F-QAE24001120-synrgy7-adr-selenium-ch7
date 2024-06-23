import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


//Abaikan class ini tidak ada POM
public class loginTestSuccess {

 @Test
    public void loginSuccess() {
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
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/");
        System.out.println("URL is: " + driver.getCurrentUrl());

        //assert text dashboard
        Assert.assertEquals(driver.findElement(By.className("title")).getText(), "Products");



        //close the browser
        driver.quit();


    }



}
