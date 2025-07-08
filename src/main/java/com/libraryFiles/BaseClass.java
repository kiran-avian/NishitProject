package com.libraryFiles;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import copFrontEnd.HomePage.HomePage;



public class BaseClass {
	public static WebDriver driver;
	
	
	public ChromeOptions options;
	
	public HomePage homepage;
	public void initialiseBrowser() throws IOException, InterruptedException {
		if (driver == null) {
		      // Create ChromeOptions instance
	         options = new ChromeOptions();
	        
	        // Add argument to disable notifications
	        options.addArguments("--disable-notifications");
	        
	     // Disable JavaScript
	        Map<String, Object> prefs = new HashMap<String, Object>();
	        prefs.put("profile.managed_default_content_settings.javascript", 2);
	        options.setExperimentalOption("prefs", prefs);
		
	        driver = new ChromeDriver();

			driver.manage().window().maximize();
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//	        js.executeScript("document.body.style.zoom='100%'");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		   driver.get(UtilityClass.getPFData("ClientURL"));
			//HelperMethods.AdminLogin(driver);
			driver.get(UtilityClass.getPFData("ClientURL"));
		}
		// driver.switchTo().newWindow(WindowType.TAB);
		// driver.get("https://www.google.com/");

	}
	
	public String captureSS( String TestName) throws IOException {
		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized. Unable to capture screenshot.");
		}
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		LocalDateTime time = LocalDateTime.now();
		String TimeStamp = TestName + time.format(DateTimeFormatter.ofPattern("yyyy.MMM.dd.HH.mm.ss"));
		String path = System.getProperty("user.dir") + ".\\FailedTCScreenShot\\" + TimeStamp + ".png";
		File dest = new File(path);
		FileHandler.copy(src, dest);
		return path;
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		// driver.close();
		if (driver != null) {
			driver = null;
		}
	}
}
