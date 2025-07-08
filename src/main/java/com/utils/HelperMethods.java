package com.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import com.libraryFiles.UtilityClass;

public class HelperMethods {

	

	public static LinkedHashMap<String, String> getHelperMethodAlertMsgErrorMsg(WebDriver driver,
			FluentWait<WebDriver> flwait, WebElement submitBtn, WebElement sweetAlertMsg, WebElement errorMsg,
			List<WebElement> errorMsgLst) throws InterruptedException {
		LinkedHashMap<String, String> lmp = new LinkedHashMap<String, String>();
		lmp.put("AlertMsg", "0");
		lmp.put("Errors", "0");
		String msg = null;
		try {
			submitBtn.click();
			// Check for SweetAlert message
			flwait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(sweetAlertMsg),
					ExpectedConditions.visibilityOf(errorMsg)));
			msg = sweetAlertMsg.getText();
			UtilityClass.changeBackgroundColorAndBorder(driver, sweetAlertMsg);
			lmp.put("AlertMsg", msg);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// Handle other exceptions
			try {
				UtilityClass.changeBackgroundColorAndBorder(driver, errorMsg);
				lmp.put("Errors", String.valueOf(errorMsgLst.size()));
			} catch (org.openqa.selenium.NoSuchElementException ea) {
				// Handle other exceptions
				System.out.println("An unexpected error occurred:both are not displayed" + e.getMessage());
			}
		} catch (org.openqa.selenium.TimeoutException e) {

			System.out.println("An unexpected error occurred: " + e.getMessage());
			lmp.put("Unexpected Error", "both are not visible");
		}
		// Return the result
		return lmp;
	}

	public static LinkedHashMap<String, String> getHelperMethodErrorMsgAlertMsg(WebDriver driver,
			FluentWait<WebDriver> flwait, WebElement submitBtn, WebElement sweetAlertMsg, WebElement errorMsg,
			List<WebElement> errorMsgLst) throws InterruptedException {
		LinkedHashMap<String, String> lmp = new LinkedHashMap<String, String>();
		lmp.put("AlertMsg", "0");
		lmp.put("Errors", "0");
		String msg = null;
		try {
			submitBtn.click();
			// Check for SweetAlert message
			flwait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(sweetAlertMsg),
					ExpectedConditions.visibilityOf(errorMsg)));
			UtilityClass.changeBackgroundColorAndBorder(driver, errorMsg);
			lmp.put("Errors", String.valueOf(errorMsgLst.size()));
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// Handle other exceptions
			try {
				msg = sweetAlertMsg.getText();
				UtilityClass.changeBackgroundColorAndBorder(driver, sweetAlertMsg);
				lmp.put("AlertMsg", msg);

			} catch (org.openqa.selenium.NoSuchElementException ea) {
				// Handle other exceptions
				System.out.println("An unexpected error occurred:both are not displayed" + e.getMessage());
			}
		} catch (org.openqa.selenium.TimeoutException e) {

			System.out.println("An unexpected error occurred: " + e.getMessage());
			lmp.put("Unexpected Error", "both are not visible");
		}
		// Return the result
		return lmp;
	}

	public static ArrayList<String> getHelperMethodViewPageAllRecord(WebDriver driver, WebElement recordSel,
			WebElement infoBtn, WebElement nextbtn, List<WebElement> pageLst) throws InterruptedException {
		SelectionMethod.selectTextOptionFromSelectLB(recordSel, "100");

		WaitUtils.waitForAjax(driver);
		ArrayList<String> al = new ArrayList<String>();
		String[] arr;
		do {

			for (WebElement pg : pageLst) {
				al.add(pg.getText());
			}
			arr = infoBtn.getText().split(" ");
			try {
				nextbtn.click();
			} catch (org.openqa.selenium.ElementClickInterceptedException e) {
				e.printStackTrace();
			}
			WaitUtils.waitForAjax(driver);

		} while (!arr[3].equals(arr[5]));

		Collections.sort(al);
		return al;

	}
}