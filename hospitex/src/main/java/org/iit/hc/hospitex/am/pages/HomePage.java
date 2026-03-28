package org.iit.hc.hospitex.am.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	protected WebDriver driver;
	public HomePage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("Admin Portal - Nexus Pulse")) {
			throw new IllegalStateException("This is not Home Page of logged in user," +
					" current page is: " + driver.getCurrentUrl());
		}
	}
	public  void navigateToAModulesPage(String moduleName) {


		driver.findElement(By.linkText(moduleName)).click();



	}

}
