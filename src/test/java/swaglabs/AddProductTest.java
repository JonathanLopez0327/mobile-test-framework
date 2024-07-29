package swaglabs;

import basetest.BaseTest;
import gestures.GesturesUtils;
import io.appium.java_client.AppiumDriver;
import pages.swaglabs.HomePage;
import pages.swaglabs.LoginPage;
import pages.swaglabs.ProductItem;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddProductTest extends BaseTest {

    private HomePage homePage;
    private GesturesUtils gesturesUtils;
    private LoginPage loginPage;

    @BeforeMethod
    void setUp() {
        loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");
        homePage = new HomePage(getDriver());
        gesturesUtils = new GesturesUtils(getDriver());
    }

    @Test(description = "Drag product to cart")
    void drag_to_cart() {
        ProductItem productItem = homePage.productItem("Sauce Labs Backpack");
        gesturesUtils.dragTo(productItem.dragHandle(), homePage.dropZone());
        Assert.assertEquals(homePage.cartCount(), 1, "No se ha añadido el producto al carrito.");
    }
}
