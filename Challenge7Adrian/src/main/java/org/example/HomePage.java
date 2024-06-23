package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    WebDriver driver;
    WebDriver wait;

    //class attributes
    @FindBy(className = "title")
    WebElement pageTitle;

    @FindBy(className = "inventory_item_price")
    List<WebElement> itemPrices;


    @FindBy(xpath = "//button[@class='btn_primary btn_inventory']")
    List<WebElement> addToCartButton;

    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItems;

    @FindBy(className = "product_sort_container")
    WebElement sortDropdown;

    @FindBy(xpath = "//option[contains(text(),'Price (high to low)')]")
    WebElement highToLowOption;

    @FindBy(className = "shopping_cart_link")
    WebElement cartLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //create method for action



    // Method to check if inventory is displayed
    public boolean isInventoryDisplayed() {
        return !inventoryItems.isEmpty();
    }



    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void selectSortOption(String option) {
        sortDropdown.click();
        for (WebElement opt : sortDropdown.findElements(By.tagName("option"))) {
            if (opt.getText().equals(option)) {
                opt.click();
                break;
            }
        }
    }

    public double getItemPrice(int index) {
        String priceText = itemPrices.get(index).getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    public void addItemToCart(String itemId) {
        driver.findElement(By.id(itemId)).click();
    }

    public void clickCart() {
        cartLink.click();
    }

}
