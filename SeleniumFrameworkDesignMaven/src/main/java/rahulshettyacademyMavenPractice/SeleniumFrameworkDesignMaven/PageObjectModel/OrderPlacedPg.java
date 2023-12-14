package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.AbstractReusableComponent.AbStractReusableComponent;

public class OrderPlacedPg extends AbStractReusableComponent 
{
	WebDriver driver;

	public OrderPlacedPg(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}  
	
	@FindBy(css=".hero-primary")
	WebElement orderCompletionMSG;
	
	public String compareCompletionMSG()
	{
		String msg=orderCompletionMSG.getText();
		return msg;
	}
	
	//String orderCmpMsg= driver.findElement(By.cssSelector(".hero-primary")).getText();
			//Assert.assertTrue(orderCmpMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


}
