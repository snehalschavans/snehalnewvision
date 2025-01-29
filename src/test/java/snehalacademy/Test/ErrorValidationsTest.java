package snehalacademy.Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import snehalacademy.TestComponent.BaseTest;
import snehalacademy.pageobjects.ProductCatlog;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups=("Verification"))
	
	public void ErrorValidationTest()throws InterruptedException, IOException {

		String productName = "IPHONE 13 PRO";
		
		landing.LoginApplication("snehalschavans@gmail.com", "Sne@19");

		//div[@class='ng-tns-c4-2 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
		Assert.assertEquals("Incorrect email password.",landing.geErrorMsg());
		
		

		
	}
	@Test
	public void VerifyLogin()
	{
		System.out.println("Check login");
	}

}
