package copFrontEnd.c5AllBrand;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.libraryFiles.UtilityClass;
import com.utils.AppiumWaitUtils;
import com.utils.WaitUtils;

import io.appium.java_client.AppiumDriver;

public class ModelPage {
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

	@FindBy(xpath = "(//div[@role='tablist'])[2]/button/strong")
	private List<WebElement> variantColorBtnLst;
	@FindBy(xpath = "//h3[.='Picked Color']/following-sibling::p")
	private WebElement colorName;
	@FindBy(xpath = "(//h4[.='Key Specs']/..)[3]//li")
	private List<WebElement> keyFeatureLst;
	@FindBy(xpath = "(//h4[.='Key Safety']/..)[6]//li/h5")
	private List<WebElement> keySafetyLst;
	@FindBy(xpath = "(//div[@class='slick-list'])[4]/../button")
	private List<WebElement> keyfeatureSafetyBtnLst;

	@FindBy(xpath = "//a[.='Specs']")
	private WebElement specificationBtn;
	@FindBy(xpath = "//a[.='Overview']")
	private WebElement 	overviewBtn;

	@FindBy(xpath = "(//div[@role='tabpanel'])[1]//button/span")
	private List<WebElement> specificationBtnLst;

	@FindBy(xpath = "//button[.='Features']")
	private WebElement featureBtn;

	@FindBy(xpath = "//div[@id='radix-:rl:-content-specTab2']//button/span")
	private List<WebElement> featureBtnLst;

	@FindBy(xpath = "//button[.='Safety']")
	private WebElement safetyBtn;
	@FindBy(xpath = "//span[.='Safety']")
	private WebElement safetyBtn1;

	@FindBy(xpath = "(//div[@role='tablist'])[1]/button")
	private List<WebElement> fuelTypeBtnLst;

	@FindBy(xpath = "//button[.='Detail']")
	private WebElement detailsBtn;
	@FindBy(linkText = "Variants")
	private WebElement variantBtn;
	@FindBy(linkText = "Price")
	private WebElement PriceBtn;
	@FindBy(xpath = "(//div[@role='tabpanel'])[1]/descendant::button/span[1]")
	private List<WebElement> exShrPriceVarLst;
	@FindBy(xpath = "(//div[@role='tabpanel'])[1]/descendant::button/span[2]")
	private List<WebElement> exShrPriceVarLst1;

	@FindBy(xpath = "//div[@class='w-full flex flex-wrap gap-y-[20px]']/div//li")
	private List<WebElement> rowLst;
	@FindBy(xpath = "//h1")
	private WebElement titleEle;
	@FindBy(linkText =   "Specs")
	private WebElement specBtn;
	
	Actions act;
	WebDriverWait wait;
	
	public ModelPage(WebDriver driver) {
		// PageFactory.initElements(driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		this.act = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	public void clickModelPageVarientHeadSpecs(WebDriver driver) {
		WaitUtils.waitToBeClickable(driver, specBtn, 10);
		specBtn.click();WaitUtils.waitToBeClickable(driver, specBtn, 10);
	}
	public void clickModelPageVarientBtn() throws InterruptedException {
		variantBtn.click();
	}
	public LinkedHashMap<String, ArrayList<ArrayList<String>>> getModelPageModelOverViewImpSpecification(WebDriver driver)
			throws InterruptedException {
		LinkedHashMap<String, ArrayList<ArrayList<String>>> al3 = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
		overviewBtn.click();
		List<WebElement> specificationBtnLst = driver
				.findElements(By.cssSelector("(//div[@dir='ltr'])[3]//button"));
		wait.until(ExpectedConditions.visibilityOfAllElements(specificationBtnLst));
		for (WebElement btn : specificationBtnLst) {
			wait.until(ExpectedConditions.visibilityOf(btn));
			String spec = btn.getText().trim();
			act.click(btn).perform();
			ArrayList<ArrayList<String>> al2 = new ArrayList<ArrayList<String>>();
			List<WebElement> rowLst = driver.findElements(By.xpath("//span[.='" + spec + "']/ancestor::div[2]//li"));
			for (WebElement r : rowLst) {
				ArrayList<String> al = new ArrayList<String>();
				List<WebElement> eles = r.findElements(By.xpath("./*"));
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

	public void clickModelPageVarientSpecs(WebDriver driver, String var) throws InterruptedException, IOException {
		Thread.sleep(500);
		By varBtnLocator = By.xpath("//a[.='" + var + "']");
		wait.until(ExpectedConditions.elementToBeClickable(varBtnLocator));
		Thread.sleep(2000);
		WebElement btn = driver.findElement(varBtnLocator); // Refetch to avoid stale reference
		// UtilityClass.scrollUpToWebElement(driver, btn);
		Thread.sleep(2000);
		act.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(varBtnLocator));
		String url = UtilityClass.getPFData("ClientURL") + btn.getDomAttribute("href");
		System.out.println();
		driver.get(url);
		// btn.click();
		// UtilityClass.jsClick(driver, btn);
		Thread.sleep(2000);
		specificationBtn.click();
	}
	public LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> getModelPageModelImpSpecificationNishit(WebDriver driver)
			throws InterruptedException {
		LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> lmp = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>();
		WaitUtils.waitToBeClickable(driver, specificationBtn, 10);
		specificationBtn.click();
		WaitUtils.waitToBeClickable(driver, specificationBtn, 10);
		List<WebElement> specificationBtnLst = driver
				.findElements(By.cssSelector("span.mr-auto.text-start.ml-\\[10px\\]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(specificationBtnLst));
		for (WebElement btn : specificationBtnLst) {
			wait.until(ExpectedConditions.visibilityOf(btn));
			String spec = btn.getText().trim();
			act.click(btn).perform();
			ArrayList<LinkedHashMap<String, String>> al2 = new ArrayList<LinkedHashMap<String, String>>();
			List<WebElement> rowLst = driver.findElements(By.xpath("//span[.='" + spec + "']/ancestor::div[2]//li"));
			for (WebElement r : rowLst) {
				
				LinkedHashMap<String, String> mp=new LinkedHashMap<String, String>();
				List<WebElement> eles = r.findElements(By.xpath("./*"));
				String key = eles.get(0).getText();
				String val = eles.get(1).getText();
				if (val.isEmpty()) {
					val=key;
				}
				mp.put(key, val);
				//System.out.println(mp.toString());
				al2.add(mp);
			}
			lmp.put(spec, al2);
		}
		return lmp;
	}
	public LinkedHashMap<String, ArrayList<ArrayList<String>>> getModelPageModelImpSpecification(WebDriver driver)
			throws InterruptedException {
		LinkedHashMap<String, ArrayList<ArrayList<String>>> al3 = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
		specificationBtn.click();
		List<WebElement> specificationBtnLst = driver
				.findElements(By.cssSelector("span.mr-auto.text-start.ml-\\[10px\\]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(specificationBtnLst));
		for (WebElement btn : specificationBtnLst) {
			wait.until(ExpectedConditions.visibilityOf(btn));
			String spec = btn.getText().trim();
			act.click(btn).perform();
			ArrayList<ArrayList<String>> al2 = new ArrayList<ArrayList<String>>();
			List<WebElement> rowLst = driver.findElements(By.xpath("//span[.='" + spec + "']/ancestor::div[2]//li"));
			for (WebElement r : rowLst) {
				ArrayList<String> al = new ArrayList<String>();
				List<WebElement> eles = r.findElements(By.xpath("./*"));
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

	public LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> getModelPageModelImpSpecification2(
			WebDriver driver) throws InterruptedException {

		LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> lmp = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>();

		specificationBtn.click();
		List<WebElement> specificationBtnLst = driver
				.findElements(By.cssSelector("span.mr-auto.text-start.ml-\\[10px\\]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(specificationBtnLst));
		for (WebElement btn : specificationBtnLst) {
			wait.until(ExpectedConditions.visibilityOf(btn));
			String spec = btn.getText().trim();
			act.click(btn).perform();
			ArrayList<LinkedHashMap<String, String>> al2 = new ArrayList<LinkedHashMap<String, String>>();
			List<WebElement> rowLst = driver.findElements(By.xpath("//span[.='" + spec + "']/ancestor::div[2]//li"));
			for (WebElement r : rowLst) {
				wait.until(ExpectedConditions.visibilityOf(r));
				ArrayList<String> al = new ArrayList<String>();
				String ele1 = r.findElement(By.xpath("./span")).getText().trim();
				String ele2 = r.findElement(By.xpath("./strong")).getText().trim();
				LinkedHashMap<String, String> lmp1 = new LinkedHashMap<String, String>();

				lmp1.put(ele1, ele2);

				al2.add(lmp1);
			}
			al2.sort(Comparator.comparing(o -> o.keySet().iterator().next()));
			lmp.put(spec, al2);
		}
		act.sendKeys(Keys.PAGE_UP).perform();
		return lmp;
	}

	public LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> getModelPageModelInteExteFeatures2(
			WebDriver driver) throws InterruptedException {
		LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> lmp = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>();
		act.sendKeys(Keys.PAGE_UP).perform();
		act.sendKeys(Keys.PAGE_UP).perform();
		// specificationBtn.click();
		act.sendKeys(Keys.PAGE_UP).perform();
		wait.until(ExpectedConditions.elementToBeClickable(featureBtn));

		featureBtn.click();
		List<WebElement> featureBtnLst = driver.findElements(By.cssSelector("span.mr-auto.text-start.ml-\\[10px\\]"));

		wait.until(ExpectedConditions.visibilityOfAllElements(featureBtnLst));
		for (WebElement fbtn : featureBtnLst) {
			wait.until(ExpectedConditions.elementToBeClickable(fbtn));
			fbtn.click();
			String featr = fbtn.getText();
			ArrayList<LinkedHashMap<String, String>> al2 = new ArrayList<LinkedHashMap<String, String>>();
			List<WebElement> rowLst = driver.findElements(By.xpath("//span[.='" + featr + "']/ancestor::div[2]//li"));
			wait.until(ExpectedConditions.visibilityOfAllElements(rowLst));
			for (WebElement r : rowLst) {
				wait.until(ExpectedConditions.visibilityOf(r));
				ArrayList<String> al = new ArrayList<String>();
				String ele1 = r.findElement(By.xpath("./span")).getText().trim();
				String ele2 = r.findElement(By.xpath("./strong")).getText().trim();
				LinkedHashMap<String, String> lmp1 = new LinkedHashMap<String, String>();

				lmp1.put(ele1, ele2);

				al2.add(lmp1);
			}
			fbtn.click();
			al2.sort(Comparator.comparing(o -> o.keySet().iterator().next()));
			lmp.put(featr, al2);
		}
		return lmp;
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

	public ArrayList<String> getModelPageVarientColorLst(WebDriver driver) throws InterruptedException {
		ArrayList<String> al = new ArrayList<String>();
		for (WebElement wb : variantColorBtnLst) {
			String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", wb);
			al.add(text);
		}
		return al;
	}

	public LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> getModelPageNonEvVarientTypeAndLst(
			WebDriver driver) throws InterruptedException {
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> lmp2 = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>();
		wait.until(ExpectedConditions.elementToBeClickable(variantBtn));
		variantBtn.click();
		Thread.sleep(8000);
		// Wait until at least one button under the first tablist is present
		By fuelButtonsLocator = By.xpath("(//div[@role='tablist'])[1]/button");

		wait.until(ExpectedConditions.presenceOfElementLocated(fuelButtonsLocator));

		// Now fetch the list
		List<WebElement> fuelTypeBtnLst = driver.findElements(fuelButtonsLocator);

		// Optional: Wait for all of them to be visible
		wait.until(ExpectedConditions.visibilityOfAllElements(fuelTypeBtnLst));

		// Iterate over the fuel type buttons
		for (WebElement btn : fuelTypeBtnLst) {
			// Map to hold variant data for the current fuel type
			LinkedHashMap<String, ArrayList<String>> lmp = new LinkedHashMap<String, ArrayList<String>>();

			wait.until(ExpectedConditions.elementToBeClickable(btn));
			String fuelName = btn.getText();

			// WebElement b1 = driver.findElement(By.xpath("//button[.='" + fuelName +
			// "']"));
			// wait.until(ExpectedConditions.elementToBeClickable(b1));
			UtilityClass.scrollUpToWebElement(driver, titleEle);
			// act.click(b1).perform();
			btn.click();

			System.out.println(fuelName);

			// Locate all variant links under the specific fuel type
			List<WebElement> variantLst = driver.findElements(By.cssSelector("div.w-full.md\\:w-\\[50\\%\\]"));
			// div[@data-state='active']/div//li/div[1]

			// Loop through each variant link
			for (WebElement lnk : variantLst) {
				wait.until(ExpectedConditions.elementToBeClickable(lnk));
				ArrayList<String> al1 = new ArrayList<>();

				// Extract car variant name
				String carVarName = lnk.findElement(By.xpath("./h3/a")).getText();
				String tf = lnk.findElement(By.xpath("./p")).getText();
				String cc = lnk.findElement(By.xpath("./div/p[1]")).getText();
				String ml = lnk.findElement(By.xpath("./div/p[2]")).getText();
				String price = lnk.findElement(By.xpath("./following-sibling::div/p[1]")).getText();
				// Add variant details to the list
				al1.addAll(Arrays.asList(tf, price, cc, ml));

				// Put variant data in the map, avoiding duplicates
				lmp.putIfAbsent(carVarName, al1);
				// System.out.println(carVarName + al1);
			}
			// Add the fuel type and its variants to the final map
			lmp2.put(fuelName, lmp);
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
		variantBtn.click();
		Thread.sleep(5000);
		LinkedHashMap<String, ArrayList<String>> lmp = new LinkedHashMap<>();

		// Use a single findElements call to get all the links instead of querying each
		// link individually
		List<WebElement> variantLinks = driver.findElements(By.xpath("//div[@data-state='active']/div//li"));

		// Iterate over the list of variant links
		// Loop through each variant link
		for (WebElement lnk : variantLinks) {
			ArrayList<String> al1 = new ArrayList<>();
			Thread.sleep(1000);
			String price = lnk.findElement(By.xpath("./div[2]/p[1]")).getText();
			String cc = lnk.findElement(By.xpath("./div[1]/div/p[1]")).getText();
			String ml = lnk.findElement(By.xpath("./div[1]/div/p[2]")).getText();
			String tf = lnk.findElement(By.xpath("./div[1]/p")).getText();

			// Add variant details to the list
			al1.addAll(Arrays.asList(tf, price, cc, ml));

			// Extract car variant name
			String carVarName = lnk.findElement(By.xpath("./div[1]/h3")).getText();

			// Put variant data in the map, avoiding duplicates
			lmp.putIfAbsent(carVarName, al1);
			System.out.println(carVarName + al1);
		}
		return lmp;
	}
	public LinkedHashMap<String, String> getModelPageModelOverviewImpSpec(WebDriver driver) throws InterruptedException {

		overviewBtn.click();
		LinkedHashMap<String, String> lmp12 = new LinkedHashMap<String, String>();
		// Thread.sleep(5000);
		WaitUtils.waitUptoVisibilityOfAllElement(driver, exShrPriceVarLst, 10);
		int i = 0;
		for (WebElement var : exShrPriceVarLst) {
			String fk = var.getDomProperty("textContent").trim().replaceAll("\\s+", " ");
			String fv = exShrPriceVarLst1.get(i).getDomProperty("textContent");
			i++;
			// Thread.sleep(200);
			if (!lmp12.containsKey(fk)) {
				lmp12.put(fk, fv);
			} else {
				System.out.println("Error= Duplicate key in hashed map found");
			}
		}
		return lmp12;
	}

	public LinkedHashMap<String, String> getModelPageModelShowroomPrice(WebDriver driver) throws InterruptedException {
        WaitUtils.waitToBeClickable(driver, PriceBtn, 10);
		PriceBtn.click();
		LinkedHashMap<String, String> lmp12 = new LinkedHashMap<String, String>();
		Thread.sleep(5000);
		 WaitUtils.waitToBeClickable(driver, PriceBtn, 10);
		WaitUtils.waitUptoVisibilityOfAllElement(driver, exShrPriceVarLst, 10);
		int i = 0;
		for (WebElement var : exShrPriceVarLst) {
			//WaitUtils.waitToBeClickable(driver, var, 10);
			//WaitUtils.waitToBeClickable(driver, exShrPriceVarLst1.get(i), 10);
			String fk = var.getDomProperty("textContent").trim().replaceAll("\\s+", " ");
			String fv = exShrPriceVarLst1.get(i).getDomProperty("textContent");
			i++;
			Thread.sleep(200);
			if (!lmp12.containsKey(fk)) {
				lmp12.put(fk, fv);
				System.out.println(lmp12.toString());
			} else {
				System.out.println("Error= Duplicate key in hashed map found");
			}
		}
		return lmp12;
	}
	public ArrayList<String> getModelPageModelShowroomPriceNishit(WebDriver driver,String var) throws InterruptedException {
        WaitUtils.waitToBeClickable(driver, PriceBtn, 10);
		PriceBtn.click();
		LinkedHashMap<String, String> lmp12 = new LinkedHashMap<String, String>();
		Thread.sleep(5000);
		 WaitUtils.waitToBeClickable(driver, PriceBtn, 10);
		WaitUtils.waitUptoVisibilityOfAllElement(driver, exShrPriceVarLst, 10);
		WebElement varName=driver.findElement(By.xpath("//span[.='"+var+"']"));
		act.click(varName).perform();
		List<WebElement> valLst=varName.findElements(By.xpath("./../../../div//strong"));
		ArrayList<String> Lst = UtilityClass.getListOfWebElement(valLst);
		////span[.='GDI DCT SX(O) Turbo P']/../../../div//strong
//		int i = 0;
//		for (WebElement var1 : exShrPriceVarLst) {
//			//WaitUtils.waitToBeClickable(driver, var, 10);
//			//WaitUtils.waitToBeClickable(driver, exShrPriceVarLst1.get(i), 10);
//			String fk = var1.getDomProperty("textContent").trim().replaceAll("\\s+", " ");
//			String fv = exShrPriceVarLst1.get(i).getDomProperty("textContent");
//			i++;
//			Thread.sleep(200);
//			if (!lmp12.containsKey(fk)) {
//				lmp12.put(fk, fv);
//				System.out.println(lmp12.toString());
//			} else {
//				System.out.println("Error= Duplicate key in hashed map found");
//			}
//		}
		return Lst;
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
		act.sendKeys(Keys.PAGE_UP).perform();
		act.sendKeys(Keys.PAGE_UP).perform();
		// specificationBtn.click();
		act.sendKeys(Keys.PAGE_UP).perform();
		wait.until(ExpectedConditions.elementToBeClickable(featureBtn));

		featureBtn.click();
		List<WebElement> featureBtnLst = driver.findElements(By.cssSelector("span.mr-auto.text-start.ml-\\[10px\\]"));

		wait.until(ExpectedConditions.visibilityOfAllElements(featureBtnLst));
		for (WebElement fbtn : featureBtnLst) {
			wait.until(ExpectedConditions.elementToBeClickable(fbtn));
			fbtn.click();
			LinkedHashMap<String, String> lmp = new LinkedHashMap<String, String>();
			wait.until(ExpectedConditions.visibilityOfAllElements(rowLst));
			for (WebElement r : rowLst) {
				wait.until(ExpectedConditions.elementToBeClickable(r));
				String fk = r.findElement(By.xpath("./span")).getText();
				String fv = r.findElement(By.xpath("./strong")).getText();
				if (!lmp.containsKey(fk)) {
					lmp.put(fk, fv);
				} else {
					System.out.println("Error= Duplicate key in hashed map found");
				}
				// System.out.println(fk + fv);
			}
			lmp1.put(fbtn.getText(), lmp);
			fbtn.click();
		}
		return lmp1;
	}

	public LinkedHashMap<String, String> getModelPageModelSafetyFeatures(WebDriver driver) throws InterruptedException {
		// specificationBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(safetyBtn));
		UtilityClass.scrollUpToWebElement(driver, safetyBtn);
		safetyBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(safetyBtn1));
		// Thread.sleep(2000);
		safetyBtn1.click();
		LinkedHashMap<String, String> lmp = new LinkedHashMap<String, String>();
		// System.out.println(rowLst.size());

		for (WebElement r : rowLst) {
			wait.until(ExpectedConditions.elementToBeClickable(r));
			String fk = r.findElement(By.xpath("./span")).getText();
			String fv = r.findElement(By.xpath("./strong")).getText();
			Thread.sleep(50);
			if (!lmp.containsKey(fk)) {
				lmp.put(fk, fv);
			} else {
				System.out.println("Error= Duplicate key in hashed map found");
			}
		}
		return lmp;
	}

}
