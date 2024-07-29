package basepage;

import basetest.BaseTest;
import extentreport.SuiteListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public WebElement findElement(AppiumBy locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(d -> driver.findElement(locator).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(d -> driver.findElement(locator).isEnabled());
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }
    
    public void writeTextOn(AppiumBy element, String text) {
        try {
            findElement(element);
            wait.until(
                    d -> {
                        driver.findElement(element).click();
                        driver.findElement(element).clear();
                        driver.findElement(element).sendKeys(text);
                        return true;
                    });
        } catch (NoSuchElementException e) {
            log.error("Error writing. Element not found: {}", element);
        }
    }

    public void clickOn(AppiumBy element) {
        try {
            findElement(element);
            wait.until(
                    d -> {
                        driver.findElement(element).click();
                        return true;
                    });
        } catch (NoSuchElementException e) {
            log.error("Error clicking. Element not found: {}", element);
        }
    }

    public boolean isElementPresent(AppiumBy element, int timeout) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(element));
        } catch (NoSuchElementException e) {
            log.error("Error verifying if element present: {}", element);
            return false;
        }
        return true;
    }

    public synchronized void createStep(String description, boolean decision, boolean isScreenshot, boolean documentation) {
        if (documentation) {
            BaseTest.createStep(description, decision, isScreenshot);
            log.info("Step created: {}", description);
        }
    }
}
