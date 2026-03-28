package org.iit.healthcare.hospitex;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Custom_Methods_NP {

	WebDriver driver;
	public Custom_Methods_NP(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String url,String username, String password)
	{

		// STEP 1: Open Login Page
		driver.get(url);

		driver.findElement(By.name("username")).sendKeys(username);

		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	public List<String> getCompletedAppointmentDetails_Admin_Module(String patientFLName) {
		driver.findElement(By.linkText("Reports")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Upload Report']")).click();
		Select patientSelect = new Select(driver.findElement(By.name("patientId")));
		patientSelect.selectByVisibleText(patientFLName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("appointmentId")));
		Select appointDetails = new Select(driver.findElement(By.name("appointmentId")));
		List<WebElement> appDetailsList = appointDetails.getOptions();
		List<String> appDetailsTextList = new ArrayList<String>();
		//skip the first option as it is default and not an actual appointment detail
		for(int i=1;i<appDetailsList.size();i++) {
			System.out.println("appDetailsList: "+ appDetailsList.get(i).getText());
			appDetailsTextList.add(appDetailsList.get(i).getText());
		}
		Collections.sort(appDetailsTextList);
		return appDetailsTextList;
	}

	public List<String> getCompleteAppointmentDetails_Pat_Module() {

		List<WebElement> appointmentStatus = driver.findElements(By.xpath("//table[@class='table-styled']//td[5]"));
		List<String> completedAppointments = new ArrayList<String>();
		for(int i=0;i<appointmentStatus.size();i++) {
			String statusText = appointmentStatus.get(i).getText();
			if(statusText.equals("COMPLETED")) {

				String date = driver.findElement(By.xpath("//table[@class='table-styled']/tbody/tr["+(i+1)+"]/td[1]")).getText();
				String time = driver.findElement(By.xpath("//table[@class='table-styled']/tbody/tr["+(i+1)+"]/td[2]")).getText();
				String doctor = driver.findElement(By.xpath("//table[@class='table-styled']/tbody/tr["+(i+1)+"]/td[4]")).getText();
				completedAppointments.add(date +"T"+ time +" - "+ doctor);
			}
		}
		System.out.println("completedAppointments: "+ completedAppointments);
		Collections.sort(completedAppointments);
		return completedAppointments;
	}
}
