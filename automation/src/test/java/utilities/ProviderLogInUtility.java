/*
 *  Utility to handle logging into external storage providers.  
 */

package utilities;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProviderLogInUtility {
	
	
	static Logger log = Logger.getLogger(ProviderLogInUtility.class);
	
	public static class HightailOAuthElements {
		public static WebElement hightailEmailField(WebDriver driver) {
			WebElement element = null;
			element = driver.findElement(By.cssSelector("input#ysi_email"));
			return element;
		}
		
		public static WebElement hightailPswdField(WebDriver driver) {
			WebElement element = null;
			element = driver.findElement(By.cssSelector("input#ysi_password"));
			return element;
		}
		
		public static WebElement hightailSignInBtn(WebDriver driver) {
			WebElement element = null;
			element = driver.findElement(By.cssSelector("button.btnLogin"));
			return element;
		}
		
		public static WebElement hightailOAuthAcceptBtn(WebDriver driver) {
			WebElement element= null;
			element = driver.findElement(By.cssSelector("div#btnAccept"));
			return element;
		}
	}

	public static class DropBoxOAuthElements {
		public static WebElement dropBoxEmailField(WebDriver driver){
			WebElement element = null;
			element = driver.findElement(By.cssSelector("div.text-input-wrapper >input[name=login_email]"));
			return element;
		}
		
		public static WebElement dropBoxPswdField(WebDriver driver){
			WebElement element = null;
			element = driver.findElement(By.cssSelector("div.text-input-wrapper >input[name=login_password]"));
			return element;
		}
		
		public static WebElement dropBoxSignInButton(WebDriver driver){
			WebElement element = null;
			element = driver.findElement(By.cssSelector("button.login-button.button-primary"));
			return element;
		}
		
		public static WebElement dropBoxAllowButton(WebDriver driver){
			WebElement element = null;
			element = driver.findElement(By.cssSelector("button.auth-button.button-primary"));
			return element;
		}
	}
	
	public static class GoogleOAuthElements {
		public static WebElement googleDriveEmailField(WebDriver driver){
			WebElement element = null;
			element = driver.findElement(By.cssSelector("input#Email"));
			return element;
		}
		
		public static WebElement googleDriveNextButton(WebDriver driver){
			WebElement element = null;
			element = driver.findElement(By.cssSelector("input#next"));
			return element;
		}
		
		public static WebElement googleDrivePswdField(WebDriver driver){
			WebElement element = null;
			element = driver.findElement(By.cssSelector("input#Passwd"));
			return element;
		}
		
		public static WebElement googleDriveSignInButton(WebDriver driver){
			WebElement element = null;
			element = driver.findElement(By.cssSelector("input#signIn"));
			return element;
		}
		
		public static WebElement googleDriveAllowButton(WebDriver driver){
			WebElement element = null;
			element = driver.findElement(By.cssSelector("button#submit_approve_access"));
			return element;
		}
	}
	
	public static class OneDriveOAuthElements {
		public static WebElement oneDriveEmailField(WebDriver driver) {
			WebElement element = null;
			element = driver.findElement(By.cssSelector("div#idDiv_PWD_UsernameTb > div > input"));
			return element;
		}
		
		public static WebElement oneDrivePswdField(WebDriver driver) {
			WebElement element = null;
			element = driver.findElement(By.cssSelector("div#idDiv_PWD_PasswordTb > div > input"));
			return element;
		}
		
		public static WebElement oneDriveSignInButton(WebDriver driver) {
			WebElement element = null;
			element = driver.findElement(By.cssSelector("input#idSIButton9"));
			return element;
		}
	}

	// Log into external service provider
	public static void logInToService(WebDriver driver, String serviceProvider)
			throws Exception {

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
				case "hightail":
					String hightailEmail = ExcelUtility.getCellData(3, 1);
					String hightailPswd = ExcelUtility.getCellData(3, 2);
					
					Thread.sleep(2000);
					HightailOAuthElements.hightailEmailField(driver).sendKeys(hightailEmail);
					log.info("hightail o-auth user "+ hightailEmail +" entered");
					HightailOAuthElements.hightailPswdField(driver).sendKeys(hightailPswd);
					Thread.sleep(1000);
					HightailOAuthElements.hightailSignInBtn(driver).click();
					log.info("hightail o-auth login is successful");
					Thread.sleep(1000);
					log.info("Hightail o-auth accept permission");
					HightailOAuthElements.hightailOAuthAcceptBtn(driver).click();
					
				case "dropbox":
					String dropBoxEmail = ExcelUtility.getCellData(8, 1);
					String dropBoxPswd = ExcelUtility.getCellData(8, 2);

					Thread.sleep(2000);
//					dropBoxEmailField.sendKeys(dropBoxEmail);
					DropBoxOAuthElements.dropBoxEmailField(driver).sendKeys(dropBoxEmail);
					log.info("Dropbox user " + dropBoxEmail + " entered");
//					dropBoxPswdField.sendKeys(dropBoxPswd);
					DropBoxOAuthElements.dropBoxPswdField(driver).sendKeys(dropBoxPswd);

					Thread.sleep(1000);
//					dropBoxSignInButton.click();
					DropBoxOAuthElements.dropBoxSignInButton(driver).click();
					log.info("Dropbox login is successful");
					Thread.sleep(1000);
//					dropBoxAllowButton.click();
					DropBoxOAuthElements.dropBoxAllowButton(driver).click();
					log.info("Dropbox accept permission");
					break;

				case "googledrive":
					String googleEmail = ExcelUtility.getCellData(10, 1);
					String googlePswd = ExcelUtility.getCellData(10, 2);

					Thread.sleep(2000);
//					googleDriveEmailField.sendKeys(googleEmail);
					GoogleOAuthElements.googleDriveEmailField(driver).sendKeys(googleEmail);
					log.info("Google user " + googleEmail + " entered");
//					googleDriveNextButton.click();
					GoogleOAuthElements.googleDriveNextButton(driver).click();
					Thread.sleep(1000);
//					googleDrivePswdField.sendKeys(googlePswd);
					GoogleOAuthElements.googleDrivePswdField(driver).sendKeys(googlePswd);
//					googleDriveSignInButton.click();
					GoogleOAuthElements.googleDriveSignInButton(driver).click();
					Thread.sleep(1000);
//					googleDriveAllowButton.click();
					GoogleOAuthElements.googleDriveAllowButton(driver).click();
					log.info("GoogleDrive accept permission");
					break;

				case "onedrive":
					String oneDriveEmail = ExcelUtility.getCellData(9, 1);
					String oneDrivePswd = ExcelUtility.getCellData(9, 2);

					Thread.sleep(2000);
//					oneDriveEmailField.sendKeys(oneDriveEmail);
					OneDriveOAuthElements.oneDriveEmailField(driver).sendKeys(oneDriveEmail);
					log.info("OneDrive user " + oneDriveEmail + " entered");
//					oneDrivePswdField.sendKeys(oneDrivePswd);
					OneDriveOAuthElements.oneDrivePswdField(driver).sendKeys(oneDrivePswd);

					Thread.sleep(1000);
//					oneDriveSignInButton.click();
					OneDriveOAuthElements.oneDriveSignInButton(driver).click();
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
