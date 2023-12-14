package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.CartPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.CheckoutPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.GetProduct;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.LandingPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.OrderPlacedPg;

public class StandAloneTest2POM 
{
	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "C:\\ProgramData\\chromedriver-win64\\chromedriver_win32\\chromedriver.exe"); 
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String prodName= "ADIDAS ORIGINAL";
		
		
		LandingPage landingpage = new LandingPage(driver); 
		landingpage.GoTo();
		GetProduct productcatalogue=landingpage.ActionsPerformed("meghashree.banerjee05@gmail.com", "Rahulshetty@academy5");
		
		//GetProduct productcatalogue= new GetProduct(driver);
		List<WebElement> products= productcatalogue.getProductList();
		productcatalogue.addProducttoCart(prodName);
		CartPage cartpage = productcatalogue.goToCartPage();
		
		//CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.compareNamewithCartProduct(prodName);
		Assert.assertTrue(match);
		CheckoutPage checkOut= cartpage.goToCheckoutPg();
		checkOut.typeSendCountry("ind");
		checkOut.matchCountry("India");
		checkOut.selectDateCVV();
		OrderPlacedPg orderPlaced = checkOut.goToPlacedOrderPg();
		String msg=orderPlaced.compareCompletionMSG();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
