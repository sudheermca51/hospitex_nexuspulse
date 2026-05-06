package org.iit.healthcare.patientmodule;

import java.util.HashMap;

import org.iit.hc.hospitex.pm.pages.BaseClass;
import org.iit.hc.hospitex.pm.pages.NewPatientRegisterPage;
import org.iit.utils.RandomData;
import org.iit.utils.ScreenshotUtil;
import org.testng.annotations.Test;

public class NewPatientRegisterTest extends BaseClass {
	public HashMap<String, String> getTestData() {

		HashMap<String, String> data = new HashMap<>();
		RandomData random = new RandomData();

		// Fill in the registration form with valid data
		data.put("firstName", random.generateRandomString(8));
		data.put("lastName", random.generateRandomString(8));
		data.put("username", random.generateRandomString(8));
		data.put("password", random.generateRandomString(10));
		data.put("email", random.generateRandomString(5) + "@example.com");
		data.put("phone", prop.getProperty("phone"));
		data.put("gender", prop.getProperty("gender"));
		data.put("dob", prop.getProperty("dob"));
		return data;

	}

	@Test
	public void testPatientRegistration() {

		NewPatientRegisterPage registerPage = new NewPatientRegisterPage(driver);
		HashMap<String, String> testData = getTestData();

		registerPage.registerNewPatient(testData);
		ScreenshotUtil.getScreenshotPath("PatientRegistration_Success", driver);
		registerPage.submitAndHandleAlert();

	}

}
