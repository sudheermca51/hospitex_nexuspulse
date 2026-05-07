package org.iit.hc.hospitex.am.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    protected WebDriver driver;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("/admin/login") ));
        if (driver.getCurrentUrl().contains("/admin/login")) {
            throw new IllegalStateException(
                    "This is not Home Page of logged in user, current page is: "
                            + driver.getCurrentUrl()
            );
        }
    }

    public void navigateToAModulesPage(String moduleName) {
        driver.findElement(By.linkText(moduleName)).click();
    }
}
