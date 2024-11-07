package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object class representing the shopping cart page
 */
public class Cart extends BasePage {
    public Cart(WebDriver driver) {
        super(driver);
    }

    // Element representing the cart item count number
    @FindBy(xpath = "//span[@class='counter-number']")
    private WebElement cartCount;

    // Element representing the cart icon/button
    @FindBy(xpath = "//*[@class='minicart-wrapper']")
    private WebElement cartButton;

    // Element for viewing product details in cart
    @FindBy(xpath = "//*[@class='product options']")
    private WebElement productSeeDetails;

    // Element showing the selected product size
    @FindBy(xpath = "//*[@class='product options list']//dt[contains(text(), 'Size')]/following-sibling::dd[1]/span")
    private WebElement productSize;

    // Element showing the selected product color
    @FindBy(xpath = "//*[@class='product options list']//dt[contains(text(), 'Color')]/following-sibling::dd[1]/span")
    private WebElement productColor;

    // Element for removing items from cart
    @FindBy(xpath = "//*[@class='action delete']")
    private WebElement deleteButton;

    // Element for proceeding to checkout
    @FindBy(id = "top-cart-btn-checkout")
    private WebElement checkoutButton;

    /**
     * Checks if the shopping cart is empty
     * 
     * @return true if cart is empty, false otherwise
     */
    public boolean isCartEmpty() {
        return !isElementVisibleWithWait(cartCount);
    }

    /**
     * Gets the number of items in cart
     * 
     * @return String representing number of items
     */
    public String getCartCount() {
        scrollIntoViewUsingJS(cartCount);
        return cartCount.getText();
    }

    /**
     * Clicks on the cart icon to view cart contents
     */
    public void clickOnCartIcon() {
        scrollIntoViewUsingJS(cartButton);
        cartButton.click();
    }

    /**
     * Clicks to view detailed product information
     */
    public void clickOnProductSeeDetails() {
        waitUntilElementAppears(productSeeDetails);
        productSeeDetails.click();
    }

    /**
     * Gets the size of the product in cart
     * 
     * @return String representing product size
     */
    public String getProductSizeFromCart() {
        return productSize.getText();
    }

    /**
     * Gets the color of the product in cart
     * 
     * @return String representing product color
     */
    public String getProductColorFromCart() {
        return productColor.getText();
    }

    /**
     * Removes an item from the cart
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */
    public void clickOnDeleteButton() throws InterruptedException {
        deleteButton.click();
        Thread.sleep(2000);
    }

    /**
     * Proceeds to checkout with cart items
     */
    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }
}
