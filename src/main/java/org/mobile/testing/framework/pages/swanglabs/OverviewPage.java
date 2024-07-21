package org.mobile.testing.framework.pages.swanglabs;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.mobile.testing.framework.pages.base.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Log4j2
public class OverviewPage extends BasePage {

    private AppiumBy overviewContent = new AppiumBy.ByAccessibilityId("test-CHECKOUT: OVERVIEW");
    private AppiumBy overviewItem = new AppiumBy.ByAccessibilityId("test-Item");
    private AppiumBy removeButton = new AppiumBy.ByAccessibilityId("test-Delete");
    private AppiumBy finishButton = new AppiumBy.ByAccessibilityId("test-FINISH");

    public OverviewPage(AppiumDriver driver) {
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

    public void clickFinishButton() {
        try {
            clickOn(finishButton);
            log.info("Finish button clicked");
        } catch (NoSuchElementException e) {
            log.error("Error clicking finish button", e);
        }
    }

    public WebElement findCartItemElement(final String title) {
        WebElement cartList = findElement(overviewContent);
        return cartList.findElements(overviewItem)
                .stream()
                .filter(item -> new CartItem(item).getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public CartItem cartItem(final String title) {
        WebElement itemElement = findCartItemElement(title);
        return itemElement != null ? new CartItem(itemElement) : null;
    }

}
