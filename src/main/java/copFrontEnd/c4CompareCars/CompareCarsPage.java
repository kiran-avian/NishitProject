package copFrontEnd.c4CompareCars;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.libraryFiles.UtilityClass;

public class CompareCarsPage {
	@FindBy(css = "img[data-nimg='1']")
	private WebElement addCarBtn;
	@FindBy(css = "img[data-nimg='1']")
	private List<WebElement> addCarBtnLst;
	@FindBy(xpath = "//ul//button")
	private List<WebElement> modelLst;
	@FindBy(css = "input[placeholder='Search Model']")
	private WebElement modelInp;
	@FindBy(css = "input[placeholder='Search variant']")
	private WebElement varientInp;
	
	@FindBy(xpath = "//button[.='Brand/Model']")
	private WebElement modelPopBtn;
	@FindBy(xpath = "//button[.='Compare']")
	private WebElement compareBtn;

	@FindBy(xpath = "//div[@role='presentation']//span[2]")
	private List<WebElement> brandLst;

	Actions act;
	WebDriverWait wait;

	public CompareCarsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	
	public void clickCompareCarsPageComparebtn()
			throws InterruptedException {
		compareBtn.click();
	}
	public void CompareCarsPageAddCar(WebDriver driver, String brand, String model, String variant) throws InterruptedException {
		addCarBtn.click();
		modelPopBtn.click();
		modelInp.sendKeys(brand);
		for (WebElement md : modelLst) {
			if (md.getText().equalsIgnoreCase(model)) {
				act.click(md).perform();
				break;
			}
		}
		Thread.sleep(2000);
		varientInp.sendKeys(variant);
		for (WebElement md : modelLst) {
			if (md.getText().equalsIgnoreCase(variant)) {
				UtilityClass.scrollUpToWebElement(driver, md);
				act.click(md).perform();
				break;
			}
		}
	}
}
