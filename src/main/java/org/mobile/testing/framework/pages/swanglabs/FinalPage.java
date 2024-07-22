package org.mobile.testing.framework.pages.swanglabs;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.mobile.testing.framework.pages.base.BasePage;

public class FinalPage extends BasePage {
    private AppiumBy completeContent = new AppiumBy.ByAccessibilityId("test-CHECKOUT: COMPLETE!");

    public FinalPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isComplete() {
        return findElement(completeContent)
                .findElement(AppiumBy.className("android.widget.TextView"))
                .getText().equalsIgnoreCase("THANK YOU FOR YOU ORDER");
    }
}
