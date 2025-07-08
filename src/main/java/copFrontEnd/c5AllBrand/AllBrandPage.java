package copFrontEnd.c5AllBrand;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.libraryFiles.UtilityClass;

import io.appium.java_client.AppiumDriver;


public class AllBrandPage {
	@FindBy(xpath = "//a/h5")
	private List<WebElement> allBrandLst;
	@FindBy(xpath = "//a/h5")
	private WebElement allBrand;
	
	Actions act;	
	public AllBrandPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}
	public ArrayList<String> getAllBrandPageLst() {	
		return UtilityClass.getListOfWebElement(allBrandLst);
	}
	public void clickAllBrandPageBrandBtn(WebDriver driver,String brand) {
		WebElement wb = driver.findElement(By.xpath("//a/h5[.='"+brand+"']"));
		act.click(wb).perform();
	}
}
