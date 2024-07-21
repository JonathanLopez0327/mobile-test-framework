package base;

import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.mobile.testing.framework.deviceconfig.DeviceType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.AppiumDriverEx;

@Log4j2
public class BaseTest {
    private static AppiumDriver driver;

    public BaseTest() {
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("deviceName")
    public static void setUp(@Optional String deviceName) {
        if (deviceName == null || deviceName.isEmpty()) {
            deviceName = DeviceType.NONE.name();
        }

        driver = AppiumDriverEx.getAppiumDriver();
        log.info("Device name: {}", deviceName);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        AppiumDriverEx.quitDriver();
    }
}