package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
 
public class ExtentReportManager {
	
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = "C:\\Users\\hemar\\Jenkins_Workspace\\Project Workspace\\StoreOpsOrders\\Reports\\extentreport.html";
	
	
	public static ExtentReports GetExtent(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporter());
		return extent;
	}
 
	private static ExtentHtmlReporter getHtmlReporter() {
	
        htmlReporter = new ExtentHtmlReporter(filePath);
		
	    // make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.setAppendExisting(true);		
        htmlReporter.config().setDocumentTitle("QA - Automation report");
        htmlReporter.config().setReportName("ATG/Web Order Creation - Dev2");
        return htmlReporter;
	}
	
	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}
}