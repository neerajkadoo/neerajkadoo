package pageClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.javascript.host.media.rtc.webkitRTCPeerConnection;

import utilities.Constants;

public class CreateSpacePageFactory {

	WebDriver driver;
	Actions action;
	static Logger log = Logger.getLogger(CreateSpacePageFactory.class);
	// Create a space button on new login
	@FindBy(xpath = ".//*[@id='ng-app']/body/div[1]/div[1]/div[3]/div/ul[1]/li[1]/div/div/input")
	WebElement createASpace;

	// Click on Create A space button for newly signed up user
	public void createASpace() {
		log.info("Click on Create a Space button");
		createASpace.click();
	}

	// Create new space button on existing login and space
	@FindBy(css = "div.create-space-menu.ng-scope")
	WebElement createNewSpace;

	// Click on Create New space button for user who has a space
	public void createNewSpace() {
		createNewSpace.click();
	}

	// Upload a file to space
	@FindBy(css = "i.provider.my-computer > label > input[type=\"file\"")
	WebElement fileUpload;

	// Upload a file to Spaces
	public void uploadFiletoSpace() {
		fileUpload.sendKeys(Constants.File_path_For_Space1);
	}

	// Create a Space name element
	@FindBy(css = "input.inline-edit")
	WebElement spaceName;

	// Name the newly created space
	public void spaceName() {
		// Create Date as current Date in MM DD YYYY HH mm format
		String date = new SimpleDateFormat("MMddyyyyHHmm").format(new Date());
		// Generate random email using the Date format
		String newSpaceName = "New Space " + date;
		spaceName.sendKeys(newSpaceName);
	}

	// Space description element
	@FindBy(css = "div.space-description.ng-binding.show")
	WebElement spaceDesc;

	// Click on space description element
	public void spaceDesc() {
		spaceDesc.click();
	}

	// Description for Spaces element
	@FindBy(css = ".space-description-editor")
	WebElement addSpaceDesc;

	// Enter description
	public void EnterSpaceDesc() {
		spaceDesc.sendKeys("This description is added for new space");
	}

	// Click on the new space created
	@FindBy(css = "div.space-card-mask")
	WebElement firstSpace;

	// Click on the first space created
	public void clickFirstSpace() {
		firstSpace.click();
	}

	// Add more files button
	@FindBy(css = "div.options.live > label > input[type=\"file\"")
	WebElement addMoreFiles;

	// Upload one more file to Spaces
	public void uploadMoreFiletoSpace() {
		// addMoreFiles.sendKeys(Constants.File_path_For_Space2);
		addMoreFiles.sendKeys(Constants.File_path_For_Space2);
	}

	// First space on all spaces page
	// @FindBy(css="")
	// WebElement firstSpace;

	// Click on Delete button on Space
	@FindBy(css = "span.acknowledge")
	WebElement deleteButton;

	// Click on Delete button
	public void clickDeleteButton() {
		deleteButton.click();
	}

	// Space menu option
	@FindBy(css = "div.opt.open.space")
	WebElement spaceMenu;

	// Click on Space menu option
	public void clickSpaceMenu() {
		spaceMenu.click();
	}

	// Delete space from menu
	@FindBy(css = "div.opt.delete-space.ng-scope")
	WebElement deleteMenuOption;

	// click Delete space menu option
	public void clickDeletemenuOption() {
		deleteMenuOption.click();
	}

	// Delete space confirmation message
	@FindBy(css = ".message")
	WebElement deleteSpaceConfirm;

	// Check delete space confirmation message
	public boolean isDeleteSpaceMsg() {
		deleteSpaceConfirm.isDisplayed();
		return true;
	}

	// Add file menu
	@FindBy(css = "div.ht-add-files-menu.ng-isolate-scope > div")
	WebElement addFileButton;

	// Click on Add file menu button
	public void clickAddFileMenu() {
		addFileButton.click();
	}

	// Upload background image for Space element
	@FindBy(css = "div.add-bg > input[type=\"file\"]")
	WebElement backgroundImage;

	// Upload file to spaces background
	public void uploadSpacesBackgroundImage() {
		backgroundImage.sendKeys(Constants.Space_background_image);
	}

	// Save background image element
	@FindBy(css = ".save")
	WebElement saveBackgroundImage;

	// Click on Save button on uploading space background image
	public void saveBackgroundImageButton() {
		saveBackgroundImage.click();
	}

	// Remove background image for space element
	@FindBy(css = ".remove-bg")
	WebElement removeBackgroundImage;

	// CLick on Remove background image for space button
	public void removeBackgroundImageSpace() {
		removeBackgroundImage.click();
	}

	public boolean isRemoveImageAvailable() {
		removeBackgroundImage.isDisplayed();
		return true;
	}
	
	//First image web element of the space
	@FindBy(css="button.btn-view-space")
	WebElement firstSpaceFile;
	
	//Click on Space first image
	public void clickFirstImage(){
		firstSpaceFile.click();
	}
	
	//Click on file options on file details page
	@FindBy(css="div.opt.open.file")
	WebElement fileDetailsOptions;
	
	//Click on File details options 
	public void clickFileDetailsOptions(){
		fileDetailsOptions.click();
	}
	
	//Approve file web element
	@FindBy(css="div.opt.approval.ng-scope")
	WebElement approveFile;
	
	//Click on Approve file option
	public void clickApproveFile(){
		approveFile.click();
	}
	
	//Approved file web element
	@FindBy(css=".version-approved")
	WebElement fileApproved;
	
	//Check if file is approved
	public boolean isFileApproved(){
		fileApproved.isDisplayed();
		return true;
	}
	
	//Add file options on file details page
	@FindBy(css="div.ht-add-files-menu.ng-isolate-scope > div.opt.open.file")
	WebElement addFileOption;
	
	//Click on File add option on File details page
	public void clickAddFileOptions(){
		addFileOption.click();
	}
	
	//Version test on File details page
	@FindBy(css=".version")
	WebElement versionText;
	
	//Check if new version test is available
	public boolean isVersionTextAvailable(){
		versionText.isDisplayed();
		return true;
	}
	
	//Un-approve button web element
	@FindBy(css=".unapproval")
	WebElement unApprove;
	
	//CLick on Un-approve button
	public void clickUnapprove(){
		unApprove.click();
	}
	
	//Add file version button
	@FindBy(css="div.live > label > input[type=\"file\"")
	WebElement addFileVersion;
	
	public void addFileVersion(){
		addFileVersion.sendKeys(Constants.File_path_For_Space2);
	}

	// Initialize webdriver
	public CreateSpacePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Find dashboard icon
	@FindBy(css = "a.view-activity")
	WebElement dashboardIcon;
	
	// Open dashboard
	public void openDashboard() {
		dashboardIcon.click();
	}

	// Create space via Hightail Oauth
	@FindBy(css = "i.provider:nth-child(2)")
	WebElement hightailUpload;	
	// click on hightail upload
	public void clickHightailUpload() {
		hightailUpload.click();
	}

	// Create space via Dropbox
	@FindBy(css = "i.provider:nth-child(3)")
	WebElement dropboxUpload;	
	// click on dropbox upload
	public void clickDropboxUpload() {
		dropboxUpload.click();
	}

	// Create space via Microsoft One Drive
	@FindBy(css = "i.provider:nth-child(4)")
	WebElement oneDriveUpload;	
	// click on Microsoft One Drive upload
	public void clickOneDriveUpload() {
		oneDriveUpload.click();
	}

	// Create space via Google Drive
	@FindBy(css = "i.provider:nth-child(5)")
	WebElement googleDriveUpload;	
	// click on Google Drive upload
	public void clickGoogleUpload() {
		googleDriveUpload.click();
	}
	
	//Find Image locator and click on it
	@FindBy(css="div.annotation-media-container")
	WebElement imageLocator;
	
	//Click on the image to open the comment field
	public void clickOnImage(){
		imageLocator.click();
	}
	//Locate comment field
	@FindBy(css="textarea.ng-valid:nth-child(2)")
	WebElement commentField;

	//Type @Mention in the comment field
	public void enterMention() throws InterruptedException{
		commentField.click();
		commentField.sendKeys("@prd");
		Thread.sleep(2000);
		commentField.sendKeys(Keys.RETURN);
	}
	
	//Type @Mention in the comment field
		public void enterComment(){
			commentField.sendKeys("This is the comment added on the file");
		}
	
	//Find Post comment button
	@FindBy(css=".post-comment")
	WebElement postCommentButton;
	
	//click on Post comment field
	public void clickPostComment(){
		postCommentButton.click();
	}
	
	//Find follow up checkbox
	@FindBy(css="div.needs-followup")
	WebElement followup;
	
	//Select follow up checkbox
	public void selectFollowUp(){
		followup.click();
	}
	
	//Delete file option from file details page
	@FindBy(css=".delete-file")
	WebElement deleteFile;
	
	//Click on Delete file option on File details page
	public void deleteFile(){
		deleteFile.click();
	}
}
