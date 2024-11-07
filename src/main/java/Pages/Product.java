package Pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;

/*Provides a page object for a product card
 * Can be used on all pages that has a product card
*/
public class Product extends BasePage {
    // WebElement representing the product card container
    WebElement productCard;
    // Random number generator for selecting random products/options
    Random random;

    // Constructor for creating Product page object without specific product card
    public Product(WebDriver driver) {
        super(driver);
        this.random = new Random();
    }

    // Constructor for creating Product page object with specific product card
    public Product(WebDriver driver, WebElement productCard) {
        super(driver);
        this.productCard = productCard;
        // this.productName = getProductNameFromUi();
        this.random = new Random();
    }

    // XPath selectors for various product card elements
    String sizeSelectionXpath = ".//div[@class='swatch-option text' and text()='@size']";
    String availableSizesXpath = ".//div[@class='swatch-option text']";
    String colorSelectionXpath = ".//div[@class='swatch-option color' and @option-label='@color']";
    String availableColorsXpath = ".//div[@class='swatch-option color']";
    String addToCartXpath = ".//button[@title='Add to Cart']";
    String productNameXpath = ".//*[@class='product-item-name']";
    String addToWishListXpath = ".//*[@class='action towishlist']";
    String deleteFromWishList = ".//*[@class='btn-remove action delete']";
    String productCardByName = "//*[contains(@class,'product-items')]//li[contains(@class,'product-item') and .//*[contains(text(), '{name}')]]";

    // List of all product cards on the page
    @FindBy(xpath = "//*[contains(@class,'product-items')]//li[contains(@class,'product-item')]")
    List<WebElement> productCards;

    // Returns list of all product cards from the page
    public List<WebElement> getProductCardsFromPage() {
        return productCards;
    }

    // Returns list of available sizes for the product
    public List<String> getSizes() {
        List<WebElement> sizes = productCard.findElements(By.xpath(availableSizesXpath));
        return sizes.stream().map(size -> size.getText()).toList();
    }

    // Selects a specific size for the product
    public void selectSize(String size) {
        WebElement sizeElement = productCard.findElement(By.xpath(sizeSelectionXpath.replace("@size", size)));
        sizeElement.click();
    }

    // Returns list of available colors for the product
    public List<String> getColors() {
        List<WebElement> colors = productCard.findElements(By.xpath(availableColorsXpath));
        return colors.stream().map(color -> color.getAttribute("option-label")).toList();
    }

    // Selects a specific color for the product
    public void selectColor(String color) {
        WebElement colorElement = productCard.findElement(By.xpath(colorSelectionXpath.replace("@color", color)));
        colorElement.click();
    }

    // Returns the product name from the product card
    public String getProductName() {
        WebElement productNameElement = productCard.findElement(By.xpath(productNameXpath));
        return productNameElement.getText();
    }

    // Adds product to cart by selecting first available size and color
    public void addToCartBySelectingOptions() {
        scrollIntoViewUsingJS(productCard);
        moveToElement(productCard);
        List<String> sizes = getSizes();
        List<String> colors = getColors();
        if (sizes.size() != 0)
            selectSize(sizes.get(0));
        if (colors.size() != 0)
            selectColor(colors.get(0));
        WebElement addToCartButton = productCard.findElement(By.xpath(addToCartXpath));
        addToCartButton.click();
    }

    // Adds product to cart directly without selecting options
    public void addToCart() throws InterruptedException {
        WebElement addToCartButton = productCard.findElement(By.xpath(addToCartXpath));
        addToCartButton.click();
        Thread.sleep(2000);
    }

    // Sets the product card from a given WebElement
    public void setProductFromCard(WebElement element) {
        this.productCard = element;
    }

    // Finds and sets product card by product name from UI
    public void setProductFromUiByName(String productName) {
        this.productCard = findElementByXpath(productCardByName.replace("{name}", productName));
        scrollIntoViewUsingJS(productCard);
        moveToElement(productCard);
    }

    // Adds current product to wish list
    public void clickAddToWishList() {
        WebElement addToWishListButton = productCard.findElement(By.xpath(addToWishListXpath));
        addToWishListButton.click();
    }

    // Removes current product from wish list
    public void clickDeleteFromWishList() {
        WebElement deleteFromWishListButton = productCard.findElement(By.xpath(deleteFromWishList));
        deleteFromWishListButton.click();
    }
}
