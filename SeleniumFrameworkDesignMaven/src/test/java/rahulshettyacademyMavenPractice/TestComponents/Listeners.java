package rahulshettyacademyMavenPractice.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademyMavenPratice.Resources.ExtentReportNG;

/*public class Listeners extends BaseTest implements ITestListener
{
	ExtentReports extent = ExtentReportNG.getReportObject();
	ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		String filePath= null;
		// TODO Auto-generated method stub
		test.log(Status.FAIL, "test failed");
		test.fail(result.getThrowable());
		
		//get Driver info into life
		
	   try 
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		
		//take screenshot and attach it
		try 
		{
			filePath = getScreenShot(test.getModel().getName(), driver);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(filePath, test.getModel().getName());
	}  
	


	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}

	

}  */

public class Listeners extends BaseTest implements ITestListener{

ExtentTest test;

ExtentReports extent = ExtentReportNG.getReportObject();

ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe

@Override

public void onTestStart(ITestResult result) {

// TODO Auto-generated method stub

test = extent.createTest(result.getMethod().getMethodName());

extentTest.set(test);//unique thread id(ErrorValidationTest)->test

}



@Override

public void onTestSuccess(ITestResult result) {

// TODO Auto-generated method stub

extentTest.get().log(Status.PASS, "Test Passed");

}



@Override

public void onTestFailure(ITestResult result) {

// TODO Auto-generated method stub

extentTest.get().fail(result.getThrowable());//

try {

driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")

.get(result.getInstance());

} catch (Exception e1) {

// TODO Auto-generated catch block

e1.printStackTrace();

}

String filePath = null;

try {

filePath = getScreenShot(result.getMethod().getMethodName(),driver);

} catch (IOException e) {

// TODO Auto-generated catch block

e.printStackTrace();

}

extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

//Screenshot, Attach to report

}

@Override

public void onTestSkipped(ITestResult result) {

// TODO Auto-generated method stub

}



@Override

public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

// TODO Auto-generated method stub

}



@Override

public void onStart(ITestContext context) {

// TODO Auto-generated method stub

}



@Override

public void onFinish(ITestContext context) {

// TODO Auto-generated method stub

extent.flush();

}

}
