package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Constants;

public class FilePickerPageFactory {
	WebDriver driver;
	WebElement element = null;

	public FilePickerPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ADD button in file picker 
	@FindBy(css = ".button-choose")
	WebElement addFilePickerBtn;
	// Click ADD button on file picker
	public void clickAddFilePickerBtn() {
		addFilePickerBtn.click();
	}
	
	// Cancel button in file picker 
	@FindBy(css = ".button-choose")
	WebElement cancleFilePickerBtn;
	// Click ADD button on file picker
	public void clickCancleFilePickerBtn() {
		cancleFilePickerBtn.click();
	}

	//Selecting File from File picker 
	@FindBy(css = "tr.ng-scope:nth-child(2) > td:nth-child(1) > div:nth-child(1) > i:nth-child(1)")	
	WebElement selectFile;
	// Select file from picker
	public void SelectFile() {
		selectFile.click();
	}	
	
}
