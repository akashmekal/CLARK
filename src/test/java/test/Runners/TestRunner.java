package test.Runners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automationpractice.core.base.DriverManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import static com.automationpractice.core.base.Globals.log;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions
(
		features = {"src/test/resources/features/"},
		glue  = {"test.Runners","test.stepDefination"},
		tags = {"@SmokeTest"}
		)
public class TestRunner
{

	public static WebDriver driver;
	private TestNGCucumberRunner testNGCucumberRunner;
	private DriverManager driverManager;
	private String resources = "\\src\\test\\resources\\";
	private String screenshotDirectory = System.getProperty("user.dir") + resources+"screenshots";
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@BeforeClass
	public void setUP() throws IOException
	{
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		extent = new ExtentReports (System.getProperty("user.dir")  + "\\target\\ExtentReport.html", true);
		extent.loadConfig(new File("\\extent-config.xml"));
		log.info("Started Test Suite Execution");
		File screenshot = new File(System.getProperty("user.dir") + resources + "screenshots\\ScreenShot.png");
		if(screenshot.exists() && screenshot.isDirectory())
			FileUtils.deleteDirectory(screenshot);
		else {
			new File(screenshotDirectory).mkdirs();
		}
		driverManager = new DriverManager();
	}

	@BeforeMethod
	private void setTestPrerequisite() {
		driverManager.createDriver("chrome");
	}


	@Test(description="login",dataProvider="features")
	public void login(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper)
	{
		System.out.println("Started Test Execution");
		String scenarioName = pickleWrapper.toString();
		extentTest.set(extent.startTest(scenarioName));
		
		try {
			testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
	@DataProvider(name="features")
	private Object[][] scenarios()
	{
		Object[][] test =  testNGCucumberRunner.provideScenarios();
		//System.out.println(test.length);
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterMethod
	private void testClosure(ITestResult result) {
		Date d = new Date();
		System.out.println(d.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			extentTest.get().log( LogStatus.PASS , "Test Execution Passed" + extentTest.get().addScreenCapture(System.getProperty("user.dir") + resources+"screenshots\\"+sdf.format(d)+".png"));
			break;
		case ITestResult.FAILURE:
			extentTest.get().log( LogStatus.FAIL , "Test Execution Failed " + extentTest.get().addScreenCapture(System.getProperty("user.dir") +resources+"screenshots\\"+sdf.format(d)+".png"));
			break;
		case ITestResult.SKIP:
			extentTest.get().log(LogStatus.SKIP, "Test Execution Failed " + extentTest.get().addScreenCapture(System.getProperty("user.dir") + resources+"screenshots\\"+sdf.format(d)+".png"));
			break;
		}
		extent.endTest(extentTest.get());
	}
	@AfterClass
	public void tearDown()
	{
		extent.flush();
		extent.close();
		testNGCucumberRunner.finish();
	}



}