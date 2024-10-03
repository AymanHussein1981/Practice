package class_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class A002_Config_Prop {
	
	
	WebDriver driver;
	String Browser;
	String URL;
	String userName;
	String Password;
	
	
	By USERNAME = By.xpath("//input[@id='user_name']");
	By PASSWORD = By.xpath("//input[@id='password']");
	By LOGINBUTTON = By.xpath("//*[@id=\"login_submit\"]");
	By HEADERTEXT = By.xpath("/html/body/div[1]/section/div/div[2]/div/div/header/div/strong");
	
	
	@BeforeClass
	public void Config() {
		
		try {
			InputStream input = new FileInputStream("src\\main\\java\\class_2\\config.properties");
			Properties prop=new Properties();
			prop.load(input);
			Browser=prop.getProperty("browser1");
			URL=prop.getProperty("url");
			userName=prop.getProperty("username");
			Password=prop.getProperty("password");
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	
	@BeforeMethod
	public void init() {
	
		if(Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Pleas enter valid browser name");
		}
		
		driver.get(URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void loginplusAssertion() throws InterruptedException {
		
		driver.findElement(USERNAME).sendKeys(userName);
		driver.findElement(PASSWORD).sendKeys(Password);
		driver.findElement(LOGINBUTTON).click();
		Thread.sleep(10000);
		
		Assert.assertEquals(driver.getTitle(), "Codefios", "Page not Found!!!");
	
		
		
		
		
	}
	
	
	@AfterMethod
	public void Teardown() {
		driver.close();
	}

}
