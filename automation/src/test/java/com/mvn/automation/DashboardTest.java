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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageClasses.CreateSpacePageFactory;
import pageClasses.SigninPageFactory;
import pageClasses.myAccountPageFactory;
import pageClasses.ShareSpacesFactory;
import pageClasses.DashboardPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;


public class DashboardTest {
	private WebDriver driver;

	SigninPageFactory signIn;
	myAccountPageFactory myAccount;
	CreateSpacePageFactory spaceCreate;
	ShareSpacesFactory shareSpace;
	DashboardPageFactory dashboard;

	static Logger log = Logger.getLogger(DashboardTest.class);

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
		dashboard = new DashboardPageFactory(driver);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
	}

	@Test(groups = { "positive", "Smoke" })
	public void dashboardCreation() throws Exception {
		log.info("dashboardCreation testcase");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);

		signIn.clickSigninLink();
		log.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(2000);

		//Refresh page to get dashboard icon 
		driver.navigate().refresh();
		Thread.sleep(5000);
		
		// Click on dashboard icon
		spaceCreate.openDashboard();
		Thread.sleep(2000);
		
		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {

				// Switch to the new window
				driver.switchTo().window(handle);
				Thread.sleep(3000);
				
				//Verify if dashboard appeared
				Assert.assertEquals(dashboard.VerifySpaceHealth(), "Space Health");
			
				// Switch back to parent window
				driver.close();
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}

		// Logout from account
		// Condition for clicking on default or customize Avatar
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
		myAccount.clicksignOut();
		Thread.sleep(1000);
	}

	@Test(groups = { "positive", "Smoke" })
	public void dashboardHealthCheck() throws Exception {
		log.info("Running dashboardHealthCheck testcase");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		String userEmail = ExcelUtility.getCellData(5, 1);
		String userPassword = ExcelUtility.getCellData(5, 2);

		signIn.clickSigninLink();
		log.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		Thread.sleep(3000);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(2000);

		//Refresh page to get dashboard icon
		driver.navigate().refresh();
		Thread.sleep(5000);
		
		// Click on dashboard icon
		spaceCreate.openDashboard();
		Thread.sleep(2000);
		
		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {

				// Switch to the new window
				driver.switchTo().window(handle);
				Thread.sleep(3000);
				
				//Verify dashboard has positive space health
				log.info("Health Score : " + dashboard.getSpaceHealthScoreNo());				
				Assert.assertTrue (dashboard.getSpaceHealthScoreNo() != null);
			
				// Switch back to parent window
				driver.close();
				driver.switchTo().window(parentHandle);
				Thread.sleep(3000);
			}
		}

		// Logout from account
		// Condition for clicking on default or customize Avatar
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
		myAccount.clicksignOut();
		Thread.sleep(1000);
	}

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
