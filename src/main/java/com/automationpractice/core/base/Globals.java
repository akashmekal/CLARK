package com.automationpractice.core.base;


import java.util.ArrayList;
import org.openqa.selenium.WebDriver;

import com.automationpractice.core.utils.Log;

public class Globals {
	
	public static int ELEMENTTIMEOUT = 7;
	public static ArrayList<String>  url  = new ArrayList<String> ();
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); 
	public static Log log = new Log(setLogger());
	public static final String TESTDATA_EXCEL_FILEPATH = "src/test/resources/rundata/TestData.xlsx";
	
	public static String setLogger() {
		System.setProperty("app.root", System.getProperty("user.dir") + "\\");
		return System.getProperty("user.dir") + "\\";
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void setDriver(WebDriver driver) {
		tlDriver.set(driver);
	}
}
