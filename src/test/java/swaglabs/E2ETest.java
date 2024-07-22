package swaglabs;

import base.BaseTest;
import org.mobile.testing.framework.gestures.GesturesUtils;
import org.mobile.testing.framework.pages.swanglabs.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class E2ETest extends BaseTest {

    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OverviewPage overviewPage;
    private GesturesUtils gesturesUtils;

    @BeforeMethod()
    public void login_test() {
        LoginPage loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        cartPage = new CartPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
        overviewPage = new OverviewPage(getDriver());
        gesturesUtils = new GesturesUtils(getDriver());

        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Completar la comprar de un producto")
    void complete_buy() {
        ProductItem productItem = homePage.productItem("Sauce Labs Backpack");
        gesturesUtils.dragTo(productItem.dragHandle(), homePage.dropZone());
        homePage.clickCartButton();
        cartPage.clickCheckoutButton();
        checkoutPage.fillForm("John", "Doe", "12345");
        overviewPage.clickFinishButton();
        Assert.assertEquals(new FinalPage(getDriver()).isComplete(), true, "No se completo correctamente la compra");
    }
}
