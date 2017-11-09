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

import org.apache.commons.io.FileUtils;

//import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class STS_CSROrder {
	
	public ObjectMap objMap;
	public GenericFunctions func;
	ExcelUtilities objExcel;
	public WebDriver driver;
	WebDriverWait wait;
	public TakesScreenshot ts;
	String[][] testData;
	public int i,j,row,col;
	public ITestResult result;
	
	@BeforeClass
	public void Setup() throws IOException{
		objMap=new ObjectMap("C:\\Users\\hemar\\Jenkins_Workspace\\Project Workspace\\git\\SelTestNG_DD\\UI Map\\EOM.properties");
		objExcel=new ExcelUtilities();
		func=new GenericFunctions();
		testData=objExcel.readExcel("C:\\Users\\hemar\\Jenkins_Workspace\\Project Workspace\\git\\SelTestNG_DD\\TestData","TestDataFile.xlsx","STS_TestData");
		System.setProperty("webdriver.chrome.driver",objMap.getValue("chromeDriverPath"));
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--incognito");
		//DesiredCapabilities capabilities=new DesiredCapabilities();
		//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,15);
		ts=(TakesScreenshot)driver;
		
	row=testData.length;
	col=testData[0].length;
	
	System.out.println("Row Count: "+row+" ; Column Count: "+col);
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
//	
//				rb.keyPress(KeyEvent.VK_ENTER);
//				rb.keyRelease(KeyEvent.VK_ENTER);
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("shipToStoreRadioButton")));
					Thread.sleep(3000);			
                driver.findElement(objMap.getLocator("shipToStoreRadioButton")).click();
                
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("selectLinkShipToStore")));
				Thread.sleep(3000);			
            driver.findElement(objMap.getLocator("selectLinkShipToStore")).click();    
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("zipCodeSearchField")));
            driver.findElement(objMap.getLocator("zipCodeSearchField")).click();
            driver.findElement(objMap.getLocator("zipCodeSearchField")).sendKeys(testData[i][5]);
            Thread.sleep(3000);
            driver.findElement(objMap.getLocator("goLocationSearch")).click();
            Thread.sleep(7000);
            
            driver.findElement(By.xpath("//label[text()='"+testData[i][4]+"']/following::span[contains(@id,'olm-button')][text()='SHIP TO']")).click();
            Thread.sleep(3000);
         //   func.moveToElement(objMap.getLocator("addItemToCart"));
            driver.findElement(objMap.getLocator("addItemToCart")).click();
            wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));

				if (!((i+1)==row))
				{
				if (testData[i][0]==testData[i+1][0])
				{
					System.out.println("Adding remaining items to the cart");
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
				driver.findElement(objMap.getLocator("customerSearch_Registered")).sendKeys("u5926660026p");
				
				driver.findElement(objMap.getLocator("customerSearch_Registered")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//label[text()='IDENTIFY REGISTERED CUSTOMER']//following::input[contains(@id,'olm-customersearchcombo')][@placeholder='name, email, phone']/following::div[1]")).click();
				Actions builder=new Actions(driver);
				Action seriesofActions=builder.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build();
				seriesofActions.perform();
				
//				Thread.sleep(3000);
//				Robot robot=new Robot();
//				robot.keyPress(KeyEvent.VK_ENTER);
//				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				driver.findElement(objMap.getLocator("doneSelectCustomer_Registered")).click();
				Thread.sleep(5000);
				driver.findElement(objMap.getLocator("proceedToPayment")).click();
				Thread.sleep(4000);
				driver.findElement(objMap.getLocator("addGiftCardLabel")).click();
				driver.findElement(objMap.getLocator("giftCardNum")).click();
				driver.findElement(objMap.getLocator("giftCardNum")).sendKeys("6006496103999906765");
				driver.findElement(objMap.getLocator("giftCardPin")).click();
				driver.findElement(objMap.getLocator("giftCardPin")).sendKeys("8145");	
				driver.findElement(objMap.getLocator("giftCardAdd")).click();
				Thread.sleep(3000);
				driver.findElement(objMap.getLocator("proceedToSummary")).click();
				Thread.sleep(3000);
				driver.findElement(objMap.getLocator("placeOrder")).click();
				Thread.sleep(7000);
				func.TakeScreenShot(Thread.currentThread().getName()+"_Screenshot_"+func.getCurrentDateTime(), ts);
				String orderNumRaw=driver.findElement(objMap.getLocator("orderConfirmationMessage")).getText();
				String[] orderNumarr=orderNumRaw.split(Pattern.quote("("));
				//System.out.println("Value:"+orderNumarr[1].substring(0, 8));
				String orderNum=orderNumarr[1].substring(0, 8);
				System.out.println("STS Order#: "+orderNum+" has been created");
				objExcel.updateExcel("C:\\Users\\hemar\\Jenkins_Workspace\\Project Workspace\\git\\SelTestNG_DD\\TestData","TestDataFile.xlsx","STS_TestData", orderNum, i, 8);
				driver.findElement(objMap.getLocator("orderConfirmSaveButton")).click();
				Thread.sleep(2000);
				driver.findElement(objMap.getLocator("xClose")).click();
				Thread.sleep(5000);
	}
	
	@AfterMethod
	public void Capture_Screenshot(ITestResult result) throws Exception 
	{
		 
		// Call method to capture screenshot
		File source=ts.getScreenshotAs(OutputType.FILE);

		 
		// Copy files to specific location here it will save all screenshot in our project home directory and
		// result.getName() will return name of test case so that screenshot name will be same
		if(result.getStatus()==1) 
		{
			FileUtils.copyFile(source, new File("./Execution Reports/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png"));
			//test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png");
		}
		else
		{
			FileUtils.copyFile(source, new File("./Execution Reports/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png"));
			//test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png");
		}
		System.out.println("Screenshot has been captured for the test"+result.getName());
		//test.addScreenCaptureFromPath("../Screenshots/"+result.getName()+".png");
	}
	
	
	@AfterClass
	public void teardown(){
		driver.close();
		driver.quit();
	}

	
}
