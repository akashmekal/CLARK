package com.automationpractice.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automationpractice.core.base.ElementActions;
import com.automationpractice.core.base.Globals;
import com.automationpractice.core.utils.ConfigFileReader;

public class BedarfTab {
	
	
	private ElementActions elementActions = new ElementActions();

	@FindBy(xpath = "//h1[contains(.,'Du hast alle empfohlenen Produkte!')]")
	private WebElement DuHastAlleEmpfohlenenProdukte;
	
	@FindBy(xpath = "(//a[contains(.,'Bedarf')])[1]")
	private WebElement Bedarf;
	
	@FindBy(xpath = "//a[contains(.,'Clark jetzt empfehlen')]")
	private WebElement ClarkJetzEmpfehlen;
	
	@FindBy(xpath = "//input[@type='email']")
	private WebElement InviteViaEmailValue;
	
	@FindBy(xpath = "//button[@id='sendInvitationEmail']")
	private WebElement EmailInviteButton;
	
	@FindBy(xpath = "//*[@id=\"numberOneRecModal\"]/div/i")
	private WebElement InfoPopUpClose;
	
	String leftcolumn = "//*[@id=\"optimisationsSlider\"]/ul/li[1]";
	String middlecolumn = "//*[@id=\"optimisationsSlider\"]/ul/li[2]";
	String rightcolumn = "//*[@id=\"optimisationsSlider\"]/ul/li[3]";
	String childxpath = "//h1[contains(.,'Du hast alle empfohlenen Produkte!')]";
	
	
	public BedarfTab(){
		PageFactory.initElements(Globals.getDriver(), this);
	}

	
	public void AssertRecommedProduct() {
		elementActions.ElementClick(Bedarf);
		elementActions.isElementDisplay(DuHastAlleEmpfohlenenProdukte, 20);
		System.out.println("Du hast alle empfohlenen Produkte! is been displayed");
		
		if (elementActions.isElementDisplay(InfoPopUpClose))
			elementActions.ElementClick(InfoPopUpClose);
		else {
			System.out.println("NO Pop up was displayed");
		}
		
		if (elementActions.isElementDisplay(By.xpath(rightcolumn+childxpath), 30))
			System.out.println("Du hast alle empfohlenen Produkte! is on left desktop side which is right side to the user");
			else if(elementActions.isElementDisplay(By.xpath(leftcolumn+childxpath), 30)) {
						System.out.println("Du hast alle empfohlenen Produkte! is not on left desktop side but on right desktop side");}
		else
		{
			System.out.println("Du hast alle empfohlenen Produkte! is not on the right side of desktop");
		}
		
	}
	
	public void InviteAFriendViaEmail(String Email) {
		elementActions.javascriptclick(ClarkJetzEmpfehlen);
		elementActions.javascriptclick(InviteViaEmailValue);
		elementActions.ClearTypeElement(InviteViaEmailValue, Email);
		elementActions.ElementClick(EmailInviteButton);
		System.out.println("Invitation has been on " + Email);
	}
}
