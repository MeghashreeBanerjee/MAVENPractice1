package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.AbstractReusableComponent.AbStractReusableComponent;

public class OrderPage extends AbStractReusableComponent
{	WebDriver driver;
boolean match;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderPageProducts;
	
	public void getOrderPageProductList() // just for internal test purpoose: can delete this method
	{
		//WaitForElementToAppearByDriver(itemImage);
	orderPageProducts.stream().forEach(p->System.out.println(p.getText()));
	}
	
	public boolean VerifyOrderDisplay(String prodName)
	{	
		boolean match = orderPageProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(prodName));
		//System.out.println(match);
		return match;
		
	}
}
