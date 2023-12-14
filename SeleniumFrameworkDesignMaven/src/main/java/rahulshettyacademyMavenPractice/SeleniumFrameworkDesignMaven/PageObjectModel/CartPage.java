package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.AbstractReusableComponent.AbStractReusableComponent;

public class CartPage extends AbStractReusableComponent
{
	WebDriver driver;
	Boolean match;
	
	
	public CartPage(WebDriver driver)
	{
	super(driver);
	this.driver= driver;
	PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	//wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".itemImg"))));
	@FindBy(css=".itemImg")
	WebElement itemImage;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	WebElement goToChechoutButton;
	
	public List<WebElement> getCartProductList()
	{
		WaitForElementToAppearByDriver(itemImage);
		return cartProducts;
	}
	
	public boolean compareNamewithCartProduct(String prodName)
	{
	//COMPARE PRODNAME WITH LIST: RETURN BOOLEAN match
			for(int i=0;i<cartProducts.size();i++)
				if(cartProducts.get(i).getText().contains(prodName))
				{
					match=true;
					System.out.println(cartProducts.get(i).getText());
				}
				else
				{
					match=false;
				}
	
			return match;
			//return match;
	}
	
	public CheckoutPage goToCheckoutPg()
	{
		goToChechoutButton.click();
		CheckoutPage checkOut = new CheckoutPage(driver);
		return checkOut;
	}
	
	
}
