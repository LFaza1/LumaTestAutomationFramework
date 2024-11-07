package Tests;

import org.testng.annotations.Test;

import Configurations.BaseSuite;
import Constants.ConfigConstants;
import Pages.SignInPage;

public class SignInTest extends BaseSuite {
    @Test
    public void signIn() {
        header.navigateToSignIn();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.inputEmail(ConfigConstants.TESTUSERNAME);
        signInPage.inputPassword(ConfigConstants.TESTPASSWORD);
        signInPage.clickSignInButton();
        softAssert.assertTrue(header.isWelcomeMessageDisplayed());
        softAssert.assertEquals(header.getWelcomeMessage(),
                uiConstants.SIGNINSUCCESSMESSAGE.replace("@firstName", "TestUser").replace("@lastName", "User"));
        softAssert.assertAll();
    }
}
