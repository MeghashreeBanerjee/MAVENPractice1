package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
//import org.testng.Assert;

import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.AbstractReusableComponent.AbStractReusableComponent;

public class CheckoutPage extends AbStractReusableComponent
{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) 
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	boolean nomatch;
	//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
	@FindBy(css="input[placeholder='Select Country']")
	WebElement typeCountryInput;
	//List <WebElement> place= driver.findElements(By.cssSelector(".list-group-item"));
	@FindBy(css=".list-group-item")
	List<WebElement> listOfMatchCountry;
	//WebElement staticDropdown= driver.findElement(By.cssSelector("select.ddl:first-of-type"));
	@FindBy(css="select.ddl:first-of-type")
	WebElement selectMonth;
	//WebElement staticDropdown2= driver.findElement(By.cssSelector("select.ddl:last-of-type"));
	@FindBy(css="select.ddl:last-of-type")
	WebElement selectDay;
	//driver.findElement(By.xpath("//div/div/input[@class='input txt']")).sendKeys("99");
	@FindBy(xpath="//div/div/input[@class='input txt']")
	WebElement cvv;
	//WebElement element = driver.findElement(By.cssSelector(".action__submit"));
	@FindBy(css=".action__submit")
	WebElement cSubmit;

	public void typeSendCountry(String countryClue) throws InterruptedException
	{
		typeCountryInput.sendKeys(countryClue);
		Thread.sleep(5000);
	}
	
	//CHECKOUT PAGE	//	
					
		public boolean matchCountry(String country) throws InterruptedException
		{
			
			for(WebElement opt :listOfMatchCountry)
			  {	
				  //Thread.sleep(5000);
				  if(opt.getText().equalsIgnoreCase(country))
				  {
					  opt.click();
					//  System.out.println(opt.getText());
					  break;
				  }
				  else
					  nomatch=true;
			  } 
			return nomatch;
		}

		//DATE: STATIC DROPDOWN
		public void selectDateCVV() throws InterruptedException
		{
			Select dp1= new Select(selectMonth);
			dp1.selectByIndex(9);
			//WaitForElementSelectionStateToBeTrue(selectMonth);
			Thread.sleep(5000);
			Select dp2= new Select(selectDay);
			dp2.selectByIndex(4);
			//WaitForElementSelectionStateToBeTrue(selectDay);
			Thread.sleep(5000);
			cvv.sendKeys("99");
		}
			
			public OrderPlacedPg goToPlacedOrderPg() throws InterruptedException
			{   //WaitForElementSelectionStateToBeTrue(cvv);
				Thread.sleep(5000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", cSubmit);
				System.out.println("order placed");
				OrderPlacedPg orderPlaced = new OrderPlacedPg(driver);
				return orderPlaced;
			}
			
}