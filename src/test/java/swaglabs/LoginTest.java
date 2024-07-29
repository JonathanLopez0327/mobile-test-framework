package swaglabs;

import basetest.BaseTest;
import io.appium.java_client.AppiumDriver;
import pages.swaglabs.HomePage;
import pages.swaglabs.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @Test(description = "Iniciar sesion con credenciales validas")
    public void login_test() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(homePage.isProductsDisplayed(), "No ha iniciado sesion correctamente.");
    }

//    @Test(description = "Iniciar sesion con credenciales validas")
//    void login_with_invalid_credentials() {
//        loginPage = new LoginPage(getDriver());
//        homePage = new HomePage(getDriver());
//        loginPage.login("error_user", "error_sauce");
//        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "No se muestra mensaje de error correctamente.");
//    }
}
