package org.iit.hc.hospitex.pm.pages;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	protected WebDriver driver;
	protected Properties prop;

	@BeforeMethod
	public void setup() throws Exception {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// load properties file
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/resources/config/nexuspulse_newpatient.properties");
		prop.load(fis);
		driver.get(prop.getProperty("patienturl"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
