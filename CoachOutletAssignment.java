package testNgPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CoachOutletAssignment {
	
WebDriver driver;
	
	// Set the path for the webdriver and maximize it.

	@BeforeTest
	
		public void path()
	{
		System.setProperty("webdriver.chrome.driver", "/Users/vinay/Desktop/Testing/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
		
	}
	
	// Login to the account.
	
	@Test(dataProvider = "dp")
	
		public void login(String userName, String passWord, String message)
		{
			driver.get("https://www.coach--outlet.com/");
			
	    	driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul[2]/li/a[1]")).click();
	    	WebElement username = driver.findElement(By.id("login-email-address"));
	    	WebElement password = driver.findElement(By.id("login-password"));
	    	WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/button"));
	    	
	    	username.sendKeys(userName);
	    	password.sendKeys(passWord);
	    	submitButton.submit();
	    	
		}
	
	// Multiple login credentials.
	
	@DataProvider
	public Object[][] dp(){
		return new Object[][] {
			new Object[] {"rashvin@gmail.com","AutomationTesting@10","Wrong credentials"},
			new Object[] {"rashvin2714@gmail.com","Schaumburg@10","Wrong credentials"},
			new Object[] {"rashvin2714@gmail.com","Schaumburg10","Correct credentials"},
		};
	}
	
	
	// Find the title of the webpage.
	
	@Test(dependsOnMethods = "login")
	
		public void title()
		{
			String expectedTitle = "Coach";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			System.out.println("The title of the page is: "+actualTitle);
		}
	
	// Find the LOGO of the webpage
	
	@Test (dependsOnMethods = "login")
	
		public void logo()
		{
			WebElement logo = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[1]/a/img"));
			Assert.assertEquals(true, logo.isDisplayed());
		}
	
	// Check the availabilty of the required weblink
	
	@Test(dependsOnMethods = "login")
	
		public void checkLink() throws InterruptedException
		{
			String expectedLink = "BUY NEW PRODUCTS";
			String actualLink = driver.findElement(By.xpath("//*[@id=\"lp-wrapper\"]/div[1]/div/a/div/div[2]")).getText();
			Assert.assertEquals(actualLink, expectedLink);
			System.out.println("The required link is: "+actualLink);
			driver.findElement(By.xpath("//*[@id=\"lp-wrapper\"]/div[1]/div/a/div/div[2]")).click();
			Thread.sleep(2000);
			driver.navigate().back();
		}
	
	// Edit existing account details
	
	@Test(dependsOnMethods = "checkLink")
		
		public void editAccountDetails() throws InterruptedException
		{
			driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul[2]/li/a[1]")).click();
			driver.findElement(By.xpath("//*[@id=\"myAccountGen\"]/li[1]/a")).click();
			WebElement firstName = driver.findElement(By.name("firstname"));
			WebElement lastName = driver.findElement(By.name("lastname"));
			WebElement email = driver.findElement(By.name("email_address"));
			WebElement telephone = driver.findElement(By.name("telephone"));
			WebElement faxNumber = driver.findElement(By.name("fax"));
			
			telephone.clear();
			telephone.sendKeys("987654321");
			Thread.sleep(2000);
			faxNumber.clear();
			faxNumber.sendKeys("123456789");
			Thread.sleep(2000);
			
			WebElement radioBtnHTML = driver.findElement(By.xpath("//*[@id=\"accountEditDefault\"]/form/fieldset[2]/label[1]"));
			
			WebElement radioBtnTextOnly = driver.findElement(By.xpath("//*[@id=\"accountEditDefault\"]/form/fieldset[2]/label[2]"));
			
			radioBtnTextOnly.click();
			
			driver.findElement(By.xpath("//*[@id=\"accountEditDefault\"]/form/div[2]/button")).submit();
		}
	
	// Add a new address to the address book
	
	@Test(dependsOnMethods = "editAccountDetails")
	
		public void addAddress() throws InterruptedException
		{
		
		//	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//*[@id=\"myAccountGen\"]/li[2]/a")).click();
			
			driver.findElement(By.xpath("//*[@id=\"addressBookDefault\"]/div[2]/a/button")).click();
			
			WebElement firstname = driver.findElement(By.id("firstname"));
			WebElement lastname = driver.findElement(By.id("lastname"));
			WebElement streetAddress = driver.findElement(By.id("street-address"));
			WebElement addressLine2 = driver.findElement(By.id("suburb"));
			WebElement city = driver.findElement(By.id("city"));
			
			Select state = new Select( driver.findElement(By.id("stateZone")));
			
			WebElement postCode = driver.findElement(By.id("postcode"));
			
			Select country = new Select(driver.findElement(By.id("country")));
			
			WebElement setPrimaryAddress = driver.findElement(By.className("checkboxLabel"));
			WebElement submitInfo = driver.findElement(By.xpath("//*[@id=\"addressBookProcessDefault\"]/form/div[1]/button[1]"));
			
			firstname.sendKeys("Rash");
			Thread.sleep(2000);
			lastname.sendKeys("Vin");
			Thread.sleep(2000);
			country.selectByVisibleText("United States");
			Thread.sleep(2000);
			streetAddress.sendKeys("1510 valley lake drive");
			Thread.sleep(2000);
			city.sendKeys("Schaumburg");
			Thread.sleep(2000);
			state.selectByVisibleText("Illinois");
			Thread.sleep(2000);
			postCode.sendKeys("60195");
			Thread.sleep(2000);
			
			//setPrimaryAddress.click();
			//Thread.sleep(2000);
			
			
			
			submitInfo.click();
			
			//Assert.assertNotNull(lastname, "Last name is required");
		}
	
	// Delete the added address
	
	@Test(dependsOnMethods = "addAddress")
	
		public void deleteAddress() throws InterruptedException
		{
			driver.findElement(By.xpath("//*[@id=\"addressBookDefault\"]/fieldset/div[2]/a[2]/button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"addressBookProcessDefault\"]/div[2]/a/button")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@id=\"addressBookDefault\"]/div[4]/a/button")).click();
		}
	
	// Subscribe or unsubscribe to newsletter
	
	@Test(dependsOnMethods = "deleteAddress")
	
		public void subscribeUnsubscribeNewsLtr() throws InterruptedException
		{
			driver.findElement(By.xpath("//*[@id=\"myAccountNotify\"]/li[1]/a")).click();
			
			WebElement subscribeCheckBox = driver.findElement(By.className("checkboxLabel"));
			subscribeCheckBox.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"acctNewslettersDefault\"]/form/div[1]/button")).click();
		}
	
	// Enable or disable product notification
	
	@Test(dependsOnMethods = "subscribeUnsubscribeNewsLtr")
	
		public void productNotification() throws InterruptedException
		{
			driver.findElement(By.xpath("//*[@id=\"myAccountNotify\"]/li[2]/a")).click();
			WebElement receiveNotificationChBox = driver.findElement(By.xpath("//*[@id=\"accountNotifications\"]/form/fieldset[1]/label"));
			receiveNotificationChBox.click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@id=\"accountNotifications\"]/form/div[2]/button")).click();
		}
	
	// Sign out of the webpage and close the browser
	
	@AfterTest
	
	public void close() 
	{
		driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul[2]/li/a[2]")).click();
		driver.close();
	}

}
