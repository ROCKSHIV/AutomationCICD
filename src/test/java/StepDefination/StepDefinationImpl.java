 package StepDefination;

import java.io.IOException;

import org.testng.Assert;

import Udemy.SeleniumFrameworkDesign.pageObject.CartPage;
import Udemy.SeleniumFrameworkDesign.pageObject.Checkout;
import Udemy.SeleniumFrameworkDesign.pageObject.ConfirmationPage;
import Udemy.SeleniumFrameworkDesign.pageObject.LandingPage;
import Udemy.SeleniumFrameworkDesign.pageObject.ProductCatalogue;
import Udemy.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	public String confirmMessage;
	
	
	@Given("I landed on Ecommerce page")   //feature line defn.
	public void i_landed_on_Ecommerce_page() throws IOException {
		
		landingPage=launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$") //^ $ these 2 are used in regex(regular expression) & (.+) is used to let know the compiler that data is comming in this  
	public void logged_in_with_username_and_password(String userName, String passWord) {
		
		productCatalogue = landingPage.loginApplication(userName,passWord);
	}
	
	@When("^I add the product  (.+) to cart$")
	public void i_add_the_product_to_cart(String productName) {
		  productCatalogue.getProductList();
		  productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName){
		cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		Checkout checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
		
	}
	
	@Then("verify the confirmation message  {string} in this step") //When we put value directly the we mention {data Type}
	public void verify_the_confirmation_message(String string) {
		
       confirmMessage = confirmationPage.getConfirmationMessage();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")  //we can also mention the String in regex format like this 
	public void error_message_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
}
