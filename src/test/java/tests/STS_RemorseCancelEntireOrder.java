package tests;

import utilities.ExcelUtilities;
import utilities.GenericFunctions;
import utilities.ObjectMap;
import utilities.AppGenericFunctions;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;

import org.apache.commons.io.FileUtils;

//import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class STS_RemorseCancelEntireOrder {
	
	public ObjectMap objMap;
	public GenericFunctions func;
	public AppGenericFunctions appFunc;
	ExcelUtilities objExcel;
	public WebDriver driver;
	WebDriverWait wait;
	public TakesScreenshot ts;
	String[][] testData;
	public int i,j,rowCount,colCount;
	public ITestResult result;
	ExtentReports extent;
	ExtentTest report;
	
	@BeforeClass
	public void Setup() throws IOException{
		objMap=new ObjectMap(".\\UI Map\\EOM.properties");
		objExcel=new ExcelUtilities();
		func=new GenericFunctions();
		appFunc=new AppGenericFunctions();
		testData=objExcel.readExcel(".\\TestData","TestDataFile.xlsx","STS_TestData");
		System.setProperty("webdriver.chrome.driver",objMap.getValue("chromeDriverPath"));
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--incognito");
		//DesiredCapabilities capabilities=new DesiredCapabilities();
		//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,15);
		ts=(TakesScreenshot)driver;
		
		extent=func.extentReportInvoke();
		report=extent.createTest("Ship To Store - CSR order", "STS CSR Order");
		
		rowCount=testData.length;
	colCount=testData[0].length;
	
	//System.out.println("Row Count: "+row+" ; Column Count: "+col);
	}
	
	
	@Test
public void CSR_RemorseCancel() throws Exception
	{
		    appFunc.Login_EOM(driver);
		    
		    i=appFunc.AddItemsToCartShipToStore(driver, testData, rowCount);
				
				appFunc.CheckoutAndSelectRegisteredCustomer(driver);
				appFunc.ProceedToPaymentAndPayWithGiftCard(driver);
				String orderNum=appFunc.ProceedToSummaryAndPlaceOrder(driver);
				
				report.pass(func.extentLabel("Order#: "+orderNum, ExtentColor.GREEN));
				//System.out.println("row: "+i);
				objExcel.updateExcel(".\\TestData","TestDataFile.xlsx","STS_TestData", orderNum, i-1, 8);

	}
	
	@AfterMethod(alwaysRun=true)
	public void Capture_Screenshot(ITestResult result) throws Exception 
	{
		 
		// Call method to capture screenshot
		File source=ts.getScreenshotAs(OutputType.FILE);

		 
		// Copy files to specific location here it will save all screenshot in our project home directory and
		// result.getName() will return name of test case so that screenshot name will be same
		if(result.getStatus()==1) 
		{
			FileUtils.copyFile(source, new File("./test-output/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png"));
			//test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png");
			report.addScreenCaptureFromPath("./test-output/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png");
		}
		else
		{
			FileUtils.copyFile(source, new File("./test-output/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png"));
			report.fail("Test Failed - please refer log file & screnshot for the exact error details");
			report.addScreenCaptureFromPath("./test-output/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png");
			//test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png");
		}
		//System.out.println("Screenshot has been captured for the test"+result.getName());
		//test.addScreenCaptureFromPath("../Screenshots/"+result.getName()+".png");
	}
	
	
	@AfterClass(alwaysRun=true)
	public void teardown(){
		extent.flush();
		driver.close();
		driver.quit();
		System.out.println("********************END of 'Ship To Store_CSR Test'*************************");
	}

	
}
