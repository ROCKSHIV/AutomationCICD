package Udemy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Udemy.SeleniumFrameworkDesign.pageObject.CartPage;
import Udemy.SeleniumFrameworkDesign.pageObject.Checkout;
import Udemy.SeleniumFrameworkDesign.pageObject.ConfirmationPage;
import Udemy.SeleniumFrameworkDesign.pageObject.ProductCatalogue;
import Udemy.TestComponents.BaseTest;

public class StandAloneTest153 extends BaseTest {

	
	@Test
	public void submitOrder() throws IOException {
		// TODO Auto-generated method stub

		//WebDriverManager.chromedriver().setup(); //driver manager
		
		//WebDriver driver = new ChromeDriver();
		
		String productName ="ZARA COAT 3";
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicit wait
		
		//driver.manage().window().fullscreen();
		
		//driver.get("https://rahulshettyacademy.com/client/");
		
		//driver.manage().window().fullscreen();
		
		//driver.findElement(By.id("userEmail")).sendKeys("knowledge9337080935@gmail.com");
		
		//driver.findElement(By.id("userPassword")).sendKeys("1@Shivkumar");
		
		//driver.findElement(By.id("login")).click();
		
		//LandingPage landingPage = new LandingPage(driver);
		
		//landingPage.goTo();
		
		//landingPage.loginApplication("knowledge9337080935@gmail.com", "1@Shivkumar");
		
		// List<WebElement> products = driver.findElements(By.cssSelector("div.col-lg-4"));	
		
		//LandingPage landingPage = launchApplication();
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("knowledge9337080935@gmail.com", "1@Shivkumar");
		
		//WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText()
		//.equals(productName)).findFirst().orElse(null);

//the simplest way to iterate via the elements fetched

//the ".findFirst" will return an Optional ie. A container object which may or may not contain a non-null value.

//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		

		//here ":last-of-type" is traversing the same type of elements to the last type as 
				//here inside the card there are 2 type of buttons the above statement selects the last one that is the add to cart
				
				//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
				//explicit wait
				
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				//waiting for toast to appear
				
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
				//waiting for loading to disappear
				//instead we can write 
				
				
				//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
				
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink*='cart']")));
		
		List<WebElement> products =  productCatalogue.getProductList();
		
		productCatalogue.addProductToCart(productName);
		
	//	driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		//productCatalogue.goToCartPage();
		
		
		// List<WebElement> cart =driver.findElements(By.cssSelector(".cart h3"));
		
		//Boolean match = cart.stream().anyMatch(carts->carts.getText().equalsIgnoreCase(productName));
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		
		Assert.assertTrue(match);
		
		System.out.println("Its a match");
		
		
		
		//driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Checkout checkoutPage = cartPage.goToCheckout();
		
		
		
		
		//Actions a = new Actions(driver);
		
		//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		checkoutPage.selectCountry("India");
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//driver.findElement(By.cssSelector(".action__submit")).click();
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		//We should not to assertions in action classes the action classes are only for the actions
		
		
		System.out.println("Test is sucessful");
		
		
		//driver.close();
		
	}
	
	
	

}
