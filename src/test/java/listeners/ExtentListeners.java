package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Log4j2
public class ExtentListeners implements ITestListener {

    private static ExtentReports extentReports;
    public  static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onStart(ITestContext context) {
        String fileName = ExtentManager.getReportNameWithTimeStamp();
        String fullReportPath = System.getProperty("user.dir") + "\\reports\\" + fileName;
        extentReports = ExtentManager.createInstance(fullReportPath, "Name", "Title");
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports
                .createTest("Test case " + result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " - PASSED" + "</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);

        extentTest.get().pass("<b>" + "<font color=" + "green>" + "Screenshot of success" + "</font>" + "</b>",
                MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.getScreenshotName())
                        .build());
    }

    public void onTestFailure(ITestResult result) {
        try {
            ExtentManager.captureScreenshot();
            extentTest.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.getScreenshotName())
                            .build());
        } catch (Exception e) {
            log.error("Error ", e);
        }
    }

    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
