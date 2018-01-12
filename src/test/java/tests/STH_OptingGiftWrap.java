
package tests;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.AppGenericFunctions;
import utilities.ExcelUtilities;
import utilities.GenericFunctions;
import utilities.ObjectMap;


	public class STH_OptingGiftWrap {
		
				
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
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				wait=new WebDriverWait(driver,15);
				ts=(TakesScreenshot)driver;
				
				extent=func.extentReportInvoke();
				report=extent.createTest("Ship From Store - CSR order", "SFS CSR Order");
				
				rowCount=testData.length;
			colCount=testData[0].length;
		
		//System.out.println("Row Count: "+row+" ; Column Count: "+col);
		}
		
		
		@Test
	public void CSR_Order() throws Exception
		{
			
			appFunc.Login_EOM(driver);
		    
			i=appFunc.AddItemsToCartShipToStore(driver, testData, rowCount);
			
			appFunc.CheckoutAndSelectRegisteredCustomer(driver);

wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(objMap.getLocator("addServices"))));
		//	driver.findElement(objMap.getLocator("yesButton")).click();
//Thread.sleep(3000);
			driver.findElement(objMap.getLocator("addServices")).click();
			List<WebElement> giftBox = driver.findElements(objMap.getLocator("giftBox"));
			if(giftBox!=null&&!giftBox.isEmpty()){
			giftBox.get(5).click();
			
			}
			/*appFunc.ProceedToPaymentAndPayWithVisa(driver);
			String orderNum=appFunc.ProceedToSummaryAndPlaceOrder(driver);
			
			report.pass(func.extentLabel("Order#: "+orderNum, ExtentColor.GREEN));
			//System.out.println("row: "+i);
			objExcel.updateExcel(".\\TestData","TestDataFile.xlsx","SFS_TestData", orderNum, i-1, 8);*/
			
			/*	List<WebElement> giftWrapYes = driver.findElements(objMap.getLocator("yesButton"));
				if(giftWrapYes!=null&&!giftWrapYes.isEmpty()){
					giftWrapYes.get(1).click();
				}
				Thread.sleep(5000);
				List<WebElement> giftWrapAdd = driver.findElements(objMap.getLocator("addServices"));
				if(giftWrapAdd!=null&&!giftWrapAdd.isEmpty()){
					giftWrapAdd.get(1).click();
				}
				
				
			
				
				*/
		
		}
				
		}
