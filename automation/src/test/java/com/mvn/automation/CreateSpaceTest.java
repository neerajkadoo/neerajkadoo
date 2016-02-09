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
import utilities.SetUp;

public class CreateSpaceTest {

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
	public void createNewSpace() throws Exception {
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

		// To click on Craete A space, click this button
		// spaceCreate.createASpace();

		// This can be used if we want to interact with native file upload
		// window
		// driver.switchTo().activeElement().sendKeys(Constants.File_path_For_Space);
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// Upload file to New space
		spaceCreate.createNewSpace();
		log1.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		log1.info("Entered Space name");
		spaceCreate.uploadFiletoSpace();
		log1.info("Uploaded new files to Spaces");
		Thread.sleep(3000);
		// spaceCreate.spaceDesc();
		// spaceCreate.EnterSpaceDesc();
	}

	@Test(groups = { "positive", "Smoke" })
	public void addMoreFilestoSpace() throws Exception {
		log.info("Running Add more files to space testcase");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		signIn.userEmail(userEmail);
		log.info("Using "+userEmail+" to login to Spaces");
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log.info("Login is Successful");
		Thread.sleep(3000);
		spaceCreate.createNewSpace();
		log.info("Clicked on Create Space button");
		spaceCreate.spaceName();
		spaceCreate.uploadFiletoSpace();
		log.info("Uploaded new files to Spaces");
		Thread.sleep(3000);
		// spaceCreate.spaceDesc();
		// spaceCreate.EnterSpaceDesc();
		spaceCreate.clickAddFileMenu();
		log.info("CLick on Add more files menu option");
		spaceCreate.uploadMoreFiletoSpace();
		log.info("Added more files to Space");
		Thread.sleep(3000);
	}

	@Test(groups = { "positive", "Smoke" })
	public void deleteSpace() throws Exception {
		log.info("Running create new space testcase");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);
		log.info("Click on Sign in Link");
		signIn.clickSigninLink();
		log.info("Using "+userEmail+" to login to Spaces");
		signIn.userEmail(userEmail);
		log.info("Enter password");
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log.info("Click on Sign in with email button");
		Thread.sleep(5000);
		WebElement firstSpace = driver.findElement(By.cssSelector("li.fadeIn"));
		log.info("Moved cursor to the Delete space button");
		action.moveToElement(firstSpace).perform();
		Thread.sleep(3000);
		WebElement delete = driver.findElement(By.cssSelector("div.delete"));
		action.moveToElement(delete).perform();
		Thread.sleep(2000);
		delete.click();
		log.info("Clicking Delete space button");
		spaceCreate.clickDeleteButton();
		Thread.sleep(3000);
	}

	@Test(groups = { "positive", "Smoke" })
	public void deleteSpaceMenu() throws Exception {
		log.info("Running create new space testcase");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log.info("Clicked on Sign in user button");
		signIn.userEmail(userEmail);
		log.info("Using "+userEmail+" to login to Spaces");
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log.info("Login is Successful");
		Thread.sleep(5000);
		spaceCreate.clickFirstSpace();
		log.info("Click on the first space");
		Thread.sleep(3000);
		spaceCreate.clickSpaceMenu();
		log.info("Click on Menu option button");
		spaceCreate.clickDeletemenuOption();
		log.info("Clicked on Delete menu option");
		Thread.sleep(3000);
		Assert.assertTrue(spaceCreate.isDeleteSpaceMsg());
	}

	@Test(groups = { "positive", "Smoke" })
	public void backgroundImageSpace() throws Exception {
		log.info("Running create new space testcase");
		
		//Use Log1 to get the method name in the log file.
		//example: This will write "backgroundImageSpace" name in the log file
		Logger log1 = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getMethodName());
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log1.info("Clicked on Sign in user button");
		signIn.userEmail(userEmail);
		log1.info("Using "+userEmail+" to login to Spaces");
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log1.info("Login is Successful");
		Thread.sleep(5000);
		// Upload file to New space
		log1.info("Uploading new file to Spaces");
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(3000);
		// spaceCreate.spaceDesc();
		// spaceCreate.EnterSpaceDesc();
		log1.info("Upload spaces background image");
		Thread.sleep(3000);
		spaceCreate.uploadSpacesBackgroundImage();
		log1.info("Uploading new image for background");
		Thread.sleep(6000);
		spaceCreate.saveBackgroundImageButton();
		log1.info("Saved background image");
		Thread.sleep(3000);
	}

	@Test(groups = { "positive" })
	public void RemoveBackgroundImageSpace() throws Exception {
		log.info("Running create new space testcase");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");
		// String fullName = ExcelUtility.getCellData(1, 0);
		String userEmail = ExcelUtility.getCellData(2, 1);
		String userPassword = ExcelUtility.getCellData(2, 2);

		signIn.clickSigninLink();
		log.info("Clicked on Sign in user button");
		signIn.userEmail(userEmail);
		log.info("Using "+userEmail+" to login to Spaces");
		signIn.userPassword(userPassword);
		signIn.clickSignInWithEmail();
		log.info("Login is Successful");
		Thread.sleep(3000);

		// Upload file to New space
		log.info("Uploading new file to Spaces");
		spaceCreate.createNewSpace();
		spaceCreate.spaceName();
		spaceCreate.uploadFiletoSpace();
		Thread.sleep(5000);
		// spaceCreate.spaceDesc();
		// spaceCreate.EnterSpaceDesc();
		log.info("Upload spaces background image");

		 //Check if there is previous background image available
//		 if(spaceCreate.isRemoveImageAvailable()){
//		 spaceCreate.removeBackgroundImageSpace();
//		 Thread.sleep(3000);
//		 }
		spaceCreate.uploadSpacesBackgroundImage();
		Thread.sleep(6000);
		spaceCreate.saveBackgroundImageButton();
		log.info("Saved background image");
		Thread.sleep(3000);
		spaceCreate.removeBackgroundImageSpace();
		log.info("Delete background image");
		Thread.sleep(3000);
	}
	
	
	//This method allows you to take a screenshot when a test fails
	/*@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		String destDir = "Screenshots/Failures";
		String fileName = this.getClass().getSimpleName();
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getStatus());
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmssaa");

			// To create folder to store screenshots
			new File(destDir).mkdirs();
			// Set file name with combination of test class name + date time.
			String destFile = fileName + "_" + dateFormat.format(new Date()) + ".png";

			try {
				// Store file at destination folder location
				FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// FileUtils.copyFile(scrFile, new
			// File("//Users//neeraj.kadoo//testScreenShot.jpg"));
		}
	}*/
	
	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();

	}
}
