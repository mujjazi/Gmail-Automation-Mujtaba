package com.crossover.pageobjects;

public class GMailPageObjects {

	public static String UserName_TextBox = "identifierId";
	public static String UserName_Next = "identifierNext";
	public static String Password_TextBox = "password";
	public static String Password_Next = "passwordNext";
	public static String Compose_Button = "//div[text()='Compose']";
	public static String Mail_TO = "to";
	public static String Mail_Subject = "subjectbox";
	public static String Message_Sent = "//*[contains(text(),'Message sent')]";
	public static String Mail_Body = "//div[text()='Test Automated Email']";
	public static String More_Options = "//div[@data-tooltip='More options']";
	public static String Label_Selector = "//div[text()='Label']";
	public static String Enter_Label = "//input[@ignoreesc='true']";
	public static String Social_Label_Selector = "//div[@title='Social']";
	public static String Social_Label_Selected = "//div[@aria-checked='true' and @title='Social']";
	public static String Social_Label_Click = "//div[text()='Social']";
	public static String Mail_Send = "//div[text()='Send']";
	public static String Gmail_Inbox = "//a[text()='Inbox']";
	public static String Mail_Refresh = "//div[@class='asa']";
	public static String Search = "q";
	public static String Starred_Email = "//div[@aria-checked='false' and @title='Not starred']";
	public static String Click_Email = "//*[contains(text(),'Me')]";
	public static String Click_Email_Box = "//*[@class='yW']/span";
	public static String Smart_Social_Label = "^smartlabel_social";
	public static String Check_Mail_Subject = "//h2[text()='QA Engineer Test']";
}
