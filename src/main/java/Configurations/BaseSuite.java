package Configurations;

import java.util.Random;
import java.util.List;

import Pages.Header;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import Constants.ConfigConstants;
import Constants.UiConstants;

public class BaseSuite {
    public static WebDriver driver;
    public static Header header;
    public Random random;
    public UiConstants uiConstants;
    public SoftAssert softAssert;

    @BeforeSuite
    public void setup() {
        random = new Random();
    }

    @BeforeMethod
    public void initiateDriver() throws InterruptedException {
        softAssert = new SoftAssert();
        uiConstants = new UiConstants();
        switch (ConfigConstants.BROWSER) {
            case "firefox":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case "chrome":
                driver = WebDriverManager.chromedriver().create();
                break;
            case "edge":
                driver = WebDriverManager.edgedriver().create();
                break;
            default:
                new RuntimeException("Browser not defined");
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigConstants.BASEURL);
        Thread.sleep(4000);
        header = Header.getInstance(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        header.cleanUp();
    }

    public WebDriver getDriver() {
        return driver;
    }

    // common method to select a random element from a List.
    public <T> T getRandomElementFromList(List<T> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    //navigation path like Women -> Tops -> Jackets
    public String getPageTitleFromNavigation(String navigation) {
        // Reverse the array of menus and add -
        String[] navigationArray = navigation.split(" -> ");
        String pageTitle = "";
        for (int i = navigationArray.length - 1; i >= 0; i--) {
            pageTitle += navigationArray[i];
            if (i != 0) {
                pageTitle += " - ";
            }
        }
        return pageTitle;
    }
}
