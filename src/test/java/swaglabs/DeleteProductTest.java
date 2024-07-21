package swaglabs;

import base.BaseTest;
import org.mobile.testing.framework.gestures.GesturesUtils;
import org.mobile.testing.framework.pages.swanglabs.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteProductTest extends BaseTest {

    private HomePage homePage;
    private CartPage cartPage;
    private GesturesUtils gesturesUtils;
    private LoginPage loginPage;

    private static final String PRODUCT_NAME = "Sauce Labs Backpack";

    @BeforeMethod
    void setUp() {
        loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");
        gesturesUtils = new GesturesUtils(getDriver());
        homePage = new HomePage(getDriver());
        cartPage = new CartPage(getDriver());
    }

    @Test(description = "Drag product to cart")
    void delete_one_product_of_cart() {
        ProductItem productItem = homePage.productItem(PRODUCT_NAME);
        gesturesUtils.dragTo(productItem.dragHandle(), homePage.dropZone());
        homePage.clickCartButton();
        CartItem cartItem = cartPage.cartItem(PRODUCT_NAME);
        gesturesUtils.swipe(GesturesUtils.Direction.LEFT, cartItem.element(), 25);
        cartPage.removeItem();
        Assert.assertEquals(homePage.isCartCountEqualsToZero(), true, "No se ha removido con exito el producto.");
    }
}
