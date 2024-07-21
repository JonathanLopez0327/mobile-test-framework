package org.mobile.testing.framework.pages.swanglabs;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.mobile.testing.framework.pages.base.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static io.appium.java_client.AppiumBy.className;

@Log4j2
public class HomePage extends BasePage {

    private AppiumBy carButton = new AppiumBy.ByAccessibilityId("test-Cart");
    private AppiumBy dropZone = new AppiumBy.ByAccessibilityId("test-Cart drop zone");
    private AppiumBy products = new AppiumBy.ByAccessibilityId("test-PRODUCTS");
    private AppiumBy productItem = new AppiumBy.ByAccessibilityId("test-Item");
    private AppiumBy itemTitle = new AppiumBy.ByAccessibilityId("test-Item title");
    private static final String CART_COUNT_CLASNAME = "android.widget.TextView";


    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isProductsDisplayed() {
        return isElementPresent(products, 2);
    }

    public WebElement dropZone() {
        return findElement(dropZone);
    }

    public WebElement cartButton() {
        return findElement(carButton);
    }

    public void clickCartButton() {
        try {
            cartButton().click();
            log.info("Cart button clicked");
        } catch (NoSuchElementException e) {
            log.error("Error clicking cart button", e);
        }
    }

    public int cartCount() {
        final var countText = cartButton().findElement(className(CART_COUNT_CLASNAME))
                .getText();
        return Integer.parseInt(countText);
    }

    public boolean isCartCountEqualsToZero() {
        return cartButton().findElements(className(CART_COUNT_CLASNAME)).isEmpty();
    }

    public ProductItem productItem(final String title) {
        WebElement productList = findElement(products);
        WebElement productElement = productList.findElements(productItem)
                .stream()
                .filter(item -> item.findElement(itemTitle).getText().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
        return productElement != null ? new ProductItem(productElement) : null;
    }
}
