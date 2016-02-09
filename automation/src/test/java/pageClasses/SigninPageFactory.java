package pageClasses;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SigninPageFactory {

	WebDriver driver;

	// Xpath for Sign-up link on home page
	@FindBy(css = ".sign-in")
	WebElement SignIn;

	// Click on Sign-in link on Home page
	public void clickSigninLink() {
		SignIn.click();
	}

	// Email address location
	@FindBy(css = "form[name=\'loginForm\'] > div.field > input[name=\'email\'")
	WebElement email;

	// User email to sign In
	public void userEmail(String userEmail) {
		email.sendKeys(userEmail);
	}

	// Password field location
	@FindBy(css = "form[name=\'loginForm\'] > div.field > input[name=\'password'")
	WebElement password;

	// User password for sign In
	public void userPassword(String userPassword) {
		password.sendKeys(userPassword);
	}

	// Sign up with email button location
	// @FindBy(xpath =
	// "id('ng-app')/body/div/div[2]/div[1]/div/div[2]/div/div[1]/button")
	@FindBy(css = ".login-form > button:nth-child(3)")
	WebElement signInWithEmail;

	// CLick on Sign In with email button
	public void clickSignInWithEmail() {
		signInWithEmail.click();
	}

	public SigninPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Sign-up from Hightail oAuth on home page
	//@FindBy(css = ".signup > div:nth-child(3) > button:nth-child(3)")
	@FindBy(css = "div.login.open > div.content > button.auth.hightail")
	WebElement oAuthHtl;

	// Click on Hightail oAuth button
	public void clickoAuthHtl() {
		oAuthHtl.click();
	}

	// Hightail oAuth email
	@FindBy(css = "#ysi_email")
	WebElement oAuthemail;

	public void EnteroAuthHtlEmail(String oAuthHtlEmail) {
		oAuthemail.sendKeys(oAuthHtlEmail);
	}

	public void clickoAuthHtlEmail() {
		oAuthemail.click();
	}

	// Hightail oAuth password
	@FindBy(css = "#ysi_password")
	WebElement oAuthpwd;

	public void EnteroAuthHtlpwd(String oAuthHtlpwd) {
		oAuthpwd.sendKeys(oAuthHtlpwd);
	}

	// Hightail oAuth SignIn button
	@FindBy(css = ".btnLogin")
	WebElement oAuthsignIn;

	public void clickoAuthHtlsubmit() {
		oAuthsignIn.click();
	}

	// Hightail oAuth accept button
	@FindBy(css = "#btnAccept")
	WebElement oAuthAccept;

	public void clickoAuthHtlAccept() {
		oAuthAccept.click();
	}

	// Error for entering invalid credentials in oAuth
	@FindBy(css = "#loginServerError")
	WebElement oAuthSigninError;

	// Check if error exist on oAuth page
	public boolean oAuthSigninErrorExist() {
		oAuthSigninError.isDisplayed();
		return true;
	}

	// Error for empty email Id in oAuth
	@FindBy(css = "#validationEmailMessage")
	WebElement oAuthNoemailError;

	// Check if error exist on oAuth page
	public boolean oAuthNoemailErrorExist() {
		oAuthNoemailError.isDisplayed();
		return true;
	}

	// Dropbox oAuth email
	//@FindBy(css = "id^='pyx'")
	@FindBy(xpath = ".//*[contains(text(), 'Email')]" )
	WebElement dropboxOauthEmail;
	public void EnterDropboxEmail(String dropboxEmail) {
		dropboxOauthEmail.sendKeys(dropboxEmail);
	}
	public void clickDropboxOauthEmail() {
		dropboxOauthEmail.click();
	}

	// Dropbox oAuth password
	@FindBy(xpath = ".//*[contains(text(), 'Password')]" )	
	WebElement dropboxOauthpwd;
	public void EnterDropboxPwd(String dropboxPwd) {
		dropboxOauthpwd.sendKeys(dropboxPwd);
	}

	// Signin in Dropbox Oauth
	@FindBy(css = "button.login-button:nth-child(2)")
	WebElement dropboxSignIn;	
	// click on dropbox SignIn
	public void clickDropboxSignIn() {
		dropboxSignIn.click();
	}

	// Accept Dropbox agreement -"Allow"
	@FindBy(css = "button.auth-button:nth-child(4)")
	WebElement dropboxAllow;	
	// click on dropbox Allow button
	public void clickDropboxAllow() {
		dropboxAllow.click();
	}

	// SignIn in Add Files"
	@FindBy(css = ".not-connected > button:nth-child(2)	")
	WebElement addFilesSignIn;	
	// click on SingIn button on Add Files dialog
	public void clickAddFilesSignIn() {
		addFilesSignIn.click();
	}
	
	
	// Google oAuth email
	@FindBy(css = "#Email")
	WebElement googleOauthEmail;
	public void EnterGoogleEmail(String googleEmail) {
		googleOauthEmail.sendKeys(googleEmail);
	}
	public void clickGoogleOauthEmail() {
		googleOauthEmail.click();
	}

	// Google oAuth Next button
	@FindBy(css = "#next")
	WebElement googleOauthNextBtn;
	public void clickgoogleOauthNextBtn() {
		googleOauthNextBtn.click();
	}
	
	// Google oAuth password
	@FindBy(css = "#Passwd")
	WebElement googleOauthpwd;
	public void EnterGooglePwd(String googlePwd) {
		googleOauthpwd.sendKeys(googlePwd);
	}

	// Signin in Google Oauth
	@FindBy(css = "#signIn")
	WebElement googleSignIn;	
	// click on google SignIn
	public void clickGoogleSignIn() {
		googleSignIn.click();
	}

	// Accept google agreement -"Allow"
	@FindBy(css = "#submit_approve_access")
	WebElement googleAllow;	
	// click on google Allow button
	public void clickGoogleAllow() {
		googleAllow.click();
	}	
	
	
	// Microsoft One Drive oAuth email
	@FindBy(css = "#i0116")
	WebElement microsoftOauthEmail;
	public void EnterMicrosoftEmail(String microsoftEmail) {
		microsoftOauthEmail.sendKeys(microsoftEmail);
	}
	public void clickMicrosoftOauthEmail() {
		microsoftOauthEmail.click();
	}
	
	// Microsoft oAuth password
	@FindBy(css = "#i0118")
	WebElement microsoftOauthpwd;
	public void EnterMicrosoftPwd(String microsoftPwd) {
		microsoftOauthpwd.sendKeys(microsoftPwd);
	}

	// Sign-in in Microsoft Oauth
	@FindBy(css = "#idSIButton9")
	WebElement microsoftSignIn;	
	// click on Microsoft SignIn
	public void clickMicrosoftSignIn() {
		microsoftSignIn.click();
	}

	
	
}
