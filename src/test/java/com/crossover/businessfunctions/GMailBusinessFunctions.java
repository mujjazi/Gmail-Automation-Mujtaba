package com.crossover.businessfunctions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.crossover.common.GMailCommonActions;
import com.crossover.pageobjects.GMailPageObjects;

public class GMailBusinessFunctions {

	GMailCommonActions commonActions = new GMailCommonActions();

	public void openURL(WebDriver driver, String url) {
		commonActions.openURL(driver, url);
	}

	public void enterEmailAddress(WebDriver driver, String email) {
		commonActions.sendKeys(driver, By.id(GMailPageObjects.UserName_TextBox), email);
	}

	public void clickNextAfterEnteringEmail(WebDriver driver) {
		commonActions.click(driver, By.id(GMailPageObjects.UserName_Next));
	}

	public void enterPassword(WebDriver driver, String password) {
		commonActions.sendKeys(driver, By.name(GMailPageObjects.Password_TextBox), password);
	}

	public void clickNextAfterEnteringPassword(WebDriver driver) {
		commonActions.click(driver, By.id(GMailPageObjects.Password_Next));
	}

	public void composeEmailAndSend(WebDriver driver, String to, String subject, String body) throws InterruptedException {
		commonActions.click(driver, By.xpath(GMailPageObjects.Compose_Button));
		commonActions.sendKeys(driver, By.name(GMailPageObjects.Mail_TO), String.format("%s@gmail.com", to));
		commonActions.sendKeys(driver, By.name(GMailPageObjects.Mail_TO), Keys.ENTER);
		commonActions.sendKeys(driver, By.name(GMailPageObjects.Mail_Subject), subject);
		commonActions.sendKeys(driver,By.name(GMailPageObjects.Mail_Subject),  Keys.TAB);
		WebElement bodyele = driver.switchTo().activeElement();
		bodyele.sendKeys(body);
		commonActions.click(driver, By.xpath(GMailPageObjects.More_Options));
		commonActions.click(driver, By.xpath(GMailPageObjects.Label_Selector));
		commonActions.sendKeys(driver, By.xpath(GMailPageObjects.Enter_Label), "Social");
		commonActions.sendKeys(driver, By.xpath(GMailPageObjects.Enter_Label), Keys.ENTER);
		commonActions.checkElementIsDisplayed(driver, By.xpath(GMailPageObjects.Mail_Send));
		commonActions.click(driver, By.xpath(GMailPageObjects.Mail_Send));
		commonActions.waitForElementToLoad(driver, By.xpath(GMailPageObjects.Message_Sent));
		commonActions.click(driver, By.xpath(GMailPageObjects.Gmail_Inbox));
	}

	public void clickEmail(WebDriver driver, String subject) {
		driver.navigate().refresh();
		commonActions.click(driver, By.xpath(GMailPageObjects.Social_Label_Click));
		commonActions.elementClick(driver, GMailPageObjects.Click_Email_Box, subject);
		commonActions.click(driver, By.xpath(GMailPageObjects.Starred_Email));
	}

	public boolean verifySocialEmailTag(WebDriver driver) {
		if (commonActions.checkElementIsDisplayed(driver, By.name(GMailPageObjects.Smart_Social_Label)))
			return true;
		else
			return false;
	}

	public boolean verifyEmailSubject(WebDriver driver, String subject) {
		if (commonActions.checkElementIsDisplayed(driver,
				By.xpath(GMailPageObjects.Check_Mail_Subject.replaceAll("replacethissubject", subject))))
			return true;
		else
			return false;
	}

	public boolean verifyEmailBody(WebDriver driver, String body) {
		if (commonActions.checkElementContainsText(driver, By.xpath(GMailPageObjects.Mail_Body), body))
			return true;
		else
			return false;
	}
}