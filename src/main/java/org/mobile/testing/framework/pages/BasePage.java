package org.mobile.testing.framework.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class BasePage {
    protected AppiumDriver driver;
    private WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public void findElement(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(d -> driver.findElement(locator).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(d -> driver.findElement(locator).isEnabled());
        wait.until(ExpectedConditions.elementToBeClickable(locator));
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

    public void isElementPresent(AppiumBy element, int timeout) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(element));
        } catch (NoSuchElementException e) {
            log.error("Error verifying if element present: {}", element);
        }
    }
}
