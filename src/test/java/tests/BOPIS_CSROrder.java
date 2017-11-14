package tests;

import utilities.ExcelUtilities;
import utilities.GenericFunctions;
import utilities.ObjectMap;

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
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.apache.commons.io.FileUtils;

//import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class BOPIS_CSROrder {
	
	public ObjectMap objMap;
	public GenericFunctions func;
	ExcelUtilities objExcel;
	public 
	WebDriver driver;
	WebDriverWait wait;
	public TakesScreenshot ts;
	String[][] testData;
	public int i,j,row,col;
	public ITestResult result;
	ExtentReports extent;
	ExtentTest report;
	
	
	@BeforeClass
	public void Setup() throws IOException{
		objMap=new ObjectMap(".\\UI Map\\EOM.properties");
		objExcel=new ExcelUtilities();
		func=new GenericFunctions();
		testData=objExcel.readExcel(".\\TestData","TestDataFile.xlsx","BOPIS_TestData");
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
		report=extent.createTest("Buy Online Pick Up in Store - CSR order", "BOPIS CSR Order");

		//System.out.println("Thread Name: "+Thread.currentThread().getName()+"Class Name: "+driver.getClass().getName());
		
		
		
	row=testData.length;
	col=testData[0].length;
	
	//System.out.println("Row Count: "+row+" ; Column Count: "+col);
	}
	
	
	@Test
public void CSR_Order() throws Exception
	{
		

		    driver.navigate().to(objMap.getValue("baseUrl"));
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.manage().window().maximize();
				driver.findElement(objMap.getLocator("userName")).clear();
				driver.findElement(objMap.getLocator("userName")).sendKeys(objMap.getValue("userNameValue"));
				//func.TakeScreenShot("Enter_User_Name",ts);
				driver.findElement(objMap.getLocator("passWord")).clear();
				driver.findElement(objMap.getLocator("passWord")).sendKeys(objMap.getValue("passWordValue"));
				//func.TakeScreenShot("Enter_Password",ts);
				Thread.sleep(3000);
				driver.findElement(objMap.getLocator("loginButton")).click();
				Thread.sleep(10000);
								
			  /*  driver.switchTo().activeElement().click();
				driver.findElement(objMap.getLocator("selectStore173")).click();
				TakeScreenShot("SelectStore",ts);
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("okButtonSelectStore")));
                driver.findElement(objMap.getLocator("okButtonSelectStore")).click();
                */
				//TakeScreenShot("EOM_HomePage",ts);

				Actions act=new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]"))).build().perform();
				//act.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]//*[contains(@id,'triggerfield')] [contains(@placeholder,'item description')]"))).click().perform();
				
			    report.info("Able to login successfully");
			    Thread.sleep(3000);
				
				addLine:
				for(i=1;i<row;i++)
				{
					if(i==1)
					{
					driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();						
					driver.findElement(objMap.getLocator("itemTileSearchByItem")).clear();
					driver.findElement(objMap.getLocator("itemTileSearchByItem")).sendKeys(testData[i][1]);
					driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();
					driver.findElement(objMap.getLocator("itemTileSearchByItemSearchIcon")).click();
					}
					else
					{
						wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));
						Thread.sleep(5000);
						driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();						
						driver.findElement(objMap.getLocator("itemSearchByKeyword")).clear();
						driver.findElement(objMap.getLocator("itemSearchByKeyword")).sendKeys(testData[i][1]);
						driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();	
						driver.findElement(objMap.getLocator("itemSearchByKeywordSearchIcon")).click();
					}
//				Robot rb=new Robot();
//				rb.keyPress(KeyEvent.VK_ENTER);
//				rb.keyRelease(KeyEvent.VK_ENTER);
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("pickUpAtStoreRadioButton")));
					Thread.sleep(3000);			
                driver.findElement(objMap.getLocator("pickUpAtStoreRadioButton")).click();
                
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("selectLinkPickUpAtStore")));
				Thread.sleep(3000);			
            driver.findElement(objMap.getLocator("selectLinkPickUpAtStore")).click();    
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("zipCodeSearchField")));
            driver.findElement(objMap.getLocator("zipCodeSearchField")).click();
            driver.findElement(objMap.getLocator("zipCodeSearchField")).sendKeys(testData[i][5]);
            Thread.sleep(3000);
            driver.findElement(objMap.getLocator("goLocationSearch")).click();
            Thread.sleep(7000);
            if (driver.findElement(By.xpath("//label[text()='"+testData[i][4]+"']/following::span[contains(@id,'olm-button')][text()='PICK UP']")).isEnabled())
            {
            driver.findElement(By.xpath("//label[text()='"+testData[i][4]+"']/following::span[contains(@id,'olm-button')][text()='PICK UP']")).click();
            }
            else
            {
            	System.out.println("Pick UP button is disabled for the item: "+testData[i][1]);
            	report.warning("Pick Up option is unavailable for the item: "+testData[i][1]+"; Store#: "+testData[i][4]);
            	continue addLine;
            }
            Thread.sleep(3000);
         //   func.moveToElement(objMap.getLocator("addItemToCart"));
            driver.findElement(objMap.getLocator("addItemToCart")).click();
            //Clean the below code later - Mark Up
            report.pass(func.extentLabel(testData[i][1]+" has been successfully added to the cart", ExtentColor.GREY));
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));

				if (!((i+1)==row))
				{
				if (testData[i][0]==testData[i+1][0])
				{
					System.out.println("Adding remaining items to the cart");
					report.info("Adding remaining items to the cart");
					Thread.sleep(5000);
					continue addLine;
				}
				}
				else
				{
					Thread.sleep(5000);
				}
				}//for loop close
				
				driver.findElement(objMap.getLocator("checkout")).click();
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("customerSearch_Registered")));
				driver.findElement(objMap.getLocator("customerSearch_Registered")).click();
				driver.findElement(objMap.getLocator("customerSearch_Registered")).sendKeys(objMap.getValue("eomRegisteredCustomer"));
				driver.findElement(objMap.getLocator("customerSearch_Registered")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//label[text()='IDENTIFY REGISTERED CUSTOMER']//following::input[contains(@id,'olm-customersearchcombo')][@placeholder='name, email, phone']/following::div[1]")).click();
				Actions builder=new Actions(driver);
				Action seriesofActions=builder.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build();
				seriesofActions.perform();
				//				Robot robot=new Robot();
//				robot.delay(2000);
//				robot.keyPress(KeyEvent.VK_ENTER);
//				robot.keyRelease(KeyEvent.VK_ENTER);
				//driver.findElement(By.xpath("//label[text()='IDENTIFY REGISTERED CUSTOMER']//following::input[contains(@id,'olm-customersearchcombo')][@placeholder='name, email, phone']/following::div[1]")).click();
//				Thread.sleep(2000);
//				robot.delay(2000);
//				robot.keyPress(KeyEvent.VK_ENTER);
//				robot.keyRelease(KeyEvent.VK_ENTER);
			    //func.clickUsingSikuli("C:\\Users\\hemar\\Jenkins_Workspace\\Project Workspace\\git\\SelTestNG_DD\\TestData\\Images_Sikuli\\RegisteredCustomer_u5926660026p.PNG");
				Thread.sleep(5000);
				driver.findElement(objMap.getLocator("doneSelectCustomer_Registered")).click();
				report.info("Customer ID: "+objMap.getValue("eomRegisteredCustomer")+" has been selected");		
				Thread.sleep(5000);
				driver.findElement(objMap.getLocator("proceedToPayment")).click();
				Thread.sleep(4000);
				driver.findElement(objMap.getLocator("addGiftCardLabel")).click();
				driver.findElement(objMap.getLocator("giftCardNum")).click();
				driver.findElement(objMap.getLocator("giftCardNum")).sendKeys(objMap.getValue("giftCardNumber"));
				driver.findElement(objMap.getLocator("giftCardPin")).click();
				driver.findElement(objMap.getLocator("giftCardPin")).sendKeys(objMap.getValue("gcPin"));	
				driver.findElement(objMap.getLocator("giftCardAdd")).click();
				report.info("Payment has been added successfully");
				Thread.sleep(3000);
				driver.findElement(objMap.getLocator("proceedToSummary")).click();
				Thread.sleep(3000);
				driver.findElement(objMap.getLocator("placeOrder")).click();
				Thread.sleep(7000);
				//func.TakeScreenShot(this.getClass().getSimpleName()+"_Screenshot_"+func.getCurrentDateTime(), ts);
				String orderNumRaw=driver.findElement(objMap.getLocator("orderConfirmationMessage")).getText();
				String[] orderNumarr=orderNumRaw.split(Pattern.quote("("));
				//System.out.println("Value:"+orderNumarr[1].substring(0, 8));
				String orderNum=orderNumarr[1].substring(0, 8);
				System.out.println("BOPIS Order#: "+orderNum+" has been created");
				driver.findElement(objMap.getLocator("orderConfirmSaveButton")).click();
				Thread.sleep(2000);
				driver.findElement(objMap.getLocator("xCloseCustInfoSAVE")).click();
				Thread.sleep(5000);
				if(func.isElementPresent(objMap.getLocator("xCloseConcurrencyException")))
				{
					driver.findElement(objMap.getLocator("xCloseConcurrencyException")).click();
				}
				report.pass(func.extentLabel("Order#: "+orderNum, ExtentColor.GREEN));
				//String data[2][2];
				//data[0][0]="Serial#";data[0][1]="Order#";data[1][0]="1";data[1][1]=orderNum;
				//report.pass(func.extentTable(data));
				//System.out.println("row: "+i);
				objExcel.updateExcel(".\\TestData","TestDataFile.xlsx","BOPIS_TestData", orderNum, i-1, 8);

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
		System.out.println("Screenshot has been captured for the test"+result.getName());
		//test.addScreenCaptureFromPath("../Screenshots/"+result.getName()+".png");
	}
//	
	@AfterClass(alwaysRun=true)
	public void teardown() throws Exception{
		//func.Capture_Screenshot(result, ts);
		extent.flush();
		driver.close();
		driver.quit();
	}


}

