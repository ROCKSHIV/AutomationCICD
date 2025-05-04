package Udemy.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.lang.Object; //package for Object data type
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import Udemy.SeleniumFrameworkDesign.pageObject.CartPage;
import Udemy.SeleniumFrameworkDesign.pageObject.Checkout;
import Udemy.SeleniumFrameworkDesign.pageObject.ConfirmationPage;
import Udemy.SeleniumFrameworkDesign.pageObject.OrderPage;
import Udemy.SeleniumFrameworkDesign.pageObject.ProductCatalogue;
import Udemy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	//String productName ="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups={"Purchase"}) //running test using dataprovider
	//public void submitOrder(String userName,String passWord,String productName) throws IOException {
		public void submitOrder(HashMap<String,String> input) throws IOException {	//using hashmap
		
		 
		//ProductCatalogue productCatalogue = landingPage.loginApplication(userName,passWord);
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password")); //using hashmap
		
		productCatalogue.getProductList();
		
		//productCatalogue.addProductToCart(productName);
		productCatalogue.addProductToCart(input.get("product"));//using hashmap
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//Boolean match = cartPage.VerifyProductDisplay(productName);
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));//using hashmap
		
		Assert.assertTrue(match);
		
		System.out.println("Its a match");
		
		Checkout checkoutPage = cartPage.goToCheckout();
		
		checkoutPage.selectCountry("India");
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		System.out.println("Test is sucessful");
		
		
		
		
	}
	
	//@Test(dependsOnMethods = {"submitOrder"}) //this test will runs after the submit order test
	public void OrderHistoryTest() {
		String productName ="ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("knowledge9337080935@gmail.com", "1@Shivkumar");
		
		OrderPage orderPage = productCatalogue.goToMyOrders();
		
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	
	//@DataProvider  //using data provider 
	//public Object[][] getData() {
		
	//	return new Object[][] {{"knowledge9337080935@gmail.com","1@Shivkumar","ZARA COAT 3"},{"heyo@gmail.com",
	//		"1@heyo@gmail.coM","IPHONE 13 PRO"}};
		
		
	//}
	

	@DataProvider  //using hashmap data provider 
	public Object[][] getData() throws IOException {
		
		//HashMap<String,String> map = new HashMap<String,String>();
		//map.put("email", "knowledge9337080935@gmail.com");
		//map.put("password", "1@Shivkumar");
		//map.put("product", "ZARA COAT 3");
		
		//HashMap<String,String> map1 = new HashMap<String,String>();
		//map1.put("email", "heyo@gmail.com");
		//map1.put("password", "1@heyo@gmail.coM");
		//map1.put("product", "IPHONE 13 PRO");
		
		//return new Object[][] {{map},{map1}};
		
		List<HashMap<String,String>> data = getJsonDataToMap("F:\\Eclipse\\SeleniumFrameworkDesign\\src\\test\\java\\dataPackage\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		//In Java, HashMap is part of the Java Collections Framework and is found in the java.util package. It provides the basic implementation of the Map interface in Java. HashMap stores data in (key, value) pairs. Each key is associated with a value, and you can access the value by using the corresponding key.

		//Internally uses Hashing (similar to Hashtable in Java).
		//Not synchronized (unlike Hashtable in Java) and hence faster for most of the cases.
		//Allows to store the null keys as well, but there should be only one null key object, and there can be any number of null values.
		//Duplicate keys are not allowed in HashMap, if you try to insert the duplicate key, it will replace the existing value of the corresponding key. 
		//HashMap uses keys in the same way as an Array uses an index.
		//HashMap allows for efficient key-based retrieval, insertion, and removal with an average O(1) time complexity.
		
		
	}
	
	
	
	
	//https://rahulshettyacademy.com/client/
	//to run jenkins -->F:\Jenkins>java -jar jenkins.war -httpPort=8080
		//jenkins secret code ->9900077ba04541f4a0eac2a835e19228 -->adminstrator password
		//localhost:8080
		//jenkins credintials - username - ShivKumar   , password- 9337080935
}
