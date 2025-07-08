package copFrontEnd.c5AllBrand;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.libraryFiles.UtilityClass;
import com.utils.SelectionMethod;

public class BrandPage {
	@FindBy(xpath = "//div[@class='px-1 lg:px-0']/a[1]")
	private List<WebElement> allModelLst;
	@FindBy(xpath = "//div[@class='hidden lg:gap-3 lg:grid-cols-3 lg:grid']/a/h5")
	private List<WebElement> brandLst;
	@FindBy(xpath = "//button[@role='combobox']")
	private WebElement brandsSel;
	@FindBy(css = "div > button[role='tab']")
	private List<WebElement> btnLst;
	@FindBy(xpath = "//button[contains(.,'All')]")
	private WebElement allBtn;
	@FindBy(xpath = "//button[contains(.,'SUV')]")
	private WebElement suvBtn;
	@FindBy(xpath = "//button[contains(.,'Sedan')]")
	private WebElement sedanBtn;
	@FindBy(xpath = "//button[contains(.,'Hatchback')]")
	private WebElement hatchbackBtn;
	@FindBy(xpath = "//button[contains(.,'MPV')]")
	private WebElement mpvBtn;
	
	Actions act;	
	public BrandPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}
	public void clickBrandPageAllBtn() {		
		allBtn.click();
	}
	public String getBrandPageSelectedBrand(WebDriver driver) {	
		Dimension d =new Dimension(300,720);
		driver.manage().window().setSize(d);
		String st = SelectionMethod.getSelectedOptionFromSelectLB(brandsSel);
		driver.manage().window().maximize();
		return st;
	}
	public ArrayList<String> getBrandPageBrandSelestLB(WebDriver driver) {
		Dimension d =new Dimension(300,720);
		driver.manage().window().setSize(d);
		ArrayList<String> Lst = SelectionMethod.getAllOptionFromSelectLB(brandsSel);
		driver.manage().window().maximize();
		Lst.remove(0);
		return Lst;
	}
	public ArrayList<String> getBrandPageBrandLst() {		
		return UtilityClass.getListOfWebElement(brandLst);
	}
	public ArrayList<String> getBrandPageModelLst() {		
		return UtilityClass.getListOfWebElement(allModelLst);
	}
	public ArrayList<String> getBrandPageAllCategoryModelSumLst() {		
		ArrayList<String> al=new ArrayList<String>();
		btnLst.remove(0);
		btnLst.remove(0);
		for(int i=1;i<btnLst.size();i++) {
			btnLst.get(i).click();
			al.addAll(getBrandPageModelLst());
		}
		return al;
	}
	public void clickBrandPageModelBtn(WebDriver driver,String model) {		
		
		WebElement wb = driver.findElement(By.xpath("//a[contains(.,'"+model+"')]"));
		act.click(wb).perform();
	}
	
	
}
