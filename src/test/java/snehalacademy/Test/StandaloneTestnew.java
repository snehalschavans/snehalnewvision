package snehalacademy.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import snehalacademy.pageobjects.Landingpage;

public class StandaloneTestnew {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		;
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.id("userEmail")).sendKeys("snehalschavans@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sne@1993");
		Landingpage l = new Landingpage(driver);
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card']//h5/b")));

		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card']//h5/b"));
		/*
		 * for(WebElement s:products) { System.out.println(s.getText()); String
		 * value=s.getText(); if(value.contains("IPHONE 13 PRO")) {
		 * driver.findElement(By.cssSelector("button[class*='btn w-10 rounded']")).click
		 * (); }
		 * 
		 * } System.out.println("Added");
		 */
		WebElement requireditem = products.stream().filter(s -> s.getText().contains("IPHONE 13 PRO")).findFirst()
				.orElse(null);
		requireditem.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ng-animating")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartproduct = driver.findElements(By.xpath("//div[@class='cart']//div/h3"));
		boolean match = cartproduct.stream().anyMatch(s -> s.getText().contains("IPHONE 13 PRO"));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
	}

}
