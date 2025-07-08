package com.libraryFiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class UtilityClass {
	public static String getPFData(String key) throws IOException {
		FileInputStream file = new FileInputStream(".\\PropertyFile.properties");
		Properties p = new Properties();
		p.load(file);
		String value = p.getProperty(key);
		file.close();
		return value;
	}

	public static void jsClick(WebDriver driver, WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void changeBackgroundColorAndBorder(WebDriver driver, WebElement element)
			throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Execute the JavaScript to change the background color and border of the
		// element
		String script = "arguments[0].style.backgroundColor ='yellow';" + "arguments[0].style.border ='3px solid red';";
		js.executeScript(script, element);
		Thread.sleep(2000);
	}

	public static void changeToDarkTheme(WebDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//body"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "arguments[0].style.backgroundColor = 'black';" + // Dark background
				"arguments[0].style.border = '3px solid white';"; // Optional white border to match dark theme

		js.executeScript(script, element);

		// Wait for the visual changes to be observed
		new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(element));
	}

	public static void sendkeyOnWebElement(WebElement wb, String text) {
		wb.clear();
		wb.sendKeys(text);
	}

	public static void scrollUpToWebElement(WebDriver driver, WebElement wb) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", wb);
		// js.executeScript("window.scrollBy(arguments[0], arguments[1]);", 300, 400);
	}

	public static ArrayList<String> getListOfWebElement(List<WebElement> wbLst) {
		ArrayList<String> al = new ArrayList<String>();
		for (WebElement b : wbLst) {
			al.add(b.getText());
		}
		return al;
	}

	public static void clickOptionFromWebElementLst(List<WebElement> wbLst, String option) {
		for (WebElement wb : wbLst) {
			if (wb.getText().equals(option)) {
				wb.click();
				break;
			}
		}
	}

	public static ArrayList<String> getSelectiveColumnValueFromArray(String[][] arr, int columnNumber) {
		ArrayList<String> columnValue = new ArrayList<String>();
		for (String[] row : arr) {
			if (row.length > 0) { // Ensure the row is not empty
				columnValue.add(row[columnNumber - 1]); // Add first column value
			}
		}
		return columnValue;
	}

	public static ArrayList<String> getRowValueFromArrayApplyingTrimFunction(String[][] arr, int rowNum) {
		ArrayList<String> rowData = new ArrayList<>();
		// Check for valid rowIndex
		if (arr == null || rowNum < 0 || rowNum >= arr.length+1) {
			throw new IllegalArgumentException("Invalid rowIndex.");
		}

		// Access the specific row
		String[] row = arr[rowNum - 1];

		// Add all values in the row to the ArrayList
		for (String value : row) {
			rowData.add(value.trim());
		}

		return rowData;
	}

	public static void isImageSame(WebDriver driver, String actualImagePath, String expectedImagePath)
			throws IOException {
		// Take screenshot using AShot
		Screenshot screenshot = new AShot().takeScreenshot(driver);
		BufferedImage actualImage = screenshot.getImage();

		// Save the actual screenshot (optional, useful for debugging)
		ImageIO.write(actualImage, "PNG", new File(actualImagePath));

		// Load expected image from disk
		BufferedImage expectedImage = ImageIO.read(new File(expectedImagePath));

		// Compare images
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);

		if (diff.hasDiff()) {
			System.out.println("❌ Images are different!");
		} else {
			System.out.println("✅ Images are the same.");
		}
	}
	public static void isImageSameSDET(WebDriver driver, WebElement webEle,String actualImagePath, String expectedImagePath)
			throws IOException {
		// Load expected image from disk
		BufferedImage expectedImage = ImageIO.read(new File(expectedImagePath));

		// Take screenshot using AShot
		Screenshot screenshot = new AShot().takeScreenshot(driver, webEle);
		BufferedImage actualImage = screenshot.getImage();

		// Save the actual screenshot (optional, useful for debugging)
		ImageIO.write(actualImage, "PNG", new File(actualImagePath));

		
		
	
		// Compare images
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);

		if (diff.hasDiff()) {
			System.out.println("❌ Images are different!");
		} else {
			System.out.println("✅ Images are the same.");
		}
	}
}
