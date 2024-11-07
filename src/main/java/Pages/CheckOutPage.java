package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object class representing the Checkout page with shipping, payment and
 * order placement functionality
 */
public class CheckOutPage extends BasePage {
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    // Web elements for checkout step titles
    @FindBy(xpath = "//*[@class='step-title' and text()='Shipping Address']")
    private WebElement shippingAddressTitle;
    @FindBy(xpath = "//*[@class='step-title' and text()='Shipping Method']")
    private WebElement shippingMethodTitle;
    @FindBy(xpath = "//*[@class='step-title' and text()='Payment Method']")
    private WebElement paymentMethodTitle;

    // Web elements for shipping details
    @FindBy(xpath = "//*[contains(concat(' ', normalize-space(@class), ' '), ' shipping-address-item ')]")
    private WebElement shippingAddress;
    @FindBy(xpath = "//*[@id='checkout-shipping-method-load']//tbody//tr")
    private List<WebElement> shippingMethods;

    // Navigation and action buttons
    @FindBy(xpath = "//button[.//*[contains(text(),'Next')]]")
    private WebElement nextButton;
    @FindBy(xpath = "//button[.//*[contains(text(), 'Place Order')]]")
    private WebElement placeOrderButton;
    @FindBy(xpath = "//*[@class='page-title']")
    private WebElement pageTitle;

    /**
     * Returns the complete shipping address text
     */
    public String getShippingAddress() {
        return shippingAddress.getText();
    }

    /**
     * Checks if shipping address details are visible on the page
     */
    public boolean isShippingAddressDetailsDisplayed() {
        return isElementVisibleWithWait(shippingAddress);
    }

    /**
     * Methods to get individual components of the shipping address
     */
    public String getShippingAddressName() {
        return shippingAddress.getText().split("\n")[0];
    }

    public String getShippingAddressStreet() {
        return shippingAddress.getText().split("\n")[1];
    }

    public String getShippingAddressCityStateZip() {
        return shippingAddress.getText().split("\n")[2];
    }

    public String getShippingAddressCountry() {
        return shippingAddress.getText().split("\n")[3];
    }

    public String getShippingAddressPhone() {
        return shippingAddress.getText().split("\n")[4];
    }

    /**
     * Selects the first available shipping method after waiting for the loader to
     * disappear
     */
    public void selectFirstShippingMethod() {
        waitTillLoaderDisappears();
        shippingMethods.get(0).click();
    }

    /**
     * Clicks the Next button and waits for the page to load
     */
    public void clickOnNextButton() {
        nextButton.click();
        waitTillLoaderDisappears();
    }

    /**
     * Methods to check visibility of different checkout steps
     */
    public boolean isShippingAddressPageDisplayed() {
        return isElementVisibleWithWait(shippingAddressTitle);
    }

    public boolean isPaymentMethodsPageDisplayed() {
        return isElementVisibleWithWait(paymentMethodTitle);
    }

    /**
     * Clicks the Place Order button to complete the order
     */
    public void clickOnPlaceOrderButton() {
        placeOrderButton.click();
    }

    /**
     * Methods to verify order success
     */
    public boolean isOrderSuccessPageDisplayed() {
        waitTillLoaderDisappears();
        return isElementVisibleWithWait(pageTitle);
    }

    public String getOrderSuccessPageTitle() {
        return pageTitle.getText();
    }
}