package Udemy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Udemy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test; //Defines a test. You can add logs, snapshots, assign author and categories to a test and its children.
	ExtentReports extent = ExtentReporterNG.getReportObject(); //extent object is created with class.methodname
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //this is called thread safe what it does it crates unique thread id to avoid concorrency
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName()); //here test is created by fetching the method name from result
		extentTest.set(test); // pusing the variable to safe thread to create unique id of the variable
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, "Test Passed");
		
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		//test.fail(result.getThrowable()); //it will print the error message
		
		extentTest.get().fail(result.getThrowable()); //it will fetch the test with the thread id like a map
		
		
		
		//take screenshot & attach it to report
		
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			//here as we have to extract driver which is inside the field that field is inside the class not method
			//hence we used testclass(testng) then real class of listeners then fields
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		 
	}
	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Extent.flush()");
		extent.flush();
	}
	
	//to run jenkins -->F:\Jenkins>java -jar jenkins.war -httpPort=8080
	//jenkins secret code ->9900077ba04541f4a0eac2a835e19228 -->adminstrator password
	//jenkins credintials - username - ShivKumar   , password- 9337080935
	
	
}
