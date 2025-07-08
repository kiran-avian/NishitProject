package copFrontEnd.c5AllBrand;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.libraryFiles.UtilityClass;

public class ModelPageOld {
	@FindBy(xpath = "(//div[@class='slick-track'])[2]/div")
	private List<WebElement> colorLst;
	@FindBy(xpath = "//h4[.='Description']/following-sibling::div/p/*")
	private List<WebElement> descriptionLst;
	@FindBy(xpath = "//span[.='View More']")
	private WebElement viewMoreBtn;
	@FindBy(xpath = "//h4[contains(.,'Description')]")
	private WebElement DescriptionBody;
	@FindBy(xpath = "(//ul[@class='flex flex-col gap-y-2'])[2]//a")
	private List<WebElement> eleInEachLst;

	@FindBy(xpath = "(//div[@dir='ltr']/child::button[2])[1]")
	private WebElement variantColorBannerBtn;
	@FindBy(xpath = "(//div[@dir='ltr']/child::button[2])[1]")
	private List<WebElement> variantColorBannerBtnLst;
	@FindBy(xpath = "//h3[text()='Picked Color']/following-sibling::p")
	private WebElement colorName;
	@FindBy(xpath = "(//h4[.='Key Specs']/..)[3]//li")
	private List<WebElement> keyFeatureLst;
	@FindBy(xpath = "(//h4[.='Key Safety']/..)[6]//li/h5")
	private List<WebElement> keySafetyLst;
	@FindBy(xpath = "(//div[@class='slick-list'])[4]/../button")
	private List<WebElement> keyfeatureSafetyBtnLst;

	@FindBy(xpath = "//button[contains(.,'Specification')]")
	private WebElement specificationBtn;
	@FindBy(xpath = "//div[@class='flex flex-col gap-y-2']/descendant::button//span")
	private List<WebElement> specificationBtnLst;

	@FindBy(xpath = "//button[.='Features']")
	private WebElement featureBtn;

	@FindBy(xpath = "//div[@class='flex flex-col gap-y-2']//span")
	private List<WebElement> featureBtnLst;

	@FindBy(xpath = "//button[contains(.,'Safety')]")
	private WebElement safetyBtn;

	@FindBy(xpath = "(//div[@role='tablist'])[2]/button")
	private List<WebElement> fuelTypeBtnLst;

	@FindBy(xpath = "//button[.='Detail']")
	private WebElement detailsBtn;
	@FindBy(xpath = "(//tbody)[13]/tr/td[1]")
	private List<WebElement> exShrPriceLst;
	@FindBy(xpath = "//ul[@class='relative']/li")
	private List<WebElement> rowLst;

	Actions act;
	WebDriverWait wait;

	public ModelPageOld(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public LinkedHashMap<String, ArrayList<ArrayList<String>>> getModelPageModelImpSpecification(WebDriver driver)
			throws InterruptedException {
		UtilityClass.scrollUpToWebElement(driver, specificationBtn);
		act.click(specificationBtn).perform();
		LinkedHashMap<String, ArrayList<ArrayList<String>>> al3 = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
		for (WebElement btn : specificationBtnLst) {
			String spec = btn.getText().trim();
			System.out.println(spec);
			act.click(btn).perform();
			ArrayList<ArrayList<String>> al2 = new ArrayList<ArrayList<String>>();
			List<WebElement> rowLst = driver.findElements(By.xpath("//span[.='" + spec + "']/ancestor::div[2]//li"));
			for (WebElement r : rowLst) {
				ArrayList<String> al = new ArrayList<String>();
				List<WebElement> eles = r.findElements(By.xpath("./p"));
				for (WebElement ele : eles) {
					String val = ele.getText();
					if (val.isEmpty()) {
						System.out.println(ele);
					}
					al.add(val);
				}
				al2.add(al);
			}
			al3.put(spec, al2);
		}
		return al3;
	}

	public void expandModelPageDropdown(WebDriver driver, WebElement element) throws InterruptedException {
		if (element.getDomAttribute("aria-expanded").equals("false")) {
			UtilityClass.scrollUpToWebElement(driver, element);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			act.click(element).perform();
			System.out.println(element.getText() + " is expanded");
		} else {
			System.out.println(element.getText() + " area already expanded");
		}
		Thread.sleep(1000);
	}

	public void compressModelPageDropdown(WebDriver driver, WebElement element) throws InterruptedException {
		if (element.getDomAttribute("aria-expanded").equals("true")) {
			UtilityClass.scrollUpToWebElement(driver, element);
			Thread.sleep(300);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			act.click(element).perform();
			try {
				wait.until(ExpectedConditions.domAttributeToBe(element, "aria-expanded", "false"));
			} catch (org.openqa.selenium.TimeoutException e) {
				act.click(element).perform();
			}
			Thread.sleep(3000);
		} else {
			System.out.println("area already comressed" + element);
		}
	}

	public ArrayList<String> getModelPageVarientColorLst() throws InterruptedException {
		ArrayList<String> al = new ArrayList<String>();
		System.out.println(variantColorBannerBtnLst.size());
		if (variantColorBannerBtnLst.size() == 0) {
			for (WebElement wb : colorLst) {
				wb.click();
				al.add(colorName.getDomProperty("innerHTML"));
			}
			System.out.println(al);
			return al;
		} else {
			al.add(colorName.getText());
			System.out.println(colorName.getText());
			Thread.sleep(500);
			variantColorBannerBtn.click();
			Thread.sleep(500);
			do {
				String color = colorName.getText();
				// System.out.println(color);
				al.add(color);
				variantColorBannerBtn.click();
				Thread.sleep(500);
				wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(colorName, color)));
			} while (!al.get(0).equals(colorName.getText()));
			return al;
		}
	}

	

	public LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> getModelPageNonEvVarientTypeAndLst(
			WebDriver driver) throws InterruptedException {
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> lmp2 = new LinkedHashMap<>();

		// Iterate over the fuel type buttons
		for (WebElement btn: fuelTypeBtnLst) {
			//WebElement btn = fuelTypeBtnLst.get(i);

			// Click the fuel type button
			act.click(btn).perform();
			System.out.println(btn.getText());

			// Map to hold variant data for the current fuel type
			LinkedHashMap<String, ArrayList<String>> lmp = new LinkedHashMap<>();

			// Locate all variant links under the specific fuel type
			List<WebElement> variantLst = driver
					.findElements(By.xpath("(//div[@data-state='active'])[2]//a"));

			// Loop through each variant link
			for (WebElement lnk : variantLst) {
				ArrayList<String> al1 = new ArrayList<>();

				// Get the car variant details
				List<WebElement> eleInLst = lnk.findElements(By.xpath("./div/*"));
				String price = lnk.findElement(By.xpath("./p")).getText();
				String link = lnk.getDomAttribute("href");

				// Add variant details to the list
				al1.addAll(Arrays.asList(eleInLst.get(1).getText(), price, link));

				// Extract car variant name
				String carVarName = eleInLst.get(0).getText();

				// Put variant data in the map, avoiding duplicates
				lmp.putIfAbsent(carVarName, al1);
			}
			// Add the fuel type and its variants to the final map
			lmp2.put(btn.getText(), lmp);
		}
		return lmp2;
	}
	public LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> getModelPageNonEvVarientTypeAndLst1(
			WebDriver driver) throws InterruptedException {
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> lmp2 = new LinkedHashMap<>();

		// Iterate over the fuel type buttons
		for (int i = 0; i < fuelTypeBtnLst.size(); i++) {
			WebElement btn = fuelTypeBtnLst.get(i);

			// Click the fuel type button
			act.click(btn).perform();
			System.out.println(btn.getText());

			// Map to hold variant data for the current fuel type
			LinkedHashMap<String, ArrayList<String>> lmp = new LinkedHashMap<>();

			// Locate all variant links under the specific fuel type
			List<WebElement> variantLst = driver
					.findElements(By.xpath("(//div[@role='tablist'])[2]/following-sibling::div[" + (i + 1) + "]//a"));

			// Loop through each variant link
			for (WebElement lnk : variantLst) {
				ArrayList<String> al1 = new ArrayList<>();

				// Get the car variant details
				List<WebElement> eleInLst = lnk.findElements(By.xpath("./div/*"));
				String price = lnk.findElement(By.xpath("./p")).getText();
				String link = lnk.getDomAttribute("href");

				// Add variant details to the list
				al1.addAll(Arrays.asList(eleInLst.get(1).getText(), price, link));

				// Extract car variant name
				String carVarName = eleInLst.get(0).getText();

				// Put variant data in the map, avoiding duplicates
				lmp.putIfAbsent(carVarName, al1);
			}
			// Add the fuel type and its variants to the final map
			lmp2.put(btn.getText(), lmp);
		}
		return lmp2;
	}

	public LinkedHashMap<String, ArrayList<String>> getModelPageEvVarientTypeAndLst(WebDriver driver)
			throws InterruptedException {
		LinkedHashMap<String, ArrayList<String>> lmp = new LinkedHashMap<>();

		// Use a single findElements call to get all the links instead of querying each
		// link individually
		List<WebElement> variantLinks = driver.findElements(By.xpath("(//ul[@class='flex flex-col gap-y-2'])[2]//a"));

		// Iterate over the list of variant links
		for (WebElement lnk : variantLinks) {
			ArrayList<String> al1 = new ArrayList<>();
			// Get the href link directly
			String link = lnk.getDomAttribute("href");
			// Extract the variant details
			List<WebElement> eleInLst = lnk.findElements(By.xpath("./div/*"));
			// Extract price and car variant name
			String price = lnk.findElement(By.xpath("./p")).getText();
			String carVarName = eleInLst.get(0).getText();
			// Store the extracted details in the ArrayList
			al1.addAll(Arrays.asList(eleInLst.get(1).getText(), price, link));
			// Use putIfAbsent to automatically handle duplicate variant names
			if (lmp.putIfAbsent(carVarName, al1) != null) {
				System.out.println(carVarName + " Error= Duplicate car name found");
			}
		}
		return lmp;
	}

	public LinkedHashMap<String, String> getModelPageModelShowroomPrice(WebDriver driver) throws InterruptedException {

		LinkedHashMap<String, String> lmp = new LinkedHashMap<String, String>();
		System.out.println(exShrPriceLst.size());
		Thread.sleep(500);
		// Waits.waitUptoVisibilityOfAllElement(driver, inteFeatureLst, 10);
		for (int i = 1; i <= exShrPriceLst.size(); i++) {
			Thread.sleep(200);
			WebElement wb = driver.findElement(By.xpath("((//tbody)[13]/tr/td[1])[" + i + "]"));
			act.moveToElement(wb);
			Thread.sleep(1000);
			String fk = wb.getDomProperty("textContent").trim().replaceAll("\\s+", " ");
			String fv = driver.findElement(By.xpath("((//tbody)[13]/tr/td[2])[" + i + "]"))
					.getDomProperty("textContent").trim().replaceAll("\\s+", " ");
			Thread.sleep(200);
			if (!lmp.containsKey(fk)) {
				lmp.put(fk, fv);
			} else {
				System.out.println("Error= Duplicate key in hashed map found");
			}
		}
		return lmp;
	}

	public ArrayList<ArrayList<String>> getModelPageModelKeySpec() {
		ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
		for (WebElement key : keyFeatureLst) {
			ArrayList<String> al2 = new ArrayList<String>();
			String name = key.findElement(By.xpath("./h5")).getText().trim();
			String value = key.findElement(By.xpath("./p")).getText().trim();
			al2.addAll(Arrays.asList(name, value));
			al.add(al2);
		}
		return al;
	}

	public ArrayList<String> getModelPageModelKeySafety() throws InterruptedException {
		ArrayList<String> al2 = new ArrayList<String>();
		System.out.println(keyfeatureSafetyBtnLst.size());
		if (keyfeatureSafetyBtnLst.size() == 2) {
			keyfeatureSafetyBtnLst.get(1).click();
			for (WebElement key : keySafetyLst) {
				act.moveToElement(key).perform();
				al2.add(key.getText().trim());
			}
		}
		return al2;
	}

	public ArrayList<String> getModelPageDescriptionLst(WebDriver driver) throws InterruptedException {
		UtilityClass.scrollUpToWebElement(driver, DescriptionBody);
		Thread.sleep(1000);
		viewMoreBtn.click();
		return UtilityClass.getListOfWebElement(descriptionLst);
	}

	public LinkedHashMap<String, LinkedHashMap<String, String>> getModelPageModelInteExteFeatures(WebDriver driver)
			throws InterruptedException {
		LinkedHashMap<String, LinkedHashMap<String, String>> lmp1 = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		Thread.sleep(200);
		detailsBtn.click();
		Thread.sleep(200);
		featureBtn.click();
		Thread.sleep(200);
		for (WebElement fbtn : featureBtnLst) {
			fbtn.click();
			LinkedHashMap<String, String> lmp = new LinkedHashMap<String, String>();
			for (WebElement r : rowLst) {
				String fk = r.findElement(By.xpath("./p[1]")).getText();
				String fv = r.findElement(By.xpath("./p[2]")).getText();
				Thread.sleep(200);
				if (!lmp.containsKey(fk)) {
					lmp.put(fk, fv);
				} else {
					System.out.println("Error= Duplicate key in hashed map found");
				}
			}
			lmp1.put(fbtn.getText(), lmp);
			fbtn.click();
		}
		return lmp1;
	}

	public LinkedHashMap<String, String> getModelPageModelSafetyFeatures(WebDriver driver) throws InterruptedException {
		detailsBtn.click();
		Thread.sleep(500);
		act.click(safetyBtn).perform();
		Thread.sleep(500);
		LinkedHashMap<String, String> lmp = new LinkedHashMap<String, String>();
		System.out.println(rowLst.size());

		for (WebElement r : rowLst) {
			String fk = r.findElement(By.xpath("./p[1]")).getText();
			String fv = r.findElement(By.xpath("./p[2]")).getText();
			Thread.sleep(200);
			if (!lmp.containsKey(fk)) {
				lmp.put(fk, fv);
			} else {
				System.out.println("Error= Duplicate key in hashed map found");
			}
		}
		return lmp;
	}

}
