package Udemy.SeleniumFrameworkDesign;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Udemy.TestComponents.BaseTest;
import Udemy.TestComponents.Retry;

public class ErrorValidations extends BaseTest{
	
	@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class) //adding group to run specific tests //retryAnalyzer will rerun the method if it fails this is the way to inform the compiler
	public void loginErrorValidations() throws IOException {
		
		String productName ="ZARA COAT 3";
		
		landingPage.loginApplication("knowledge9337080935@gmail.com", "1@Shivkuma"); //inputing wrong password
		
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		

		
	}

}
