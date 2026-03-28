package com.saucedemo.tests;

import com.saucedemo.factory.PlaywrightFactory;
import com.saucedemo.utils.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void initBrowser() {
        PlaywrightFactory.initBrowser(PropertyReader.getProperty("browser"));
        PlaywrightFactory.getPage().navigate(PropertyReader.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        PlaywrightFactory.getBrowser().close();
        PlaywrightFactory.getPlaywright().close();
    }
}
