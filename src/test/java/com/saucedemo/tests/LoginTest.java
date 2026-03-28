package com.saucedemo.tests;

import com.saucedemo.factory.PlaywrightFactory;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod(dependsOnMethods = "initBrowser")
    public void initPage() {
        loginPage = new LoginPage(PlaywrightFactory.getPage());
    }

    @DataProvider(name = "provideCredentials")
    public Object[][] provideCredentials() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"standard_user", "wrongPass", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(description = "should display error when invalid credentials", dataProvider = "provideCredentials")
    public void shouldDisplayErrorMessage(String user, String pass, String expectedErrorMessage) {
        loginPage.doLogin(user, pass);
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedErrorMessage);
    }
}
