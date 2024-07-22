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

//    public void onTestSuccess(ITestResult result) {
//        try {
//            String base64Screenshot = ExtentManager.captureScreenshot();
//            extentTest.get().pass("<b><font color=\"green\">Screenshot of success</font></b>",
//                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//        } catch (Exception e) {
//            log.error("Error while capturing screenshot on test success", e);
//        }
//    }
//
//    public void onTestFailure(ITestResult result) {
//        try {
//            String base64Screenshot = ExtentManager.captureScreenshot();
//            extentTest.get().fail("<b><font color=\"red\">Screenshot of failure</font></b>",
//                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//        } catch (Exception e) {
//            log.error("Error ", e);
//        }
//    }

//    public void onTestSuccess(ITestResult result) {
//        try {
//            String base64Screenshot = ExtentManager.captureScreenshot();
//            String imgTag = "<img src=\"data:image/png;base64, " + base64Screenshot + "\" height=\"200\" width=\"300\" />";
//            extentTest.get().pass("<b><font color=\"green\">Screenshot of success</font></b><br>" + imgTag);
//        } catch (Exception e) {
//            log.error("Error while capturing screenshot on test success", e);
//        }
//    }
//
//    public void onTestFailure(ITestResult result) {
//        try {
//            String base64Screenshot = ExtentManager.captureScreenshot();
//            String imgTag = "<img src=\"data:image/png;base64, " + base64Screenshot + "\" height=\"200\" width=\"300\" />";
//            extentTest.get().fail("<b><font color=\"red\">Screenshot of failure</font></b><br>" + imgTag);
//        } catch (Exception e) {
//            log.error("Error ", e);
//        }
//    }

//    public void onTestSuccess(ITestResult result) {
//        try {
//            String base64Screenshot = ExtentManager.captureScreenshot();
//            String imgTag = "<img src=\"data:image/png;base64, " + base64Screenshot + "\" style=\"max-width:25%; height:auto;\" />";
//            extentTest.get().pass("<b><font color=\"green\">Screenshot of success</font></b><br>" + imgTag);
//        } catch (Exception e) {
//            log.error("Error while capturing screenshot on test success", e);
//        }
//    }

    public void onTestSuccess(ITestResult result) {
        try {
            String base64Screenshot = ExtentManager.captureScreenshot();
            String imgTag = "<img src=\"data:image/png;base64, " + base64Screenshot + "\" style=\"max-width:25%; height:auto;\" onclick=\"this.style.maxWidth='100%'; this.style.height='auto';\" />";
            extentTest.get().pass("<b><font color=\"green\">Screenshot of success</font></b><br>" + imgTag);
        } catch (Exception e) {
            log.error("Error while capturing screenshot on test success", e);
        }
    }

    public void onTestFailure(ITestResult result) {
        try {
            String base64Screenshot = ExtentManager.captureScreenshot();
            String imgTag = "<img src=\"data:image/png;base64, " + base64Screenshot + "\" style=\"max-width:25%; height:auto;\" />";
            extentTest.get().fail("<b><font color=\"red\">Screenshot of failure</font></b><br>" + imgTag);
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
