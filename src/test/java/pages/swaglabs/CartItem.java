package pages.swaglabs;

import io.appium.java_client.AppiumBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@AllArgsConstructor
public class CartItem {

    private final WebElement element;

    public WebElement amount() {
        return element.findElement(AppiumBy.accessibilityId("test-Amount"));
    }

    public WebElement description() {
        return element.findElement(AppiumBy.accessibilityId("test-Description"));
    }

    public WebElement title() {
        return description().findElements(AppiumBy.className("android.widget.TextView")).get(0);
    }

    public String getTitle() {
        return title().getText();
    }

    public WebElement price() {
        return element.findElement(AppiumBy.accessibilityId("test-Price"));
    }

    public WebElement removeButton() {
        return element.findElement(AppiumBy.accessibilityId("test-REMOVE"));
    }
}
