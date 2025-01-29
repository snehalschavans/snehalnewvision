package snehalacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.implementation.bind.annotation.Super;
import snehalacademy.AbstractComponents.AbstractComponents;

public class Landingpage extends AbstractComponents {
	WebDriver driver;
	
	public Landingpage(WebDriver driver)
	{
		super(driver);//pasing child variables to parent
		this.driver=driver;
		PageFactory.initElements(driver, this);//this represent class driver
	}
	
	//Pagefactory
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement pass;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='ng-trigger-flyInOut']")
	WebElement toasterrormsg;
	
	//to get access of driver we need to use initElements method
	
	public ProductCatlog LoginApplication(String usermail,String userpass)
	{
		useremail.sendKeys(usermail);
		pass.sendKeys(userpass);
		submit.click();
		ProductCatlog prodcat=new ProductCatlog(driver);
		return prodcat;
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String geErrorMsg()
	{
		WaitWeblelementToAppear(toasterrormsg);
		return toasterrormsg.getText();
		
		
	}
	
	
	

}
