package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class providing common page interaction methods using Selenium
 * WebDriver
 */
public class PageUtilities {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    /**
     * Constructor initializing WebDriver instance and common utilities
     * 
     * @param driver WebDriver instance to be used
     */
    public PageUtilities(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    /**
     * Scrolls to the specified element using JavaScript and moves mouse to it
     * 
     * @param element WebElement to scroll to
     */
    public void scrollIntoViewUsingJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new Actions(driver).moveToElement(element).perform();
    }

    /**
     * Checks if element is visible with explicit wait
     * 
     * @param element WebElement to check visibility
     * @return true if element becomes visible within wait time, false otherwise
     */
    public boolean isElementVisibleWithWait(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if specified text appears in element with explicit wait
     * 
     * @param element WebElement to check text in
     * @param text    Text to look for
     * @return true if text appears within wait time, false otherwise
     */
    public boolean isTextPresentInElementWithWait(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the title of current page
     * 
     * @return Page title as String
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Waits until specified element disappears from page
     * 
     * @param element WebElement to wait for disappearance
     */
    public void waitUntilElementDisappears(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Waits until specified element appears on page
     * 
     * @param element WebElement to wait for appearance
     */
    public void waitUntilElementAppears(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until specified element becomes clickable
     * 
     * @param element WebElement to wait for clickable state
     */
    public void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Finds element using XPath locator
     * 
     * @param xpath XPath expression to locate element
     * @return Located WebElement
     */
    public WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    /**
     * Moves mouse cursor to specified element
     * 
     * @param element WebElement to move cursor to
     */
    public void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    /**
     * Refreshes current page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }
}