package rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademyMavenPractice.SeleniumFrameworkDesignMaven.AbstractReusableComponent.AbStractReusableComponent;

public class LandingPage extends AbStractReusableComponent
{
	WebDriver driver;
	public LandingPage(WebDriver driver) 
	{	super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//declarations//
	//driver.findElement(By.id("userEmail")).sendKeys("meghashree.banerjee05@gmail.com");
	@FindBy(id="userEmail")
	WebElement userEmail;
	//driver.findElement(By.id("userPassword")).sendKeys("Rahulshetty@academy5");
	@FindBy(id="userPassword")
	WebElement upwd;
	//driver.findElement(By.id("login")).click();
	@FindBy(id="login")
	WebElement submit;
	
	//Actions
	public GetProduct ActionsPerformed(String uID, String pwd)
	{
		userEmail.sendKeys(uID);
		upwd.sendKeys(pwd);
		submit.click();
		GetProduct productcatalogue= new GetProduct(driver);
		return productcatalogue;
	}
	
	public void GoTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}
	
	
}



