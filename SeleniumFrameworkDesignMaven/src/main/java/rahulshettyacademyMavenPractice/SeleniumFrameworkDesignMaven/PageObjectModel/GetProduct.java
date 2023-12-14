package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.AbstractReusableComponent.AbStractReusableComponent;

public class GetProduct extends AbStractReusableComponent
{
	WebDriver driver;

	public GetProduct(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//declaration : List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.id("toast-container");
	
	public List<WebElement> getProductList()
	{
		WaitForElementToAppear(productsBy);
		return products;
	}
	
	public void addProducttoCart(String prodName)
	{ 
		for (int i = 0; i < products.size(); i++) 
		{
			
			if (products.get(i).getText().contains(prodName)) 
			{
			WebElement button= products.get(i).findElement(addToCart);
			button.click();
			break;	
			}
		}
		
		WaitForElementToAppear(toastMsg);
		WaitForElementToDisappear(spinner);
		
	}
	
}
