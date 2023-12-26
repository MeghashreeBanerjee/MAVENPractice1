package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.AbstractReusableComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.CartPage;
import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel.OrderPage;

public class AbStractReusableComponent 
{
	WebDriver driver;
	public AbStractReusableComponent(WebDriver driver) 
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
	@FindBy(css="[routerlink*=cart]")
	WebElement cartHeader;
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	//As headers can be common from other page 
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}


	public void WaitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void WaitForElementToAppearByDriver(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void WaitForElementSelectionStateToBeTrue(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementSelectionStateToBe(ele, true));
	}
	
	public void WaitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
