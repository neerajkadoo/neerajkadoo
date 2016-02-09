package utilities;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import pageClasses.CreateSpacePageFactory;
import pageClasses.SigninPageFactory;
import pageClasses.SignupPageFactory;

@Deprecated
public class SetUp {

	public static WebDriver driver;
//	SignupPageFactory signUp;
//	CreateSpacePageFactory spaceCreate;
//	SigninPageFactory signIn;
//	Actions action;

	//@Parameters("browserType")
	public static void browser(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			// http://chromedriver.storage.googleapis.com/index.html
			System.setProperty("webdriver.chrome.driver", Constants.Chrome_driver_location);
			driver = new ChromeDriver();
		}

//		new SignupPageFactory(driver);
//		new CreateSpacePageFactory(driver);
//		new SigninPageFactory(driver);
//		new Actions(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
		
	}
	
	public SetUp(WebDriver driver) {
		SetUp.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
