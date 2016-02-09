package com.mvn.automation;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
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

public class AdminResetPwdTest {
	private WebDriver driver;
	SigninPageFactory signIn;
	myAccountPageFactory myAccount;
	AdminPageFactory admin;
	// Log4j logger init
	static Logger log = Logger.getLogger(AdminResetPwdTest.class);

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

	@Test(groups = { "pasitive", "Smoke" })
	public void AdminResetPwd() throws Exception {
		log.info("Admin SignIn link, forgot password Test case");
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(7, 1);
		
		//Launch admin login url
		driver.get(Constants.URL+ "/admin-login");	

		// SignIn with Admin account
		admin.enterAdminEmail(userEmail);
		admin.clickResetPwd();
		Thread.sleep(1000);
		Assert.assertEquals(admin.VerifyResetPopupH1(), "Forgot your password?");
		Assert.assertEquals(admin.VerifyResetPopupH2(), "Enter your email associated with your Hightail Spaces account");		
		admin.clickNextBtn();
		Thread.sleep(1000);
		Assert.assertEquals(admin.VerifyConfResetPwdH1(), "Check your inbox");
		Assert.assertEquals(admin.VerifyConfResetPwdH2(), "Click the button in your email to update your password");
		Thread.sleep(1000);

		//***TO DO********CHECK RESET EMAIL AND RESET IT********** 
	}
	
	@Test(groups = { "pasitive", "Smoke" })
	public void AdminResetPwdResend() throws Exception {
		log.info("Admin SignIn link, Resend reset password Test case");
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(7, 1);
		
		//Launch admin login url
		driver.get(Constants.URL+ "/admin-login");	

		// SignIn with Admin account
		admin.enterAdminEmail(userEmail);
		admin.clickResetPwd();
		Thread.sleep(1000);
		Assert.assertEquals(admin.VerifyResetPopupH1(), "Forgot your password?");
		Assert.assertEquals(admin.VerifyResetPopupH2(), "Enter your email associated with your Hightail Spaces account");		
		admin.clickNextBtn();
		Thread.sleep(1000);
		Assert.assertEquals(admin.VerifyConfResetPwdH1(), "Check your inbox");
		Assert.assertEquals(admin.VerifyConfResetPwdH2(), "Click the button in your email to update your password");
		admin.clickResendBtn();
		Thread.sleep(1000);

		//***TO DO********CHECK RESET EMAIL AND RESET IT********** 	
	}
	
	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
