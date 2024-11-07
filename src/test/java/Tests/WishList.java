package Tests;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import Configurations.BaseSuite;
import Constants.ConfigConstants;
import DataProvider.DataProviders;
import Pages.Product;
import Pages.SignInPage;

public class WishList extends BaseSuite {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "product")
    public void addItemToWishList(Hashtable<String, String> productData) throws InterruptedException {
        // login
        header.navigateToSignIn();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.inputEmail(ConfigConstants.TESTUSERNAME);
        signInPage.inputPassword(ConfigConstants.TESTPASSWORD);
        signInPage.clickSignInButton();
        softAssert.assertTrue(header.isWelcomeMessageDisplayed());
        // navigate to product
        header.navigateToSubMenu(productData.get("Navigation"));
        Product product = new Product(driver);
        product.setProductFromUiByName(productData.get("Name"));
        product.clickAddToWishList();
        Assert.assertTrue(product.isSuccessMessageDisplayed(), "Success message was not displayed.");
        softAssert.assertEquals(product.getSuccessMessage(), uiConstants.ADDTOWISHLISTSUCCESSMESSAGE.replace("@productName", productData.get("Name")));
    }
    @Test(dataProviderClass = DataProviders.class, dataProvider = "product", dependsOnMethods = "addItemToWishList")
    public void removeItemFromWishList(Hashtable<String, String> productData) throws InterruptedException {
        // login
        header.navigateToSignIn();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.inputEmail(ConfigConstants.TESTUSERNAME);
        signInPage.inputPassword(ConfigConstants.TESTPASSWORD);
        signInPage.clickSignInButton();
        softAssert.assertTrue(header.isWelcomeMessageDisplayed());
        // navigate to wishlist
        header.navigateToMyWishList();
        Product product = new Product(driver);
        product.setProductFromUiByName(productData.get("Name"));
        product.clickDeleteFromWishList();
        Assert.assertTrue(product.isSuccessMessageDisplayed(), "Success message was not displayed.");
        softAssert.assertEquals(product.getSuccessMessage(), uiConstants.REMOVEFROMWISHLISTSUCCESSMESSAGE.replace("@productName", productData.get("Name")));
    }
}
