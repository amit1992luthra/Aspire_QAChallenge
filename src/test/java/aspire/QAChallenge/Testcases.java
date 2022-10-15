package aspire.QAChallenge;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testcases {
	
	WebDriver driver;
	
	@BeforeMethod
	public void init()
	{
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver= new ChromeDriver();
		System.out.println("Navigating to the application");
		driver.get("https://codechallenge.odoo.com/web/login");
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void quit()
	{
		driver.quit();
	}
	
	@Test
	public void testcase1() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,20);
		String productname = "test"+randomnumber();
		//Step 1 :- Logging Application
		
		driver.findElement(By.id("login")).sendKeys("user@codechallenge.app");
		driver.findElement(By.id("password")).sendKeys("@sp1r3app");
		driver.findElement(By.cssSelector("button.btn.btn-primary.btn-block")).click();
		
		//Step 2:- Navigate to `Inventory` feature
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@id='result_app_1']")).click();
		
		//Step 3:- From the top-menu bar, select `Products -> Products` item, then create a new	product
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[text()='Products']")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("button.btn.btn-primary.o-kanban-button-new")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='o_field_input_12']")).sendKeys(productname);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("button.btn.btn-primary.o_form_button_save")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Update Quantity']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.btn-primary.o_list_button_add")));
		driver.findElement(By.cssSelector("button.btn.btn-primary.o_list_button_add")).click();
		
		//Step 4:- Update the quantity of new product is more than 10
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='inventory_quantity']")).clear();
		driver.findElement(By.xpath("//input[@name='inventory_quantity']")).sendKeys("15");
		driver.findElement(By.cssSelector("button.btn.btn-primary.o_list_button_save")).click();
		
		//Step 5: From top-left page, click on `Application` icon
		
		driver.findElement(By.cssSelector("a.fa.o_menu_toggle.fa-th")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//Step 6:- Navigate to `Manufacturing` feature, then create a new Manufacturing Order item for the created Product on step #3
		
		driver.findElement(By.cssSelector("a#result_app_2")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("button.btn.btn-primary.o_list_button_add")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//td/label[text()='Product']/../following-sibling::td/div/div/div/input")).sendKeys(productname);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//li[@class='ui-menu-item']/a[text()='"+productname+"']")).click();
		
		//Step 7:- Update the status of new Orders to “Done” successfully
		
		driver.findElement(By.xpath("//button[@name='action_confirm']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("button[class='btn btn-primary']")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[text()='Ok']")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button/span[text()='Apply']")).click();
		
		//Step 8:- Validate the new Manufacturing Order is created with corrected information.
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		String result = driver.findElement(By.xpath("//button[contains(text(),'Done')]")).getAttribute("title");
		Assert.assertEquals(result, "Current state");
		
	}
	
	public int randomnumber()
	{
		
	        Random rand = new Random();
	        int minRange = 1000, maxRange= 5000;
	        int value = rand.nextInt(maxRange - minRange) + minRange;
	        return value;
	        
	    
	}

}
