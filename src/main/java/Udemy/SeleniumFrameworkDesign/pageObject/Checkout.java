package Udemy.SeleniumFrameworkDesign.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Udemy.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class Checkout extends AbstractComponents{

	WebDriver driver;
	
	public Checkout(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	//driver.findElement(By.cssSelector("[placeholder='Select Country']"))
		@FindBy(css="[placeholder='Select Country']")
		WebElement country;
	
	//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"))
		
	//@FindBy(xpath="//button[contains(@class,'ta-item')])[2]")
	//WebElement selectCountry;
	
	@FindBy(xpath="//section/button[2]") //due to build issue i have to change it
	WebElement selectCountry;
	
	
	//driver.findElement(By.cssSelector(".action__submit"))
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
	
		//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		a.sendKeys(country, countryName).build().perform();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		waitForElementToAppear(results);
		System.out.println("waiting");
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
}
