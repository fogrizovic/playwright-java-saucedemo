package com.saucedemo.factory;

import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.Locale;

public class PlaywrightFactory {

    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return playwrightThreadLocal.get();
    }

    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    public static BrowserContext getBrowserContext() {
        return browserContextThreadLocal.get();
    }

    public static Page getPage() {
        return pageThreadLocal.get();
    }

    public static Page initBrowser(String browserName) {

        playwrightThreadLocal.set(Playwright.create());

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        System.out.println("browser name is: " + browserName);

        switch (browserName.toLowerCase(Locale.ROOT)) {
            case "chromium":
                browserThreadLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "firefox":
                browserThreadLocal.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "safari":
                browserThreadLocal.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "chrome":
                browserThreadLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless)));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("target/videos/"));
        browserContextThreadLocal.set(getBrowser().newContext(contextOptions));
        pageThreadLocal.set(getBrowserContext().newPage());

        return getPage();
    }
}
