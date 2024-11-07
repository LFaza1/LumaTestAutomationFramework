package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Page Object class representing the Sign In page
 * Contains elements and methods for interacting with the login form
 */
public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    // Web elements for the login form
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@id='pass']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@id='send2']")
    private WebElement signInButton;

    /**
     * Enters the email address into the email field
     * 
     * @param email The email address to input
     */
    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    /**
     * Enters the password into the password field
     * 
     * @param password The password to input
     */
    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    /**
     * Clicks the sign in button to submit the login form
     */
    public void clickSignInButton() {
        signInButton.click();
    }
}