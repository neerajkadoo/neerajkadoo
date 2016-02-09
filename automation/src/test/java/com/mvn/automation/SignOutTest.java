package com.mvn.automation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageClasses.CreateSpacePageFactory;
import pageClasses.SigninPageFactory;
import pageClasses.SignupPageFactory;
import pageClasses.myAccountPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;

public class SignOutTest {
	
	private WebDriver driver;
	SigninPageFactory signIn;
	myAccountPageFactory myAccount;
	//Log4j logger init
	static Logger log = Logger.getLogger(SignInTest.class);
	
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
		myAccount = new myAccountPageFactory(driver);
		signIn = new SigninPageFactory(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
	}

	@Test(groups = "positive")
	public void signOutUser() throws Exception{
		log.info("Running Sign Out user Test case");
		
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(4, 1);
		String userPassword = ExcelUtility.getCellData(4, 2);
		
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Signing out of the account
		// Condition for clicking on default or customize avatar
		try
		{
			if (driver.findElement(By.cssSelector(".default")).isDisplayed()) {
				myAccount.clickAvater();
			}
		}		 
		catch (Exception e)
		{
			myAccount.clickAvaterImage();
		}		
		Thread.sleep(1000);
		myAccount.clicksignOut();
		Thread.sleep(1000);
	}

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
