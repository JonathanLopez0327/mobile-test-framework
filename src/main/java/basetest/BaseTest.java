package basetest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import extentreport.ExtentManager;
import extentreport.ExtentReport;
import extentreport.SuiteListener;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import deviceconfig.DeviceType;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.AppiumDriverEx;

import java.lang.reflect.Method;


@Log4j2
public class BaseTest {

    private static ThreadLocal<AppiumDriver> driver;

    @Getter
    private static ExtentReports extentReports = ExtentManager.getInstance();

    private static ExtentTest scenario;


    public BaseTest() {
        // comment
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("deviceType")
    public static void setUp(@Optional String deviceType, ITestContext context, ITestResult result, Method testMethod) {

        String scenarioName = context.getCurrentXmlTest().getName();
        scenario = extentReports.createTest(scenarioName + " - " + testMethod.getAnnotation(Test.class).description());

        ExtentReport.setExtentTest(scenario);
        scenario.assignCategory(new String[]{scenarioName});
        scenario.assignCategory("<b>TOTALS</b>");
        

        if (deviceType == null || deviceType.isEmpty()) {
            deviceType = DeviceType.NONE.name();
        }

        driver = AppiumDriverEx.getAppiumDriver(context);
        scenario.assignDevice(new String[]{deviceType});
        log.info("Device name: {}", deviceType);
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }


    public static void createStep(String description, boolean decision, boolean isScreenshot) {
        try {
            String screenshot = isScreenshot ? ExtentManager.captureScreenshot(getDriver()) : "";

            if (decision && isScreenshot) {
                ExtentReport.getExtentTest().pass(description, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
            } else if (decision && !isScreenshot) {
                ExtentReport.getExtentTest().pass(description);
            } else if (!decision && isScreenshot) {
                ExtentReport.getExtentTest().fail(description, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
            } else if (!decision && !isScreenshot) {
                ExtentReport.getExtentTest().fail(description);
            }

        } catch (Exception e) {
            log.error("Error creating step", e);
        }
    }

    @AfterMethod()
    public void tearDown() {
        AppiumDriverEx.quitDriver();
    }

}