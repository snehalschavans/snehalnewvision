package snehalacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import snehalacademy.pageobjects.Landingpage;

public class BaseTest {
	public Landingpage landing;
	
    public WebDriver driver;
	public WebDriver initalizeDriver() throws IOException
	{

		Properties file=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/snehalacademy/resources/GlobalParameter.properties");
		//FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/java/snehalacademy/resources/GlobalParameter.properties");
		file.load(fis);
		String browsername= System.getProperty("browser")!=null ? System.getProperty("browser") : file.getProperty("browser");//dynamic value sending from maven
		//String browsername=file.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			//System.setProperty(WebDriver.geckodriver.driver, "");
		}
		else if(browsername.equalsIgnoreCase("Edge"))
		{
			//Edge
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       //if use properties extension then by using properties class can parse all the global parameters
		//Control complete framework by settong global properties
		return driver;
	}
	public List<HashMap<String, String>> getJsondataToMap(String Filepath) throws IOException
	{
		//read json to String
		String JsonData=FileUtils.readFileToString(new File(Filepath),StandardCharsets.UTF_8);
		//Convert String to Hashmap by using dependency Jackson Databind
		ObjectMapper mapper=new ObjectMapper();
	    List<HashMap<String,String>> data=mapper.readValue(JsonData, new TypeReference<List<HashMap<String,String>>>(){});
	
	
	//return {map,map}
	return data;
	
		
	}
	public String takeScreenShortOnFailure(String TestcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=((TakesScreenshot)driver);
	    File src	=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+ "//reports//" + TestcaseName + ".png");
		FileUtils.copyFile(src,dest);
		return System.getProperty("user.dir")+ "//reports//" + TestcaseName + ".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public Landingpage LaunchApplication() throws IOException
	{
		driver=initalizeDriver();
		landing = new Landingpage(driver);
		landing.goTo();
		return landing;
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.close();
		//extent.flush();
		
	}

}
