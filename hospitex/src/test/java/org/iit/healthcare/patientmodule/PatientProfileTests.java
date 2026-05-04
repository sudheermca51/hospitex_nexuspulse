package org.iit.healthcare.patientmodule;

import java.util.HashMap;
import java.util.Map;

import org.iit.hc.hospitex.am.pages.AdminPatientsPage;
import org.iit.hc.hospitex.am.pages.HomePage;
import org.iit.hc.hospitex.am.pages.LoginPage;
import org.iit.hc.hospitex.pm.pages.PatientProfilePage;
import org.iit.utils.BaseClass;
import org.iit.utils.InputDataGeneration;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatientProfileTests extends BaseClass {

    private PatientProfilePage loginAsPatient() {

        navigateToUrl(prop.getProperty("patienturl"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValidPatient(
                prop.getProperty("patientusername"),
                prop.getProperty("patientpassword")
        );

        return new PatientProfilePage(driver);
    }

    private AdminPatientsPage loginAsAdminAndNavigateToPatientsPage() {

        navigateToUrl(prop.getProperty("adminurl"));

        LoginPage loginPage = new LoginPage(driver);

        HomePage homePage = loginPage.loginValidUser(
                prop.getProperty("adminusername"),
                prop.getProperty("adminpassword")
        );

        homePage.navigateToAModulesPage("Patients");

        return new AdminPatientsPage(driver);
    }

    private void verifyProfileUpdateAlert(PatientProfilePage profilePage, String failureMessage) {

        String alertMsg = profilePage.getSuccessAlertMessage();

        Assert.assertEquals(
                alertMsg,
                "Profile updated successfully",
                failureMessage
        );
    }

    private String generateLastName() {
        InputDataGeneration data = new InputDataGeneration();
        return data.generateRandomString(1, 5, 0);
    }

    private String generateEmail() {
        InputDataGeneration data = new InputDataGeneration();
        return data.generateRandomString(1, 5, 3) + "@mail.com";
    }

    @Test
    public void validateUpdatePatientLastName() {

        PatientProfilePage profilePage = loginAsPatient();

        String randomLastName = generateLastName();

        Map<String, String> profileData = new HashMap<>();
        profileData.put(PatientProfilePage.LAST_NAME, randomLastName);

        profilePage.updateProfile(profileData);

        verifyProfileUpdateAlert(profilePage, "Last name update failed");

        AdminPatientsPage adminPatientsPage = loginAsAdminAndNavigateToPatientsPage();

        String actualLastName = adminPatientsPage.getPatientLastNameByUsername(
                prop.getProperty("patientusername")
        );

        Assert.assertEquals(
                actualLastName,
                randomLastName,
                "Updated last name is not matching in admin module"
        );

        System.out.println("Expected Last Name: " + randomLastName);
        System.out.println("Actual Last Name in Admin: " + actualLastName);
    }

    @Test
    public void validateUpdatePatientEmail() {

        PatientProfilePage profilePage = loginAsPatient();

        String dynamicEmail = generateEmail();

        Map<String, String> profileData = new HashMap<>();
        profileData.put(PatientProfilePage.EMAIL, dynamicEmail);

        profilePage.updateProfile(profileData);

        verifyProfileUpdateAlert(profilePage, "Email update failed");

        System.out.println("Updated Email: " + dynamicEmail);
    }
}