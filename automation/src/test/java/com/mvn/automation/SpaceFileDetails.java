package com.mvn.automation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageClasses.CreateSpacePageFactory;
import pageClasses.SigninPageFactory;
import pageClasses.SignupPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;
import utilities.FullScreenCapture;

public class SpaceFileDetails {

	private WebDriver driver;
	SignupPageFactory signUp;
	CreateSpacePageFactory spaceCreate;
	SigninPageFactory signIn;
	Actions action;
	// Log4j logger init
	static Logger log = Logger.getLogger(CreateSpaceTest.class);
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
		action = new Actions(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
	}

	@Test(groups = { "positive", "Smoke" })
	public void approveFile() throws Exception {
		log.info("Running create new space testcase");
		Logger log1 = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getMethodName());
		log1.info("Running Log1");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		
		log1.info("Getting login credentials from excel File");
		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log1.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log1.info("Login is Successful");
		Thread.sleep(3000);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log1.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		log1.info("Entered Space name");
		spaceCreate.uploadFiletoSpace();
		log1.info("Uploaded new files to Spaces");
		Thread.sleep(3000);
		log1.info("Clicking on the first image upladed to space");
		//Move cursor to the First image of the space
		WebElement moveToFirstImage = driver.findElement(By.cssSelector(".file-info"));
		action.moveToElement(moveToFirstImage).perform();
		spaceCreate.clickFirstImage();
		log1.info("Click on File details page options");
		spaceCreate.clickFileDetailsOptions();
		log1.info("Click on Approve file option");
		spaceCreate.clickApproveFile();
		Thread.sleep(3000);
		Assert.assertTrue(spaceCreate.isFileApproved());
		
	}
	
	@Test(groups = { "positive", "Smoke" })
	public void deleteFile() throws Exception {
		log.info("Running create new space testcase");
		Logger log1 = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getMethodName());
		log1.info("Running Log1");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		
		log1.info("Getting login credentials from excel File");
		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log1.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log1.info("Login is Successful");
		Thread.sleep(3000);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log1.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		log1.info("Entered Space name");
		spaceCreate.uploadFiletoSpace();
		log1.info("Uploaded new files to Spaces");
		Thread.sleep(3000);
		log1.info("Clicking on the first image upladed to space");
		//Move cursor to the First image of the space
		WebElement moveToFirstImage = driver.findElement(By.cssSelector(".file-info"));
		action.moveToElement(moveToFirstImage).perform();
		spaceCreate.clickFirstImage();
		log1.info("Click on File details page options");
		spaceCreate.clickFileDetailsOptions();
		log1.info("Click on Approve file option");
		spaceCreate.deleteFile();
		Thread.sleep(3000);
	}
	
	@Test(groups = { "positive", "Smoke" })
	public void addFileVersion() throws Exception {
		log.info("Running create new space testcase");
		Logger log1 = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getMethodName());
		log1.info("Running Log1");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		
		log1.info("Getting login credentials from excel File");
		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log1.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log1.info("Login is Successful");
		Thread.sleep(3000);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log1.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		log1.info("Entered Space name");
		spaceCreate.uploadFiletoSpace();
		log1.info("Uploaded new files to Spaces");
		Thread.sleep(3000);
		log1.info("Clicking on the first image upladed to space");
		//Move cursor to the First image of the space
		WebElement moveToFirstImage = driver.findElement(By.cssSelector(".file-info"));
		action.moveToElement(moveToFirstImage).perform();
		spaceCreate.clickFirstImage();
		log1.info("Click on Add File on page options");
		spaceCreate.clickAddFileOptions();
		log1.info("Add new file version from My computer");
		spaceCreate.addFileVersion();
		Thread.sleep(3000);
		Assert.assertTrue(spaceCreate.isVersionTextAvailable(), "VERSION 2");
		
	}
	
	@Test(groups = { "positive", "Smoke" })
	public void unApproveFile() throws Exception {
		log.info("Running create new space testcase");
		Logger log1 = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getMethodName());
		log1.info("Running Log1");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		
		log1.info("Getting login credentials from excel File");
		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log1.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log1.info("Login is Successful");
		Thread.sleep(3000);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log1.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		log1.info("Entered Space name");
		spaceCreate.uploadFiletoSpace();
		log1.info("Uploaded new files to Spaces");
		Thread.sleep(3000);
		log1.info("Clicking on the first image upladed to space");
		//Move cursor to the First image of the space
		WebElement moveToFirstImage = driver.findElement(By.cssSelector(".file-info"));
		action.moveToElement(moveToFirstImage).perform();
		spaceCreate.clickFirstImage();
		log1.info("Click on File details page options");
		spaceCreate.clickFileDetailsOptions();
		log1.info("Click on Approve file option");
		spaceCreate.clickApproveFile();
		Thread.sleep(3000);
		//Assert.assertTrue(spaceCreate.isFileApproved());
		spaceCreate.clickFileDetailsOptions();
		log1.info("Click on un-approve file option");
		spaceCreate.clickUnapprove();
		Thread.sleep(1000);
	}
	
	@Test(groups = { "positive", "Smoke" })
	public void mentionUser() throws Exception {
		log.info("Running create new space testcase");
		Logger log1 = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getMethodName());
		log1.info("Running Log1");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		
		log1.info("Getting login credentials from excel File");
		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log1.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log1.info("Login is Successful");
		Thread.sleep(3000);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log1.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		log1.info("Entered Space name");
		spaceCreate.uploadFiletoSpace();
		log1.info("Uploaded new files to Spaces");
		Thread.sleep(3000);
		log1.info("Clicking on the first image upladed to space");
		//Move cursor to the First image of the space
		WebElement moveToFirstImage = driver.findElement(By.cssSelector(".file-info"));
		action.moveToElement(moveToFirstImage).perform();
		spaceCreate.clickFirstImage();
		log1.info("Click on image to open the comment field");
		spaceCreate.clickOnImage();
		log1.info("Enter @Mention in the comment field");
		spaceCreate.enterMention();
		log1.info("Click on Post comment button");
		spaceCreate.clickPostComment();
		Thread.sleep(3000);
	}
	
	@Test(groups = { "positive", "Smoke" })
	public void followUp() throws Exception {
		log.info("Running create new space testcase");
		Logger log1 = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getMethodName());
		log1.info("Running Log1");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		
		log1.info("Getting login credentials from excel File");
		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log1.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log1.info("Login is Successful");
		Thread.sleep(3000);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log1.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		log1.info("Entered Space name");
		spaceCreate.uploadFiletoSpace();
		log1.info("Uploaded new files to Spaces");
		Thread.sleep(3000);
		log1.info("Clicking on the first image upladed to space");
		//Move cursor to the First image of the space
		WebElement moveToFirstImage = driver.findElement(By.cssSelector(".file-info"));
		action.moveToElement(moveToFirstImage).perform();
		spaceCreate.clickFirstImage();
		log1.info("Click on image to open the comment field");
		spaceCreate.clickOnImage();
		log1.info("Enter @Mention in the comment field");
		spaceCreate.enterMention();
		log1.info("Select Follow up option");
		spaceCreate.selectFollowUp();
		log1.info("Click on Post comment button");
		spaceCreate.clickPostComment();
		Thread.sleep(3000);
	}
	
	@Test(groups = { "positive", "Smoke" })
	public void addComment() throws Exception {
		log.info("Running create new space testcase");
		Logger log1 = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getMethodName());
		log1.info("Running Log1");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		
		log1.info("Getting login credentials from excel File");
		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log1.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log1.info("Login is Successful");
		Thread.sleep(3000);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log1.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		log1.info("Entered Space name");
		spaceCreate.uploadFiletoSpace();
		log1.info("Uploaded new files to Spaces");
		Thread.sleep(3000);
		log1.info("Clicking on the first image upladed to space");
		//Move cursor to the First image of the space
		WebElement moveToFirstImage = driver.findElement(By.cssSelector(".file-info"));
		action.moveToElement(moveToFirstImage).perform();
		spaceCreate.clickFirstImage();
		log1.info("Click on image to open the comment field");
		spaceCreate.clickOnImage();
		log1.info("Enter @Mention in the comment field");
		spaceCreate.enterComment();
		log1.info("Click on Post comment button");
		spaceCreate.clickPostComment();
		Thread.sleep(3000);
	}
	
	

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
