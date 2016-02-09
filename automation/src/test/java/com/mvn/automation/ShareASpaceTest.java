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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageClasses.CreateSpacePageFactory;
import pageClasses.SigninPageFactory;
import pageClasses.myAccountPageFactory;
import pageClasses.ShareSpacesFactory;
import utilities.Constants;
import utilities.ExcelUtility;

public class ShareASpaceTest {

	private WebDriver driver;
	SigninPageFactory signIn;
	myAccountPageFactory myAccount;
	CreateSpacePageFactory spaceCreate;
	ShareSpacesFactory shareSpace;
	// Log4j logger init
	static Logger log = Logger.getLogger(SignInTest.class);
	// Create Date as current Date in MM DD YYYY HH mm ss format
	String date = new SimpleDateFormat("MMddyyyyHHmmss").format(new Date());
	// Generate random email using the Date format
	String email = "sel_" + date + "@yopmail.com";

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
		spaceCreate = new CreateSpacePageFactory(driver);
		shareSpace = new ShareSpacesFactory(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		// driver.get(Constants.URL);
	}

	@Test(groups = "positive")
	public void shareAspace() throws Exception {
		log.info("Running Share A Space Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);
		String recptxlsEmail = ExcelUtility.getCellData(6, 1);
		String shareMsg = ExcelUtility.getCellData(6, 2);

		// Sign-in
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();
		// Create a Space
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(2000);
		// spaceCreate.spaceDetails();
		// spaceCreate.spaceDesc();
		// Thread.sleep(7000);

		// Share above created space
		shareSpace.shareBtnClick();
		Thread.sleep(3000);
		// Getting dynamic element attribute
//		WebElement rcptEmail = driver.findElement(By.xpath("//*[starts-with(@id,'as-input-')]"));
//		rcptEmail.sendKeys(recptxlsEmail);
		shareSpace.enterRcptEmail(recptxlsEmail);
		shareSpace.enterShareMsg(shareMsg);
		shareSpace.sendShareBtnClick();
		// Getting success message occur only for few seconds
		WebElement spaceShared = driver.findElement(By.className("shared"));
		Assert.assertEquals(spaceShared.isDisplayed(), true);
		Thread.sleep(4000);

		// Copy shared space link
		// to-do

		// Sign-out with sharer's account
		myAccount.clickAvater();
		Thread.sleep(1000);
		myAccount.clicksignOut();
		Thread.sleep(1000);

/*		// Check for share email
		// ---TO-DO-LATER---

		// Sign-in with sharee's account
		signIn.clickSigninLink();
		signIn.userEmail(recptxlsEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Verify that sharee sees the shared spaces
		// --to-do--

		// Sign-out with sharee's account
		myAccount.clickAvater();
		Thread.sleep(1000);
		myAccount.clicksignOut();
		Thread.sleep(1000);*/
	}
	
	@Test(groups = "positive")
	public void shareAWithApprvDwnldCmmnt() throws Exception {
		log.info("Running Share A Space Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);
		String recptxlsEmail = ExcelUtility.getCellData(6, 1);
		String shareMsg = ExcelUtility.getCellData(6, 2);

		// Sign-in
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();
		// Create a Space
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(2000);
		// spaceCreate.spaceDetails();
		// spaceCreate.spaceDesc();
		// Thread.sleep(7000);

		// Share above created space
		shareSpace.shareBtnClick();
		Thread.sleep(3000);
		// Getting dynamic element attribute
//		WebElement rcptEmail = driver.findElement(By.xpath("//*[starts-with(@id,'as-input-')]"));
//		rcptEmail.sendKeys(recptxlsEmail);
		shareSpace.enterRcptEmail(recptxlsEmail);
		shareSpace.enterShareMsg(shareMsg);
		shareSpace.selectApproveDwnldComment();
		shareSpace.sendShareBtnClick();
		// Getting success message occur only for few seconds
		WebElement spaceShared = driver.findElement(By.className("shared"));
		Assert.assertEquals(spaceShared.isDisplayed(), true);
		Thread.sleep(4000);

		// Copy shared space link
		// to-do

		// Sign-out with sharer's account
		myAccount.clickAvater();
		Thread.sleep(1000);
		myAccount.clicksignOut();
		Thread.sleep(1000);

/*		// Check for share email
		// ---TO-DO-LATER---

		// Sign-in with sharee's account
		signIn.clickSigninLink();
		signIn.userEmail(recptxlsEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Verify that sharee sees the shared spaces
		// --to-do--

		// Sign-out with sharee's account
		myAccount.clickAvater();
		Thread.sleep(1000);
		myAccount.clicksignOut();
		Thread.sleep(1000);*/
	}
	
	@Test(groups = "positive")
	public void shareWithCommentOnly() throws Exception {
		log.info("Running Share A Space Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);
		String recptxlsEmail = ExcelUtility.getCellData(6, 1);
		String shareMsg = ExcelUtility.getCellData(6, 2);

		// Sign-in
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();
		// Create a Space
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(2000);
		// spaceCreate.spaceDetails();
		// spaceCreate.spaceDesc();
		// Thread.sleep(7000);

		// Share above created space
		shareSpace.shareBtnClick();
		Thread.sleep(3000);
		// Getting dynamic element attribute
//		WebElement rcptEmail = driver.findElement(By.xpath("//*[starts-with(@id,'as-input-')]"));
//		rcptEmail.sendKeys(recptxlsEmail);
		shareSpace.enterRcptEmail(recptxlsEmail);
		shareSpace.enterShareMsg(shareMsg);
		shareSpace.selectCommentOnly();
		shareSpace.sendShareBtnClick();
		// Getting success message occur only for few seconds
		WebElement spaceShared = driver.findElement(By.className("shared"));
		Assert.assertEquals(spaceShared.isDisplayed(), true);
		Thread.sleep(4000);

		// Copy shared space link
		// to-do

		// Sign-out with sharer's account
		myAccount.clickAvater();
		Thread.sleep(1000);
		myAccount.clicksignOut();
		Thread.sleep(1000);

/*		// Check for share email
		// ---TO-DO-LATER---

		// Sign-in with sharee's account
		signIn.clickSigninLink();
		signIn.userEmail(recptxlsEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Verify that sharee sees the shared spaces
		// --to-do--

		// Sign-out with sharee's account
		myAccount.clickAvater();
		Thread.sleep(1000);
		myAccount.clicksignOut();
		Thread.sleep(1000);*/
	}

	//@Test(groups = "positive") //This is TO do test, we have a prob wiht Slack as it is not allowing us to 
	//use the pop-up for automation.
	public void shareAspaceonSlack() throws Exception {
		log.info("Running Share A Space on Slack Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);
		String recptxlsEmail = ExcelUtility.getCellData(6, 1);
		String shareMsg = ExcelUtility.getCellData(6, 2);

		// Sign-in
		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();
		// Create a Space
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(2000);

		// Share above created space
		shareSpace.shareBtnClick();
		shareSpace.clickSlackButton();

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to the new window
				driver.switchTo().frame(handle);

				Thread.sleep(5000);
				//Enter Slack Team name
				shareSpace.enterSlackTeamName("HTQA");
			}
		}
		// Getting dynamic element attribute
		// WebElement rcptEmail =
		// driver.findElement(By.xpath("//*[starts-with(@id,'as-input-')]"));
		// rcptEmail.sendKeys(recptxlsEmail);
		// shareSpace.enterShareMsg(shareMsg);
		// shareSpace.sendShareBtnClick();
		// // Getting success message occur only for few seconds
		// WebElement spaceShared = driver.findElement(By.className("shared"));
		// Assert.assertEquals(spaceShared.isDisplayed(), true);
	}

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
