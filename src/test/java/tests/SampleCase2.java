package tests;

import java.awt.event.KeyEvent;
import java.sql.SQLException;

import org.apache.http.util.Asserts;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.asserts.*;
import utilities.GenericFunctions;
import utilities.ObjectMap;

public class SampleCase2 {
	WebDriver driver;
	GenericFunctions func=new GenericFunctions(); //Static methods cab be called without creating an object for the class.
	ObjectMap objMap=new ObjectMap("C:\\Users\\hemar\\workspace\\SelTestNG_DD\\UI Map\\EOM.properties");
	//Screen scr=new Screen();
	//Pattern pat=new Pattern("C:\\Users\\hemar\\workspace\\SelTestNG_DD\\TestData\\Images_Sikuli\\google_textbox.PNG");
	@Test
	public void SampleCase() throws ClassNotFoundException, SQLException, FindFailed, InterruptedException
	{

	driver=func.dynamicBrowserSelection("chrome-incognito");
	//driver=new ChromeDriver();
	driver.navigate().to("http://www.yahoo.com");
	driver.manage().window().maximize();
	//Asserts.check(false, "Test Failed");
	Thread.sleep(5000);
		// TODO Auto-generated method stub
		//System.out.println("Hi...This is a sample test");
	 
	// String[][] Result=func.executeDBQuery("select * from item_cbo where ROWNUM<=5");
	 //System.out.println("Rows: "+Result.length+" ; Columns:"+Result[0].length);
		
func.clickUsingSikuli("C:\\Users\\hemar\\workspace\\SelTestNG_DD\\TestData\\Images_Sikuli\\google_textbox.PNG");

	 //Result.length
	}
}
