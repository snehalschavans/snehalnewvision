package snehalacademy.TestComponent;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.model.Test;

import com.aventstack.extentreports.ExtentTest;

import snehalacademy.resources.ExtentReportTG;

  

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent=ExtentReportTG.getExtentReportObject();
	
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
		//System.out.println(test);
		System.out.println("test starts");
		}
	
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS,"Test is passed");
		System.out.println("test success");
	}
	public void onTestFailure(ITestResult result) 
	{
		System.out.println("Test fail");
		test.fail(result.getThrowable());
		
		String filepath=null;
		try
		{
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		   filepath=takeScreenShortOnFailure(result.getMethod().getMethodName(),driver);
		
		
		
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}
		test.addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
	}
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	
	

	
	
	

}
