package copFrontEnd.HomePage;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {

	@FindBy(xpath = "//a[.=' view all ']")
	private WebElement viewAllBrandBtn;
	@FindBy(css = "input[placeholder='Search Cars']")
	private WebElement searchBoxInp;
	@FindBy(xpath = "//div[@id='search-results']/ul/li/a/span")
	private List<WebElement> searchBoxEleLst;
	
	Actions act;
WebDriverWait wait;
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}

	public void clickHomePageViewAllBrandBtn() throws InterruptedException {
		act.scrollToElement(viewAllBrandBtn).sendKeys(Keys.ARROW_DOWN).perform();
		viewAllBrandBtn.click();
	}
	public void clickHomePageSearchBox() throws InterruptedException {
        searchBoxInp.click();}
	public void inpHomePageSearchBox(WebDriver driver, String model) throws InterruptedException {
        searchBoxInp.sendKeys(model);
    //    System.out.println(model); 
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfAllElements(searchBoxEleLst));
		 
		for (WebElement wb : searchBoxEleLst) {
			// System.out.println(wb.getText()); 
			if (wb.getText().equalsIgnoreCase(model)) {
				act.click(wb).perform();
				break;
			}
		}
	//	searchBoxInp.sendKeys(Keys.ENTER);
		
	}
	
}
