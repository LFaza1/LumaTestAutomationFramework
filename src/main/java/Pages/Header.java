package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object class representing the header section of the website
 * Implements Singleton pattern for single instance
 */
public class Header extends BasePage {
    private static Header instance;
    public Cart cart;

    public Header(WebDriver driver) {
        super(driver);
        this.cart = new Cart(driver);
    }

    // Web elements in the header section
    @FindBy(xpath = "//header//span[contains(@class,'logged-in')]")
    private WebElement welcomeMessage;
    @FindBy(xpath = "//header//*[text()='Create an Account']")
    public WebElement createAccountButton;
    @FindBy(xpath = "//header//*[contains(text(), 'Sign In')]")
    public WebElement signInButton;
    @FindBy(xpath = "//header//button[@data-action='customer-menu-toggle']")
    public WebElement customerMenuToggle;
    @FindBy(xpath = "//header//*[@class='customer-menu']//li[.//*[contains(text(), 'Sign Out')]]")
    public WebElement signOutButton;
    @FindBy(xpath = "//header//*[@class='customer-menu']//li[.//*[contains(text(), 'My Wish List')]]")
    public WebElement myWishListButton;

    // XPath templates for navigation menu elements
    String navButtonXpath = "//*[@id='store.menu']//li[.//span[contains(text(),'@btnName')]]";
    String navSubMenuXpath = "//li[.//span[contains(text(), '@subMenu')]]";

    /**
     * Checks if welcome message is displayed to logged in user
     */
    public boolean isWelcomeMessageDisplayed() {
        return isTextPresentInElementWithWait(welcomeMessage, "Welcome");
    }

    /**
     * Returns the welcome message text
     */
    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }

    /**
     * Navigates to account creation page
     */
    public void navigateToCreateAnAccount() {
        createAccountButton.click();
    }

    /**
     * Navigates to sign in page
     */
    public void navigateToSignIn() {
        signInButton.click();
    }

    /**
     * Performs sign out operation
     */
    public void signOut() {
        customerMenuToggle.click();
        signOutButton.click();
    }

    /**
     * Navigates to user's wish list page
     */
    public void navigateToMyWishList() {
        customerMenuToggle.click();
        waitUntilElementAppears(myWishListButton);
        myWishListButton.click();
    }

    /**
     * Navigates to a main menu item
     * 
     * @param menuName Name of the menu to navigate to
     */
    public void navigateToMenu(String menuName) throws InterruptedException {
        findElementByXpath(navButtonXpath.replace("@btnName", menuName)).click();
        Thread.sleep(2000);
    }

    /**
     * Navigates through nested submenus using -> as delimiter
     * Example: "Men -> Tops -> Jackets"
     * 
     * @param subMenuToNavigateRecursively Path to submenu using -> as delimiter
     */
    public void navigateToSubMenu(String subMenuToNavigateRecursively) throws InterruptedException {
        String[] subMenus = subMenuToNavigateRecursively.split(" -> ");
        String xpathBuffer = "";
        for (int i = 0; i < subMenus.length; i++) {
            WebElement menu = null;
            if (i == 0) {
                xpathBuffer += navButtonXpath.replace("@btnName", subMenus[i]);
                menu = findElementByXpath(xpathBuffer);
                moveToElement(menu);
            } else if (i < subMenus.length - 1) {
                xpathBuffer += navSubMenuXpath.replace("@subMenu", subMenus[i]);
                menu = findElementByXpath(xpathBuffer);
                moveToElement(menu);
            }
        }

        xpathBuffer += navSubMenuXpath.replace("@subMenu", subMenus[subMenus.length - 1]);
        WebElement menu = findElementByXpath(xpathBuffer);
        waitUntilElementAppears(menu);
        menu.click();
        Thread.sleep(2000);
    }

    /**
     * Returns singleton instance of Header
     * 
     * @param driver WebDriver instance
     */
    public static Header getInstance(WebDriver driver) {
        if (instance == null) {
            instance = new Header(driver);
        }
        return instance;
    }

    /**
     * Cleans up the singleton instance
     */
    public void cleanUp() {
        instance = null;
    }
}
