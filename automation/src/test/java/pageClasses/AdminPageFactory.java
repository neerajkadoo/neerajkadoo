package pageClasses;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPageFactory {

	WebDriver driver;

	public AdminPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// Admin console button
	@FindBy(css = ".upgrade-btn")
	WebElement adminConsoleBtn;
	// Click on admin console button
	public void clickadminConsoleBtn() {
		adminConsoleBtn.click();
	}

	// Admin Plan
	@FindBy(css = ".yourplan")
	WebElement adminPlan;
	// Check if plan is BUSSINESS
	public String VerifyAdminPlan() {
		 return adminPlan.getText();
	}

	// Members Link on Admin console
	@FindBy(css = ".tab-selected")
	WebElement adminHeader;
	// Verify Admin Header
	public String VerifyAdminHeader() {
		 return adminHeader.getText();
	}

	// Activity Link on Admin console
	@FindBy(css = "div.tab:nth-child(2) > span:nth-child(1)")
	WebElement adminHeaderActivity;
	// Verify Activity Header
	public String VerifyAdminHeaderActivity() {
		 return adminHeaderActivity.getText();
	}
	// Click Activity link on admin header
	public void clickAdminHeaderActivity() {
		 adminHeaderActivity.click();
	}

	// Focus on Email address in /admin-login page
	@FindBy(css = "body > div > div.ht-admin-login.ng-scope > div.login.open > div.content > form > div:nth-child(1) > input")
	WebElement adminEmailF;
	// admin email to focus
	public void focusAdminEmail() {
		adminEmailF.click();
	}

	// Email address in /admin-login page
	@FindBy(css = "body > div > div.ht-admin-login.ng-scope > div.login.open > div.content > form > div:nth-child(1) > input")
		WebElement adminEmail;
	// admin email to sign In
	public void enterAdminEmail(String userEmail) {
		adminEmail.sendKeys(userEmail);
	}

	// Password field in /admin-login page
	@FindBy(css = "div.field:nth-child(2) > input:nth-child(1)")
	WebElement adminPwd;
	// User password in /admin-login page
	public void enterAdminPwd(String userPassword) {
		adminPwd.sendKeys(userPassword);
	}

	//Error verification on admin signin page
	@FindBy(css = ".errors > p:nth-child(1)")
	WebElement errOnSignin;
	// Verify Error on SignIn
	public String VerifyErrOnSignin() {
		 return errOnSignin.getText();
	}
	
	//Signin button in /admin-login page	
	@FindBy(css = ".submit")
	WebElement adminSigninBtn;
	// CLick on Sign In with email button
	public void clickAdminSigninBtn() {
		adminSigninBtn.click();
	}
	
	//Signout button in /admin-login page	
	@FindBy(css = "div.sign-out:nth-child(2)")
	WebElement adminSignout;
	// CLick on Sign out with email button
	public void clickAdminSignout() {
		adminSignout.click();
	}

	//Add Members button in admin console	
	@FindBy(css = ".add-members > button:nth-child(1)")
	WebElement addMembers;
	// CLick on Sign out with email button
	public void clickAddMembers() {
		addMembers.click();
	}
	
	// Name field to add members 
	@FindBy(css = ".table-invites > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > input:nth-child(1)")
	WebElement memberName;
	// User password in /admin-login page
	public void enterMemberName(String subUserName) {
		memberName.sendKeys(subUserName);
	}

	// email field to add members 
	@FindBy(css = ".table-invites > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > input:nth-child(1)")
	WebElement memberEmail;
	// User password in /admin-login page
	public void enterMemberEmail(String subUserEmail) {
		memberEmail.sendKeys(subUserEmail);
	}

	//Add Members button on add members popup	
	@FindBy(css = "div.button-menu:nth-child(6) > button:nth-child(2)")
	WebElement addMembersBtn;
	// CLick on Sign out with email button
	public void clickAddMembersBtn() {
		addMembersBtn.click();
	}

	//Cancel button on add members popup	
	@FindBy(css = "div.button-menu:nth-child(6) > button:nth-child(1)")
	WebElement cancelBtn;
	// CLick on Sign out with email button
	public void clickCancelBtn() {
		cancelBtn.click();
	}
	
	// Reveiw add member popup
	@FindBy(css = ".confirmation-message")
	WebElement confMsg;
	// Verify Admin Header
	public String VerifyConfMsg() {
		 return confMsg.getText();
	}
	
	//Add member btn on confirmation popup	
	@FindBy(css = ".confirm > button:nth-child(2)")
	WebElement addMemberConfBtn;
	// CLick on Sign out with email button
	public void clickaddMemberConfBtn() {
		addMemberConfBtn.click();
	}

	// Success message for adding member
	@FindBy(css = ".notification-message")
	WebElement successMsg;
	// Verify success message
	public String VerifySuccessMsg() {
		 return successMsg.getText();
	}
	
	//Reset "?" icon next to password on /admin-login link	
	@FindBy(css = ".reset-password")
	WebElement resetPwd;
	// Click on reset icon
	public void clickResetPwd() {
		resetPwd.click();
	}
	
	// Reset password popup Header1, "Forgot your password?"
	@FindBy(css = "	.send-reset > h1:nth-child(1)")
	WebElement resetPopupH1;
	// Reset password popup header2
	public String VerifyResetPopupH1() {
		 return resetPopupH1.getText();
	}

	// Reset password popup Header2, "Enter your email associated with your Hightail Spaces account"
	@FindBy(css = ".send-reset > p:nth-child(2)")
	WebElement resetPopupH2;
	// Reset password popup header2
	public String VerifyResetPopupH2() {
		 return resetPopupH2.getText();
	}
	
	//Next button on Reset password popup	
	@FindBy(css = ".next")
	WebElement nextBtn;
	// Click on next button
	public void clickNextBtn() {
		nextBtn.click();
	}

	// Confirmation message Header1, "Check your inbox"
	@FindBy(css = ".email-sent > h1:nth-child(1)")
	WebElement confResetPwdH1;
	// Verify reset password confirmation message header1
	public String VerifyConfResetPwdH1() {
		 return confResetPwdH1.getText();
	}
	
	// Confirmation message Header2 , "Click the button in your email to update your password"
	@FindBy(css = ".email-sent > p:nth-child(2)")
	WebElement confResetPwdH2;
	// Verify reset password confirmation message header1
	public String VerifyConfResetPwdH2() {
		 return confResetPwdH2.getText();
	}
	
	//Resend button Reset password popup	
	@FindBy(css = ".resend")
	WebElement resendBtn;
	// Click on next button
	public void clickResendBtn() {
		resendBtn.click();
	}

	//Search on admin console	
	@FindBy(css = ".ng-valid")
	WebElement searchBox;
	// Click on Search box
	public void clickSearchBox() {
		searchBox.click();
	}
	// Write search string on search box
	public void enterSearchBox(String subUserEmail) {
		searchBox.sendKeys(subUserEmail);	
	}

	//Action icon located right side for invited member	
	@FindBy(css = ".action-button")
	WebElement actionIcon;
	// Click on action Icon
	public void clickActionIcon() {
		actionIcon.click();
	}
	
	//Re-send invite on action icon located right side for invited member		
	@FindBy(css = ".action-options-edit")
	WebElement actionResend;
	// Click on Re-send invite
	public void clickActionResend() {
		actionResend.click();
	}
	
	//Re-send invite on action icon located right side for invited member		
	@FindBy(css = ".action-options-disable")
	WebElement actionCancel;
	// Click on Cancel invite
	public void clickActionCancel() {
		actionCancel.click();
	}
	
	//No member found label on empty console		
	@FindBy(css = "td.no-members")
	WebElement noUserFnd;
	// Verify "No users found" lebel displayed
	public String VerifyNoUserFnd() {
		 return noUserFnd.getText();
	}
	
	//FIrst column "CREATED BY"on Activity tab		
	@FindBy(css = "th.creator > span:nth-child(1)")
	WebElement activityCreatedBy;
	// Verify "No users found" lebel displayed
	public String VerifyActivityTab() {
		 return  activityCreatedBy.getText();
	}

}














