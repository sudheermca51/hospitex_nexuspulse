package org.iit.hc.hospitex.am.pages;

import org.iit.hc.hospitex.pm.pages.PatientHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    protected WebDriver driver;

    private By usernameBy = By.name("username");
    private By passwordBy = By.name("password");
    private By loginBy = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().toLowerCase().contains("login")) {
            throw new IllegalStateException(
                    "This is not Login Page. Current URL is: " + driver.getCurrentUrl()
            );
        }
    }

    // Admin login
    public HomePage loginValidUser(String username, String password) {
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(loginBy).click();
        return new HomePage(driver);
    }

    // Patient login
    public PatientHomePage loginValidPatient(String username, String password) {

        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(loginBy).click();
        return new PatientHomePage(driver);
    }
}