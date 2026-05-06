package org.iit.hc.hospitex.pm.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewPatientRegisterPage {
	protected WebDriver driver;
	WebDriverWait wait;

	public NewPatientRegisterPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
	}

	By RegisterBy = By.xpath("//a[text()='New user? Register']");
	By FirstNameBy = By.name("firstName");
	By LastNameBy = By.name("lastName");
	By UserNameBy = By.name("username");
	By PasswordBy = By.name("password");
	By Email = By.name("email");
	By Phone = By.name("phone");
	By Gender = By.xpath("//select[@name='gender']");
	By DOBBy = By.xpath("//input[@name='dob']");
	By RegisterButtonBy = By.xpath("//button[@type='submit']");

	public void clickRegisterLink() {
		wait.until(ExpectedConditions.elementToBeClickable(RegisterBy)).click();
	}

	public void enterFirstName(String firstName) {
		driver.findElement(FirstNameBy).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		driver.findElement(LastNameBy).sendKeys(lastName);
	}

	public void enterUserName(String userName) {
		driver.findElement(UserNameBy).sendKeys(userName);
	}

	public void enterPassword(String password) {
		driver.findElement(PasswordBy).sendKeys(password);
	}

	public void enterEmail(String email) {
		driver.findElement(Email).sendKeys(email);
	}

	public void enterPhone(String phone) {
		driver.findElement(Phone).sendKeys(phone);
	}

	public void selectGender(String gender) {
		Select select = new Select(driver.findElement(Gender));
		select.selectByVisibleText(gender);
	}

	public void enterDOB(String dob) {
		driver.findElement(DOBBy).sendKeys(dob);
	}

	public String submitAndHandleAlert() {
		driver.findElement(RegisterButtonBy).click();
		wait.until(ExpectedConditions.alertIsPresent());
		String alertText = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return alertText;
	}

	public void registerNewPatient(HashMap<String, String> patientData) {
		clickRegisterLink();
		enterFirstName(patientData.get("firstName"));
		enterLastName(patientData.get("lastName"));
		enterUserName(patientData.get("username"));
		enterPassword(patientData.get("password"));
		enterEmail(patientData.get("email"));
		enterPhone(patientData.get("phone"));
		selectGender(patientData.get("gender"));
		enterDOB(patientData.get("dob"));
		
	}

}
