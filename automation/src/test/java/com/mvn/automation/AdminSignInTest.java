package com.mvn.automation;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
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

import pageClasses.SigninPageFactory;
import pageClasses.myAccountPageFactory;
import pageClasses.AdminPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;

public class AdminSignInTest {
	private WebDriver driver;
	SigninPageFactory signIn;
	myAccountPageFactory myAccount;
	AdminPageFactory admin;
	// Log4j logger init
	static Logger log = Logger.getLogger(AdminSignInTest.class);

	// Use this class for mouse-over actions
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
		myAccount = new myAccountPageFactory(driver);
		signIn = new SigninPageFactory(driver);
		admin = new AdminPageFactory(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
	}

	@Test(groups = { "positive", "Smoke" })
	public void AdminSignInUser() throws Exception {
		log.info("Admin SignIn and Admin console verification Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(7, 1);
		String userPassword = ExcelUtility.getCellData(7, 2);

		// SignIn with Admin account
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

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

		// Verify Admin Plan		
		Thread.sleep(1000);
		Assert.assertEquals(admin.VerifyAdminPlan(), "BUSINESS PLAN - Hightail, Inc.");

		// Click on the Admin Console
		admin.clickadminConsoleBtn();
		Thread.sleep(5000);

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {

				// Switch to the new window
				driver.switchTo().window(handle);
				Thread.sleep(3000);

				// Verify Admin console is open
				Assert.assertEquals(admin.VerifyAdminHeader(), "MEMBERS");

				// Close Admin window
				driver.close();

				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}

		// Logout from Admin account
		myAccount.clicksignOut();
		Thread.sleep(1000);
	}

	
	@Test(groups = { "positive", "Smoke" })
	public void AdminLinkSignIn() throws Exception {
		log.info("Admin SignIn from /admin-login link Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(7, 1);
		String userPassword = ExcelUtility.getCellData(7, 2);

		//Launch Admin login url
		driver.get(Constants.URL+ "/admin-login");	

		// SignIn with Admin account
		admin.enterAdminEmail(userEmail);
		admin.enterAdminPwd(userPassword);
		admin.clickAdminSigninBtn();
		Thread.sleep(3000);

		// Verify Admin console is open
		Assert.assertEquals(admin.VerifyAdminHeader(), "MEMBERS");
		Thread.sleep(1000);

		// Logout from Admin account
		admin.clickAdminSignout();
		Thread.sleep(3000);
	}		

	@Test(groups = { "negative" })
	public void AdminSignInEmpty() throws Exception {
		log.info("Admin SignIn from /admin-login link when no password entered, Test case");

		//Launch Admin login url
		driver.get(Constants.URL+ "/admin-login");	

		//Three times SignIn button click displays error message
		admin.clickAdminSigninBtn();
		admin.clickAdminSigninBtn();
		admin.clickAdminSigninBtn();
		Thread.sleep(1000);

		// Verify error message 
		Assert.assertEquals(admin.VerifyErrOnSignin(), "Hmmm...there's still a problem. Make sure your email address and password are correct.");
	}
	
	@Test(groups = { "negative" })
	public void AdminSignInNoPwd() throws Exception {
		log.info("Admin SignIn from /admin-login link when no password entered, Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(7, 1);

		//Launch Admin login url
		driver.get(Constants.URL+ "/admin-login");	

		// SignIn with Admin account
		admin.enterAdminEmail(userEmail);

		//Three times SignIn button click displays error message
		admin.clickAdminSigninBtn();
		admin.clickAdminSigninBtn();
		admin.clickAdminSigninBtn();
		Thread.sleep(1000);

		// Verify error message 
		Assert.assertEquals(admin.VerifyErrOnSignin(), "Hmmm...there's still a problem. Make sure your email address and password are correct.");
	}		

	@Test(groups = { "negative" })
	public void AdminSignInNoEmail() throws Exception {
		log.info("Admin SignIn from /admin-login link when no Email entered, Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userPassword = ExcelUtility.getCellData(7, 2);

		//Launch Admin login url
		driver.get(Constants.URL+ "/admin-login");

		// SignIn with Admin account
		admin.enterAdminPwd(userPassword);

		//Three times SignIn button click displays error message
		admin.clickAdminSigninBtn();
		admin.clickAdminSigninBtn();
		admin.clickAdminSigninBtn();
		Thread.sleep(1000);

		// Verify error message  
		Assert.assertEquals(admin.VerifyErrOnSignin(), "Hmmm...there's still a problem. Make sure your email address and password are correct.");
	}		

	@Test(groups = { "negative" })
	public void AdminSignInInvalidEmail() throws Exception {
		log.info("Admin SignIn from /admin-login link with invalid email");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = "sel_invalid_email";
		String userPassword = ExcelUtility.getCellData(7, 2);

		//Launch Admin login url
		driver.get(Constants.URL+ "/admin-login");	

		// SignIn with Admin account
		admin.enterAdminEmail(userEmail);
		admin.enterAdminPwd(userPassword);
		Thread.sleep(1000);

		//Three times SignIn button click displays error message
		admin.clickAdminSigninBtn();
		Thread.sleep(1000);
		admin.clickAdminSigninBtn();
		Thread.sleep(1000);
		admin.clickAdminSigninBtn();
		Thread.sleep(1000);

		// Verify error message 
		Assert.assertEquals(admin.VerifyErrOnSignin(), "Hmmm...there's still a problem. Make sure your email address and password are correct.");
	}		

	@Test(groups = { "negative" })
	public void AdminSignInInvalidPwd() throws Exception {
		log.info("Admin SignIn from /admin-login link with invalid password");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(7, 1);
		String userPassword = "2222222";
		
		//Launch Admin login url
		driver.get(Constants.URL+ "/admin-login");	

		// SignIn with Admin account
		admin.enterAdminEmail(userEmail);
		admin.enterAdminPwd(userPassword);
		Thread.sleep(1000);

		//Three times SignIn button click displays error message
		admin.clickAdminSigninBtn();
		Thread.sleep(500);
		admin.clickAdminSigninBtn();
		Thread.sleep(500);
		admin.clickAdminSigninBtn();
		Thread.sleep(1000);

		// Verify error message 
		Assert.assertEquals(admin.VerifyErrOnSignin(), "Hmmm...there's still a problem. Make sure your email address and password are correct.");
	}		
	
	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();

	}
}
