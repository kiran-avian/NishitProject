package com.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	public static void waitToBeClickable(WebDriver driver,WebElement wb, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(wb)).click();	
	}
	public static void waitUptoVisibilityOfAllElement(WebDriver driver,List<WebElement> wb, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfAllElements(wb));	
	}
	public static void waitUntilInvisibility(WebDriver driver,WebElement wb, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.invisibilityOf(wb));	
	}
	public static void waitForAjax(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(driver1 -> {
			JavascriptExecutor js = (JavascriptExecutor) driver1;
			//Return true if no active AJAX requests are found
			return (Boolean) js.executeScript("return jQuery.active == 0");
		});
	}
}
