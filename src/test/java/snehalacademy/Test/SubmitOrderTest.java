package snehalacademy.Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import snehalacademy.TestComponent.BaseTest;
import snehalacademy.pageobjects.CheckoutPage;
import snehalacademy.pageobjects.ConfirmationPage;
import snehalacademy.pageobjects.Landingpage;
import snehalacademy.pageobjects.MyCart;
import snehalacademy.pageobjects.ProductCatlog;

public class SubmitOrderTest extends BaseTest {
	String productName = "IPHONE 13 PRO";
	@Test(dataProvider="getData",groups={"Purchase"})
	
	
	public void SubmitOrder(HashMap<String,String> input)throws InterruptedException, IOException {

		ProductCatlog prodcat = landing.LoginApplication(input.get("email"),input.get("password"));

		List<WebElement> products = prodcat.getProdList();
		prodcat.addProdToCart(input.get("productname"));

		Thread.sleep(1000);

		MyCart cart = prodcat.gotoCart();

		// MyCart cart=new MyCart(driver);//encapsulation of class object creation to
		// gotoCart method
		boolean match = cart.VerifyAddedProducts(input.get("productname"));
		AssertJUnit.assertTrue(match);// all assertions shoub be in Test cases
		CheckoutPage check = cart.Checkout();
		check.AddShippingInfo("india");
		ConfirmationPage confirm = check.SubmitInfo();
		String confirmedMsg = confirm.getConfirmationMessg();
		Assert.assertTrue(confirmedMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		
	}
	
	
	/**/
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		
		List<HashMap<String,String>> data=getJsondataToMap(System.getProperty("user.dir")+"//src//test//java//snehalacademy//data//PurchaseOrder.json");
		/*Object[][] obj= new Object[][] {{"snehalschavans@gmail.com","Sne@1993","IPHONE 13 PRO"},{"shetty@gmail.com","Iamking@000","QWERTY"}};
		return obj;*/
		return new Object[][]  {{data.get(0)},{data.get(1)}};
		
	}

}
