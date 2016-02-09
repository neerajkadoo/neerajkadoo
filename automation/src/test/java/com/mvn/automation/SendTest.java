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
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageClasses.HomePageFactory;
import pageClasses.SigninPageFactory;
import pageClasses.SendPageFactory;
import utilities.Constants;
import utilities.ExcelUtility;

public class SendTest {
	private WebDriver driver;
	SigninPageFactory signIn;
	HomePageFactory homePage;
	SendPageFactory sendPage;
	// LOGGER DECLARATION//
	static Logger log = Logger.getLogger(SendTest.class);

	@Parameters({ "browserType", "env" })
	@BeforeMethod
	public void setUp(String browser, String envir) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			// http://chromedriver.storage.googleapis.com/index.html
			System.setProperty("webdriver.chrome.driver",
					Constants.Chrome_driver_location);
			driver = new ChromeDriver();
		}
		if (envir.equalsIgnoreCase("stage")) {
			driver.get(Constants.URL);
		} else if (envir.equalsIgnoreCase("prod")) {
			driver.get(Constants.PROD_URL);
		}

		signIn = new SigninPageFactory(driver);
		homePage = new HomePageFactory(driver);
		sendPage = new SendPageFactory(driver);

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		// driver.get(Constants.URL);

	}

	@Test(groups = { "positive", "Smoke" })
	public void sendASingleFile() throws Exception {
		log.info("Running 'Send a single file' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Test");
		sendPage.enterMessage("Generic message");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		sendPage.clickSendBtn();
		log.info("Clicked Send button");
		Thread.sleep(5000);
	}

	@Test(groups = "positive")
	public void sendMultipleFiles() throws Exception {
		log.info("Running 'Send multiple files' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Test");
		sendPage.enterMessage("Generic message");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		Thread.sleep(3000);
		sendPage.clickAddMoreFiles();
		log.info("Clicking ADD MORE FILES button");
		sendPage.addMoreFilesFromDesktop();
		log.info("Uploading a second file from my computer");
		Thread.sleep(3000);
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		sendPage.clickSendBtn();
		log.info("Clicked Send button");
		Thread.sleep(5000);
	}

	@Test(groups = "positive")
	public void removeAFile() throws Exception {
		log.info("Running 'Remove a file' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		Thread.sleep(3000);
		sendPage.clickAddMoreFiles();
		log.info("Clicking ADD MORE FILES button");
		sendPage.addMoreFilesFromDesktop();
		log.info("Uploading a second file from my computer");
		Thread.sleep(3000);
		sendPage.removeFirstFile();
		log.info("Removing the first attached file");
		Thread.sleep(2000);
		log.info("The number of attached files is "
				+ sendPage.getNumberOfFiles());
		Assert.assertEquals(sendPage.getNumberOfFiles(), 1);

		driver.close();
	}

	@Test(groups = "negative")
	public void sendToAnInvalidEmail() throws Exception {
		log.info("Running send a single file test case");

		String invalidEmailError = "Email recipients list contains errors";

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("invalid_email@");
		log.info("Recipient is invalid_email");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		Assert.assertEquals(sendPage.errorMessageText(), invalidEmailError);

		driver.close();
	}

	@Test(groups = "negative")
	public void sendToBlankEmail() throws Exception {
		log.info("Running send a single file test case");

		String blankEmailError = "Please specify at least one email recipient";

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		// sendPage.enterRecipientEmail("");
		log.info("TO field is empty");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		Assert.assertEquals(sendPage.errorMessageText(), blankEmailError);

		driver.close();
	}

	@Test(groups = "positive")
	public void sendWithAccessCode() throws Exception {
		log.info("Running 'Send with access code' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Access Code Test");
		sendPage.enterMessage("Code is 1234");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		Thread.sleep(3000);
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		sendPage.enterAccessCode("1234");
		log.info("Entering access code: 1234");
		sendPage.toggleAccessCode();
		log.info("Hiding the access code");
		sendPage.clickSendBtn();
		log.info("Clicked Send button");
		Thread.sleep(5000);
	}

	@Test(groups = "positive")
	public void sendWithExpiration() throws Exception {
		log.info("Running 'Send with expiration date' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Expiration Date Test");
		sendPage.enterMessage("Setting expiration date");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		Thread.sleep(3000);
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		Thread.sleep(3000);
		sendPage.clickExpirationDatePicker();
		log.info("Opening the calendar");
		sendPage.clickNextMonthButton();
		log.info("Advancing to the next month");
		sendPage.clickFirstDateOfNextMonth();
		log.info("Picking first day of the new month");

		sendPage.clickSendBtn();
		log.info("Clicked Send button");
		Thread.sleep(5000);
	}

	@Test(groups = "positive")
	public void sendWithVerifyRecipientIdentity() throws Exception {
		log.info("Running 'Send with VRI' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Verify Recipient Identity Enabled Test");
		sendPage.enterMessage("VRI is enabled");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		Thread.sleep(3000);
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		Thread.sleep(3000);
		sendPage.toggleVerifyRecipientIdentity();
		log.info("VRI is turned on");
		sendPage.clickSendBtn();
		log.info("Clicked Send button");
		Thread.sleep(5000);
	}

	@Test(groups = "positive")
	public void sendWithDownloadReceipt() throws Exception {
		log.info("Running 'Send with download receipt' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Download Receipt Test");
		sendPage.enterMessage("Dowload receipt is requested");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		Thread.sleep(3000);
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		Thread.sleep(3000);
		sendPage.toggleDownloadReceipt();
		log.info("Return receipt is turned on");
		sendPage.clickSendBtn();
		log.info("Clicked Send button");
		Thread.sleep(5000);
	}

	@Test(groups = { "positive", "Smoke" })
	public void sendWithAllOptions() throws Exception {
		log.info("Running 'Send with all options' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("All Options Enabled Test");
		sendPage.enterMessage("Testing a send with all options enabled");

		// Upload a file
		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		Thread.sleep(3000);

		// Add an access code: 1234
		sendPage.enterAccessCode("1234");
		log.info("Adding access code 1234");
		sendPage.toggleAccessCode();
		log.info("Toggling access code");
		Thread.sleep(2000);

		// Set expiration day for the first of the following month
		sendPage.clickExpirationDatePicker();
		log.info("Opening the calendar");
		sendPage.clickNextMonthButton();
		log.info("Advancing to the next month");
		sendPage.clickFirstDateOfNextMonth();
		log.info("Picking first day of the new month");
		Thread.sleep(2000);

		// Turn VRI on
		sendPage.toggleVerifyRecipientIdentity();
		log.info("VRI is turned on");
		Thread.sleep(2000);

		// Turn download receipt on
		sendPage.toggleDownloadReceipt();
		log.info("Return receipt is turned on");
		Thread.sleep(2000);

		// Click send
		sendPage.clickSendBtn();
		log.info("Clicked Send button");
		Thread.sleep(5000);
	}

	@Test(groups = { "positive", "Smoke" })
	public void sendFromAllStorageProviders() throws Exception {
		log.info("Running 'Send from cloud storage providers' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String newSendEmail = ExcelUtility.getCellData(12, 1);
		String newSendPswd = ExcelUtility.getCellData(12, 2);

		Thread.sleep(2000);

		signIn.userEmail(newSendEmail);
		log.info("Using " + newSendEmail + " for login to Spaces");
		signIn.userPassword(newSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("From different file services");
		sendPage.enterMessage("Adding files from each storage service");

		sendPage.clickHTIcon();
		log.info("Clicking on Hightail icon");
		sendPage.selectAFile();
		log.info("Selecting a file in the list");
		sendPage.clickAddButtonFromServicePicker();
		log.info("Adding file from Hightail");
		sendPage.addFileFromService("dropbox");
		log.info("Adding a file from Dropbox");
		Thread.sleep(1000);
		sendPage.addFileFromService("onedrive");
		log.info("Adding a file from One Drive");
		Thread.sleep(1000);
		sendPage.addFileFromService("googledrive");
		log.info("Adding a file from Google Drive");
		Thread.sleep(1000);

		sendPage.clickNextBtn();
		log.info("Clicked the Next button");

		sendPage.clickSendBtn();
		log.info("Clicked Send button");
		Thread.sleep(5000);
	}

	@Test(groups = "positive")
	public void liteSingleFileHTOauth() throws Exception {
		log.info("Running 'Send a single file with lite account' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();
		log.info("Clicking on Hightail o-auth button");
		signIn.clickoAuthHtl();

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to OAuth window
				log.info("Switched focus to Hightail o-auth window");
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH
						+ Constants.FILE_NAME, "Sheet1");
				String liteSendEmail = ExcelUtility.getCellData(13, 1);
				String liteSendPswd = ExcelUtility.getCellData(13, 2);

				Thread.sleep(2000);
				signIn.clickoAuthHtlEmail();
				signIn.EnteroAuthHtlEmail(liteSendEmail);
				log.info("Spaces send user " + liteSendEmail + " entered");
				signIn.EnteroAuthHtlpwd(liteSendPswd);

				Thread.sleep(1000);
				signIn.clickoAuthHtlsubmit();
				log.info("Hightail o-auth login is successful");
				Thread.sleep(1000);
				log.info("Hightail o-auth accept permission");
				signIn.clickoAuthHtlAccept();

				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(5000);

			}
		}

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Lite Acct Test");
		sendPage.enterMessage("Generic message");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		sendPage.clickSendBtn();
		log.info("Clicked Send button");
		Thread.sleep(5000);
	}

	@Test(groups ="positive")
	public void liteAccessCodePaywall() throws Exception {
		log.info("Running 'Lite account access code paywall' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String liteSendEmail = ExcelUtility.getCellData(13, 1);
		String liteSendPswd = ExcelUtility.getCellData(13, 2);

		Thread.sleep(2000);

		signIn.userEmail(liteSendEmail);
		log.info("Using " + liteSendEmail + " for login to Spaces");
		signIn.userPassword(liteSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Paywall-Access Code Test");
		sendPage.enterMessage("Lite acct access code paywall");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		Thread.sleep(1000);
		sendPage.clickAccessCode();
		log.info("Clicking access code text box");
		Thread.sleep(1000);
		Assert.assertEquals(true, sendPage.isGoBackButtonPresent());
	}

	@Test(groups = { "positive", "Smoke" })
	public void liteExpirationPaywall() throws Exception {
		log.info("Running 'Lite account expiration date paywall' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String liteSendEmail = ExcelUtility.getCellData(13, 1);
		String liteSendPswd = ExcelUtility.getCellData(13, 2);

		Thread.sleep(2000);

		signIn.userEmail(liteSendEmail);
		log.info("Using " + liteSendEmail + " for login to Spaces");
		signIn.userPassword(liteSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Paywall-Expiration Date Test");
		sendPage.enterMessage("Lite acct expiration paywall");

		sendPage.uploadFileToSend();
		log.info("Uploading a file to send");
		Thread.sleep(3000);
		sendPage.clickNextBtn();
		log.info("Clicked the Next button");
		Thread.sleep(3000);
		sendPage.clickExpirationDatePicker();
		log.info("Clicking expiration date picker");
		Thread.sleep(1000);
		Assert.assertEquals(true, sendPage.isGoBackButtonPresent());
	}

	@Test(groups = { "positive", "Smoke" })
	public void liteFileSizePaywall() throws Exception {
		log.info("Running 'Lite acct file size paywall' test case");

		// Click Sign In link
		log.info("Click on Sign In link on Home Page");
		signIn.clickSigninLink();

		ExcelUtility.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME,
				"Sheet1");
		String liteSendEmail = ExcelUtility.getCellData(13, 1);
		String liteSendPswd = ExcelUtility.getCellData(13, 2);

		Thread.sleep(2000);

		signIn.userEmail(liteSendEmail);
		log.info("Using " + liteSendEmail + " for login to Spaces");
		signIn.userPassword(liteSendPswd);
		signIn.clickSignInWithEmail();
		log.info("Login is successful");
		Thread.sleep(3000);

		// Click the Send link in the header
		homePage.clickSendInHeader();
		// Enter recipient email
		sendPage.enterRecipientEmail("prd_newsend@dispostable.com");
		log.info("Recipient is prd_newsend@dispostable.com");
		sendPage.enterSubject("Paywall-File Size Test");
		sendPage.enterMessage("Lite acct file size paywall");

		sendPage.uploadLargeFileToSend();
		log.info("Uploading a 300 MB file to send");
		Thread.sleep(1000);
		Assert.assertEquals(true, sendPage.isGoBackButtonPresent());
	}

	@AfterMethod
	public void afterClass(ITestResult testResult) throws IOException {
		utilities.FullScreenCapture.takeScreenShotOnFailure(testResult, driver);
		driver.close();
	}
}
