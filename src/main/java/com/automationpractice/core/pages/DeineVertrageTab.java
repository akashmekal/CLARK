package com.automationpractice.core.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.automationpractice.core.base.ElementActions;
import com.automationpractice.core.base.Globals;


public class DeineVertrageTab {

	private ElementActions elementActions = new ElementActions();

	@FindBy(xpath = "(//span[contains(.,'Gut versichert')])")
	private List<WebElement> ContractStatus;
	
	@FindBy(xpath = "//h1[contains(.,'Deine Verträge')]")
	private WebElement DeineVertrage;
	
	@FindBy(xpath = "//p[contains(.,'Gut versichert')]")
	private WebElement MainGut;
	


	public DeineVertrageTab(){
		PageFactory.initElements(Globals.getDriver(), this);
	}


	public void AssertContracts(String count, String status) {
		
		elementActions.isElementDisplay(MainGut, 30);
		List<WebElement> NumberOfContract = Globals.getDriver().findElements(By.xpath("//span[contains(.,'Gut versichert')]"));
		int counter = NumberOfContract.size();
		String WellInsuredContract = String.valueOf(counter);
		Assert.assertEquals(count, WellInsuredContract);
		System.out.println("The Number of contract with status" + " " +status+ " " + "is" + " " +WellInsuredContract);


	}
}