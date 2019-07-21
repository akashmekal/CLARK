package com.automationpractice.core.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automationpractice.core.base.ElementActions;
import com.automationpractice.core.base.Globals;

public class RenteTab {

	private ElementActions elementActions = new ElementActions();

	@FindBy(xpath = "(//dd[contains(@class,'1aoxqn')])[1]")
	private WebElement RetirmentIncomeValue;
	
	@FindBy(xpath = "(//a[contains(.,'Rente')])[1]")
	private WebElement Rente;
	
	@FindBy(xpath = "//h1[contains(.,'Deine Rentenprodukte')]")
	private WebElement DeineRentenprodukte;
	
	public RenteTab(){
		PageFactory.initElements(Globals.getDriver(), this);
	}


	public void RetirementIncomeAssertion(String amount) {
		elementActions.ElementClick(Rente);
		elementActions.isElementDisplay(DeineRentenprodukte, 30);
		elementActions.scrollToElement(RetirmentIncomeValue);
		String retirementValue = elementActions.getText(RetirmentIncomeValue);
		String[] AmountArray = retirementValue.split("€");;
		String  TestData = AmountArray[0].replace(".","");
		String actualvalue = TestData.replace(",",".");
		double actual =Double.parseDouble(actualvalue);
		double expected = Double.parseDouble(amount);
		
		if (actual < expected) {
			System.out.println("The Dein Renteneinkommen amount is" + " " + actualvalue + "which is less than" + " " + amount);
		}
		else {
			System.out.println("The Dein Renteneinkommen amount is" + " " + actualvalue + "which is greater than" + " " + amount);

		}

	}


}
