package org.iit.utils;

import java.io.File;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	/**
	 * 
	 * @param testCaseName
	 * @param driver
	 * @return
	 *
	 */
	public static String getScreenshotPath(String testCaseName,WebDriver driver) {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\" + testCaseName + ".png";
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try 
		{
			FileHandler.copy(screenshotFile, new File(path));

		} 
		catch (Exception e) 
		{
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}

		return path;
	}

}
