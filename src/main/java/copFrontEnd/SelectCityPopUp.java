package copFrontEnd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.WaitUtils;


public class SelectCityPopUp {
	@FindBy(xpath = "//li/h6")
	private List<WebElement> popularCityLst;

	@FindBy(xpath = "//ul[@id='globalOtherCities']/li")
	private List<WebElement> globalOtherCityLst;

	Actions act;	
	public SelectCityPopUp(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}
	public void clickSelectCityPopUp(WebDriver driver) {
		WebElement cityBtn=driver.findElement(By.xpath("//li/button[.='Surat']"));
		cityBtn.click();
		WaitUtils.waitUntilInvisibility(driver, cityBtn, 10);
	}
}
