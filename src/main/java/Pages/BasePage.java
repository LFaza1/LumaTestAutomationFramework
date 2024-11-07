package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.PageUtilities;

import org.openqa.selenium.WebElement;

/**
 * Base page class containing common web elements and methods used across pages
 */
public class BasePage extends PageUtilities{

    /**
     * Constructor to initialize the page elements
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Web Elements
    @FindBy(xpath = "//*[contains(@class, 'message-success')]")
    private WebElement successMessage;
    
    @FindBy(xpath = "//*[@class='loader']") 
    private WebElement loader;
    
    @FindBy(xpath = "//*[contains(@class, 'modal-popup confirm')]")
    private WebElement confirmationPopup;
    
    @FindBy(xpath ="//*[contains(@class, 'modal-popup confirm')]//*[@class='modal-content']")
    private WebElement confirmationMessage;
    
    @FindBy(xpath ="//*[contains(@class, 'modal-popup confirm')]//*[@class='modal-footer']//button[.//*[contains(text(),'OK')]]")
    private WebElement confirmationOkButton;

    /**
     * Checks if success message is displayed
     * @return true if message is visible, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementVisibleWithWait(successMessage);
    }

    /**
     * Gets the text of success message
     * @return Success message text
     */
    public String getSuccessMessage() {
        return successMessage.getText();
    }

    /**
     * Waits for the loading indicator to disappear
     */
    public void waitTillLoaderDisappears() {
        waitUntilElementDisappears(loader);
    }

    /**
     * Checks if confirmation popup is displayed
     * @return true if popup is visible, false otherwise
     */
    public boolean isConfirmationPopupDisplayed() {
        return isElementVisibleWithWait(confirmationPopup);
    }

    /**
     * Gets the text of confirmation message
     * @return Confirmation message text
     */
    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    /**
     * Clicks OK button on confirmation popup and waits for popup to close
     */
    public void clickOnConfirmationOkButton() {
        confirmationOkButton.click();
        waitUntilElementDisappears(confirmationPopup);
    }
}
