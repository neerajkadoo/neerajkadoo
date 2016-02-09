package com.mvn.automation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageClasses.SigninPageFactory;
import pageClasses.myAccountPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;
import utilities.ProviderLogInUtility;

public class ContentProviderTest {
	private WebDriver driver;

	SigninPageFactory signIn;
	myAccountPageFactory myAccount;

	static Logger log = Logger.getLogger(ContentProviderTest.class);

	@Parameters({ "browserType", "env" })
	@BeforeMethod
	public void setUp(String browser, String envir) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			// http://chromedriver.storage.googleapis.com/index.html
			System.setProperty("webdriver.chrome.driver",
					Constants.Chrome_driver_location);
			driver = new ChromeDriver();
		}
		if (envir.equalsIgnoreCase("stage")) {
			driver.get(Constants.URL);
		} else if (envir.equalsIgnoreCase("prod")) {
			driver.get(Constants.PROD_URL);
		}

		// INITIALIZE DRIVER AS NEEDED//
		myAccount = new myAccountPageFactory(driver);
		signIn = new SigninPageFactory(driver);

		// MANDATORY- MAXIMIZE THE BROWSER'S WINDOW
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
	}

	@Test(groups = "positive")
	public void connectToDropbox() throws Exception {
		log.info("Running 'Connect to Dropbox from sidebar' case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");

		String userEmail = ExcelUtility.getCellData(13, 1);
		String userPassword = ExcelUtility.getCellData(13, 2);

		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Opening side bar menu
		myAccount.clickAvater();
		Thread.sleep(2000);
		
		// Check to see if you are already connected and if so, disconnect
		log.info("Checking to see if user is already connected");
		if (myAccount.dropboxConnection().equalsIgnoreCase("DISCONNECT")) {
			log.info("Disconnecting from Dropbox");
			myAccount.clickDropboxDisconnect();
			Thread.sleep(1000);
		}
		
		myAccount.clickDropboxDisconnect();
		log.info("Log into Dropbox");
//		myAccount.logInToService("dropbox");
		ProviderLogInUtility.logInToService(driver, "dropbox");
		
		myAccount.clickAvater();
		Thread.sleep(2000);

		Assert.assertEquals("DISCONNECT", myAccount.dropboxConnection());
	}
	
	@Test(groups = "positive")
	public void disconnectDropbox() throws Exception {
		log.info("Running 'Disconnect from Dropbox from sidebar' case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");

		String userEmail = ExcelUtility.getCellData(13, 1);
		String userPassword = ExcelUtility.getCellData(13, 2);

		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Opening side bar menu
		myAccount.clickAvater();
		Thread.sleep(2000);
		
		// Check to see if you are already connected and if so, disconnect
		log.info("Checking to see if user is already disconnected");
		if (myAccount.dropboxConnection().equalsIgnoreCase("CONNECT")) {
			log.info("Logging into Dropbox");
			myAccount.clickDropboxDisconnect();
//			myAccount.logInToService("dropbox");
			ProviderLogInUtility.logInToService(driver, "dropbox");
			Thread.sleep(1000);
		}
		
		myAccount.clickAvater();
		log.info("Logging out of Dropbox");
		myAccount.clickDropboxDisconnect();

		Thread.sleep(2000);

		Assert.assertEquals("CONNECT", myAccount.dropboxConnection());
	}
	
	@Test(groups = "positive")
	public void connectToGoogleDrive() throws Exception {
		log.info("Running 'Connect to Google Drive from sidebar' case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");

		String userEmail = ExcelUtility.getCellData(13, 1);
		String userPassword = ExcelUtility.getCellData(13, 2);

		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Opening side bar menu
		myAccount.clickAvater();
		Thread.sleep(2000);
		
		// Check to see if you are already connected and if so, disconnect
		log.info("Checking to see if user is already connected");
		if (myAccount.googleConnection().equalsIgnoreCase("DISCONNECT")) {
			log.info("Disconnecting from Google Drive");
			myAccount.clickGoogleDisconnect();
			Thread.sleep(1000);
		}
		
		myAccount.clickGoogleDisconnect();
		log.info("Log into Google Drive");
//		myAccount.logInToService("googledrive");
		ProviderLogInUtility.logInToService(driver, "googledrive");
		
		myAccount.clickAvater();
		Thread.sleep(2000);

		Assert.assertEquals("DISCONNECT", myAccount.googleConnection());
	}
	
	@Test(groups = "positive")
	public void disconnectGoogle() throws Exception {
		log.info("Running 'Disconnect from Google Drive from sidebar' case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");

		String userEmail = ExcelUtility.getCellData(13, 1);
		String userPassword = ExcelUtility.getCellData(13, 2);

		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Opening side bar menu
		myAccount.clickAvater();
		Thread.sleep(2000);
		
		// Check to see if you are already connected and if so, disconnect
		log.info("Checking to see if user is already disconnected");
		if (myAccount.googleConnection().equalsIgnoreCase("CONNECT")) {
			log.info("Logging into Google Drive");
			myAccount.clickGoogleDisconnect();
//			myAccount.logInToService("googledrive");
			ProviderLogInUtility.logInToService(driver, "googledrive");
			Thread.sleep(1000);
		}
		
		myAccount.clickAvater();
		log.info("Logging out of Google Drive");
		myAccount.clickGoogleDisconnect();

		Thread.sleep(2000);

		Assert.assertEquals("CONNECT", myAccount.googleConnection());
	}
	
	@Test(groups = "positive")
	public void connectToOneDrive() throws Exception {
		log.info("Running 'Connect to One Drive from sidebar' case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");

		String userEmail = ExcelUtility.getCellData(13, 1);
		String userPassword = ExcelUtility.getCellData(13, 2);

		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Opening side bar menu
		myAccount.clickAvater();
		Thread.sleep(2000);
		
		// Check to see if you are already connected and if so, disconnect
		log.info("Checking to see if user is already connected");
		if (myAccount.onedriveConnection().equalsIgnoreCase("DISCONNECT")) {
			log.info("Disconnecting from One Drive");
			myAccount.clickOnedriveDisconnect();
			Thread.sleep(1000);
		}
		
		myAccount.clickOnedriveDisconnect();
		log.info("Log into One Drive");
//		myAccount.logInToService("onedrive");
		ProviderLogInUtility.logInToService(driver, "onedrive");
		
		myAccount.clickAvater();
		Thread.sleep(2000);

		Assert.assertEquals("DISCONNECT", myAccount.onedriveConnection());
	}
	
	@Test(groups = "positive")
	public void disconnectOneDrive() throws Exception {
		log.info("Running 'Disconnect from One Drive from sidebar' case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");

		String userEmail = ExcelUtility.getCellData(13, 1);
		String userPassword = ExcelUtility.getCellData(13, 2);

		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Opening side bar menu
		myAccount.clickAvater();
		Thread.sleep(2000);
		
		// Check to see if you are already connected and if so, disconnect
		log.info("Checking to see if user is already disconnected");
		if (myAccount.onedriveConnection().equalsIgnoreCase("CONNECT")) {
			log.info("Logging into One Drive");
			myAccount.clickOnedriveDisconnect();
//			myAccount.logInToService("onedrive");
			ProviderLogInUtility.logInToService(driver, "onedrive");
			Thread.sleep(1000);
		}
		
		myAccount.clickAvater();
		log.info("Logging out of One Drive");
		myAccount.clickOnedriveDisconnect();

		Thread.sleep(2000);

		Assert.assertEquals("CONNECT", myAccount.onedriveConnection());
	}

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
