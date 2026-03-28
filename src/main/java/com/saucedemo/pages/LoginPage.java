package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    private String usernameInput = "[data-test='username']";
    private String passwordInput = "[data-test='password']";
    private String loginButton = "[data-test='login-button']";
    private String errorMessage = "[data-test='error']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void doLogin(String user, String pass) {
        page.locator(usernameInput).fill(user);
        page.locator(passwordInput).fill(pass);
        page.locator(loginButton).click();
    }

    public String getErrorMessage() {
        return page.locator(errorMessage).textContent();
    }
}
