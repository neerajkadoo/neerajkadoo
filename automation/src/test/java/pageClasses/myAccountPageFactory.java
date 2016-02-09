package pageClasses;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mvn.automation.ContentProviderTest;
import com.mvn.automation.SendTest;

import utilities.Constants;
import utilities.ExcelUtility;

public class myAccountPageFactory {

	WebDriver driver;

	static Logger log = Logger.getLogger(ContentProviderTest.class);

	// Initialize webdriver
	public myAccountPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Close the help popup element
	@FindBy(css = ".close-button")
	WebElement closeHelpPopup;

	// Click on Close help popup
	public void closeHelp() {
		closeHelpPopup.click();
	}

	// Default Avatar image element
	// @FindBy(css = ".default") //Only works for prod
	@FindBy(css = "div.button-bar > div.menu > div.ht-person.ng-isolate-scope > div.color-wrapper > img")
	// Use for stage
	WebElement clickAvatar;

	// Click on Avatar image
	public void clickAvater() {
		clickAvatar.click();
	}

	// Web element when user has Avatar image
	@FindBy(css = ".user-avatar")
	WebElement avatarImage;

	// CLick on Avatar image when we have a image uploaded for avatar
	public void clickAvaterImage() {
		avatarImage.click();
	}

	// Upgrade button element
	@FindBy(css = ".upgrade-btn")
	WebElement upgradeButton;

	// Click on Upgrade account button
	public void clickUpgrade() {
		upgradeButton.click();
	}

	// Credit card number element
	@FindBy(name = "cardNumber")
	WebElement CCnumber;
	// CC expiration element
	@FindBy(name = "expiration")
	WebElement CCexpiration;

	// CC CVV number element
	@FindBy(name = "cvv")
	WebElement CVV;

	// CC ZIP elememt
	@FindBy(name = "zip")
	WebElement CCzip;

	// Upgrade my account button element
	@FindBy(css = ".orange")
	WebElement upgradeAccountButton;

	// Click on Upgrade my account button
	public void clickUpgradeAccount() {
		upgradeAccountButton.click();
	}

	// Method for filling CC details
	public void CCDetails() {
		CCnumber.sendKeys(Constants.Credit_card_number);
		CCexpiration.sendKeys(Constants.Credit_card_expiration);
		CVV.sendKeys(Constants.Credit_card_cvv);
		CCzip.sendKeys(Constants.Credit_card_zip);
	}

	// Your plan element
	@FindBy(css = ".yourplan")
	WebElement yourPlan;

	// Check your plan
	public String yourPlan() {
		return yourPlan.getText();
	}

	// Sign out from right rail
	@FindBy(css = ".signout > a:nth-child(1)")
	WebElement signOut;

	// Sign Out
	public void clicksignOut() {
		signOut.click();
	}

	// Change Password element
	@FindBy(css = "div.change")
	WebElement passChange;

	// Click on Password change link
	public void clickPassChange() {
		passChange.click();
	}

	// Current password field element
	@FindBy(css = "input.ng-pristine:nth-child(2)")
	WebElement currentPassword;

	// Enter current password
	public void userCurrentPassword(String userCurrentPassword) {
		currentPassword.sendKeys(userCurrentPassword);
	}

	// New password field element
	@FindBy(css = "input.ng-pristine:nth-child(3)")
	WebElement newPassword;

	// Enter new password
	public void userNewPassword(String userNewPassword) {
		newPassword.sendKeys(userNewPassword);
	}

	// Update password button element
	@FindBy(css = "div.actions:nth-child(2) > button:nth-child(2)")
	WebElement updateButton;

	// Click Update button for change password
	public void clickUpdatePasswordButton() {
		updateButton.click();
	}

	// Forget password element
	@FindBy(css = ".reset-password")
	WebElement resetPassword;

	// CLick on Reset password
	public void resetPass() {
		resetPassword.click();
	}

	// Disconnect from Dropbox service
	@FindBy(css = "tr.ng-scope:nth-child(1) > td:nth-child(3)")
	WebElement dropboxService;

	// Click on dropbox disconnect link
	public void clickDropboxDisconnect() {
		dropboxService.click();
	}

	// Check if dropbox is connected ot disconnected
	public String dropboxConnection() {
		return dropboxService.getText();
	}

	// Disconnect from Google service
	@FindBy(css = "tr.ng-scope:nth-child(2) > td:nth-child(3)")
	WebElement googleService;

	// Click on dropbox disconnect link
	public void clickGoogleDisconnect() {
		googleService.click();
	}

	// Check if google is disconnected
	public String googleConnection() {
		return googleService.getText();
	}

	// Disconnect from Microsoft One Drive service
	@FindBy(css = "tr.ng-scope:nth-child(3) > td:nth-child(3)")
	WebElement onedriveService;

	// Click on Microsoft One Drive disconnect link
	public void clickOnedriveDisconnect() {
		onedriveService.click();
	}

	// Check if Microsoft One Drive is disconnected
	public String onedriveConnection() {
		return onedriveService.getText();
	}

	// Find Dropbox email field
	@FindBy(css = "div.text-input-wrapper >input[name=login_email]")
	public static WebElement dropBoxEmailField;

	// Find Dropbox password field
	@FindBy(css = "div.text-input-wrapper >input[name=login_password]")
	WebElement dropBoxPswdField;

	// Find Dropbox Sign In button
	@FindBy(css = "button.login-button.button-primary")
	WebElement dropBoxSignInButton;

	// Find Dropbox Allow button
	@FindBy(css = "button.auth-button.button-primary")
	WebElement dropBoxAllowButton;

	// Find GoogleDrive email field
	@FindBy(css = "input#Email")
	WebElement googleDriveEmailField;

	// Find Google Next button
	@FindBy(css = "input#next")
	WebElement googleDriveNextButton;

	// Find Google password field
	@FindBy(css = "input#Passwd")
	WebElement googleDrivePswdField;

	// Find Google SignIn button
	@FindBy(css = "input#signIn")
	WebElement googleDriveSignInButton;

	// Find Google Allow button
	@FindBy(css = "button#submit_approve_access")
	WebElement googleDriveAllowButton;

	// Find OneDrive email field
	@FindBy(css = "div#idDiv_PWD_UsernameTb > div > input")
	WebElement oneDriveEmailField;

	// Find OneDrive password field
	@FindBy(css = "div#idDiv_PWD_PasswordTb > div > input")
	WebElement oneDrivePswdField;

	// Find OneDrive Sign In button
	@FindBy(css = "input#idSIButton9")
	WebElement oneDriveSignInButton;

	// Log into external service provider
	public void logInToService(String serviceProvider) throws Exception {

		// Get the handle
		String parentHandle = driver.getWindowHandle();
		// Get all handles
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				// Switch to OAuth window
				log.info("Switched focus to external service login window");
				driver.switchTo().window(handle);

				Thread.sleep(1000);
				ExcelUtility.setExcelFile(Constants.FILE_PATH
						+ Constants.FILE_NAME, "Sheet1");

				switch (serviceProvider.toLowerCase()) {
				case "dropbox":
					String dropBoxEmail = ExcelUtility.getCellData(8, 1);
					String dropBoxPswd = ExcelUtility.getCellData(8, 2);

					Thread.sleep(2000);
					dropBoxEmailField.sendKeys(dropBoxEmail);
					log.info("Dropbox user " + dropBoxEmail + " entered");
					dropBoxPswdField.sendKeys(dropBoxPswd);

					Thread.sleep(1000);
					dropBoxSignInButton.click();
					log.info("Dropbox login is successful");
					Thread.sleep(1000);
					dropBoxAllowButton.click();
					log.info("Dropbox accept permission");
					break;

				case "googledrive":
					String googleEmail = ExcelUtility.getCellData(10, 1);
					String googlePswd = ExcelUtility.getCellData(10, 2);

					Thread.sleep(2000);
					googleDriveEmailField.sendKeys(googleEmail);
					log.info("Google user " + googleEmail + " entered");
					googleDriveNextButton.click();
					Thread.sleep(1000);
					googleDrivePswdField.sendKeys(googlePswd);
					googleDriveSignInButton.click();
					Thread.sleep(1000);
					googleDriveAllowButton.click();
					log.info("GoogleDrive accept permission");
					break;

				case "onedrive":
					String oneDriveEmail = ExcelUtility.getCellData(9, 1);
					String oneDrivePswd = ExcelUtility.getCellData(9, 2);

					Thread.sleep(2000);
					oneDriveEmailField.sendKeys(oneDriveEmail);
					log.info("OneDrive user " + oneDriveEmail + " entered");
					oneDrivePswdField.sendKeys(oneDrivePswd);

					Thread.sleep(1000);
					oneDriveSignInButton.click();
					log.info("OneDrive login is successful");
					break;
				}

				// Switch back to parent window
				driver.switchTo().window(parentHandle);
				Thread.sleep(2000);
			}
		}
	}
}