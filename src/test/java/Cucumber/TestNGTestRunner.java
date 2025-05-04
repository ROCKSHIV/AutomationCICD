package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="StepDefination",monochrome=true,
tags = "@ErrorValidation", plugin= {"html:target/cucumber.html",}) //here we used tags for selectedly run test case if we not use it will run all the feature file inside of the given directory "src/test/java/Cucumber"  
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
