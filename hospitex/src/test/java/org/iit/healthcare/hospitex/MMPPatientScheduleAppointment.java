package org.iit.healthcare.hospitex;

import java.time.Duration;
import java.util.List;

import org.iit.utils.FutureDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPPatientScheduleAppointment {

	public static void main(String[] args) {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");

		WebDriver driver = new ChromeDriver(options);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {

			// STEP 1: Open Login Page
			driver.get("http://82.197.92.72:8080/patient/login");

			// STEP 2: Login
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("IITWorkforce");

			driver.findElement(By.name("password")).sendKeys("welcome");

			driver.findElement(By.xpath("//button[@type='submit']")).click();

			// STEP 3: Click Schedule Appointment
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//span[normalize-space()='Schedule Appointment']"))).click();

			// STEP 4: Wait for doctor cards and click first doctor
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".doctor-card")));

			List<WebElement> doctors = driver.findElements(By.cssSelector(".doctor-card"));

			doctors.get(0).click();

			System.out.println("Doctor selected"+doctors.get(0).getText());

			// Click visible date field
			WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select date' and @readonly]")));

			dateField.click();

			System.out.println("Datepicker opened successfully");
			String result = FutureDate.getFutureDate(30,"MMMM/d/yyyy");
			System.out.println(result);
			String targetMonth = result.split("/")[0];
			String targetDay = result.split("/")[1];
			String targetYear = result.split("/")[2];
			System.out.println("Month: " + targetMonth);
			System.out.println("Day: " + targetDay);
			System.out.println("Year: " + targetYear);
			
			while (true) 
			{

			    WebElement monthDropdown = driver.findElement(By.className("flatpickr-monthDropdown-months"));
			    Select selectMonth = new Select(monthDropdown);
			    String currentMonth = selectMonth.getFirstSelectedOption().getText();
			    String currentYear =driver.findElement(By.className("cur-year")).getAttribute("value");
			    System.out.println(currentMonth + " " + currentYear);
			    if (currentMonth.equals(targetMonth) && currentYear.equals(targetYear)) 
			    {

			        break;
			    }
			    driver.findElement(By.className("flatpickr-next-month")).click();
			}
			 

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='flatpickr-day' and text()='" + targetDay + "']"))).click();
			System.out.println("Date selected successfully");
			driver.findElement(By.name("reason")).sendKeys("Regular Checkup");
			
			driver.findElement(By.className("btn-primary")).click();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// driver.quit();
	}
}
