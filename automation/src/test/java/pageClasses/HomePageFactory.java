package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

public class HomePageFactory {

	WebDriver driver;

	static Logger log = Logger.getLogger(HomePageFactory.class);
	
	public HomePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public HomePageFactory() {
		PageFactory.initElements(driver, this);
	}

	public void navigateToSpacesHomePage(WebDriver driver) {
		driver.findElement(By.id("ng-app"));

		// Follwing is page element for SQC
		// driver.findElement(By.id("scnry-hightail"));
	}

	// Find the Send link in the homepage header
	@FindBy(css = "div.nav-options > a[href='/send']")
	WebElement sendPage;

	// Click the Send link
	public void clickSendInHeader() {
		sendPage.click();
	}
}
