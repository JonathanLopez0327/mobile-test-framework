package base;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.mobile.testing.framework.deviceconfig.DeviceType;
import org.mobile.testing.framework.utils.PropertyLoader;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Log4j2
public class BaseTest {

    private static final String AUTOMATION_NAME = "appium.automationName";
    private static final String PLATFORM_NAME = "platformName";
    private static final String PLATFORM_VERSION = "appium.platformVersion";
    private static final String DEVICE_NAME = "appium.deviceName";
    private static final String APP_ACTIVITY = "appium.appActivity";
    private static final String APP_PACKAGE = "appium.appPackage";
    private static final String SERVER_URL = "appium.server.url";

    private static final DesiredCapabilities capabilities = new DesiredCapabilities();
    private static String appiumServerUrl;
    private static AppiumDriver driver;

    public BaseTest() {
    }

    private static AppiumDriver loadAndroidCapabilities() throws URISyntaxException, MalformedURLException {
        Properties properties = PropertyLoader.loadProperties("src/main/resources/android.properties");
        if (properties != null) {
            capabilities.setCapability("appium:automationName", properties.getProperty(AUTOMATION_NAME));
            capabilities.setCapability("platformName", properties.getProperty(PLATFORM_NAME));
            capabilities.setCapability("appium:platformVersion", properties.getProperty(PLATFORM_VERSION));
            capabilities.setCapability("appium:deviceName", properties.getProperty(DEVICE_NAME));
            capabilities.setCapability("appium:appActivity", properties.getProperty(APP_ACTIVITY));
            capabilities.setCapability("appium:appPackage", properties.getProperty(APP_PACKAGE));

            return new AppiumDriver(new URI(properties.getProperty(SERVER_URL)).toURL(), capabilities);
        } else {
            log.error("Properties could not be loaded.");
        }

        return null;
    }

    public static AppiumDriver getDevice(DeviceType deviceType) throws URISyntaxException {
        try {
            return switch (deviceType) {
                case ANDROID -> loadAndroidCapabilities();
                case IOS -> loadAndroidCapabilities();
                default -> null;
            };
        } catch (MalformedURLException e) {
            log.error("Error creating Appium driver: {}", e.getMessage());
            return null;
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("deviceName")
    public static void setUp(@Optional String deviceName) throws URISyntaxException {
        if (deviceName.isEmpty()) {
            deviceName = String.valueOf(DeviceType.NONE);
        }

        driver = getDevice(DeviceType.valueOf(deviceName));
        log.info("Device name: {}", deviceName);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}