package snehalacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import snehalacademy.AbstractComponents.AbstractComponents;

public class MyCart extends AbstractComponents{
 
	WebDriver driver;
	public MyCart(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy(xpath="//div[@class='cart']//div/h3")
List <WebElement> MyCartProds;
//driver.findElement(By.cssSelector(".totalRow button")).click();
@FindBy(css=".totalRow button")
WebElement Cartcheckout;


public boolean VerifyAddedProducts(String productName)
{
	boolean match=MyCartProds.stream().anyMatch(s->s.getText().contains(productName));
	return match;
}
public CheckoutPage Checkout()
{
	Cartcheckout.click();
	//CheckoutPage check=new CheckoutPage();
	return new CheckoutPage(driver);
}
	

}
