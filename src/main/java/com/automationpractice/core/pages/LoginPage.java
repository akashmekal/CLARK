package com.automationpractice.core.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.core.base.ElementActions;
import com.automationpractice.core.base.Globals;
import com.automationpractice.core.utils.ConfigFileReader;

public class LoginPage {

	private ElementActions elementActions = new ElementActions();

	@FindBy(xpath = "//a[contains(.,'Login')]")
	private WebElement LoginButton;

	@FindBy(xpath = "//input[contains(@type,'email')]")
	private WebElement email;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement LoginSubmit;


	public LoginPage(){
		PageFactory.initElements(Globals.getDriver(), this);
	}

	public void UserLogsIn() {
		elementActions.ElementClick(LoginButton);
		elementActions.ClearTypeElement(email, new ConfigFileReader().getProperty("email"));
		elementActions.ClearTypeElement(password, new ConfigFileReader().getProperty("password"));
		elementActions.ElementClick(LoginSubmit);

	}



}
