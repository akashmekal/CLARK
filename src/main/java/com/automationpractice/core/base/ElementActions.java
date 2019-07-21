package com.automationpractice.core.base;


import static com.automationpractice.core.base.Globals.log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementActions extends BaseActions{


	public void javascriptclick(WebElement element) {

		isElementDisplay(element);
		JavascriptExecutor executor = (JavascriptExecutor)Globals.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebElement element) {
		try {
			((JavascriptExecutor) Globals.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
		} catch(Exception e) {
			log.logStackTrace(e);
			System.out.println("Error while performing scroll to element action");
		}		
	}
	
	public String getText (WebElement element) {
		// declaring local variables
		String returnValue = "";
		returnValue = element.getText().trim();

		if (returnValue.equals("")) {
			returnValue = element.getAttribute("value");

			if(returnValue == null) {
				System.out.println("No text was found in this text web element "
						+ "as it lacks both text within the web element and a value attribute.");
			}
		}
		return returnValue;    
	}

	public void selectByText(WebElement element, String itemName){
		Select selectElement = new Select(element);
		selectElement.selectByVisibleText(itemName);
		System.out.println("Selected the specified "+itemName+" successfully.");
	}	


	public void selectByValue(WebElement element, String value){
		Select selectElement = new Select(element);
		selectElement.selectByValue(value);
		System.out.println("Selected according to the specified "+value+" successfully.");
	}


	public void selectByIndex(WebElement element, int index){
		Select selectElement = new Select(element);
		selectElement.selectByIndex(index);
		//System.out.println("Selected according to the specified "+index+" successfully.");
	}	
	public String getSelectedText(WebElement element){
		Select selectElement = new Select(element);
		String selectedText = selectElement.getFirstSelectedOption().getText();
		return selectedText;
	}	

	public void ElementClick(WebElement element) {    	
		if(isElementEnabled(element)) {
			element.click();
		//	System.out.println("Element clicked successfully.");
		} 	
	}
	
	public void ClickItNow(WebElement element) {    	
		
			element.click();

	}
	public void TypeElement(WebElement element, String value) {
		element.sendKeys(value);
		//System.out.println("Typed "+value+ "on the specified web element successfully");
	}

	public void ClearTypeElement(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
		
	}
}

