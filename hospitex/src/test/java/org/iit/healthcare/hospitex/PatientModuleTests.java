package org.iit.healthcare.hospitex;

import java.util.HashMap;

import org.iit.utils.FutureDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatientModuleTests {
	
	@Test(description = "US-PAT-02_Book/Schedule Appointment_Test case for booking an appointment")
	public void testBookAppointment() {
		
		HashMap<String, String> expectedHMap = new HashMap<>();
		
		HashMap<String, String> actualHMap = new HashMap<>();
		
		WebDriver driver = new ChromeDriver();
		
		// 1. Open Application
		driver.get("http://82.197.92.72:8080/patient/login");
		
		driver.manage().window().maximize();

		// 2. Enter Credentials & Login
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("patient1");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Mmp@2025!Patient#93");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//3. Click on Schedule Appointment
		driver.findElement(By.xpath("//span[text()='Schedule Appointment']")).click();
		
		// 4. Select Doctor
		String expectedDoctor = "Dr. Arjun Heart";
		expectedHMap.put("Doctor", expectedDoctor);
		driver.findElement(By.xpath("//div[@class='doctor-card']/div[1][text()='"+expectedDoctor+"']")).click();
		
		//5. Select Date and Time
		driver.findElement(By.xpath("//input[@placeholder='Select date' and @readonly='readonly']")).click();
		
		String targetDate= FutureDate.getFutureDate(30,"MMMM/d/yyyy"); // Get date 7 days from today
		String targetMonth=targetDate.split("/")[0]; // Extract month
		String targetDay= targetDate.split("/")[1]; // Extract day
		String targetYear = targetDate.split("/")[2]; // Extract year
		String exepectedDate = FutureDate.getFutureDate(30,"yyyy-MM-dd");
		
		// Navigate to the correct month and year
		Select monthSelect = new Select(driver.findElement(By.className("flatpickr-monthDropdown-months")));
		String displayedMonth= monthSelect.getFirstSelectedOption().getText();
		
		String displayedYear = driver.findElement(By.className("numInputWrapper")).findElement(By.tagName("input")).getAttribute("value"); 
		
		System.out.println("Displayed Month: " + displayedMonth + " Displayed Year: " + displayedYear);
		System.out.println("Target Date: " + targetDate + " Month: " + targetMonth + " Day: " + targetDay + " Year: " + targetYear);
		
		while(true)
		{
			if((targetMonth.equals(displayedMonth) && targetYear.equals(displayedYear)))
			{

				break;
			}
			//click on next
			driver.findElement(By.className("flatpickr-next-month")).click();
			displayedMonth= monthSelect.getFirstSelectedOption().getText();
			displayedYear = driver.findElement(By.className("numInputWrapper")).findElement(By.tagName("input")).getAttribute("value");
		}
		
		driver.findElement(By.xpath("//span[@class='flatpickr-day' and text()='"+targetDay+"']")).click();
		
		//6. Enter Reason for Visit
		String expectedReason = "Regular Checkup";
		expectedHMap.put("Reason", expectedReason);
		driver.findElement(By.name("reason")).sendKeys(expectedReason);
		
		//6. Click on Book Appointment
		driver.findElement(By.xpath("//button[text()='Create']")).click();
		
	 	
		String actualReason = driver.findElement(By.xpath("//td[text()='"+exepectedDate+"']/following-sibling::td[2]")).getText();
		actualHMap.put("Reason", actualReason);
		
		String actualDoctor = driver.findElement(By.xpath("//td[text()='"+exepectedDate+"']/following-sibling::td[3]")).getText();
		actualHMap.put("Doctor", actualDoctor);
		
		System.out.println("Date:: " + exepectedDate + " Doctor: " + actualDoctor + " Reason: " + actualReason);
		
	 	Assert.assertEquals(actualReason, expectedReason, "Reason for visit does not match");
	 	
	 	Assert.assertEquals(actualDoctor, expectedDoctor, "Doctor name does not match");
	 	
	 	Assert.assertEquals(actualHMap, expectedHMap, "Actual and Expected details do not match");
	}

}
