package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven;

import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.CartPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.CheckoutPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.GetProduct;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.LandingPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.OrderPlacedPg;
import rahulshettyacademyMavenPractice.TestComponents.BaseTest;


public class ErrorValidationTest extends BaseTest
{
	@Test(groups= {"ErrorHandling"})
	public void logInPageValidation() throws InterruptedException, IOException
	{
		String prodName= "ADIDAS ORIGINAL";
		landingpage.ActionsPerformed("meghashree.banerjee@gmail.com", "Rahul@academy5");
		Assert.assertEquals("Incorrect  or password.", landingpage.getErrorMsg());
	}
	
	@Test
	public void productMatchValidationTest() throws InterruptedException, IOException
	{
		String prodName= "I PHONE";
		GetProduct productcatalogue=landingpage.ActionsPerformed("meghashree.banerjee05@gmail.com", "Rahulshetty@academy5");
		List<WebElement> products= productcatalogue.getProductList();
		productcatalogue.addProducttoCart(prodName);
		CartPage cartpage = productcatalogue.goToCartPage();
		//CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.compareNamewithCartProduct("I PHONE");
		Assert.assertFalse(match);
	}
	
	@Test(groups= {"ErrorHandling"})
	public void matchCountryTest() throws InterruptedException
	{
		String prodName= "ADIDAS ORIGINAL";
		GetProduct productcatalogue=landingpage.ActionsPerformed("anubha.banerjee@gmail.com", "Anubha@123");
		List<WebElement> products= productcatalogue.getProductList();
		productcatalogue.addProducttoCart(prodName);
		CartPage cartpage = productcatalogue.goToCartPage();
		//CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.compareNamewithCartProduct("prodName");
		
		CheckoutPage checkOut= cartpage.goToCheckoutPg();
		checkOut.typeSendCountry("ind");
		Boolean nomatch= checkOut.matchCountry("Macao");
		Assert.assertTrue(nomatch);
		
	}
	

}
