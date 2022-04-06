package testNgPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BeforeAndAfterLogin {
	
	WebDriver driver;
    @Test
    public void performLogin() throws InterruptedException {
      System.out.println("Performing Login and wait for 2 seconds");
      driver.get("https://admin-demo.nopcommerce.com/login");
      WebElement username = driver.findElement(By.name("Email"));
      WebElement password = driver.findElement(By.name("Password"));
      WebElement submitBtn = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button"));
      username.clear();
      username.sendKeys("admin@yourstore.com");
      password.clear();
      password.sendKeys("admin");
      submitBtn.click();
      System.out.println("End Result: Form submitted");
      Thread.sleep(2000);
    }
    @Test(dependsOnMethods="performLogin")
    void searchAfterLogin()
    {
    	driver.findElement(By.xpath("//*[@id=\"search-box\"]/span/input")).sendKeys("Orders");
    }

@BeforeTest
    public void beforeTest() {
        System.out.println("--@beforeTest, set the browser, maximise the window");
        System.setProperty("webdriver.chrome.driver", "/Users/vinay/Desktop/Testing/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterTest
    public void afterTest() {
        System.out.println("--@afterTest, closing the browser window");
        driver.close();
    }

}
