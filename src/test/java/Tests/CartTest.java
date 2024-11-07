package Tests;

import java.util.List;
import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Configurations.BaseSuite;
import DataProvider.DataProviders;
import Pages.HomePage;
import Pages.Product;

public class CartTest extends BaseSuite {
    @Test
    public void addAndDeleteProductFromCart() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        List<WebElement> products = homePage.productCards.getProductCardsFromPage();
        homePage.productCards.setProductFromCard(getRandomElementFromList(products));
        String productName = homePage.productCards.getProductName();
        homePage.productCards.addToCartBySelectingOptions();
        softAssert.assertFalse(header.cart.isCartEmpty());
        softAssert.assertEquals(header.cart.getCartCount(), "1");
        softAssert.assertEquals(header.getSuccessMessage(),
                uiConstants.ADDPRODUCTTOCARTSUCCESSMESSAGE.replace("@productName", productName));
        header.cart.clickOnCartIcon();
        header.cart.clickOnDeleteButton();
        Assert.assertTrue(header.isConfirmationPopupDisplayed(), "Confirmation popup not displayed");
        softAssert.assertEquals(header.getConfirmationMessage(), uiConstants.REMOVEFROMCARTCONFIRMATIONMESSAGE,
                "Invalid confirmation message");
        header.clickOnConfirmationOkButton();
        header.refreshPage();
        softAssert.assertTrue(header.cart.isCartEmpty());
        softAssert.assertAll();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "addToCartWithNavigation")
    public void addProductToCartWithNavigation(Hashtable<String, String> data) throws InterruptedException {
        header.navigateToSubMenu(data.get("Navigation"));
        Product product = new Product(driver);
        product.setProductFromUiByName(data.get("Name"));
        if (data.keySet().contains("Size"))
            product.selectSize(data.get("Size"));
        if (data.keySet().contains("Color"))
            product.selectColor(data.get("Color"));
        product.addToCart();
        softAssert.assertFalse(header.cart.isCartEmpty());
        softAssert.assertEquals(header.cart.getCartCount(), "1");
        softAssert.assertEquals(header.getSuccessMessage(),
                uiConstants.ADDPRODUCTTOCARTSUCCESSMESSAGE.replace("@productName", data.get("Name")));
        softAssert.assertAll();
    }
}
