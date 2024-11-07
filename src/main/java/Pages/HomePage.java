package Pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    public Product productCards;
    public HomePage(WebDriver driver){
        super(driver);
        productCards = new Product(driver);
    }
}
