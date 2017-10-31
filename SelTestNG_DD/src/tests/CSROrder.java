package tests;

import utilities.ExcelUtilities;
import utilities.GenericFunctions;
import utilities.ObjectMap;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.commons.io.FileUtils;

//import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class CSROrder {
	
	public ObjectMap objMap;
	public GenericFunctions func;
	ExcelUtilities objExcel;
	WebDriver driver;
	WebDriverWait wait;
	TakesScreenshot ts;
	String[][] testData;
	public int i,j,row,col;
	
	@BeforeClass
	public void Setup() throws IOException{
		objMap=new ObjectMap("C:\\Users\\hemar\\workspace\\SelTestNG_DD\\UI Map\\EOM.properties");
		objExcel=new ExcelUtilities();
		func=new GenericFunctions();
		testData=objExcel.readExcel("C:\\Users\\hemar\\workspace\\SelTestNG_DD\\TestData","TestDataFile.xlsx","TestData_Sheet");
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
				func.TakeScreenShot("Enter_User_Name",ts);
				driver.findElement(objMap.getLocator("passWord")).clear();
				driver.findElement(objMap.getLocator("passWord")).sendKeys(objMap.getValue("passWordValue"));
				func.TakeScreenShot("Enter_Password",ts);
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
				for(int i=1;i<row;i++)
				{
					if(i==1)
					{
					driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();						
					driver.findElement(objMap.getLocator("itemTileSearchByItem")).clear();
					driver.findElement(objMap.getLocator("itemTileSearchByItem")).sendKeys(testData[i][1]);
					driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();
					}
					else
					{
						wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));
						Thread.sleep(5000);
						driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();						
						driver.findElement(objMap.getLocator("itemSearchByKeyword")).clear();
						driver.findElement(objMap.getLocator("itemSearchByKeyword")).sendKeys(testData[i][1]);
						driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();	
					}
				Robot rb=new Robot();
	
				rb.keyPress(KeyEvent.VK_ENTER);
				rb.keyRelease(KeyEvent.VK_ENTER);
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
            
            driver.findElement(By.xpath("//label[text()='"+testData[i][4]+"']/following::span[contains(@id,'olm-button')][text()='PICK UP']")).click();
            Thread.sleep(3000);
         //   func.moveToElement(objMap.getLocator("addItemToCart"));
            driver.findElement(objMap.getLocator("addItemToCart")).click();

				if (!((i+1)==row))
				{
				if (testData[i][0]==testData[i+1][0])
				{
					System.out.println("Adding remaining items to the cart");
					continue addLine;
				}

				}
				
				}//for loop close
	}


}
