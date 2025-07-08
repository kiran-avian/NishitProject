package com.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;

public class SelectionMethod {
	public static void changeCheckboxStatus(WebElement Chkbox, String status) {
		boolean checked = Chkbox.isSelected();
		System.out.println(checked);
		if (status.equals("true")) {
			if (!checked) {
				Chkbox.click();
			}
		} else if (status.equalsIgnoreCase("false")) {

			if (checked) {
				Chkbox.click();
			}
		} else {
			System.out.println("Invalid checkbox status Input");
		}
	}

	public static String getCheckboxStatus(WebElement Chkbox) {
		if (Chkbox.isSelected()) {
			return "true";
		}
		return String.valueOf(false);
	}

	public static void selectOptionFromSearchLB(WebDriver driver, WebElement ele, String option) {
		ele.click();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(option + Keys.ENTER);
	}

	public static void selectOptionFromSearchLBForSEO(WebDriver driver, WebElement ele, String option) {
		ele.click();
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(option + Keys.ENTER);
	}

	public static void selectOptionFromCstmLB(List<WebElement> wbLST, String option) {

		for (WebElement wb : wbLST) {
			if (wb.getText().equals(option)) {
				wb.click();
				break;
			}
		}
	}

	public static void selectValueOptionFromSelectLB(WebElement wbe, String option) {
		Select s = new Select(wbe);
		s.selectByValue(option);
	}

	public static void selectTextOptionFromSelectLB(WebElement wbe, String option) {
		Select s = new Select(wbe);
		s.selectByVisibleText(option);
	}

	public static String getSelectedOptionFromSelectLB(WebElement wbe) {
		Select s = new Select(wbe);
		return s.getFirstSelectedOption().getText();
	}

	public static ArrayList<String> getAllOptionFromSelectLB(WebElement wbe) {
		Select s = new Select(wbe);
		ArrayList<String> al = new ArrayList<String>();
		for (WebElement wb : s.getOptions()) {
			al.add(wb.getText());
		}
		return al;
	}

	
}
