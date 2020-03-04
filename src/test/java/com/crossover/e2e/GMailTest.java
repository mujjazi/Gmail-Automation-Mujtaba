package com.crossover.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.crossover.businessfunctions.GMailBusinessFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.TestCase;

public class GMailTest extends TestCase {

	static ExtentTest test;
	static ExtentReports report;
	private WebDriver driver;
	private Properties properties = new Properties();
	private String webdriver = null;
	private String URL = null;
	private String username = null;
	private String password = null;
	private String mailSubject = "QA Engineer Test";
	private String mailBody = "Test Automated Email";

	GMailBusinessFunctions gmailBusinessFunctions = new GMailBusinessFunctions();

	public void setUp() throws Exception {
		
		properties.load(new FileReader(new File("test.properties")));
		webdriver = properties.getProperty("webdriverpath");
		System.setProperty("webdriver.chrome.driver", webdriver);
		ChromeOptions options = new ChromeOptions();
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
		options.addArguments("start-maximized");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		URL = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
		report = new ExtentReports(System.getProperty("user.dir")+"\\GmailAutomationResults.html",true);
		test = report.startTest("Gmail Automation Test by Mujtaba Mehdi");
	}

	public void tearDown() throws Exception {
		driver.quit();
		report.endTest(test);
		report.flush();
	}

	@Test
	public void testSendEmail() throws Exception {

		gmailBusinessFunctions.openURL(driver, URL);
		test.log(LogStatus.PASS, "Successfully navigated to the specified URL");
		
		gmailBusinessFunctions.enterEmailAddress(driver, username);
		test.log(LogStatus.PASS, "Email address entered succesfully");
		
		gmailBusinessFunctions.clickNextAfterEnteringEmail(driver);
		test.log(LogStatus.PASS, "Moved to the next field that is password");
		
		gmailBusinessFunctions.enterPassword(driver, password);
		test.log(LogStatus.PASS, "Password entered succesfully");
		
		gmailBusinessFunctions.clickNextAfterEnteringPassword(driver);
		test.log(LogStatus.PASS, "Moved to next button and clicked it");
		
		gmailBusinessFunctions.composeEmailAndSend(driver, username, mailSubject, mailBody);
		test.log(LogStatus.PASS, "Successfully sent email");
		
		gmailBusinessFunctions.clickEmail(driver,mailSubject);
		test.log(LogStatus.PASS, "Email clicked and Starred");
		
		assertTrue("Verifying Email Subject", gmailBusinessFunctions.verifyEmailSubject(driver, mailSubject));
		test.log(LogStatus.PASS, "Email Subject Verified successfully");
		
		assertTrue("Verifying Email Content", gmailBusinessFunctions.verifyEmailBody(driver, mailBody));
		test.log(LogStatus.PASS, "Email Body Verified successfully");
		
		assertTrue("Verifying Social Tag", gmailBusinessFunctions.verifySocialEmailTag(driver));
		test.log(LogStatus.PASS, "Social Tag Verified");
	}
}