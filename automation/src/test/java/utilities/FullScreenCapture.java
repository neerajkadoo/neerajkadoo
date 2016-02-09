package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class FullScreenCapture {

	static Logger log = Logger.getLogger(FullScreenCapture.class);

	public static void takeScreenShotOnFailure(ITestResult testResult, WebDriver driver) throws IOException {
		
		if (driver == null) {
			throw new RuntimeException("Driver is null!"); 
		}

		if (testResult != null && testResult.getStatus() == ITestResult.FAILURE) {
	
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			new File(Constants.Screenshot_On_Faliuers).mkdirs();
			
			String destLoc = getPngName(testResult.getName());
			log.info("Png Path:" + destLoc);
			testResult.setAttribute("SSNAME", destLoc);

			try {
				FileUtils.copyFile(scrFile, new File(destLoc));
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

	public static String getPngName(String testName) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmssaa");
		return Constants.Screenshot_On_Faliuers + "//" + testName + "-" + dateFormat.format(new Date()) + ".png";
	}
}