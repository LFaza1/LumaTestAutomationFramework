package Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Configurations.BaseSuite;
import Constants.ConfigConstants;
import DataProvider.DataProviders;
import Pages.CheckOutPage;
import Pages.Product;
import Pages.SignInPage;

import java.io.IOException;

import java.util.Hashtable;

public class ProductPurchase extends BaseSuite {

    @Test(dataProvider = "checkOutEndToEnd")
    public void productPurchaseEndToEnd(Hashtable<String, String> productData, Hashtable<String, String> addressData)
            throws InterruptedException {
        // log in
        header.navigateToSignIn();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.inputEmail(ConfigConstants.TESTUSERNAME);
        signInPage.inputPassword(ConfigConstants.TESTPASSWORD);
        signInPage.clickSignInButton();
        softAssert.assertTrue(header.isWelcomeMessageDisplayed());
        // navigate
        header.navigateToSubMenu(productData.get("Navigation"));
        // add a product to cart
        Product product = new Product(driver);
        product.setProductFromUiByName(productData.get("Name"));
        if (productData.keySet().contains("Size"))
            product.selectSize(productData.get("Size"));
        if (productData.keySet().contains("Color"))
            product.selectColor(productData.get("Color"));
        product.addToCart();
        // verify product is added to cart
        Assert.assertFalse(header.cart.isCartEmpty());
        softAssert.assertEquals(header.cart.getCartCount(), "1");
        // click on cart icon
        header.cart.clickOnCartIcon();
        // verify details
        header.cart.clickOnProductSeeDetails();
        if (productData.keySet().contains("Size")) {
            softAssert.assertEquals(productData.get("Size"), header.cart.getProductSizeFromCart(),
                    "Product size is not the previously selected one!");
        }
        if (productData.keySet().contains("Color")) {
            softAssert.assertEquals(productData.get("Color"), header.cart.getProductColorFromCart(),
                    "Product color is not the previously selected one!");
        }
        // click on proceed to checkout
        header.cart.clickOnCheckoutButton();
        softAssert.assertEquals(header.getPageTitle(), "Checkout");
        // enter fields
        CheckOutPage checkOut = new CheckOutPage(driver);
        Assert.assertTrue(checkOut.isShippingAddressPageDisplayed());
        Assert.assertTrue(checkOut.isShippingAddressDetailsDisplayed());
        // verify address details
        softAssert.assertEquals(checkOut.getShippingAddressName(),
                addressData.get("First Name") + " " + addressData.get("Last Name"));
        softAssert.assertEquals(checkOut.getShippingAddressStreet(), addressData.get("Street Address"));
        softAssert.assertEquals(checkOut.getShippingAddressCityStateZip(), addressData.get("City") + ", "
                + addressData.get("State/Province") + " " + addressData.get("Zip/Postal Code"));
        softAssert.assertEquals(checkOut.getShippingAddressCountry(), addressData.get("Country"));
        checkOut.selectFirstShippingMethod();
        // click next
        checkOut.clickOnNextButton();
        Assert.assertTrue(checkOut.isPaymentMethodsPageDisplayed());
        // click place order
        checkOut.clickOnPlaceOrderButton();
        // verify page
        Assert.assertTrue(checkOut.isOrderSuccessPageDisplayed());
        softAssert.assertEquals(checkOut.getOrderSuccessPageTitle(), "Thank you for your purchase!");
        softAssert.assertAll();
    }

    @DataProvider(name = "checkOutEndToEnd")
    public static Object[][] checkOutEndToEnd() throws IOException {
        Object[][] productData = DataProviders.getProductData();
        Object[][] addressData = DataProviders.getShippingAddressData();

        Hashtable<String, String> addressDataTable = (Hashtable<String, String>) addressData[0][0];

        Object[][] combinedData = new Object[productData.length][2];
        for (int i = 0; i < productData.length; i++) {
            Hashtable<String, String> productDataTable = (Hashtable<String, String>) productData[i][0];
            combinedData[i][0] = productDataTable;
            combinedData[i][1] = addressDataTable;
        }
        return combinedData;
    }
}
