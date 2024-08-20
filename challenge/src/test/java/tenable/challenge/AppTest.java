package tenable.challenge;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AppTest 
{
   
	public static WebDriver driver = null;
	
	@BeforeSuite()
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
				
	}
	
	@BeforeTest()
	public void setUpBrowser()
	{		
		driver.get("https://www.tendable.com/");
		String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://www.tendable.com/", currentUrl);
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();  //accept selection on cookie banner
	}
	
	public void requestDemobuttonCheck()
	{
		driver.findElement(By.linkText("Request A Demo")).isDisplayed();
		driver.findElement(By.linkText("Request A Demo")).isEnabled();
	}
	
    @Test
    public void Task1()
    {
       String url = driver.findElement(By.className("logo")).getAttribute("href");
       Assert.assertEquals("https://www.tendable.com/", url);
       String ourStoryButtonText = driver.findElement(By.linkText("Our Story")).getText();
       Assert.assertEquals("Our Story", ourStoryButtonText);
       String ourSolutionButtonText = driver.findElement(By.linkText("Our Solution")).getText();
       Assert.assertEquals("Our Solution", ourSolutionButtonText);
       String whyTendableButtonText = driver.findElement(By.linkText("Why Tendable?")).getText();
       Assert.assertEquals("Why Tendable?", whyTendableButtonText);
       String aboutUsButtonText = driver.findElement(By.linkText("About Us")).getText();
       Assert.assertEquals("About Us", aboutUsButtonText);
    }
    
    
    
    @Test
    public void Task2()
    {
     
    	//check on Home Page
    	requestDemobuttonCheck();
    	
    	//check on Our Story Page
    	driver.findElement(By.linkText("Our Story")).click();
    	requestDemobuttonCheck();
    	
    	//check on Our Solution Page
    	driver.findElement(By.linkText("Our Solution")).click();
    	driver.findElement(By.linkText("Mobile App")).click();
    	requestDemobuttonCheck();
    	
    	//check on Why Tendable Page
    	driver.findElement(By.linkText("Why Tendable?")).click();
    	requestDemobuttonCheck();
    	
    	//check on About Us Page
    	driver.findElement(By.linkText("About Us")).click();
    	driver.findElement(By.linkText("Our team")).click();
    	requestDemobuttonCheck();
    }
    
    
    @Test
    public void Task3()
    {
     	
    	driver.findElement(By.linkText("Contact Us")).click();
    	driver.findElement(By.cssSelector("button[data-target='.toggle-163701']")).click();
    	driver.findElement(By.id("form-input-fullName")).sendKeys("Sarthak Jauhari");
    	driver.findElement(By.id("form-input-organisationName")).sendKeys("Tendable");
    	driver.findElement(By.id("form-input-cellPhone")).sendKeys("9876543210");
    	driver.findElement(By.id("form-input-email")).sendKeys("abcd@gmail.com");
        
    	WebElement iframe = driver.findElement(By.name("form_page_submit"));
        new Actions(driver)
                .scrollToElement(iframe)
                .perform();
    	
    	driver.findElement(By.id("form-input-consentAgreed-0")).click();
    	
    	driver.findElement(By.name("form_page_submit")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

    	try
    	{
    		String errMsg = driver.findElement(By.className("ff-errors")).getText();
    		System.out.println("Error message displayed : " + errMsg);
        	Assert.assertTrue(true);
    	}
    	catch(NoSuchElementException e)
    	{    		
    		Assert.assertTrue(false);
    	}
    	
    }
    
    
    @AfterSuite()
	public void tearDown()
	{
		driver.quit();
		
	}
}
