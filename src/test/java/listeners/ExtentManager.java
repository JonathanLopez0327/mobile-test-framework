package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class ExtentManager extends BaseTest {
    @Getter
    private static ExtentReports extentReports;
    @Getter
    private static String screenshotName;

    public static ExtentReports createInstance(String fileName, String reportName, String documentTitle) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle(documentTitle);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(reportName);
        sparkReporter.config().setCss("#uniqueValue {color: #6495ED;}");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Release No", "22");
        extentReports.setSystemInfo("Environment", "SQA");
        extentReports.setSystemInfo("Build No", "B-12673");

        return extentReports;
    }

    public static void captureScreenshot() {
        TakesScreenshot screenshot = getDriver();
        File src = screenshot.getScreenshotAs(OutputType.FILE);

        String timestamp = getReportNameWithTimeStamp();
        screenshotName = "screenshot_" + timestamp + ".jpg";
        File dest = new File(System.getProperty("user.dir") + "/reports/" + screenshotName);

        try (FileChannel sourceChannel = new FileInputStream(src).getChannel();
             FileChannel destChannel = new FileOutputStream(dest).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            log.info("Successfully captured a screenshot: {}", dest.getAbsolutePath());
        } catch (IOException e) {
            log.error("Error while taking screenshot: {}", e.getMessage());
        }
    }

    public static String getReportNameWithTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String timeStamp = dateTimeFormatter.format(localDateTime);
        return "Test-Report-" + timeStamp + ".html";
    }

    public static void logPassDetails(String log) {
        ExtentListeners.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }

    public static void logFailureDetails(String log) {
        ExtentListeners.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    public static void logInfoDetails(String log) {
        ExtentListeners.extentTest.get().info(log);
    }
}
