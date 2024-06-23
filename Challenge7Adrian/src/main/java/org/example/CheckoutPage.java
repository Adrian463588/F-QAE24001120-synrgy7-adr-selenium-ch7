package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;

    //class attributes
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(xpath = "//input[@id='first-name']")
    WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='last-name']")
    WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='postal-code']")
    WebElement postalCodeInput;

    @FindBy(xpath = "//input[@class='submit-button btn btn_primary cart_button btn_action' and @data-test='continue']")
    WebElement continueButton;

    @FindBy(xpath = "//a[@class='btn_secondary cart_button']")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    WebElement subtotalLabel;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    WebElement taxLabel;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    WebElement totalLabel;

    @FindBy(xpath = "//button[@class='btn btn_action btn_medium cart_button' and @data-test='finish']")
    WebElement finishButton;

    @FindBy(xpath = "//h2[@class='complete-header']")
    WebElement completeHeader;

    @FindBy(xpath = "//img[contains(@class, 'pony_express')]")
    WebElement completedImage;

    @FindBy(xpath = "//div[@class='complete-text']")
    WebElement completeText;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //create method for action
    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public String getSubtotal() {
        return subtotalLabel.getText();
    }

    public String getTax() {
        return taxLabel.getText();
    }

    public String getTotal() {
        return totalLabel.getText();
    }

    public void clickFinish() {
        finishButton.click();
    }

    public boolean isCompletedImageDisplayed() {
        return completedImage.isDisplayed();
    }

    public String getCompleteHeader() {
        return completeHeader.getText();
    }

    public String getCompleteText() {
        return completeText.getText();
    }

    public boolean isCheckoutComplete() {
        return completeHeader.isDisplayed();
    }
}
