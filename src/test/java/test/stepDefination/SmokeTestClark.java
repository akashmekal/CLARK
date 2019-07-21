package test.stepDefination;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.automationpractice.core.base.Globals;
import com.automationpractice.core.pages.BedarfTab;
import com.automationpractice.core.pages.DeineVertrageTab;
import com.automationpractice.core.pages.HomePage;
import com.automationpractice.core.pages.LoginPage;
import com.automationpractice.core.pages.RenteTab;
import com.automationpractice.core.utils.ConfigFileReader;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SmokeTestClark {


	@Given("I am on Clark - The Insurance Manager Home Page")
	public void i_am_on_Clark_The_Insurance_Manager_Home_Page() {
		Globals.getDriver().get(new ConfigFileReader().getProperty("testurl"));
		new HomePage().HomePageTitleAssertion();
	}

	@Then("I Log In into the Web Application")
	public void i_Log_In_into_the_Web_Application() {
		new LoginPage().UserLogsIn();
	}

	@Then("In Vertrage tab I assert that there are {string} contracts with {string}")
	public void in_Vertr_ge_tab_I_assert_that_there_are_contracts_with_Gut_versichert(String string,String string2) {
	   new DeineVertrageTab().AssertContracts(string, string2);
	}

	@Then("In the tab Rente I assert that Dein Renteneinkommen ist above {string} Euro")
	public void in_the_tab_Rente_I_assert_that_Dein_Renteneinkommen_ist_above_Euro(String string) {
	    new RenteTab().RetirementIncomeAssertion(string);
	}

	@Then("In the tab Bedarf I assert Du hast alle empfohlenen Produkte is displayed in the left column")
	public void in_the_tab_Bedarf_I_assert_Du_hast_alle_empfohlenen_Produkte_is_displayed_in_the_left_column() {
	    new BedarfTab().AssertRecommedProduct();
	}
	
	@Then("I click on Clark jetzt empfehlen and invite a friend  {string} and i assert the the message")
	public void i_click_on_Clark_jetzt_empfehlen_and_invite_a_friend_and_i_assert_the_the_message(String string) {
	   new BedarfTab().InviteAFriendViaEmail(string);
	}

	@And("I Take a Screenshot")
	public void i_Take_a_Screenshot() throws IOException {
		File src= ((TakesScreenshot)Globals.getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\ScreenShot.png"));
	}
	
	

}