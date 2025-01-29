package snehalacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import snehalacademy.AbstractComponents.AbstractComponents;

public class ProductCatlog extends AbstractComponents {
	WebDriver driver;
	
	public ProductCatlog(WebDriver driver)
	{
		super(driver);//every chid to serve parent class i.e invoking parent constructor
		this.driver=driver;
		PageFactory.initElements(driver, this);//this represent class driver
	}
	
	//Pagefactory
	
	//List<WebElement> products=driver.findElements(By.xpath("//div[@class='card']//h5/b"));
	
	
	@FindBy(css=".mb-3")
	List <WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastmsg=By.id("toast-container");
	
	
	//to get access of driver we need to use initElements method
	
	public List<WebElement> getProdList()
	{
		WaitElementToAppear(productBy);
		
		return products;
	}
	public WebElement getProdByName(String productName)
	{
		WebElement prod =getProdList().stream().filter(s->s.getText().contains(productName)).findFirst().orElse(null);
		//System.out.println(prod);
		return prod;
		
	}
	
	
	public void addProdToCart(String productName)
	{
		WebElement prod=getProdByName(productName);
		//System.out.println(prod);
		prod.findElement(addToCart).click();
		WaitElementToAppear(toastmsg);
		//WaitElementTodisapper(spinner);
	}
	
	
	
	

}
