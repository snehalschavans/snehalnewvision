package snehalacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTG {
	
	public static  ExtentReports getExtentReportObject()
	{
		String path=System.getProperty("user.dir")+ "//reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("MyReport");
		reporter.config().setReportName("TestReport");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		//extent.createTest(path);
		return extent;
	}


}
