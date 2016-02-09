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

import pageClasses.SignupPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;
import utilities.FullScreenCapture;

public class SignUpTest {
	private WebDriver driver;
	SignupPageFactory signUp;
	// Log4j logger init
	static Logger log = Logger.getLogger(SignUpTest.class);

	// Use this class for mouseover actions
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
		signUp = new SignupPageFactory(driver);
		action = new Actions(driver);
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(Constants.URL);
	}

	@Test(groups = { "positive", "Smoke" })
	public void signUpNewUser() throws Exception {
		log.info("Running Sign Up New User Test case");
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
		signUp.fullName(fullName);
		log.info("New email generated "+ email);
		System.out.println("New email generated " + email);
		signUp.emailSignup(email);
		signUp.passwordSignup(password);
		signUp.clickSignUpWithEmail();
		log.info("New user "+email+ " is signed up on spaces");
		Thread.sleep(3000);
		// if(signUp.emailExistError()==true){
		// ExcelUtility.setCellData("Fail", 1, 3);
		// }
		// Set the test result in the Excel file
		// ExcelUtility.setCellData("Pass", 1, 3);
		// driver.close();
	}

	@Test(groups = { "negative" })
	public void signUpexistingUser() throws Exception {
		log.info("Running Sign Up Existing User Test case");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String fullName = ExcelUtility.getCellData(1, 0);
		String email = ExcelUtility.getCellData(1, 1);
		String password = ExcelUtility.getCellData(1, 2);

		signUp.clickSignUpForFree();
		signUp.fullName(fullName);
		signUp.emailSignup(email);
		signUp.passwordSignup(password);
		signUp.clickSignUpWithEmail();
		Thread.sleep(3000);
		// Check the error is shown for signing up a registered user
		Assert.assertEquals(signUp.emailExistError(), true);
		// Set the test result in the Excel file
		// ExcelUtility.setCellData("Pass", 1, 3);
		// driver.close();
	}

	@Test(groups = { "negative" })
	public void signUpInvalidEmail() throws Exception {
		log.info("Running Sign Up with Invalid email ");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String fullName = ExcelUtility.getCellData(1, 0);
		String email = "sel_invalid_email";
		String password = ExcelUtility.getCellData(1, 2);

		signUp.clickSignUpForFree();
		signUp.fullName(fullName);
		signUp.emailSignup(email);
		signUp.passwordSignup(password);
		signUp.clickSignUpWithEmail();
		Thread.sleep(3000);
		WebElement toElement = driver.findElement(
				By.cssSelector("form[name=\'signupForm\'] > div.field > div[ng-show=\'errors.signup.email'"));
		action.moveToElement(toElement).perform();
		Thread.sleep(3000);
		// Check the error is shown for signing up with invalid email
		Assert.assertEquals(signUp.emailInvalidEmailExist(), true);
	}

	@Test(groups = { "negative" })
	public void signUpMissingName() throws Exception {
		log.info("Running Sign Up with missing name");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String fullName = "";
		// String email = ExcelUtility.getCellData(1, 1);
		String password = ExcelUtility.getCellData(1, 2);
		// Create Date as current Date in MM DD YYYY HH mm format
		String date = new SimpleDateFormat("MMddyyyyHHmmss").format(new Date());
		// Generate random email using the Date format
		String email = "sel_" + date + "@yopmail.com";

		signUp.clickSignUpForFree();
		signUp.fullName(fullName);
		signUp.emailSignup(email);
		signUp.passwordSignup(password);
		signUp.clickSignUpWithEmail();
		Thread.sleep(3000);
		WebElement toElement = driver.findElement(
				By.cssSelector("form[name=\'signupForm\'] > div.field > div[ng-show=\'errors.signup.name'"));
		action.moveToElement(toElement).perform();
		Thread.sleep(3000);
		// signUp.emailExistError();
		// Check the error is shown for signing up with missing name
		Assert.assertEquals(signUp.errorMissingName(), true);
	}

	@Test(groups = { "negative" })
	public void signUpMissingPassword() throws Exception {
		log.info("Running Sign Up with missing password");
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Sheet1");

		String fullName = ExcelUtility.getCellData(1, 0);
		;
		String email = ExcelUtility.getCellData(1, 1);
		String password = "";

		signUp.clickSignUpForFree();
		signUp.fullName(fullName);
		signUp.emailSignup(email);
		signUp.passwordSignup(password);
		signUp.clickSignUpWithEmail();
		Thread.sleep(3000);
		WebElement toElement = driver.findElement(
				By.cssSelector("form[name=\'signupForm\'] > div.field > div[ng-show=\'errors.signup.password'"));
		action.moveToElement(toElement).perform();
		Thread.sleep(3000);
		// signUp.emailExistError();
		// Check the error is shown for signing up with missing password
		Assert.assertEquals(signUp.ErrorMissingPassword(), true);
	}

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
