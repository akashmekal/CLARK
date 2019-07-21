package com.automationpractice.core.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automationpractice.core.base.ElementActions;
import com.automationpractice.core.base.Globals;

public class HomePage {

	private ElementActions elementActions = new ElementActions();

	@FindBy(xpath = "(//a[contains(.,'Clark')])[1]")
	private WebElement ClarkLogo;


	public HomePage(){
		PageFactory.initElements(Globals.getDriver(), this);
	}


	public void HomePageTitleAssertion() {
		Assert.assertEquals("CLARK - Der Versicherungsmanager", Globals.getDriver().getTitle());
		if (elementActions.isElementDisplay(ClarkLogo, 15))
			System.out.println("You are on Home Page of CLARK - The insurance manager");
		else{
			System.out.println("You are yet to arrive on Home Page of CLARK - The insurance manager");
		}

	}
}
