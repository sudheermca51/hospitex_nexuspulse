package org.iit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {

	protected WebDriver driver;
	protected Properties prop;
	private String browserType,environment;
	protected ExtentReports extent;
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Executing Before Suite method. This will run once before all tests in the suite.");
		
		extent = new ExtentReports();
		String reportPath = System.getProperty("user.dir")+"/src/test/resources/reports/ExtentReport.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		extent.attachReporter(spark);

	}

	@BeforeTest
	public void setup() throws IOException {
		//reading a property file and setting the system property for the driver
		String filePath = System.getProperty("user.dir")+"/src/test/resources/config/nexuspulse.properties";
		System.out.println("Absolute Path:" + filePath);
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);

		prop = new Properties();
		prop.load(fis);
		System.out.println("Loaded properties from config.properties file successfully.");
	}

	@BeforeClass
	public void intiateDriver() throws FileNotFoundException, IOException {
		browserType=prop.getProperty("browserType");
		environment =prop.getProperty("environment");

		if(environment.equalsIgnoreCase("qa")) {
			prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/config/nexuspulse_qa.properties")));
		}
		else if(environment.equalsIgnoreCase("staging")) {
			prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/config/nexuspulse_staging.properties")));
		}
		switch(browserType.toLowerCase()) {

		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-save-password-bubble");
			// Disable Chrome password manager popup
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);

			// Disable password leak detection
			prefs.put("profile.password_manager_leak_detection", false);

			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);
			break;
		case "edge":
			// Code to initialize Edge driver would go here
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver= new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid browser type specified in config.properties file. Please check the value and try again.");
			break;
		}
		driver.manage().deleteAllCookies();
	}
	public void navigateToUrl(String url) {
		driver.get(url);
	}
	@AfterClass	
	 public void closeDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
	@AfterSuite
	public void tearDown() {
		
		if (extent != null) {
			extent.flush();
		}
	}
}
