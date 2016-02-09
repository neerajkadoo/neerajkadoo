package com.mvn.automation;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageClasses.CreateSpacePageFactory;
import pageClasses.SigninPageFactory;
import pageClasses.myAccountPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;
import utilities.FullScreenCapture;

public class OAuth_signInTest {

	private WebDriver driver;
	SigninPageFactory oAuth_signIn;
	CreateSpacePageFactory spaceCreate;
	myAccountPageFactory myAccount;
	// Log4j logger init
	static Logger log = Logger.getLogger(OAuth_signInTest.class);

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
		oAuth_signIn = new SigninPageFactory(driver);
		spaceCreate = new CreateSpacePageFactory(driver);
		myAccount = new myAccountPageFactory(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
	}

	@Test(groups = { "negative" })
	public void oAuth_noPwdSignIn() throws Exception {
		log.info("oAuth higtail Sign In without password");

		// CLicking Sign-up for Free button
		oAuth_signIn.clickSigninLink();
		oAuth_signIn.clickoAuthHtl();

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
				String oAuthHtlEmail = ExcelUtility.getCellData(3, 1);
				String password = "";

				Thread.sleep(2000);
				oAuth_signIn.EnteroAuthHtlEmail(oAuthHtlEmail);
				oAuth_signIn.EnteroAuthHtlpwd(password);
				oAuth_signIn.clickoAuthHtlsubmit();
				Thread.sleep(2000);

				// Check the error is shown
				Assert.assertEquals(oAuth_signIn.oAuthSigninErrorExist(), true);
				Thread.sleep(2000);

				// Close oAuth window
				driver.close();
				Thread.sleep(2000);

				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}
		driver.switchTo().window(parentHandle);
		Thread.sleep(2000);
	}

	@Test(groups = { "negative" })
	public void oAuth_noEmailSignIn() throws Exception {
		log.info("oAuth higtail Sign In without Email");

		// Clicking Sign-up for Free button
		oAuth_signIn.clickSigninLink();
		oAuth_signIn.clickoAuthHtl();

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
				String email = "";
				String oAuthHtlpwd = ExcelUtility.getCellData(3, 2);
				Thread.sleep(2000);

				// Fill credentials on oAuth page
				oAuth_signIn.EnteroAuthHtlEmail(email);
				oAuth_signIn.EnteroAuthHtlpwd(oAuthHtlpwd);
				oAuth_signIn.clickoAuthHtlsubmit();
				Thread.sleep(2000);

				// Check the error is shown
				Assert.assertEquals(oAuth_signIn.oAuthNoemailErrorExist(), true);
				Thread.sleep(2000);

				// Close oAuth window
				driver.close();
				Thread.sleep(2000);

				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}
		driver.switchTo().window(parentHandle);
		Thread.sleep(2000);
	}

	@Test(groups = { "negative" })
	public void oAuth_incorrectPwdSignIn() throws Exception {
		log.info("oAuth higtail Sign In with incorrect password");

		// CLicking Sign-up for Free button
		oAuth_signIn.clickSigninLink();
		oAuth_signIn.clickoAuthHtl();

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
				String oAuthHtlEmail = ExcelUtility.getCellData(3, 1);
				String password = "222222";
				Thread.sleep(2000);

				// Fill credentials on oAuth page
				oAuth_signIn.EnteroAuthHtlEmail(oAuthHtlEmail);
				oAuth_signIn.EnteroAuthHtlpwd(password);
				oAuth_signIn.clickoAuthHtlsubmit();
				Thread.sleep(2000);

				// Check the error is shown
				Assert.assertEquals(oAuth_signIn.oAuthSigninErrorExist(), true);
				Thread.sleep(2000);

				// Close oAuth window
				driver.close();
				Thread.sleep(2000);

				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}
		driver.switchTo().window(parentHandle);
		Thread.sleep(2000);
	}

	@Test(groups = { "negative" })
	public void oAuth_incorrectEmailSignIn() throws Exception {
		log.info("oAuth higtail Sign In with incorrect Email");

		// Clicking Sign-up for Free button
		oAuth_signIn.clickSigninLink();
		oAuth_signIn.clickoAuthHtl();

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
				String email = "ras.1yopmail.com";
				String oAuthHtlpwd = ExcelUtility.getCellData(3, 2);
				Thread.sleep(2000);

				// Fill credentials on oAuth page
				oAuth_signIn.EnteroAuthHtlEmail(email);
				oAuth_signIn.EnteroAuthHtlpwd(oAuthHtlpwd);
				oAuth_signIn.clickoAuthHtlsubmit();
				Thread.sleep(2000);

				// Check the error is shown
				Assert.assertEquals(oAuth_signIn.oAuthNoemailErrorExist(), true);
				Thread.sleep(2000);

				// Close oAuth window
				driver.close();
				Thread.sleep(2000);

				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}
		driver.switchTo().window(parentHandle);
		Thread.sleep(2000);
	}

	@Test(groups = { "negative" })
	public void oAuth_nonExistingEmailSignIn() throws Exception {
		log.info("oAuth higtail Sign In with non-existing Email");

		// Clicking Sign-up for Free button
		oAuth_signIn.clickSigninLink();
		oAuth_signIn.clickoAuthHtl();

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
				String email = "ras.100001@yopmail.com";
				String oAuthHtlpwd = ExcelUtility.getCellData(3, 2);
				Thread.sleep(2000);

				// Fill credentials on oAuth page
				oAuth_signIn.EnteroAuthHtlEmail(email);
				oAuth_signIn.EnteroAuthHtlpwd(oAuthHtlpwd);
				oAuth_signIn.clickoAuthHtlsubmit();
				Thread.sleep(2000);

				// Check the error is shown
				Assert.assertEquals(oAuth_signIn.oAuthNoemailErrorExist(), true);
				Thread.sleep(2000);

				// Close oAuth window
				driver.close();
				Thread.sleep(2000);

				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}
		driver.switchTo().window(parentHandle);
		Thread.sleep(2000);
	}

	@Test(groups = { "positive", "Smoke" })
	public void oAuth_validSignIn() throws Exception {
		log.info("HIghtail oAuth Valid Sign in user Test case");

		// CLicking Signup for Free button
		log.info("CLick on Sign in Link on Home page");
		oAuth_signIn.clickSigninLink();
		log.info("Clicking on Hightail o-auth button");
		oAuth_signIn.clickoAuthHtl();

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				log.info("Switched focus to Hightail o-auth window");
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
				String oAuthHtlEmail = ExcelUtility.getCellData(3, 1);
				String oAuthHtlpwd = ExcelUtility.getCellData(3, 2);

				Thread.sleep(2000);
				oAuth_signIn.clickoAuthHtlEmail();
				oAuth_signIn.EnteroAuthHtlEmail(oAuthHtlEmail);
				log.info("hightail o-auth user "+oAuthHtlEmail+" entered");
				oAuth_signIn.EnteroAuthHtlpwd(oAuthHtlpwd);

				Thread.sleep(1000);
				oAuth_signIn.clickoAuthHtlsubmit();
				log.info("hightail o-auth login is successful");
				Thread.sleep(1000);
				log.info("Hightail o-auth accept permission");
				oAuth_signIn.clickoAuthHtlAccept();

				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(5000);

				// Signing out of the account
				myAccount.clickAvater();
				Thread.sleep(1000);
				myAccount.clicksignOut();
				Thread.sleep(1000);
			}
		}
	}
	
	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
