package org.iit.healthcare.hospitex;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NexusPulse_E2e_Tests {

	WebDriver driver;
	@Test(description="Validate that the completed appointment details displayed in the patient module match the details displayed in the admin module for the same patient.")
	public void validate_appointmentdetails_Pat_Admin_Modules()
	{
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
	    driver = new ChromeDriver(options);
	    
	    Custom_Methods_NP customMethods = new Custom_Methods_NP(driver);
	    //patient login and get completed appointment details
	    customMethods.login("http://82.197.92.72:8080/patient/login","IITWorkforce", "welcome");
	    List<String> expectedList = customMethods.getCompleteAppointmentDetails_Pat_Module();
	    //admin login and get completed appointment details for doctor
	    customMethods.login("http://82.197.92.72:8080/admin/login","admin1", "admin123");
	    List<String> actualList = customMethods.getCompletedAppointmentDetails_Admin_Module("PatientLName PatientFName");
	    Assert.assertEquals(expectedList, actualList, "Completed appointment details do not match between patient and admin modules.");
	    
	}
}
