import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


//Abaikan class ini tidak ada POM
public class loginTestFailed {

 @Test
    public void loginFailed() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        //set the browser fullscreen
        driver.manage().window().maximize();

        //navigate to the website
        driver.get("https://saucedemo.com/");

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        System.out.println("Navigated to the website");

        //asserion1 : get the title of the website
        String title = driver.getTitle();
        Assert.assertEquals(driver.getTitle(), "Swag Labs");

        System.out.println("Title is: " + title);

        //assertion2 : get the current url
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/");


        //input username
        driver.findElement(By.id("user-name")).sendKeys("standardprofile");

        //input password
        driver.findElement(By.id("password")).sendKeys("secret_saauce");

        //click login button
        driver.findElement(By.id("login-button")).click();


        //assert3: get the error message
        String errorMessage = driver.findElement(By.cssSelector("h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");

        System.out.println("Error message is: " + errorMessage);


        //close the browser
        driver.quit();


    }



}
