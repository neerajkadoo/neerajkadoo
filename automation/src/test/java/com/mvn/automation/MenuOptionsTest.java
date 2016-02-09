package com.mvn.automation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageClasses.CreateSpacePageFactory;
import pageClasses.MenuOptionsPagefactory;
import pageClasses.SigninPageFactory;
import pageClasses.SignupPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;

public class MenuOptionsTest {

	private WebDriver driver;
	SignupPageFactory signUp;
	CreateSpacePageFactory spaceCreate;
	SigninPageFactory signIn;
	MenuOptionsPagefactory menu;
	Actions action;
	// Log4j logger init
	static Logger log = Logger.getLogger(CreateSpacePageFactory.class);
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
		signUp = new SignupPageFactory(driver);
		spaceCreate = new CreateSpacePageFactory(driver);
		signIn = new SigninPageFactory(driver);
		menu = new MenuOptionsPagefactory(driver);
		action = new Actions(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
	}

	@Test(groups = { "positive", "Smoke" })
	public void downloadFiles() throws Exception {
		log.debug("Running Download all files from space testcase");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);
		log.debug("Click on Sign in Link");
		signIn.clickSigninLink();
		log.debug("Enter email");
		signIn.userEmail(userEmail);
		log.debug("Enter password");
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log.debug("Click on Sign in with email button");
		Thread.sleep(5000);
		// Upload file to New space
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(3000);
		menu.clickSpaceMenu();
		menu.clickDownloadFiles();
		Thread.sleep(3000);
	}

	@Test(groups = { "positive", "Smoke" })
	public void addAccessCode() throws Exception {
		log.debug("Running Add accesscode to space testcase");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);
		log.debug("Click on Sign in Link");
		signIn.clickSigninLink();
		log.debug("Enter email");
		signIn.userEmail(userEmail);
		log.debug("Enter password");
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log.debug("Click on Sign in with email button");
		Thread.sleep(5000);
		// Upload file to New space
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(3000);
		menu.clickSpaceMenu();
		menu.clickAccessCode();
		menu.addAccessCode("111111");
		menu.SaveAccessCode();
		Thread.sleep(3000);
	}

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();

	}

}
