package com.automationpractice.core.base;


import static com.automationpractice.core.base.Globals.log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.automationpractice.core.base.Globals.ELEMENTTIMEOUT;

public class BaseActions {

	public boolean isElementDisplay(By locator, int explicitWaitSeconds) {
		boolean isDisplay = false;
			WebDriverWait wait = new WebDriverWait(Globals.getDriver(),explicitWaitSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			isDisplay = true;
		return isDisplay;
	}

	public boolean isElementDisplay(WebElement element, int explicitWaitSeconds) {
		boolean isDisplay = false;
			WebDriverWait wait = new WebDriverWait(Globals.getDriver(),explicitWaitSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			isDisplay = true;
			  if (Globals.getDriver() instanceof JavascriptExecutor) {
			        ((JavascriptExecutor)Globals.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
			    }
			
		return isDisplay;
	}

	public boolean isElementEnabled(WebElement element, int explicitWaitSeconds) {
		boolean isEnabled = false;
			WebDriverWait wait = new WebDriverWait(Globals.getDriver(), explicitWaitSeconds);
			isElementDisplay(element, explicitWaitSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			isEnabled = true;

		return isEnabled;
	}
	
	public WebElement isElementEnabled(By locator, int explicitWaitSeconds) {
		WebElement element = null;
		try {
		boolean isEnabled = false;
			WebDriverWait wait = new WebDriverWait(Globals.getDriver(), explicitWaitSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			isEnabled = true;

		
		}catch(TimeoutException e) {
			
		}
		return element;
	}


	public boolean isElementDisappear(By locator, int explicitWaitSeconds) {
		boolean isDisappear = false;
			WebDriverWait wait= new WebDriverWait(Globals.getDriver(),explicitWaitSeconds) ;
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			isDisappear = true;
		return isDisappear;
	}


	public boolean isExpectedTextDisplay(WebElement element, String expectedText, int explicitWaitSeconds){
		boolean isExpectedTextDisplay = false;
			WebDriverWait wait = new WebDriverWait(Globals.getDriver(), explicitWaitSeconds);
			if(isElementDisplay(element)){
				wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText.trim()));
			//	Log.info("Expected Text: "+expectedText+" found successfully");
				isExpectedTextDisplay = true;
			}
		return isExpectedTextDisplay;
	}
	

	public boolean isElementDisplay(WebElement element) {
		boolean isDisplay = false;
		try {
		isDisplay = isElementDisplay(element, ELEMENTTIMEOUT);		
		}catch(Exception e) {
			log.logStackTrace(e);
		}
		return isDisplay;
	}
	
	public boolean isElementEnabled(WebElement element) {
		boolean isEnabled = false;
		try {
		isEnabled = isElementEnabled(element, ELEMENTTIMEOUT);
		}catch(Exception e) {
			log.logStackTrace(e);
		}
		return isEnabled;
	}

	public boolean isElementDisappear(By locator) {
		boolean isDisappear = false;
		isDisappear = isElementDisappear(locator,ELEMENTTIMEOUT);
		return isDisappear;
	}


	public boolean isExpectedTextDisplay(WebElement element,String expectedText){
		boolean isExpectedTextDisplay = false;
		isExpectedTextDisplay = isExpectedTextDisplay(element,expectedText,ELEMENTTIMEOUT);
		return isExpectedTextDisplay;
	}
	
}