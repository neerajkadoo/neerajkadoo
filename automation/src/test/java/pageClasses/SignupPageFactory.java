package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPageFactory {

	WebDriver driver;

	// Sign up for Free button
	@FindBy(css = "div.content > button")
	WebElement signUpForFree;

	// Click on Sign Up for Free button
	public void clickSignUpForFree() {
		signUpForFree.click();
	}

	// Full name field location
	@FindBy(css = "form[name=\'signupForm\'] > div.field > input[name=\'name'")
	WebElement fullName;

	// Fill Full name field on Signup page
	public void fullName(String fullName1) {
		fullName.sendKeys(fullName1);
	}

	// Email field on Signup page
	@FindBy(css = "form[name=\'signupForm\'] > div.field > input[name=\'email'")
	WebElement emailSignup;

	// Fill email on Signup page
	public void emailSignup(String email1) {
		emailSignup.sendKeys(email1);
	}

	// Password field on Signup page
	@FindBy(css = "form[name=\'signupForm\'] > div.field > div.pwd-display > input[name=\'password'")
	WebElement passwordSignup;

	// Fill password on Signup page
	public void passwordSignup(String password1) {
		passwordSignup.sendKeys(password1);
	}

	// Sign up with email button location
	@FindBy(css = "div.signup-form > button.submit")
	WebElement signUpWithEmail;

	// CLick Sign Up with email button
	public void clickSignUpWithEmail() {
		signUpWithEmail.click();
	}

	// Error for signing up existing user
	@FindBy(css = "div.signup-form > div[class=\'errors open'")
	WebElement errorEmailExist;

	// Check if error exist after signing up with existing email ID
	public boolean emailExistError() {
		errorEmailExist.isDisplayed();
		return true;
	}

	// Error for entering invalid email Id
	@FindBy(css = "form[name=\'signupForm\'] > div.field > div[ng-show=\'errors.signup.email'")
	WebElement errorInvalidEmail;

	// Check if error exist after entering invalid email in email field
	public boolean emailInvalidEmailExist() {
		errorInvalidEmail.isDisplayed();
		return true;
	}

	// Error for missing or invalid Name
	@FindBy(css = "form[name=\'signupForm\'] > div.field > div[ng-show=\'errors.signup.name'")
	WebElement errorIncorrectName;

	// Check if error exist on missing or invalid name
	public boolean errorMissingName() {
		errorIncorrectName.isDisplayed();
		return true;
	}

	// Error for missing or invalid password
	@FindBy(css = "form[name=\'signupForm\'] > div.field > div[ng-show=\'errors.signup.password'")
	WebElement errorIncorrectPassword;

	// Check if error exist on missing password
	public boolean ErrorMissingPassword() {
		errorIncorrectPassword.isDisplayed();
		return true;
	}

	// Initialize webdriver
	public SignupPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
