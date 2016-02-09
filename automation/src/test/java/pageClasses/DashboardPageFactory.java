package pageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPageFactory {
	WebDriver driver;
	WebElement element = null;
	
	public DashboardPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Find Space name
	@FindBy(css = "div.grid-100 > h1")
	WebElement dashboardSpaceName;
	
	// Get Space name
	public String getSpaceNameFromDashboard() {
		return "The name of the spaces is: " + dashboardSpaceName.getText();
	}
	
	// Click Space name to navigate back to the Space
	public void clickDashboardSpaceName() {
		dashboardSpaceName.click();
	}
	
	// Find Analytics Date Range button
	@FindBy(css = "div.ht-analytics-date-range > div.opt.open")
	WebElement dateRangeButton;
	
	// Open Analytics Date options menu
	public void clickDateRangeButton() {
		dateRangeButton.click();
	}
	
	// Close Analytics Date options menu
	@FindBy(css = "div.ht-analytics-date-range > div.mask")
	WebElement closeMenu;
	public void closeAnalyticsMenu() {
		closeMenu.click();
	}
	
	// Find Analytics options menu
	@FindBy(css="css=div.options.live")
	WebElement dateRangeOptions;
	// Need to add an option to capture all possible date options
	
	// Find Space Health text
	@FindBy(css=".separator > h2:nth-child(1)")
	WebElement spaceHealthText;

	public String VerifySpaceHealth() {
		 return spaceHealthText.getText();
	}
	
	// Find Space Health donut
	@FindBy(css="svg.donut.average")
	WebElement spaceHealthDonut;
	
	// Find Space Health score
	@FindBy(css = "svg.donut > text.value")
	WebElement spaceHealthScore;
	
	// Get Space Health score
	public String getSpaceHealthScore() {
		return "Space Health is: " + spaceHealthScore.getText();
	}
	
	// Get Space Health score
	public String getSpaceHealthScoreNo() {
		return spaceHealthScore.getText();
	}
	
	// Find number of files
	@FindBy(css = "div.card.files > div.media > div.media-body > div.value")
	WebElement dashboardFileCount;
	
	// Get the number of files from the dashboard
	public String getNumberOfFiles() {
		return "The number of files in the Space is: " + dashboardFileCount.getText();
	}
	
	// Find the number of comments
	@FindBy(css = "div.card.comments > div.media > div.media-body > div.value")
	WebElement dashboardCommentsCount;
	
	// Get the number of comments
	public String getNumberOfComments() {
		return "The number of comments in the Space is: " + dashboardCommentsCount.getText();
	}
	
	// Find when the space was created
	@FindBy(css = "div.card.created > div.vertical-align-wrapper > div.media > div.media-body > div.value")
	WebElement spaceCreatedDate;
	
	// Get the Space creation date
	public String getSpaceCreatedDate() {
		return "Space was created: " + spaceCreatedDate.getText();
	}
	
	// Find the space's last activity
	@FindBy(css = "div.card.activity > div.vertical-align-wrapper > div.media > div.media-body > div.value")
	WebElement lastActivity;
	
	// Get the last activity value
	public String getLastActivity() {
		return "The last activity on the space was: " + lastActivity.getText();
	}
	
	// Find percentage of files with comments
	@FindBy(css = "div#files-comments > svg.donut > text.value")
	WebElement filesWithComments;
	
	// Get the percentage of files with comments
	public String getFilesWithComments() {
		return "The percentage of files with comments is: " + filesWithComments.getText();
	}
	
	// Find average number of comments per day
	@FindBy(css = "div.trend > span#avgComments")
	WebElement avgCommentsPerDay;
		
	// Get the percentage of files with comments
	public String getAverageCommentsPerDay() {
		return "The average comments per day is: " + avgCommentsPerDay.getText();
	}
	
	// Find the number followers 
	@FindBy(css = "div.users > div")
	List<WebElement> numberOfFollowers;
	
	// Get the number of followers
	public String getNumberOfFollowers() {
		return "The number of followers is: " + numberOfFollowers.size();
	}
	
	// Find percentage of active users
	@FindBy(css = "svg.gauge > g  g > g > text.percentage")
	WebElement activeUsersPercentage;
	
	// Get the percentage of active users
	public String getPercentOfActiveUsers() {
		return "The percentage of active users is: " + activeUsersPercentage.getText();
	}
	
	// Find percentage of inactive users
	@FindBy(css = "svg.gauge > g  g:nth-child(2) > g > text.percentage")
	WebElement inactiveUsersPercentage;
	
	// Get the percentage of inactive users
	public String getPercentOfInactiveUsers() {
		return "The percentage of inactive users is: " + inactiveUsersPercentage.getText();
	}
}
