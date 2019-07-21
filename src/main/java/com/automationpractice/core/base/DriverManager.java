package com.automationpractice.core.base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverManager {

	public void createDriver(String browserName) {		
		switch (browserName.toUpperCase())	{
		case "CHROME":
			System.out.println("Browser CHROME");
			System.setProperty("webdriver.chrome.driver","C://AkashMekal//MainFrame//automationpractice//src//test//resources//drivers//chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			try {
				Globals.setDriver(new ChromeDriver(capabilities));	  
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("Browser accessed");
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "C://AkashMekal//MainFrame//automationpractice//src//test//resources//drivers//geckodriver.exe");
			DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
			FirefoxProfile fp = new FirefoxProfile();
			fp.setAcceptUntrustedCertificates(true);
			fp.setAssumeUntrustedCertificateIssuer(true);
			fp.setEnableNativeEvents(false);
			firefoxCapabilities.setCapability(FirefoxDriver.PROFILE, fp);
			firefoxCapabilities.setCapability("marionette", true);
			Globals.setDriver(new FirefoxDriver(firefoxCapabilities));
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", "src/test/drivers/IEDriver.exe");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability("nativeEvents", false);
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setCapability("disable-popup-blocking", true);
			Globals.setDriver(new InternetExplorerDriver(ieCapabilities));
			break;
		default:
			throw new IllegalArgumentException("Specified browser is not supported - " + browserName);			                
		}
		this.maximizeWindow();
	}

	public void quitDriver() {
		if (null != Globals.getDriver()) {
			Globals.getDriver().quit();
		}
	}

	public void maximizeWindow() {
		try {
			Globals.getDriver().manage().window().maximize();
		} catch(Exception e) {
			System.out.println("Error while maximizing driver/browser window");
		}
	}

}
