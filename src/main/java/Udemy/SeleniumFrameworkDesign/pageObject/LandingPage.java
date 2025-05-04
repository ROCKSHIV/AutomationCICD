package Udemy.SeleniumFrameworkDesign.pageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Udemy.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;


public class LandingPage extends AbstractComponents{
    
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this); //this will pass the driver to the page factory annotations 
		
				}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory ->Page Factory in Selenium is a design pattern that simplifies the creation and management of Page Objects
	
	@FindBy(id="userEmail") // the meaning of the line--> driver.findElement(By.id("userEmail"));
	WebElement userEmail;   //WebElement userEmail
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement passwordEle;
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submitButton;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApplication(String email,String password) {
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submitButton.click();
		
		return new ProductCatalogue(driver);
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
}
