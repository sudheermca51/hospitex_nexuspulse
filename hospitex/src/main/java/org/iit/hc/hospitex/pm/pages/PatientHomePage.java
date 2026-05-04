package org.iit.hc.hospitex.pm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientHomePage {

    private WebDriver driver;

    public PatientHomePage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().equals("Patient Login - MMP")) {
            throw new IllegalStateException(
                "This is not Patient Home Page. Current URL: " + driver.getCurrentUrl()
            );
        }
    }

    public void navigateToProfile() {
        driver.findElement(By.linkText("Profile")).click();
    }
}
