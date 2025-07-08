package com.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class AppiumWaitUtils {
	public static void waitToBeClickable(AppiumDriver driver, WebElement wb, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(wb));
	}
	public static void waitToBeClickableWithId(AppiumDriver driver, String id, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		 wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	}
	public static WebElement rtnWaitToBeClickableWithXPath(AppiumDriver driver, String xpath, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	

	public static void waitUptoVisibilityOfAllElement(AppiumDriver driver, List<WebElement> wb, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfAllElements(wb));
	}

	public static void waitUntilInvisibility(AppiumDriver driver, WebElement wb, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.invisibilityOf(wb));
	}

	public static void waitForAjax(AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(driver1 -> {
			JavascriptExecutor js = (JavascriptExecutor) driver1;
			// Return true if no active AJAX requests are found
			return (Boolean) js.executeScript("return jQuery.active == 0");
		});
	}
}
