package com.mvn.automation;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageClasses.CreateSpacePageFactory;
import pageClasses.ShareSpacesFactory;
import pageClasses.SigninPageFactory;
import pageClasses.myAccountPageFactory;
import pageClasses.FilePickerPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;

public class CreateSpaceViaOauthTest {
	
	private WebDriver driver;
	SigninPageFactory signIn;
	myAccountPageFactory myAccount;
	CreateSpacePageFactory spaceCreate;
	ShareSpacesFactory shareSpace;
	FilePickerPageFactory filePicker;
	static Logger log = Logger.getLogger(CreateSpaceViaOauthTest.class);

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
		signIn = new SigninPageFactory(driver);
		spaceCreate = new CreateSpacePageFactory(driver);
		myAccount = new myAccountPageFactory(driver);
		filePicker = new FilePickerPageFactory(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
	}

	
	@Test(groups = { "positive", "Smoke" })
	public void createSpaceDropboxOauth() throws Exception {
		log.info("Running createSpaceDropboxOauth Testcase");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);

		// Sign-in with professional user
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
			
		//Create a space via Dropbox oAuth
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();		
		spaceCreate.clickDropboxUpload();		

		// Get the handle to Dropbox window
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				log.info("Switched focus to Dropbox oAuth window");
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
				String dropboxEmail = ExcelUtility.getCellData(8, 1);
				String dropboxPwd = ExcelUtility.getCellData(8, 2);

				//SignIn in Dropbox oAuth							
				signIn.clickDropboxOauthEmail();
				signIn.EnterDropboxEmail(dropboxEmail);
				signIn.EnterDropboxPwd(dropboxPwd);
				Thread.sleep(2000);				
				signIn.clickDropboxSignIn();
				log.info("Dropbox oAuth is successful");
				Thread.sleep(1000);
				signIn.clickDropboxAllow();
				log.info("Dropbox oAuth permission accepted");
				
				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}

		//Select Dropbox file to upload
		filePicker.SelectFile();
		filePicker.clickAddFilePickerBtn();

		// Signing out of the account
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
	}

	
	@Test(groups = { "positive", "Smoke" })
	public void createSpaceGoogleOauth() throws Exception {
		log.info("Running createSpaceGoogleOauth Testcase");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);

		// Sign-in with professional user
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
			
		//Create a space via Google oAuth
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();		
		spaceCreate.clickGoogleUpload();		

		// Get the handle to Google Oauth window
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				log.info("Switched focus to Google oAuth window");
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
				String googleEmail = ExcelUtility.getCellData(10, 1);
				String googlePwd = ExcelUtility.getCellData(10, 2);

				//SignIn in Google oAuth							
				signIn.clickGoogleOauthEmail();
				signIn.EnterGoogleEmail(googleEmail);
				signIn.clickgoogleOauthNextBtn();
				signIn.EnterGooglePwd(googlePwd);
				Thread.sleep(2000);				
				signIn.clickGoogleSignIn();
				log.info("Google oAuth is successful");
				Thread.sleep(1000);
				signIn.clickGoogleAllow();
				log.info("Google oAuth permission accepted");
				
				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}

		//Select Google Drive file to upload
		filePicker.SelectFile();
		filePicker.clickAddFilePickerBtn();

		// Signing out of the account
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
	}

	
	@Test(groups = { "positive", "Smoke" })
	public void createSpaceMicrosoftOauth() throws Exception {
		log.info("Running createSpaceMicrosoftOauth Testcase");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);

		// Sign-in with professional user
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
			
		//Create a space via Microsoft oAuth
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();		
		spaceCreate.clickOneDriveUpload();		

		// Get the handle to Microsoft Oauth window
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				log.info("Switched focus to Microsoft One Drive oAuth window");
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
				String microsoftEmail = ExcelUtility.getCellData(9, 1);
				String microsoftPwd = ExcelUtility.getCellData(9, 2);

				//SignIn in Google oAuth							
				signIn.clickMicrosoftOauthEmail();
				signIn.EnterMicrosoftEmail(microsoftEmail);
				signIn.EnterMicrosoftPwd(microsoftPwd);
				Thread.sleep(2000);				
				signIn.clickMicrosoftSignIn();
				log.info("Microsoft One Drive oAuth is successful");
				Thread.sleep(1000);
				
				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}
		//Select Microsoft One Drive file to upload
		filePicker.SelectFile();
		filePicker.clickAddFilePickerBtn();

		// Signing out of the account
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
	}

	
	@Test(groups = { "positive" })
	public void disconnectDropboxOauth() throws Exception {
		log.info("Running disconnectDropboxOauth Testcase");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);

		// Sign-in with professional user
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
		
		//Disconnect from Dropbox Oauth in case of image avatar OR default avatar
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


		// Disconnect from dropbox service if already connected.
		if (myAccount.dropboxConnection() == "DISCONNECT" ){			
			myAccount.clickDropboxDisconnect();
		}	
	
		// Signing out of the account in case of image avatar OR default avatar
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
	}	

	
	@Test(groups = { "positive" })
	public void disconnectGoogleOauth() throws Exception {
		log.info("Running disconnectGoogleOauth Testcase");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);

		// Sign-in with professional user
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
		
		//Disconnect from Google Oauth in case of image avatar OR default avatar
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

		// Disconnect from google service if already connected.
		if (myAccount.googleConnection() != "CONNECT" ){			
			myAccount.clickGoogleDisconnect();
		}

		// Signing out of the account in case of image avatar OR default avatar
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
	}	

	
	@Test(groups = { "positive" })
	public void disconnectMicrosoftOauth() throws Exception {
		log.info("Running disconnectMicrosoftOauth Testcase");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);

		// Sign-in with professional user
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
		
		//Disconnect from Microsoft One Drive Oauth in case of image avatar OR default avatar
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

		// Disconnect from Microsoft One Drive service if already connected.
		if (myAccount.onedriveConnection() != "CONNECT"){
			myAccount.clickOnedriveDisconnect();
			Thread.sleep(3000);
		}

		// Signing out of the account in case of image avatar OR default avatar
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
	}	

	
	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
