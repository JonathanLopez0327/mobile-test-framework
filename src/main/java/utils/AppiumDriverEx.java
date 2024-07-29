package utils;

import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;

import java.net.URI;

@Log4j2
public class AppiumDriverEx {
    
    // private static AppiumDriver driver;
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    
    private static final String AUTOMATION_NAME = "automationName";
    private static final String PLATFORM_NAME = "platformName";
    private static final String PLATFORM_VERSION = "platformVersion";
    private static final String DEVICE_NAME = "deviceName";
    private static final String DEVICE_UDID = "deviceUDID";
    private static final String APP_ACTIVITY = "appActivity";
    private static final String APP_PACKAGE = "appPackage";
    private static final String APPIUM_URL = "appiumURL";

    private AppiumDriverEx() {
    }

    public static ThreadLocal<AppiumDriver> getAppiumDriver(ITestContext context) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability(MobileCapability.AUTOMATION_NAME, context.getCurrentXmlTest().getParameter(AUTOMATION_NAME));
            capabilities.setCapability(MobileCapability.PLATFORM_NAME, context.getCurrentXmlTest().getParameter(PLATFORM_NAME));
            capabilities.setCapability(MobileCapability.PLATFORM_VERSION, context.getCurrentXmlTest().getParameter(PLATFORM_VERSION));

            if (context.getCurrentXmlTest().getParameter(DEVICE_NAME) != null) {
                capabilities.setCapability(MobileCapability.DEVICE_NAME, context.getCurrentXmlTest().getParameter(DEVICE_NAME));
            } else {
                capabilities.setCapability(MobileCapability.DEVICE_UDID, context.getCurrentXmlTest().getParameter(DEVICE_UDID));
            }

            capabilities.setCapability(MobileCapability.DEVICE_NAME, context.getCurrentXmlTest().getParameter(DEVICE_NAME));


            capabilities.setCapability(MobileCapability.APP_ACTIVITY, context.getCurrentXmlTest().getParameter(APP_ACTIVITY));
            capabilities.setCapability(MobileCapability.APP_PACKAGE, context.getCurrentXmlTest().getParameter(APP_PACKAGE));

             driver.set(new AppiumDriver(new URI(context.getCurrentXmlTest().getParameter(APPIUM_URL)).toURL(), capabilities));
        } catch (Exception e) {
            log.error("Properties could not be loaded.");
        }

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.get().quit();
            driver = null;
        }
    }
}
