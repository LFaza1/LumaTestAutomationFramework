package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Page Object class representing the Sign Up page
 * Contains web elements and methods for user registration
 */
public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    // Web elements for registration form fields
    @FindBy(xpath = "//input[@id='firstname']")
    public WebElement firstNameField;

    @FindBy(xpath = "//input[@id='lastname']")
    public WebElement lastNameField;

    @FindBy(xpath = "//input[@id='email_address']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordField;

    @FindBy(xpath = "//input[@id='password-confirmation']")
    public WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@title='Create an Account']")
    public WebElement createAccountButton;

    /**
     * Fills in and submits the registration form
     * 
     * @param firstName       User's first name
     * @param lastName        User's last name
     * @param email           User's email address
     * @param password        User's password
     * @param confirmPassword Password confirmation
     */
    public void fillInRegistrationForm(String firstName, String lastName, String email, String password,
            String confirmPassword) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        createAccountButton.click();
    }
}