package extentreport;

import basetest.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners(SuiteListener.class)
public class SuiteListener extends BaseTest implements ITestListener, IExecutionListener {

    private ExtentReports extentReports;


    public SuiteListener() {
        // comment
    }

    @Override
    public void onStart(ITestContext context) {
        extentReports = getExtentReports();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            String base64Screenshot = ExtentManager.captureScreenshot(getDriver());
            assert base64Screenshot != null;
            ExtentReport.getExtentTest().pass("<b><font color=\"green\">Screenshot of success</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } catch (Exception e) {
            log.error("Error while capturing screenshot on test success", e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String base64Screenshot = ExtentManager.captureScreenshot(getDriver());

            assert base64Screenshot != null;
            ExtentReport.getExtentTest().fail("<b><font color=\"red\">Screenshot of failure</font></b><br>",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } catch (Exception e) {
            log.error("Error ", e);
        }
    }

    @Override
    public void onExecutionFinish() {
        extentReports.flush();
    }
}
