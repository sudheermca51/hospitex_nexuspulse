package org.iit.hc.hospitex.pm.pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientProfilePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";

    public PatientProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By profileMenu = By.xpath("//a[@href='/patient/profile']");
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By phone = By.name("phone");
    private By email = By.name("email");
    private By saveButton = By.xpath("//button[text()='Save']");

    private void enterText(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    public void openProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(profileMenu)).click();
    }

    public void updateFirstName(String value) {
        enterText(firstName, value);
    }

    public void updateLastName(String value) {
        enterText(lastName, value);
    }

    public void updatePhone(String value) {
        enterText(phone, value);
    }

    public void updateEmail(String value) {
        enterText(email, value);
    }

    public void save() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void updateProfile(Map<String, String> profileData) {
        openProfile();

        if (profileData.containsKey(FIRST_NAME)) {
            updateFirstName(profileData.get(FIRST_NAME));
        }

        if (profileData.containsKey(LAST_NAME)) {
            updateLastName(profileData.get(LAST_NAME));
        }

        if (profileData.containsKey(PHONE)) {
            updatePhone(profileData.get(PHONE));
        }

        if (profileData.containsKey(EMAIL)) {
            updateEmail(profileData.get(EMAIL));
        }

        save();
    }

    public String getSuccessAlertMessage() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String message = alert.getText();
        alert.accept();
        return message;
    }
}