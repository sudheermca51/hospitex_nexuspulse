package org.iit.healthcare.patientmodule;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class PayFeesTests {

	WebDriver driver;

	 

	@Test
	public void testPayFees() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
	    driver = new ChromeDriver(options);
	
	   

	}
	 

}
