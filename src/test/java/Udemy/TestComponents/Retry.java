package Udemy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) { //it will run until the method returns false 
		//after every test failures the controll will come inside this block to check 
		// the need for the rerun in selenium testng listiners
		
	if(count<maxTry) {
		count++;
		return true;
		
	}
	
		
		return false;
	}

}
