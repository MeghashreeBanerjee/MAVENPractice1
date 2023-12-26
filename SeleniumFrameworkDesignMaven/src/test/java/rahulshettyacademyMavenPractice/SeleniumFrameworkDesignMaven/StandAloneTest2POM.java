package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.CartPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.CheckoutPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.GetProduct;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.LandingPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.OrderPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.OrderPlacedPg;
import rahulshettyacademyMavenPractice.TestComponents.BaseTest;


public class StandAloneTest2POM extends BaseTest
{
	String prodName= "I PHONE";
	@Test(dataProvider="getData", groups={"Purchase"})

	//public void submitOrder(String uname, String pwd, String prodName) throws InterruptedException, IOException
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException
	{
		//LandingPage landingpage=launchApplication();
		//GetProduct productcatalogue=landingpage.ActionsPerformed(uname, pwd);
		GetProduct productcatalogue=landingpage.ActionsPerformed(input.get("email"), input.get("pwd"));
		
		//GetProduct productcatalogue= new GetProduct(driver);
		List<WebElement> products= productcatalogue.getProductList();
		productcatalogue.addProducttoCart(input.get("prodName"));
		CartPage cartpage = productcatalogue.goToCartPage();
		
		//CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.compareNamewithCartProduct(input.get("prodName"));
		Assert.assertTrue(match);
		CheckoutPage checkOut= cartpage.goToCheckoutPg();
		checkOut.typeSendCountry("ind");
		checkOut.matchCountry("India");
		checkOut.selectDateCVV();
		OrderPlacedPg orderPlaced = checkOut.goToPlacedOrderPg();
		String msg=orderPlaced.compareCompletionMSG();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	//Addidas Original added to order page or not
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() throws InterruptedException, IOException
	{
		GetProduct productcatalogue=landingpage.ActionsPerformed("meghashree.banerjee05@gmail.com", "Rahulshetty@academy5");
		OrderPage orderPage = productcatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(prodName));
	}
	
	/*parameterizing using JSON file to HASHMAP*/
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap("C:\\Users\\1640\\eclipse-workspace\\MyFirstJavaProject_v1\\SeleniumFrameworkDesignMaven\\src\\test\\java\\rahulshettyacademyMavenPractice\\Data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
	
	/*//PARAMETERIZING//
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"meghashree.banerjee05@gmail.com","Rahulshetty@academy5","ADIDAS ORIGINAL"},{"shetty@gmail.com","Iamking@000","ZARA COAT 3"}};
	}
	*/
	
	
	 /*//parameterizing using HASHMAP//
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> map1= new HashMap<String, String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("pwd", "Iamking@000");
		map1.put("prodName", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map2= new HashMap<String, String>();
		map2.put("email", "shetty@gmail.com");
		map2.put("pwd", "Iamking@000");
		map2.put("prodName", "ZARA COAT 3");
		
		
		return new Object[][] {{map1},{map2}};
	}  
	*/
	

