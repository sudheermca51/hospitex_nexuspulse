package org.iit.hc.hospitex.am.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPatientsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By patientsTab = By.xpath("//span[text()='Patients']");
    private By tableRows = By.xpath("//table//tbody/tr");
    private By nextButton = By.xpath("//a[contains(.,'Next')]");
    
    public AdminPatientsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openPatientsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(patientsTab)).click();
    }

    public String getPatientLastNameByUsername(String username) {

        while (true) {

            wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

            List<WebElement> rows = driver.findElements(tableRows);

            for (WebElement row : rows) {

                String rowText = row.getText();

                if (rowText.contains(username)) {

                    String fullName = row.findElement(By.xpath("./td[2]")).getText();
                    System.out.println("Full Name: " + fullName);

                    String[] nameParts = fullName.trim().split("\\s+");

                    return nameParts[nameParts.length - 1];
                }
            }

            List<WebElement> nextButtons = driver.findElements(nextButton);

            if (nextButtons.size() == 0 || !nextButtons.get(0).isEnabled()) {
                break;
            }

            nextButtons.get(0).click();
        }

        return null;
    }
}
