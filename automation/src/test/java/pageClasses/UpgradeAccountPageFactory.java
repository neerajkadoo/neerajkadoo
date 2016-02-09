package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Constants;

public class UpgradeAccountPageFactory {

	WebDriver driver;

	// Close the help popup element
	@FindBy(css = ".close-button")
	WebElement closeHelpPopup;

	// Click on Close help popup
	public void closeHelp() {
		closeHelpPopup.click();
	}

	// Avatar image element
	@FindBy(css = ".default")
	WebElement clickAvatar;

	// Click on Avatar image
	public void clickAvater() {
		clickAvatar.click();
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

	// Edit button next to Pro Plan
	@FindBy(css = ".upgrade")
	WebElement editPlan;

	// Click Edit plan button
	public void clickEditPlan() {
		editPlan.click();
	}

	// Keep pro button
	@FindBy(css = ".button-keep-pro")
	WebElement keepProButton;

	// Click on Keep pro button
	public void clickKeepProButton() {
		keepProButton.click();
	}

	// Go to Lite button
	@FindBy(css = ".button-downgrade")
	WebElement goLiteButton;

	// Click on Go lite button
	public void clickGoLite() {
		goLiteButton.click();
	}

	// Works great button on cancel account page
	@FindBy(css = ".works-great")
	WebElement worksGreatButton;

	// Click on Works great button
	public void clickWorksGreatButton() {
		worksGreatButton.click();
	}

	// Give feedback text Field
	@FindBy(css = "textarea.ng-pristine")
	WebElement giveFeedbackField;

	// Enter cancel account feedback
	public void enterFeedback() {
		giveFeedbackField.sendKeys("Works great, cancelling to test cancel flow");
	}

	// Submit button on Cancel account page
	@FindBy(css = ".send-feedback")
	WebElement submitButton;

	// Click on Submit button on Cancel account page
	public void clickSubmitButton() {
		submitButton.click();
	}

	// Initialize webdriver
	public UpgradeAccountPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
