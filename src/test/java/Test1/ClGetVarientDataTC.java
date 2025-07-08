package Test1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.libraryFiles.BaseClass;

import copFrontEnd.SelectCityPopUp;
import copFrontEnd.HomePage.HomePage;
import copFrontEnd.c5AllBrand.AllBrandPage;
import copFrontEnd.c5AllBrand.BrandPage;
import copFrontEnd.c5AllBrand.ModelPage;

public class ClGetVarientDataTC extends BaseClass {
	SoftAssert soft;
	BrandPage brandPg;
	ModelPage modelPg;
	SelectCityPopUp selCityPop;
	AllBrandPage abp;
	HomePage homepage;
	String Fuel = "nEV", Brand = "Hyundai ", Model = "Creta ", Var = "S(O) D MT DT Knight";

	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();
		
		//driver.get("http://caronphone.com/maruti-suzuki-cars/eeco");
		// Thread.sleep(5000);
		selCityPop = new SelectCityPopUp(driver);
		homepage = new HomePage(driver);
		abp = new AllBrandPage(driver);
		brandPg = new BrandPage(driver);
		modelPg = new ModelPage(driver);
		selCityPop.clickSelectCityPopUp(driver);
	}

	@BeforeMethod
	public void login() throws IOException {
		soft = new SoftAssert();
	}

	@Test(enabled = true)
	public void getModelPageDataTest() throws IOException, InterruptedException {
		homepage.inpHomePageSearchBox(driver, Brand + Model + Var);

		ArrayList<String> colorLst = modelPg.getModelPageVarientColorLst(driver);
		System.out.println("Color=" + colorLst);

		if (Fuel != "EV") {
			LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> nonEvVarientTypLst = modelPg
					.getModelPageNonEvVarientTypeAndLst(driver);
			System.out.println(nonEvVarientTypLst.toString());
			System.out.println("ALLvarientTypLst=   " + nonEvVarientTypLst.get("All").size());
			System.out.println("ALLvarientTypLst=   " + nonEvVarientTypLst.get("All").toString());
			System.out.println("PETROLvarientTypLst=" + nonEvVarientTypLst.get("Petrol").size());
			System.out.println("PETROLvarientTypLst=" + nonEvVarientTypLst.get("Petrol").toString());
			System.out.println("CNGvarientTypLst=   " + nonEvVarientTypLst.get("CNG").size());
			System.out.println("CNGvarientTypLst=   " + nonEvVarientTypLst.get("CNG").toString());
		} else {
			LinkedHashMap<String, ArrayList<String>> evVarientTypLst = modelPg.getModelPageEvVarientTypeAndLst(driver);
			System.out.println(evVarientTypLst);
			System.out.println(evVarientTypLst.size());
		}

		// ArrayList<ArrayList<String>> KeySpecs = modelPg.getModelPageModelKeySpec();
		// System.out.println("KeySpecs=" + KeySpecs.toString());

		// ArrayList<String> KeySafety = modelPg.getModelPageModelKeySafety();
		// System.out.println("KeySafety=" + KeySafety.toString());

		// ArrayList<String> description = modelPg.getModelPageDescriptionLst(driver);
		// System.out.println("Description=" + description);

		LinkedHashMap<String, ArrayList<ArrayList<String>>> impSpecMap = modelPg
				.getModelPageModelImpSpecification(driver);
		System.out.println("ImpSpecMap=" + impSpecMap.toString());

		LinkedHashMap<String, LinkedHashMap<String, String>> inteExteFeature = modelPg
				.getModelPageModelInteExteFeatures(driver);
		System.out.println("InteFeature= " + inteExteFeature.get("Interior").toString());
		System.out.println("ExteFeature= " + inteExteFeature.get("Exterior").toString());

		LinkedHashMap<String, String> safetyFeature = modelPg.getModelPageModelSafetyFeatures(driver);
		System.out.println("SafetyFeature=" + safetyFeature.toString());

		System.out.println(inteExteFeature.get("Interior").size());
		System.out.println(inteExteFeature.get("Exterior").size());
		System.out.println(safetyFeature.size());

		LinkedHashMap<String, String> exShowroomPrice = modelPg.getModelPageModelShowroomPrice(driver);
		System.out.println("exShowroomPrice=" + exShowroomPrice.toString());

		LinkedHashMap<String, String> overviewImp = modelPg.getModelPageModelOverviewImpSpec(driver);
		System.out.println(overviewImp);

		soft.assertAll();
	}

}
