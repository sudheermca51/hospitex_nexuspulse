package org.iit.hc.hospitex.am.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportsPage {
	
	protected WebDriver driver;
	double initialFeesAmount;
	public ReportsPage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("Admin - Reports")) {
			throw new IllegalStateException("This is not Reports Page of logged in user," +
					" current page is: " + driver.getCurrentUrl());
		}
	}
	public Double fetchFeesDetails() {
		String totalFeesValue = driver.findElement(By.xpath("//li[contains(text(),'Total Amount')]/strong")).getText();
		System.out.println("Total Fees Value: " + totalFeesValue);
		return Double.parseDouble(totalFeesValue.trim());
	}
	public void setFeesAmount()
	{
		initialFeesAmount = Double.parseDouble(driver.findElement(By.id("feeAmount")).getDomProperty("value").trim());
		
	}
	public double getFeesAmount()
	{
		return  initialFeesAmount;
	}
	public void uploadFile() {
		driver.findElement(By.xpath("//a[normalize-space()='Upload Report']")).click();
		Select patientSelect = new Select(driver.findElement(By.name("patientId")));
		patientSelect.selectByVisibleText("PatientLName PatientFName");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("appointmentId")));
		Select appointDetails = new Select(driver.findElement(By.name("appointmentId")));
		appointDetails.selectByIndex(1);

		Select reportTypeList =new Select(driver.findElement(By.xpath("//select[@name='reportType']")));
		reportTypeList.selectByVisibleText("Blood Report");

		setFeesAmount();

		WebElement fileInput = driver.findElement(By.name("file"));
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\bloodreport.jpg";
		fileInput.sendKeys(filePath);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Report uploaded successfully");
	}

}
