package org.iit.hc.hospitex.am.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	protected WebDriver driver;

	// <input name="user_name" type="text" value="">
	private By usernameBy =By.name("username");
	// <input name="password" type="password" value="">
	private By passwordBy = By.name("password");
	// <input name="sign_in" type="submit" value="SignIn">
	private By loginBy = By.xpath("//button[@type='submit']");

	public LoginPage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("Admin Login - MMP")) {
			throw new IllegalStateException("This is not LoginPage," +
					" current page is: " + driver.getCurrentUrl());
		}
	}

	/**
	 * Login as valid user
	 *
	 * @param userName
	 * @param password
	 * @return HomePage object
	 */


	public HomePage loginValidUser(String username, String password)
	{

	 
		driver.findElement(usernameBy ).sendKeys(username);

		driver.findElement(passwordBy ).sendKeys(password);

		driver.findElement(loginBy ).click();

		return new HomePage(driver);

	}

}
