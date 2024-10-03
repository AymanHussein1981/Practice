package class_2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class A001_Login_testNG {
	
WebDriver driver;

String url="https://codefios.com/ebilling/";
String userName="demo@codefios.com";
String password="abc123";

String browser="zzzzz";
	
	By USERNAME = By.xpath("//input[@id='user_name']");
	By PASSWORD = By.xpath("//input[@id='password']");
	By LOGINBUTTON = By.xpath("//*[@id=\"login_submit\"]");
	By HEADERTEXT = By.xpath("/html/body/div[1]/section/div/div[2]/div/div/header/div/strong");
	
	
	@BeforeMethod
	public void init() {
		
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		else {
			System.setProperty("webdriver.edge.driver","driver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		
		
		
		
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	

	@Test
	public void loginplusAssertion() throws InterruptedException {
		
		driver.findElement(USERNAME).sendKeys(userName);
		driver.findElement(PASSWORD).sendKeys(password);
		driver.findElement(LOGINBUTTON).click();
		Thread.sleep(5000);
		
		Assert.assertEquals(driver.getTitle(), "Codefios", "Page not Found!!!");
	
		
		
		
		
	}
	
	
	@AfterMethod
	public void Teardown() {
		driver.close();
	}

}
