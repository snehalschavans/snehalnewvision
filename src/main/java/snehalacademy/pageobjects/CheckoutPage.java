package snehalacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import snehalacademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	private @FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	private @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectedCountry;
	
	private @FindBy(css=".action__submit")
	WebElement submitInfo;
	
	
	By results=By.cssSelector(".ta-results");
	
	public void AddShippingInfo(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(Country,countryName).build().perform();
		//WaitElementToAppear(results);
		SelectedCountry.click();

		
	}
	
	
	public ConfirmationPage SubmitInfo()
	{
		submitInfo.click();
		return new ConfirmationPage(driver);
	}
	

}
