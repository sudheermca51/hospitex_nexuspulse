package org.iit.hc.hospitex.pm.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientHomePage {

    private WebDriver driver;

    public PatientHomePage(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("/patient/home"));
        if (!driver.getCurrentUrl().contains("/patient/home")) {
            throw new IllegalStateException(
                "This is not Patient Home Page. Current URL: " + driver.getCurrentUrl()
            );
        }
    }

    public void navigateToProfile() {
        driver.findElement(By.linkText("Profile")).click();
    }
}