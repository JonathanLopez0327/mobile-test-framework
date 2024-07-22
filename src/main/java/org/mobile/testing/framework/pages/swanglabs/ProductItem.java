package org.mobile.testing.framework.pages.swanglabs;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

public record ProductItem(WebElement element) {

    public WebElement addButton() {
        return element.findElement(AppiumBy.accessibilityId("test-ADD TO CART"));
    }

    public WebElement dragHandle() {
        return element.findElement(AppiumBy.accessibilityId("test-Drag Handle"));
    }

    public WebElement getTitle() {
        return element.findElement(AppiumBy.accessibilityId("test-Item title"));
    }

    public String title() {
        return getTitle().getText();
    }

    public String price() {
        return element.findElement(AppiumBy.accessibilityId("test-Price")).getText();
    }
}
