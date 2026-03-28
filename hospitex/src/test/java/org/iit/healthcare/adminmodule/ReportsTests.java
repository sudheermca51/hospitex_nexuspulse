package org.iit.healthcare.adminmodule;

import org.iit.hc.hospitex.am.pages.HomePage;
import org.iit.hc.hospitex.am.pages.LoginPage;
import org.iit.hc.hospitex.am.pages.ReportsPage;
import org.iit.utils.BaseClass;
import org.iit.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class ReportsTests extends BaseClass {

	private ExtentTest test;
	@Test
	public void testGenerateReports() {
		try {
			test = extent.createTest("testGenerateReports", "Test to verify report generation and fee updates in admin module.");
			navigateToUrl(prop.getProperty("adminurl"));
			test.info("Navigated to Admin URL: " + prop.getProperty("adminurl"));
			LoginPage lPage = new LoginPage(driver);
			HomePage hPage = lPage.loginValidUser(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
			hPage.navigateToAModulesPage("Reports");

			test.info("Navigated to Reports page.");

			String screenshotPath = ScreenshotUtil.getScreenshotPath("ReportsPage", driver);

			test.addScreenCaptureFromPath(screenshotPath, "Reports Page Screenshot");


			ReportsPage reportPage = new ReportsPage(driver);
			double FeeBefore =reportPage.fetchFeesDetails();
			reportPage.uploadFile();
			test.info("Uploaded report file successfully.");
			double feeAfter = reportPage.fetchFeesDetails();
			test.info("Fetched fees details after uploading report."+feeAfter);
			Assert.assertEquals(feeAfter, FeeBefore + reportPage.getFeesAmount(), "Fees did not update correctly after uploading report.");

			test.info("Verified that fees updated correctly after uploading report.");
			test.pass("Test passed: Report generation and fee updates verified successfully.");
		}
		catch(Exception e) 
		{
			 
			test.fail("Test failed due to exception: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
			
		}

	}
}
