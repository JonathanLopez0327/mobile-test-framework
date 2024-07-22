package swaglabs;

import base.BaseTest;
import org.mobile.testing.framework.gestures.GesturesUtils;
import org.mobile.testing.framework.pages.swanglabs.HomePage;
import org.mobile.testing.framework.pages.swanglabs.LoginPage;
import org.mobile.testing.framework.pages.swanglabs.ProductItem;
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
