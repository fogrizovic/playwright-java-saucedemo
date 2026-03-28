package com.saucedemo.factory;

import com.microsoft.playwright.*;

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

        System.out.println("browser name is: " + browserName);

        switch (browserName.toLowerCase(Locale.ROOT)) {
            case "chromium":
                browserThreadLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                browserThreadLocal.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                browserThreadLocal.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                browserThreadLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        browserContextThreadLocal.set(getBrowser().newContext());
        pageThreadLocal.set(getBrowserContext().newPage());

        return getPage();
    }
}
