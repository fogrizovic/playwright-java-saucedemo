package com.saucedemo.tests;

import com.saucedemo.factory.PlaywrightFactory;
import com.saucedemo.utils.PropertyReader;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseTest {

    @BeforeMethod
    public void initBrowser() {
        PlaywrightFactory.initBrowser(PropertyReader.getProperty("browser"));
        PlaywrightFactory.getPage().navigate(PropertyReader.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] screenshot = PlaywrightFactory.getPage().screenshot();
            Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        }

        PlaywrightFactory.getBrowserContext().close();

        if (result.getStatus() == ITestResult.FAILURE) {
            Path videoPath = PlaywrightFactory.getPage().video().path();
            try {
                Allure.addAttachment("Video", "video/webm", Files.newInputStream(videoPath), ".webm");
            } catch (IOException e) {
                throw new RuntimeException("Failed to attach video", e);
            }
        }

        PlaywrightFactory.getBrowser().close();
        PlaywrightFactory.getPlaywright().close();
    }
}
