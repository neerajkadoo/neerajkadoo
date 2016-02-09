package pageClasses;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mvn.automation.SendTest;
import utilities.ProviderLogInUtility;

import utilities.Constants;

public class SendPageFactory {
	WebDriver driver;
	WebElement element = null;
	
	static Logger log = Logger.getLogger(SendTest.class);

	public SendPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Find the To field
	@FindBy(css = "input.recipients")
	WebElement toField;

	// Enter the recipient's email
	public void enterRecipientEmail(String email) {
		toField.sendKeys(email);
	}

	// Find the Subject field
	@FindBy(css = "input[name='subject']")
	WebElement subjectField;

	// Enter a subject
	public void enterSubject(String subject) {
		subjectField.sendKeys(subject);
	}

	// Find the Message field
	@FindBy(css = "textarea[name='message']")
	WebElement messageField;

	// Enter a message
	public void enterMessage(String message) {
		messageField.sendKeys(message);
	}

	// Find the Upload from Desktop icon
	@FindBy(css = "i.provider.my-computer>label>input")
	WebElement uploadFrmDesktop;

	// Upload a file from desktop
	public void uploadFileToSend() {
		uploadFrmDesktop.sendKeys(Constants.File_path_For_Space1);
	}
	
	// Upload a 300 MB file from desktop
	public void uploadLargeFileToSend() {
		uploadFrmDesktop.sendKeys(Constants.File_path_For_Large_File);
	}
	
	// Find Hightail icon
	@FindBy(css="i.provider.hightail")
	WebElement hightailIcon;
	
	// Click Hightail icon
	public void clickHTIcon() {
		hightailIcon.click();
	}
	
	// Find Dropbox icon
	@FindBy(css="i.provider.dropbox")
	WebElement dropboxIcon;
	
	// Click Dropbox icon
	public void clickDBIcon() throws Exception {
		dropboxIcon.click();
		ProviderLogInUtility.logInToService(driver, "dropbox");
	}
	
	// Find OneDrive icon
	@FindBy(css="i.provider.onedrive")
	WebElement onedriveIcon;
	
	// Click OneDrive icon
	public void clickODIcon() throws Exception {
		onedriveIcon.click();
		ProviderLogInUtility.logInToService(driver, "onedrive");
	}
	
	// Find GoogleDrive icon
	@FindBy(css="i.provider.googledrive")
	WebElement googledriveIcon;
	
	// Click GoogleDrive icon
	public void clickGDIcon() throws Exception {
		googledriveIcon.click();
		ProviderLogInUtility.logInToService(driver, "googledriver");
	}
	
	// Find second file from list because some providers have folders as the first item
	@FindBy(css="div.explorer>table.table>tbody>tr:nth-of-type(3)")
	WebElement secondFileInList;
	
	// Select the second file because some providers have folders as the first item
	public void selectAFile() {
		secondFileInList.click();
	}
	
	// Find the ADD button in the "Add files" window
	@FindBy(css="div.button.button-choose")
	WebElement addButtonFromFileServicePicker;
	
	// Add the file from file service selector
	public void clickAddButtonFromServicePicker() {
		addButtonFromFileServicePicker.click();
	}
	
	// Method to add a file from different services using the ADD MORE FILES menu
	public void addFileFromService(String provider) throws Exception {
		clickAddMoreFiles();
		switch (provider) {
			case "hightail": 
				addMoreFilesHT();
				break;
			case "dropbox": 
				addMoreFilesDB();
				ProviderLogInUtility.logInToService(driver, provider);
				break;
			case "onedrive":
				addMoreFilesOD();
				ProviderLogInUtility.logInToService(driver, "onedrive");
				break;
			case "googledrive":
				addMoreFilesGD();
				ProviderLogInUtility.logInToService(driver, "googledrive");
				break;
		}
		
		selectAFile();
		clickAddButtonFromServicePicker();
	}

	// Find Add More Files button
	@FindBy(css = "div.opt.open.send")
	WebElement addMoreFilesBtn;

	// Click Add More Files button
	public void clickAddMoreFiles() {
		addMoreFilesBtn.click();
	}

	// Find the My computer label from the ADD MORE FILES menu
	@FindBy(css = "label.opt.add-files-desktop.ng-scope>input")
	WebElement addFilesFromDesktopLabel;

	// Upload a file from My computer label
	public void addMoreFilesFromDesktop() {
		addFilesFromDesktopLabel.sendKeys(Constants.File_path_For_Space2);
	}
	
	// Find the Hightail label from the ADD MORE FILES menu
	@FindBy(css="div.opt.add-files-hightail")
	WebElement hightailLabel;
	
	// Click the HT label
	public void addMoreFilesHT() {
		hightailLabel.click();
	}
	
	// Find the Dropbox label from the ADD MORE FILES menu
	@FindBy(css="div.opt.add-files-dropbox")
	WebElement addFilesFromDropboxLabel;
	
	// Click the DB label
	public void addMoreFilesDB() {
		addFilesFromDropboxLabel.click();
	}
	
	// Find the OneDrive label from the ADD MORE FILES menu
	@FindBy(css="div.opt.add-files-onedrive")
	WebElement oneDriveLabel;
	
	// Click OneDrive label
	public void addMoreFilesOD() {
		oneDriveLabel.click();
	}
	
	// Find Google Drive label from ADD MORE FILES
	@FindBy(css="div.opt.add-files-googledrive")
	WebElement googleDriveLabel;
	
	// Click Google Drive label
	public void addMoreFilesGD() {
		googleDriveLabel.click();
	}

	// Find the list of remove icons for attached files
	@FindBy(css = "div.files-state>ul>li>div.remove.anim-fade")
	List<WebElement> numberOfAttachedFiles;

	// Remove the first file in the list
	public void removeFirstFile() {
		for (WebElement file : numberOfAttachedFiles) {
			file.click();
			break;
		}
	}
	
	// Get the number of attached files
	public int getNumberOfFiles() {
		return numberOfAttachedFiles.size();
	}

	// Find Start Over button
	@FindBy(css = "div[class='start-over anim-fade']")
	WebElement startOverBtn;

	// Click Start Over button
	public void clickStartOverBtn() {
		startOverBtn.click();
	}
	
	// Find Next button
	@FindBy(css = "div[class='next anim-fade']")
	WebElement nextBtn;

	// Click Next button
	public void clickNextBtn() {
		nextBtn.click();
	}

	// Find Send button
	@FindBy(css = "div.next")
	WebElement sendBtn;

	// Click the Send button
	public void clickSendBtn() {
		sendBtn.click();
	}

	// Find Access Code text box
	@FindBy(css = "div.pwd-display>input")
	WebElement accessCodeTextBox;

	// Enter an Access Code
	public void enterAccessCode(String accessCode) {
		accessCodeTextBox.sendKeys(accessCode);
	}

	// Remove the Access Code
	public void removeAccessCode() {
		accessCodeTextBox.clear();
	}

	// Find Access Code toggle
	@FindBy(css = "span.pwd-toggle")
	WebElement accessCodeDisplayToggle;

	// Hide/Show Access Code
	public void toggleAccessCode() {
		accessCodeDisplayToggle.click();
	}

	public void getAccessCodeStatus() {
		accessCodeDisplayToggle.getText();
	}
	
	// Find Access Code box with a lite account
	@FindBy(css="div.protect-with-access-code.ng-scope>span")
	WebElement liteAcctAccessCodeField;
	
	// Click the field to encounter the paywall
	public void clickAccessCode() {
		liteAcctAccessCodeField.click();
	}

	// Find expiration date picker
	@FindBy(css = "div.expiration > label")
	WebElement expirationPicker;

	// Click expiration date picker
	public void clickExpirationDatePicker() {
		expirationPicker.click();
	}

	// Find the "Next Month" button in the calendar
	@FindBy(css = "div.pika-title >button.pika-next")
	WebElement nextMonthButton;
	
	// Click Next Month button
	public void clickNextMonthButton() {
		nextMonthButton.click();
	}
	
	// Find the date elements in the calendar
	@FindBy(css = "div.pika-lendar>table>tbody>tr>td>button")
	List<WebElement> calendarDates;

	// Click first date
	public void clickFirstDateOfNextMonth() {
		for (int i = 0; i < 1; i++) {
			calendarDates.get(i).click();
		}
	}
	
	// Find verify recipient checkbox
	@FindBy(css = "div.verify-recipient")
	WebElement verifyRecipientIdentity;

	// Toggle VRI setting
	public void toggleVerifyRecipientIdentity() {
		verifyRecipientIdentity.click();
	}

	// Find download receipt checkbox
	@FindBy(css = "div.return-receipt")
	WebElement downloadReceiptCheckbox;

	// Toggle Download Receipt
	public void toggleDownloadReceipt() {
		downloadReceiptCheckbox.click();
	}

	// Find error message for invalid recipients
	@FindBy(css = "div.recipient-error.anim-fade>h2")
	WebElement emailErrorMessage;

	// Verify if the error message is present
	public boolean errorMessagePresent() {
		return emailErrorMessage.isDisplayed();
	}
	
	// Return the error message text
	public String errorMessageText() {
		return emailErrorMessage.getText();
	}
	
	// Find "OK, GO BACK" button from paywall
	@FindBy(css="div.actions.can-upgrade > div.button.ok")
	WebElement goBackButton;
	
	// Verify if the button is present, i.e. paywall reached
	public boolean isGoBackButtonPresent() {
		return goBackButton.isDisplayed();
	}
	
	// Find "UPGRADE TO PRO" button from paywall
	@FindBy(css="div.button.upgrade.ng-scope")
	WebElement upgradeFrmPaywallBtn;
	
	// Click "UPGRADE TO PRO" button
	public void clickUpgradeFrmPaywall() {
		upgradeFrmPaywallBtn.click();
	}
}
