package Udemy.SeleniumFrameworkDesign.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Udemy.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

/**
 * Hello world!
 */
public class ProductCatalogue extends AbstractComponents {
    
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this); //this will pass the driver to the page factory annotations 
		
				}
	
	//List<WebElement> products = driver.findElements(By.cssSelector("div.col-lg-4"));	
	
	
	@FindBy(css="div.col-lg-4") 
	List<WebElement> products;
	
	//driver.findElement(By.cssSelector(".ng-animating"));
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	
	By productBy = By.cssSelector(".ng-animating");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText()
				.equals(productName)).findFirst().orElse(null);
		
		return prod;
		
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissapear(spinner);
	}
	
	
	
}
