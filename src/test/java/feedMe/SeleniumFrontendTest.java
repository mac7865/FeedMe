package feedMe;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumFrontendTest {
	 WebDriver driver = new ChromeDriver();

	 @Test
	 public void test() throws InterruptedException {
	  driver.manage().window().maximize();
	  driver.get("http://only-testing-blog.blogspot.in/2013/11/new-test.html");
	  driver.findElement(By.xpath("//input[@name='fname']")).sendKeys("junittest1 executed");
	  Thread.sleep(2000);
	  System.out.print("junittest1 class is executed");
	  driver.quit();
	 }
}
