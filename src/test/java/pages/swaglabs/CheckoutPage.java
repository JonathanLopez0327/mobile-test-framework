package pages.swaglabs;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import basepage.BasePage;
import org.openqa.selenium.NoSuchElementException;

@Log4j2
public class CheckoutPage extends BasePage {

    private final AppiumBy firstNameField = new AppiumBy.ByAccessibilityId("test-First Name");
    private final AppiumBy lastNameField = new AppiumBy.ByAccessibilityId("test-Last Name");
    private final AppiumBy postalCodeField = new AppiumBy.ByAccessibilityId("test-Zip/Postal Code");
    private final AppiumBy continueButton = new AppiumBy.ByAccessibilityId("test-CONTINUE");

    public CheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    private void fillFirstName(String firstName) {
        try {
            writeTextOn(firstNameField, firstName);
            log.info("First name filled");
        } catch (NoSuchElementException e) {
            log.error("Error filling first name", e);
        }
    }

    private void fillLastName(String lastName) {
        try {
            writeTextOn(lastNameField, lastName);
            log.info("Las name filled");
        } catch (NoSuchElementException e) {
            log.error("Error filling last name", e);
        }
    }

    private void fillPostalCode(String code) {
        try {
            writeTextOn(postalCodeField, code);
            log.info("Postal Code filled");
        } catch (NoSuchElementException e) {
            log.error("Error filling postal code", e);
        }
    }

    private void clickContinueButton() {
        try {
            clickOn(continueButton);
            log.info("continue button clicked");
        } catch (NoSuchElementException e) {
            log.error("Error clicking continue button", e);
        }
    }

    public void fillForm(String firstName, String lastName, String postalCode) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillPostalCode(postalCode);
        clickContinueButton();
    }
}
