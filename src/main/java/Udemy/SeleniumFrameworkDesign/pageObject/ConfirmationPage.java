package Udemy.SeleniumFrameworkDesign.pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Udemy.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public String getConfirmationMessage() {
		
		//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		return confirmMessage.getText();
	}
	
}
