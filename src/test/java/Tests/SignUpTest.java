package Tests;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import Configurations.BaseSuite;
import DataProvider.DataProviders;
import Pages.SignUpPage;

public class SignUpTest extends BaseSuite {
    @Test(dataProviderClass = DataProviders.class, dataProvider = "signUpDataWithEmailUpdate")
    public void signUp(Hashtable<String, String> userdata) {
        header.navigateToCreateAnAccount();
        SignUpPage signUpPage = new SignUpPage(driver);
        // verify page title as Create New Customer Account
        Assert.assertEquals(driver.getTitle(), uiConstants.CREATEACCOUNTPAGETITLE);
        signUpPage.fillInRegistrationForm(userdata.get("First Name"), userdata.get("Last Name"), userdata.get("Email"),
                userdata.get("Password"), userdata.get("Password"));
        Assert.assertTrue(signUpPage.isSuccessMessageDisplayed(), "Sign Up not successful");
        softAssert.assertEquals(signUpPage.getSuccessMessage(), uiConstants.REGISTRATIONSUCCESSMESSAGE, "Invalid registration success message");
        softAssert.assertAll();
    }
}
