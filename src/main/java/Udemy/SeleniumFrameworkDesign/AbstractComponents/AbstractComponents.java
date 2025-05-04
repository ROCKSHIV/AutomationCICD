package Udemy.SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Udemy.SeleniumFrameworkDesign.pageObject.CartPage;
import Udemy.SeleniumFrameworkDesign.pageObject.OrderPage;

public class AbstractComponents {
	
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this); //giving access to the pagefactory
		
	}

	public void waitForElementToAppear(By findBy){
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		//explicit wait
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement findBy){
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		//explicit wait
		
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	
	
	public void waitForElementToDissapear(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	
	//driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartHeader;
	
	public CartPage goToCartPage() {
		
		cartHeader.click();
		return new CartPage(driver);
	}
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;
	
	public OrderPage goToMyOrders() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	

}
