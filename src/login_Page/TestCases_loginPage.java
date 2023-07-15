package login_Page;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCases_loginPage {
	WebDriver driver;
	SoftAssert s=new SoftAssert();
	boolean Status_errormsg = false;

	@BeforeMethod
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\ALL_Selenium_Jars\\chromedriver.exe");
		 driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");   // here we are given sample facebook login url
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.manage().window().maximize();
		
	}
	
	@Test(priority =1)
	public void successful_login()
	{
		driver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']")).sendKeys("Admin"); // passed valied username
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");  // passed valied password
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    String Exp_HpgeURl=driver.getCurrentUrl();
	    String A_Hpgeurl="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
	    s.assertEquals(A_Hpgeurl, Exp_HpgeURl);
	    
	
	}
	@Test(priority =2)
	public void invaliedCredentials()
	{
		driver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']")).sendKeys("dmin"); // passed valied username
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin23");  // passed valied password
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
		boolean Status_errormsg= driver.findElement(By.xpath("//div[@class='oxd-alert oxd-alert--error']")).isDisplayed();
	   System.out.println(Status_errormsg);
		s.assertTrue(Status_errormsg);
	s.assertAll();
	}
	@Test(priority=3)
	public void getFailedlogin_credential()
	{
		driver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']")).sendKeys("dmin"); // passed valied username
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin23");  // passed valied password
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
	    String username=driver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']")).getAttribute("value");
		System.out.println("Wrong credentials provided");	
		System.out.println("wrong username:"+username);
		String password=driver.findElement(By.xpath("//input[@name='password']")).getAttribute("value");
		System.out.println("wrong password:"+password);
		s.assertAll();

		
	}
	@Test(priority =4)
	public void loginBtnDisabled()
	{
	 boolean Status_loginBtn=   driver.findElement(By.xpath("//button[@type='submit']")).isEnabled();
       System.out.println(Status_loginBtn);
       s.assertFalse(Status_loginBtn);
	   s.assertAll();
	}
	@Test (priority =5)
	public void loginBtnEnabled_validCrednetial()
	{
		driver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']")).sendKeys("Admin"); // passed valied username
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");  // passed valied password
		boolean status_loginbtn_valied=  driver.findElement(By.xpath("//button[@type='submit']")).isEnabled();
	    s.assertTrue(status_loginbtn_valied);
	    System.out.println(status_loginbtn_valied);
	    System.out.println("Login btn is displayed after valied credentials");
		
		
	}
      @Test(priority =6)
      public void redirect_forgetPass()
      {
    	  driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")).click();
          String Aurl="https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
      String Eurl=driver.getCurrentUrl();
      s.assertEquals(Aurl, Eurl);
      System.out.println("successfully redirected to forget passwod page");
      s.assertAll();
      }
      
     @Test(priority =7)
      public void validate_PassRecovery()
      {
    	  String Amsg="Reset Password link sent successfully";
    	driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")).click();
    	driver.findElement(By.xpath("//input[@name='username'] [@class='oxd-input oxd-input--active']")).sendKeys("Akshay@123");
    	driver.findElement(By.xpath("//button[@type='submit']")).click();
    	
      WebElement msg=  driver.findElement(By.xpath("//div[@class='orangehrm-card-container']"));
     String Emsg=  msg.getText();
     s.assertEquals(Amsg,Emsg);
     
       
       
      }
      
      @AfterMethod
      public void closeBrowser()
      {
    	  driver.quit();
      }
}
