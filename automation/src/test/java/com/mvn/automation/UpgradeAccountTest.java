package com.mvn.automation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageClasses.SigninPageFactory;
import pageClasses.SignupPageFactory;
import pageClasses.UpgradeAccountPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;
import utilities.FullScreenCapture;

public class UpgradeAccountTest {

	private WebDriver driver;
	SigninPageFactory signIn;
	UpgradeAccountPageFactory upgrade;
	SignupPageFactory signUp;
	// Log4j logger init
	static Logger log = Logger.getLogger(UpgradeAccountTest.class);

	// Action class
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
		signIn = new SigninPageFactory(driver);
		upgrade = new UpgradeAccountPageFactory(driver);
		signUp = new SignupPageFactory(driver);
		action = new Actions(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
	}

	@Test(groups = "positive")
	public void upgradeAccount() throws Exception {
		log.info("Running Upgrade user Test case");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String fullName = ExcelUtility.getCellData(1, 0);
		// String email = ExcelUtility.getCellData(1, 1);
		String password = ExcelUtility.getCellData(1, 2);
		// Create Date as current Date in MM DD YYYY HH mm format
		String date = new SimpleDateFormat("MMddyyyyHHmmss").format(new Date());
		// Generate random email using the Date format
		String email = "sel_" + date + "@yopmail.com";

		signUp.clickSignUpForFree();
		log.info("Clicked on Sign in button");
		signUp.fullName(fullName);
		System.out.println("New email generated " + email);
		log.info("User "+email+" is created on Spaces");
		signUp.emailSignup(email);
		signUp.passwordSignup(password);
		signUp.clickSignUpWithEmail();
		log.info("User is successfully signed up");
		Thread.sleep(3000);
		upgrade.closeHelp();
		log.info("Closed Help after new user sign up");
		Thread.sleep(3000);
		upgrade.clickAvater();
		log.info("Click on Avater image");
		Thread.sleep(3000);
		upgrade.clickUpgrade();
		log.info("Click on Upgrade option");
		upgrade.CCDetails();
		log.info("Creedit card info is entered");
		upgrade.clickUpgradeAccount();
		log.info("User is upgraded to Pro");
		Thread.sleep(5000);
		Assert.assertEquals(upgrade.yourPlan(), "PRO PLAN");
	}

	@Test(groups = "positive")
	public void keepProAccount() throws Exception {
		log.info("Running Keep Upgrade pro account Test case");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String fullName = ExcelUtility.getCellData(1, 0);
		// String email = ExcelUtility.getCellData(1, 1);
		String password = ExcelUtility.getCellData(1, 2);
		// Create Date as current Date in MM DD YYYY HH mm format
		String date = new SimpleDateFormat("MMddyyyyHHmmss").format(new Date());
		// Generate random email using the Date format
		String email = "sel_" + date + "@yopmail.com";

		signUp.clickSignUpForFree();
		log.info("CLicked on Sign in user link");
		signUp.fullName(fullName);
		System.out.println("New email generated " + email);
		log.info("User "+email+" is generated for sign up");
		signUp.emailSignup(email);
		signUp.passwordSignup(password);
		signUp.clickSignUpWithEmail();
		log.info("New user is signed up successfully");
		Thread.sleep(3000);
		upgrade.closeHelp();
		Thread.sleep(3000);
		upgrade.clickAvater();
		log.info("Click on Avater image");
		Thread.sleep(3000);
		upgrade.clickUpgrade();
		log.info("Clicked on Upgrade option");
		upgrade.CCDetails();
		log.info("Entered Credit card details");
		upgrade.clickUpgradeAccount();
		log.info("Upgrade account is successful");
		Thread.sleep(5000);
		Assert.assertEquals(upgrade.yourPlan(), "PRO PLAN");
		WebElement toElement = driver.findElement(By.cssSelector(".upgrade"));
		Thread.sleep(2000);
		action.click(toElement).perform();
		Thread.sleep(2000);
		upgrade.clickKeepProButton();
		log.info("CLicked on Keep Pro account button");
	}

	@Test(groups = "positive")
	public void downgradeAccount() throws Exception {
		log.info("Running Upgrade user Test case");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String fullName = ExcelUtility.getCellData(1, 0);
		// String email = ExcelUtility.getCellData(1, 1);
		String password = ExcelUtility.getCellData(1, 2);
		// Create Date as current Date in MM DD YYYY HH mm format
		String date = new SimpleDateFormat("MMddyyyyHHmmss").format(new Date());
		// Generate random email using the Date format
		String email = "sel_" + date + "@yopmail.com";

		signUp.clickSignUpForFree();
		log.info("CLick on Sign up user link");
		signUp.fullName(fullName);
		System.out.println("New email generated " + email);
		log.info("User "+email+" is genareted and entered in email field");
		signUp.emailSignup(email);
		signUp.passwordSignup(password);
		signUp.clickSignUpWithEmail();
		log.info("New user is signed up successfully");
		Thread.sleep(3000);
		upgrade.closeHelp();
		log.info("New user tour is dismissed");
		Thread.sleep(3000);
		upgrade.clickAvater();
		log.info("CLicked on Avatar image");
		Thread.sleep(3000);
		upgrade.clickUpgrade();
		log.info("Click on Upgrade option");
		upgrade.CCDetails();
		log.info("Entered credit card details");
		upgrade.clickUpgradeAccount();
		log.info("User is upgraded to Pro");
		Thread.sleep(5000);
		Assert.assertEquals(upgrade.yourPlan(), "PRO PLAN");
		WebElement toElement = driver.findElement(By.cssSelector(".upgrade"));
		Thread.sleep(2000);
		action.click(toElement).perform();
		log.info("CLicked on Pro acount option");
		Thread.sleep(2000);
		upgrade.clickGoLite();
		log.info("Click on Go Lite option");
		upgrade.clickWorksGreatButton();
		log.info("Clicked on Works great button");
		upgrade.enterFeedback();
		upgrade.clickSubmitButton();
		log.info("Clicked on Downgrade submit button");
		Thread.sleep(5000);
		upgrade.clickAvater();
		log.info("Check user is downgraded and shows as Lite plan");
		Thread.sleep(2000);
		Assert.assertEquals(upgrade.yourPlan(), "LITE PLAN");
	}

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
