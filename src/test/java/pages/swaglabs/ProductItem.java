package pages.swaglabs;

import io.appium.java_client.AppiumBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@AllArgsConstructor
public class ProductItem {

    private final WebElement element;

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
