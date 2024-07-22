package utils;

import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;

@Log4j2
public class AppiumDriverEx {

    private static final String AUTOMATION_NAME = "appium.automationName";
    private static final String PLATFORM_NAME = "platformName";
    private static final String PLATFORM_VERSION = "appium.platformVersion";
    private static final String DEVICE_NAME = "appium.deviceName";
    private static final String APP_ACTIVITY = "appium.appActivity";
    private static final String APP_PACKAGE = "appium.appPackage";
    private static final String SERVER_URL = "appium.server.url";

    private static AppiumDriver driver;

    public static AppiumDriver getAppiumDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability(MobileCapability.AUTOMATION_NAME, "UiAutomator2");
            capabilities.setCapability(MobileCapability.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapability.PLATFORM_VERSION, "15");
            capabilities.setCapability(MobileCapability.DEVICE_NAME, "Pixel 8 Pro API 35");
            capabilities.setCapability(MobileCapability.APP_ACTIVITY, "com.swaglabsmobileapp.MainActivity");
            capabilities.setCapability(MobileCapability.APP_PACKAGE, "com.swaglabsmobileapp");

            driver = new AppiumDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(), capabilities);
        } catch (Exception e) {
            log.error("Properties could not be loaded.");
        }

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
