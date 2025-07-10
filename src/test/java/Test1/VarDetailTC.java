
package Test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dataProviders.beDP.ModelDP;
import com.libraryFiles.BaseClass;
import com.libraryFiles.UtilityClass;
import com.utils.DataCompareMethods;

import copFrontEnd.SelectCityPopUp;
import copFrontEnd.HomePage.HomePage;
import copFrontEnd.c5AllBrand.AllBrandPage;
import copFrontEnd.c5AllBrand.BrandPage;
import copFrontEnd.c5AllBrand.ModelPage;

public class VarDetailTC extends BaseClass {
	static {
		ModelDP.setParam("Sheet1", 1, 3);
	}
	SoftAssert soft;
	BrandPage brandPg;
	ModelPage modelPg;
	SelectCityPopUp selCityPop;
	AllBrandPage abp;
	HomePage homepage;
	// String Fuel = "nEV", Brand = "Hyundai ", Model = "Creta ", Var = "S(O) D MT
	// DT Knight";

	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();

		// driver.get("http://caronphone.com/maruti-suzuki-cars/eeco");
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

	@Test(dataProvider = "ModelDS", dataProviderClass = ModelDP.class)
	public void getModelPageDataTest(String Sr, String Brand, String Model, String Var, String FuelType)
			throws IOException, InterruptedException {

		String url = "https://caronphone.com/"; 
		String uri1= Brand.toLowerCase().replace(" ", "-") + "-cars/"
				+ Model.toLowerCase().replace(" ", "-") + "/" + Var.toLowerCase().replace(" ", "-");

		driver.get(url+uri1.replace("+", "plus").replace(".", "").replace("(", "").replace(")", "") + "/specifications");
	
		// modelPg.clickModelPageVarientHeadSpecs(driver);
		// homepage.inpHomePageSearchBox(driver, Brand + " " + Model + " " + Var);

		String Engine = "", Power = "", Transmission = "", Mileage = "", Fuel = "", Tank_Capacity = "", Gear_Box = "",
				Range = "", Charging_Time_AC = "", Charging_Time_DC = "", Charging_Port = "";
		LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> impSpecMap = null;
		if (impSpecMap != null) {
			impSpecMap.clear();
		}
		impSpecMap = modelPg.getModelPageModelImpSpecificationNishit(driver, Var);

		System.out.println("ImpSpecMap=" + impSpecMap.toString());

		if (FuelType.trim().equalsIgnoreCase("nev")) {

			Engine = DataCompareMethods.getImpSpecValue(impSpecMap, "Engine", "Displacement");
			Power = DataCompareMethods.getImpSpecValue(impSpecMap, "Engine", "Power");
			Transmission = DataCompareMethods.getImpSpecValue(impSpecMap, "Transmission", "Type of Transmission");
			Mileage = DataCompareMethods.getImpSpecValue(impSpecMap, "Fuel", "Mileage");
			Fuel = DataCompareMethods.getImpSpecValue(impSpecMap, "Fuel", "Type of Fuel");
			Tank_Capacity = DataCompareMethods.getImpSpecValue(impSpecMap, "Fuel", "Fuel Tank Capacity");
			Gear_Box = DataCompareMethods.getImpSpecValue(impSpecMap, "Transmission", "Gear Box");
			System.out.println("engine=" + Engine);
			System.out.println("Power=" + Power);
			System.out.println("Transmission=" + Transmission);
			System.out.println("Mileage=" + Mileage);
			System.out.println("Fuel=" + Fuel);
			System.out.println("Tank_Capacity=" + Tank_Capacity);
			System.out.println("Gear_Box=" + Gear_Box);
		} else if (FuelType.trim().equalsIgnoreCase("ev")) {

			Engine = DataCompareMethods.getImpSpecValue(impSpecMap, "Engine", "Battery Capacity");
			Power = DataCompareMethods.getImpSpecValue(impSpecMap, "Engine", "Power (EV)");
			Range = DataCompareMethods.getImpSpecValue(impSpecMap, "Engine", "Range");
			Charging_Time_AC = DataCompareMethods.getImpSpecValue(impSpecMap, "Fuel", "Charging Time (AC)");
			Charging_Time_DC = DataCompareMethods.getImpSpecValue(impSpecMap, "Fuel", "Charging Time (DC)");
			Charging_Port = DataCompareMethods.getImpSpecValue(impSpecMap, "Fuel", "Charging Port");
			System.out.println("Battery Capacity=" + Engine);
			System.out.println("Power (EV)=" + Power);
			System.out.println("Range=" + Range);
			System.out.println("Charging Time AC=" + Charging_Time_AC);
			System.out.println("Charging Time DC=" + Charging_Time_DC);
			System.out.println("Charging Port=" + Charging_Port);
		}
		String Tyre_Size = DataCompareMethods.getImpSpecValue(impSpecMap, "Wheels", "Tyre Size");
		String Seating_Capacity = DataCompareMethods.getImpSpecValue(impSpecMap, "Weights & Capacity",
				"Seating Capacity");
		String Warranty_years = DataCompareMethods.getImpSpecValue(impSpecMap, "Warranty", "Warranty (Years)");
		String Warranty_km = DataCompareMethods.getImpSpecValue(impSpecMap, "Warranty", "Warranty (Kilometers)");

		System.out.println("Tyre_Size=" + Tyre_Size);
		System.out.println("Seating_Capacity=" + Seating_Capacity);
		System.out.println("Warranty_years=" + Warranty_years);
		System.out.println("Warranty_km=" + Warranty_km);

		String uri2 =  Brand.toLowerCase().replace(" ", "-") + "-cars/"
				+ Model.toLowerCase().replace(" ", "-");

		driver.get(url+uri2.replace("+", "plus").replace(".", "").replace("(", "").replace(")", "")+ "/price");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		ArrayList<String> Price = modelPg.getModelPageModelShowroomPriceNishit(driver, Var);
		System.out.println("Price=" + Price.toString());
		String Ex_showroom = "₹ " + Price.get(0);
		String Insurance = "₹ " + Price.get(1);
		String Rto = "₹ " + Price.get(2);
		String On_Road = Price.get(3);

		System.out.println("Ex_showroom=" + Ex_showroom);
		System.out.println("Insurance=" + Insurance);
		System.out.println("Rto=" + Rto);
		System.out.println("On_Road=" + On_Road);
		soft.assertAll();

		Map<String, String> dataMap = new LinkedHashMap<>();
		dataMap.put(Brand + Model + Var, "");
		dataMap.put("   ", "   ");
		dataMap.put("SPECIFICATION", "");

		if (FuelType.trim().equalsIgnoreCase("nev")) {
			dataMap.put("Engine", Engine);
			dataMap.put("Power", Power);
			dataMap.put("Transmission", Transmission);
			dataMap.put("Mileage", Mileage);
			dataMap.put("Fuel", Fuel);
			dataMap.put("", "");
			dataMap.put("KEY FEATURES", "");
			dataMap.put("Tank Capacity", Tank_Capacity);
			dataMap.put("Gear Box", Gear_Box);
		} else if (FuelType.trim().equalsIgnoreCase("ev")) {
			dataMap.put("Battery Capacity", Engine);
			dataMap.put("Power (EV)", Power);
			dataMap.put("Range", Range);
			dataMap.put("", "");
			dataMap.put("KEY FEATURES", "");
			dataMap.put("Charging Time (AC)", Charging_Time_AC);
			dataMap.put("Charging Time (DC)", Charging_Time_DC);
			dataMap.put("Charging Port", Charging_Port);
		}
		dataMap.put("Tyre Size", Tyre_Size);
		dataMap.put("Seating Capacity", Seating_Capacity);
		dataMap.put("Warranty (Years)", Warranty_years);
		dataMap.put("Warranty (Kilometers)", Warranty_km);

		dataMap.put(" ", " ");
		dataMap.put("PRICE TABLE", "");
		dataMap.put("Ex-showroom", Ex_showroom);
		dataMap.put("Insurance", Insurance);
		dataMap.put("RTO", Rto);
		dataMap.put("On Road", On_Road);

		writeCarDataToExcel(Brand, Model, Var, dataMap);

	}

	public static void writeCarDataToExcel(String brand, String model, String var, Map<String, String> dataMap)
			throws IOException {
		String filePath = ".\\OutputCarData\\OutputCar.xlsx";
		XSSFWorkbook workbook;

		File file = new File(filePath);

		// 1. If file exists, open it; else create new workbook
		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file)) {
				workbook = new XSSFWorkbook(fis);
			}
		} else {
			workbook = new XSSFWorkbook();
		}

		// 2. Create new sheet with Brand+Model+Var
		String sheetName = brand + model + var;

		// ⚠️ If sheet already exists, remove it
		XSSFSheet existingSheet = workbook.getSheet(sheetName);
		if (existingSheet != null) {
			int index = workbook.getSheetIndex(existingSheet);
			workbook.removeSheetAt(index);
		}

		// Now create the new sheet
		XSSFSheet sheet = workbook.createSheet(sheetName);

		// 3. Write data
		int rowIndex = 0;
		for (Map.Entry<String, String> entry : dataMap.entrySet()) {
			Row row = sheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(entry.getKey());
			row.createCell(1).setCellValue(entry.getValue());
		}

		// 4. Auto-size columns
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);

		// 5. Save workbook back to file
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			workbook.write(fos);
		}

		workbook.close();
		System.out.println("✅ Sheet '" + sheetName + "' written to: " + filePath);
	}
}
