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

public class StandAloneTest 
{
	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "C:\\ProgramData\\chromedriver-win64\\chromedriver_win32\\chromedriver.exe"); 
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		String prodName= "ADIDAS ORIGINAL";
		
		driver.findElement(By.id("userEmail")).sendKeys("meghashree.banerjee05@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahulshetty@academy5");
		driver.findElement(By.id("login")).click();
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		for (int i = 0; i < products.size(); i++) 
		{
			if (products.get(i).getText().contains("ADIDAS")) {
			WebElement button = products.get(i).findElement(By.cssSelector(".card-body button:last-of-type"));
			button.click();
			break;
			}
		}
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		Thread.sleep(12000);
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".itemImg"))));
		

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Thread.sleep(8000);
		for(int i=0;i<cartProducts.size();i++)
		{
			System.out.println(cartProducts.get(i).getText());
			if(cartProducts.get(i).getText().contains(prodName))
			{
				Assert.assertTrue(true);
			}
			
		}
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Thread.sleep(8000);
		
		
		/*	WebElement prod = products.stream().filter(p->p.findElement(By.cssSelector("b")).
		getText().equals("zara coat 3")).findFirst().orElse(null);
Thread.sleep(8000);

//System.out.println(prod.getText());
//System.out.println(prod.getSize());

prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

*/
		//cartProducts.stream().filter(p->p.getText().contains(prodName)).forEach(p->System.out.println(p));
		
		
		/*Actions a = new Actions(driver);

	    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

	    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click(); */
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		List <WebElement> place= driver.findElements(By.cssSelector(".list-group-item"));
		for(WebElement opt :place)
		  {
			  if(opt.getText().equalsIgnoreCase("India"))
			  {
				  opt.click();
				//  System.out.println(opt.getText());
				  break;
			  }

		  } 

	    Thread.sleep(5000);
	   
		//DATE: STATIC DROPDOWN
		WebElement staticDropdown= driver.findElement(By.cssSelector("select.ddl:first-of-type"));
		Select dp1= new Select(staticDropdown);
		dp1.selectByIndex(9);
		
		
		   Thread.sleep(5000);
		WebElement staticDropdown2= driver.findElement(By.cssSelector("select.ddl:last-of-type"));
		Select dp2= new Select(staticDropdown2);
		dp2.selectByIndex(4);
		
		
		   Thread.sleep(5000);
		driver.findElement(By.xpath("//div/div/input[@class='input txt']")).sendKeys("99");
		System.out.println("check4");
		
		
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
		//driver.findElement(By.cssSelector(".action__submit")).click();
		WebElement element = driver.findElement(By.cssSelector(".action__submit"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		System.out.println("order placed");
		
		String orderCmpMsg= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(orderCmpMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
		


	}

}
