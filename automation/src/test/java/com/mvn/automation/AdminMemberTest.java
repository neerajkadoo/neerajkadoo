package com.mvn.automation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class AdminMemberTest {
	private WebDriver driver;
	SigninPageFactory signIn;
	myAccountPageFactory myAccount;
	AdminPageFactory admin;
	// Log4j logger init
	static Logger log = Logger.getLogger(AdminMemberTest.class);
	
	// Use this class for mouse-over actions
	Actions action;

	String subUserName = "Sub User";
	// Create Date as current Date in MM DD YYYY HH mm format
	String date = new SimpleDateFormat("MMddyyyyHHmmss").format(new Date());
	// Generate random email using the Date format
	String subUserEmail = "sel_" + date + "@yopmail.com";
	
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
		driver.get(Constants.ADMIN_URL);
	}

	@Test(groups = { "positive", "Smoke" })
	public void AdminMemberAddSearchDelete() throws Exception {
		log.info("Running AdminMemberAddSearchDelete Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		String userEmail = ExcelUtility.getCellData(7, 1);
		String userPassword = ExcelUtility.getCellData(7, 2);

		// SignIn with Admin account
		admin.enterAdminEmail(userEmail);
		admin.enterAdminPwd(userPassword);
		admin.clickAdminSigninBtn();
		Thread.sleep(3000);
		Assert.assertEquals(admin.VerifyAdminHeader(), "MEMBERS");

		//Add New Member
		admin.clickAddMembers();
		admin.enterMemberName(subUserName);
		log.info("New email generated " + subUserEmail);

		admin.enterMemberEmail(subUserEmail);
		Thread.sleep(2000);
		admin.clickAddMembersBtn();
		Thread.sleep(2000);

		Assert.assertEquals(admin.VerifyConfMsg(), "Everything look okay?");
		admin.clickaddMemberConfBtn();
		Thread.sleep(2000);
		Assert.assertEquals(admin.VerifySuccessMsg(), "1 members added");
		Thread.sleep(2000);
		log.info("Member added to admin");
		
		//Search for added user 
		admin.clickSearchBox();
		admin.enterSearchBox(subUserEmail);
		Thread.sleep(2000);
		log.info("Admin member is searched and found");

		//Click on action icon and cancel invite
		admin.clickActionIcon();
		Thread.sleep(1000);
		admin.clickActionCancel();
		Thread.sleep(1000);
		Assert.assertEquals(admin.VerifyNoUserFnd(), "No users found");
		log.info("Added admin member is deleted");

		//Sign-out from Admin
		admin.clickAdminSignout();		
	}
		
	@Test(groups = { "positive" })
	public void AdminMemberActivity() throws Exception {
		log.info("Running AdminMemberActivity Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		String userEmail = ExcelUtility.getCellData(7, 1);
		String userPassword = ExcelUtility.getCellData(7, 2);

		// SignIn with Admin account
		admin.enterAdminEmail(userEmail);
		admin.enterAdminPwd(userPassword);
		admin.clickAdminSigninBtn();
		Thread.sleep(3000);
		Assert.assertEquals(admin.VerifyAdminHeaderActivity(), "ACTIVITY");
		
		//Click on Activity tab and Verify is Activity page is open
		admin.clickAdminHeaderActivity();		
		Thread.sleep(1000);
		Assert.assertEquals(admin.VerifyActivityTab(), "CREATED BY");

		//Sign-out from Admin
		admin.clickAdminSignout();		
	}

	@Test(groups = { "negative" })
	public void AdminMemberSearchNotFound() throws Exception {
		log.info("Running AdminMemberSearchNotFound Test case");

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		String userEmail = ExcelUtility.getCellData(7, 1);
		String userPassword = ExcelUtility.getCellData(7, 2);

		// SignIn with Admin account
		admin.enterAdminEmail(userEmail);
		admin.enterAdminPwd(userPassword);
		admin.clickAdminSigninBtn();
		Thread.sleep(3000);
		Assert.assertEquals(admin.VerifyAdminHeader(), "MEMBERS");
		
		//Search for invalid user 
		admin.clickSearchBox();
		admin.enterSearchBox("invalid@yopmail.com");
		Thread.sleep(2000);
		log.info("Admin member is searched and no user found");

		//Verify no user is found
		Assert.assertEquals(admin.VerifyNoUserFnd(), "No users found");

		//Sign-out from Admin
		admin.clickAdminSignout();		
	}

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
