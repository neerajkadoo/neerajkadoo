package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuOptionsPagefactory {

	WebDriver driver;

	// Initialize webdriver
	public MenuOptionsPagefactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Space menu option
	@FindBy(css = "div.opt.open.space")
	WebElement spaceMenu;

	// Click on Space menu option
	public void clickSpaceMenu() {
		spaceMenu.click();
	}
	
	//Download files menu
	@FindBy(css="div.opt.download-files.ng-scope")
	WebElement downloadFiles;
	
	//Click on Download files menu
	public void clickDownloadFiles(){
		downloadFiles.click();
	}
	
	//Add access code menu
	@FindBy(css="div.opt.access-code")
	WebElement accessCode;
	
	//Click Access code
	public void clickAccessCode(){
		accessCode.click();
	}
	
	//Access code input field
	@FindBy(css="input.ng-pristine")
	WebElement accessCodeField;
	
	//Add access code to the field
	public void addAccessCode(String code){
		accessCodeField.sendKeys(code);
	}
	
	//Save access code element
	@FindBy(css="button.save")
	WebElement saveAccessCode;
	
	//Click on Save access code button
	public void SaveAccessCode(){
		saveAccessCode.click();
	}

}
