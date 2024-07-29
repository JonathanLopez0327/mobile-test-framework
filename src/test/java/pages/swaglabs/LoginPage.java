package pages.swaglabs;

import basetest.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import basepage.BasePage;

import static io.appium.java_client.AppiumBy.*;

public class LoginPage extends BasePage {

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    private void enterUsername(String username) {
        AppiumBy usernameField = new ByAccessibilityId("test-Username");
        writeTextOn(usernameField, username);
    }

    private void enterPassword(String password) {
        AppiumBy passwordField = new ByAccessibilityId("test-Password");
        writeTextOn(passwordField, password);
    }

    private void clickLoginButton() {
        AppiumBy loginButton = new ByAccessibilityId("test-LOGIN");
        clickOn(loginButton);
    }

    public boolean isErrorMessageDisplayed() {
        AppiumBy errorMessage = new ByAccessibilityId("test-Error message");
        return isElementPresent(errorMessage, 2);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);

        createStep("Se completo el login con los siguientes campos.", true, true, true);


        clickLoginButton();
    }
}
