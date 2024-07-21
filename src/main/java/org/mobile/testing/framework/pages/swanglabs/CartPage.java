package org.mobile.testing.framework.pages.swanglabs;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.mobile.testing.framework.pages.base.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Log4j2
public class CartPage extends BasePage {

    private AppiumBy cartContent = new AppiumBy.ByAccessibilityId("test-Cart Content");
    private AppiumBy cartItem = new AppiumBy.ByAccessibilityId("test-Item");
    private AppiumBy removeButton = new AppiumBy.ByAccessibilityId("test-Delete");
    private AppiumBy checkoutButton = new AppiumBy.ByAccessibilityId("test-CHECKOUT");

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public void removeItem() {
        try {
            clickOn(removeButton);
            log.info("Item removed");
        } catch (NoSuchElementException e) {
            log.error("Error removing item", e);
        }
    }

    public WebElement findCartItemElement(final String title) {
        WebElement cartList = findElement(cartContent);
        return cartList.findElements(cartItem)
                .stream()
                .filter(item -> new CartItem(item).getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public CartItem cartItem(final String title) {
        WebElement itemElement = findCartItemElement(title);
        return itemElement != null ? new CartItem(itemElement) : null;
    }

    public void clickCheckoutButton() {
        try {
            clickOn(checkoutButton);
            log.info("Checkout button clicked");
        } catch (NoSuchElementException e) {
            log.error("Error clicking checkout button", e);
        }
    }
}
