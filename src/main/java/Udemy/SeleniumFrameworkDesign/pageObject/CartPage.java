package Udemy.SeleniumFrameworkDesign.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Udemy.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	 //List<WebElement> cart =driver.findElements(By.cssSelector(".cart h3"));
	
	@FindBy(css=".cart h3")
	private List<WebElement> productTitles;
	
	
	
	//driver.findElement(By.xpath("//button[text()='Checkout']")).click();
	@FindBy(xpath="//button[text()='Checkout']")
	public WebElement checkoutEle;
	

	
	
	public Boolean VerifyProductDisplay(String productName) {
		
		//Boolean match = cart.stream().anyMatch(carts->carts.getText().equalsIgnoreCase(productName));
		
		Boolean match = productTitles.stream().anyMatch(carts->carts.getText().equalsIgnoreCase(productName));
		
		return match;
	}
	
	
	public Checkout goToCheckout() {
		checkoutEle.click();
		return new Checkout(driver);
	}
	

}
