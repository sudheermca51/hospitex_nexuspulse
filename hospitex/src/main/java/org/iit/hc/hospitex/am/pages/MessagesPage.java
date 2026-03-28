package org.iit.hc.hospitex.am.pages;

import org.openqa.selenium.WebDriver;

public class MessagesPage extends ReportsPage {
	protected WebDriver driver;

	public MessagesPage(WebDriver driver) {
		super(driver);
		if (!driver.getTitle().equals("Admin - Messages")) {
			throw new IllegalStateException("This is not Messages Page of logged in user," +
					" current page is: " + driver.getCurrentUrl());
		}
	}
}
