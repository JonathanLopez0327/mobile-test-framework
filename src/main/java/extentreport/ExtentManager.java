package extentreport;

import basetest.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class ExtentManager {
    private static ExtentReports extent;
    private static final String FULL_REPORT_PATH = System.getProperty("user.dir") + "\\reports\\";

    private ExtentManager() {
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
           createInstance();
        }
        return extent;
    }

    public static ExtentReports createInstance() {
        try {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FULL_REPORT_PATH + getReportNameWithTimeStamp());

            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Reporte de Automatizacion");
            sparkReporter.config().setEncoding(String.valueOf(StandardCharsets.UTF_8));
            sparkReporter.config().setReportName("Pruebas Moviles");
            sparkReporter.config().setCss("#uniqueValue {color: #6495ED;}");
            sparkReporter.config().thumbnailForBase64(true);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Release No", "22");
            extent.setSystemInfo("Environment", "SQA");
            extent.setSystemInfo("Build No", "B-12673");

            return extent;
        } catch (Exception e) {
            log.error("Error creating Extent Report", e);
            return null;
        }
    }

    public static String captureScreenshot(AppiumDriver driver) {
       try {
           TakesScreenshot screenshot = driver;
           return screenshot.getScreenshotAs(OutputType.BASE64);
       } catch (Exception e) {
           log.error("Error capturing screenshot", e);
           return null;
       }
    }

    public static String getReportNameWithTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String timeStamp = dateTimeFormatter.format(localDateTime);
        return "Test-Report-" + timeStamp + ".html";
    }
}
