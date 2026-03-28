package org.iit.hc.hospitex.pm.pages;

import org.openqa.selenium.WebDriver;

public class RegistrationPage {
	
	protected WebDriver driver;
	public RegistrationPage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("Admin Portal - Nexus Pulse")) {
			throw new IllegalStateException("This is not Home Page of logged in user," +
					" current page is: " + driver.getCurrentUrl());
		}
	}

}
