package copFrontEnd.c8ElectricCars;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElectricCarsPage {

	@FindBy(xpath = "(//button[@role='combobox'])[1]")
	private WebElement lchMonthBtn;
	@FindBy(xpath = "//button[.='Search']")
	private WebElement searchBtn;

	@FindBy(xpath = "//a/i[@class='bx bx-right-arrow-alt text-xl']")
	private WebElement activeNextBtn;
	@FindBy(xpath = "//a[@class='group']")
	private WebElement carCardEle;
	@FindBy(xpath = "//a[@class='group']")
	private List<WebElement> carCardEleLst;
	@FindBy(xpath = "//div[@role='option']/span[2]")
	private List<WebElement> lchMonthEleLst;

	Actions act;
	WebDriverWait wait;

	public ElectricCarsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void getElectricCarsPageAllCars() throws InterruptedException {
		act.sendKeys(Keys.END).perform();
		Thread.sleep(2000);
		LinkedHashMap<String, ArrayList<String>> lmp = new LinkedHashMap<String, ArrayList<String>>();
		System.out.println(carCardEleLst.size());
		for (WebElement elw : carCardEleLst) {
			String carName = elw.findElement(By.xpath(".//h3")).getText();
			String lnk = elw.getDomAttribute("href");
			String[] Price = elw.findElement(By.xpath(".//p[1]")).getText().split(" ")[1].split("-");
			List<WebElement> RangeHp = elw.findElements(By.xpath(".//span[2]"));
			ArrayList<String> al = new ArrayList<String>();
			al.addAll(Arrays.asList(Price[0], Price[1], RangeHp.get(0).getText(), RangeHp.get(1).getText(), lnk));
			lmp.put(carName, al);
		}
		System.out.println(lmp);
	}
}
