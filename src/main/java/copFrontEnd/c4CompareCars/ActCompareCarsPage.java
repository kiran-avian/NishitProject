package copFrontEnd.c4CompareCars;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActCompareCarsPage {
	@FindBy(id = "terms")
	private WebElement hideSameSpecBtn;

	@FindBy(xpath = "(//div[@class='space-y-2'])[1]//button")
	private List<WebElement> mainSpecBtnLst;

	@FindBy(xpath = "//table")
	private List<WebElement> tableLst;
	@FindBy(css = "input[placeholder='Search Cars']")
	private WebElement searchInp;
	@FindBy(xpath = "//button[.='Safety']/../..//tr")
	private List<WebElement> safetyLst;

	@FindBy(xpath = "//button[.='Brand/Model']")
	private WebElement modelPopBtn;
	@FindBy(xpath = "//button[.='Compare']")
	private WebElement compareBtn;

	@FindBy(xpath = "//div[@role='presentation']//span[2]")
	private List<WebElement> brandLst;

	Actions act;
	WebDriverWait wait;

	public ActCompareCarsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickActCompareCarsPageHideSameSpecBtn() throws InterruptedException {
		hideSameSpecBtn.click();
	}

	public void inpActCompareCarsPageSearch(String var) throws InterruptedException {
		searchInp.sendKeys(var);

	}

	public LinkedHashMap<String, ArrayList<ArrayList<String>>> getActCompareCarsPageAddCar1(WebDriver driver)
			throws InterruptedException {
		String[] spec = { "Basic Detail", "Engine", "Fuel", "Transmission", "Suspensions & Steering", "Performance",
				"Wheels", "Dimensions", "Weights & Capacity", "Warranty", "Interior", "Exterior", "Safety" };

		LinkedHashMap<String, ArrayList<ArrayList<String>>> lmp = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
		int i = 0;
		for (WebElement tbl : tableLst) {
			ArrayList<ArrayList<String>> al1 = new ArrayList<ArrayList<String>>();
			List<WebElement> rows = tbl.findElements(By.xpath(".//tr"));
			for (WebElement r : rows) {
				ArrayList<String> al2 = new ArrayList<String>();
				List<WebElement> td = r.findElements(By.xpath(".//td"));
				for (WebElement d1 : td) {

					al2.add(d1.getText());
				}
				al1.add(al2);
			}
			lmp.put(spec[i], al1);
			i++;
		}
		return lmp;
	}

	public LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> getActCompareCarsPageImpSpecification(
			WebDriver driver,int colNo) throws InterruptedException {
		LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> lmp = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>();

		List<WebElement> specificationBtnLst = driver
				.findElements(By.xpath("(//div[@class='flex flex-col gap-y-2 mt-2'])[1]//button/div"));
		wait.until(ExpectedConditions.visibilityOfAllElements(specificationBtnLst));
		for (WebElement btn : specificationBtnLst) {
			wait.until(ExpectedConditions.visibilityOf(btn));
			String spec = btn.getText().trim();
			System.out.println(spec);
			act.click(btn).perform();
			ArrayList<LinkedHashMap<String, String>> al2 = new ArrayList<LinkedHashMap<String, String>>();
			List<WebElement> rowLst = driver.findElements(By.xpath("//div[.='" + spec + "']/ancestor::h3/..//tr"));
			for (WebElement row : rowLst) {
				wait.until(ExpectedConditions.visibilityOf(row));
				String featureKey = row.findElement(By.xpath("./td")).getText().trim();
				LinkedHashMap<String, String> lmp1 = new LinkedHashMap<String, String>();

				WebElement valueCell = row.findElement(By.xpath("./td["+(1+colNo)+"]"));
				String featureValue = valueCell.getText().trim();

				if (featureValue.isEmpty()) {
					String iconClass = valueCell.findElement(By.tagName("i")).getDomAttribute("class");
					featureValue = iconClass.contains("green") ? "Yes" : "No";
				}

				if (lmp1.putIfAbsent(featureKey, featureValue) != null) {
					System.out.println("Warning: Duplicate feature key detected -> " + featureKey);
				}
				lmp1.put(featureKey, featureValue);

				al2.add(lmp1);
			}
			al2.sort(Comparator.comparing(o -> o.keySet().iterator().next()));
			lmp.put(spec, al2);
		}
		return lmp;
	}

	public LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> getActCompareCarsPageInteExteFeatures(
			WebDriver driver,int colNo) throws InterruptedException {
		LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> lmp = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>();
		List<WebElement> featureBtnLst = driver
				.findElements(By.xpath("(//div[@class='flex flex-col gap-y-2 mt-2'])[2]//button/div"));

		wait.until(ExpectedConditions.visibilityOfAllElements(featureBtnLst));
		for (WebElement fbtn : featureBtnLst) {
			ArrayList<LinkedHashMap<String, String>> al2 = new ArrayList<LinkedHashMap<String, String>>();
			wait.until(ExpectedConditions.elementToBeClickable(fbtn));
			
			System.out.println(fbtn.getText());
			List<WebElement> rowLst = driver
					.findElements(By.xpath("//div[.='" + fbtn.getText() + "']/ancestor::h3/..//tr"));
			wait.until(ExpectedConditions.visibilityOfAllElements(rowLst));

			for (WebElement row : rowLst) {
				LinkedHashMap<String, String> lmp1 = new LinkedHashMap<String, String>();

				wait.until(ExpectedConditions.visibilityOf(row));
				String featureKey = row.findElement(By.xpath("./td")).getText().trim();

				WebElement valueCell = row.findElement(By.xpath("./td["+(1+colNo)+"]"));
				String featureValue = valueCell.getText().trim();

				if (featureValue.isEmpty()) {
					String iconClass = valueCell.findElement(By.tagName("i")).getDomAttribute("class");
					featureValue = iconClass.contains("green") ? "Yes" : "No";
				}

				if (lmp1.putIfAbsent(featureKey, featureValue) != null) {
					System.out.println("Warning: Duplicate feature key detected -> " + featureKey);
				}
				lmp1.put(featureKey, featureValue);
				al2.add(lmp1);
			}
			al2.sort(Comparator.comparing(o -> o.keySet().iterator().next()));
			lmp.put(fbtn.getText(), al2);

		}

		return lmp;
	}

	public LinkedHashMap<String, String> getActCompareCarsPageSafetyFeatures(int colNo) {
		LinkedHashMap<String, String> safetyFeaturesMap = new LinkedHashMap<>();

		for (WebElement row : safetyLst) {
			wait.until(ExpectedConditions.elementToBeClickable(row));

			String featureKey = row.findElement(By.xpath("./td[1]")).getText().trim();
			WebElement valueCell = row.findElement(By.xpath("./td["+(1+colNo)+"]"));
			String featureValue = valueCell.getText().trim();

			if (featureValue.isEmpty()) {
				String iconClass = valueCell.findElement(By.tagName("i")).getDomAttribute("class");
				featureValue = iconClass.contains("green") ? "Yes" : "No";
			}

			if (safetyFeaturesMap.putIfAbsent(featureKey, featureValue) != null) {
				System.out.println("Warning: Duplicate feature key detected -> " + featureKey);
			}
		}

		return safetyFeaturesMap;
	}

}
