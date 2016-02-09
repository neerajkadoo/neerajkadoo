package com.mvn.automation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import pageClasses.SigninPageFactory;
import pageClasses.SignupPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;
import utilities.FullScreenCapture;

public class SignInTest {
	private WebDriver driver;
	SignupPageFactory signUp;
	SigninPageFactory signIn;
	// Log4j logger init
	static Logger log = Logger.getLogger(SignUpTest.class);

	// Use this class for mouseover actions
	Actions action;

	@Parameters({"browserType", "env"})
	@BeforeMethod
	public void setUp(String browser, String envir) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			// http://chromedriver.storage.googleapis.com/index.html
			System.setProperty("webdriver.chrome.driver", Constants.Chrome_driver_location);
			driver = new ChromeDriver();
		}
		if (envir.equalsIgnoreCase("stage")){
			driver.get(Constants.URL);
		}else if (envir.equalsIgnoreCase("prod")){
			driver.get(Constants.PROD_URL);
		}
		
		signUp = new SignupPageFactory(driver);
		action = new Actions(driver);
		signIn = new SigninPageFactory(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		//driver.get(Constants.URL);
	}
	
//	public void environment(String envir) throws Exception{
//		if (envir.equalsIgnoreCase("stage")){
//			driver.get(Constants.URL);
//		}else if (envir.equalsIgnoreCase("prod")){
//			driver.get(Constants.ProdURL);
//		}
//	}
//	
	
	@Test(groups = "positive")
	public void signInUser() throws Exception {
		log.info("Running Sign In user Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);		
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		log.info("Using "+userEmail+" for login to Spaces");
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);
		// driver.close();
	}

	@AfterMethod
		public void afterClass(ITestResult testResult) throws IOException {
			utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
			driver.close();
		}
}
