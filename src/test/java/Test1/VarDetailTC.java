
package Test1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dataProviders.beDP.ModelDP;
import com.libraryFiles.BaseClass;
import com.utils.DataCompareMethods;

import copFrontEnd.SelectCityPopUp;
import copFrontEnd.HomePage.HomePage;
import copFrontEnd.c5AllBrand.AllBrandPage;
import copFrontEnd.c5AllBrand.BrandPage;
import copFrontEnd.c5AllBrand.ModelPage;

public class VarDetailTC extends BaseClass {
	static {
		ModelDP.setParam("Sheet1", 3, 3);
	}
	SoftAssert soft;
	BrandPage brandPg;
	ModelPage modelPg;
	SelectCityPopUp selCityPop;
	AllBrandPage abp;
	HomePage homepage;
	//String Fuel = "nEV", Brand = "Hyundai ", Model = "Creta ", Var = "S(O) D MT DT Knight";

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

	@Test(dataProvider = "ModelDS", dataProviderClass = ModelDP.class, enabled = true)
	public void getModelPageDataTest(String Sr,String Brand, String Model, String Var ) throws IOException, InterruptedException {
		homepage.inpHomePageSearchBox(driver, Brand + " "+Model + " "+ Var);
		modelPg.clickModelPageVarientHeadSpecs(driver);

		LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> impSpecMap = modelPg
				.getModelPageModelImpSpecificationNishit(driver);
		System.out.println("ImpSpecMap=" + impSpecMap.toString());

		String Engine = DataCompareMethods.getImpSpecValue(impSpecMap, "Engine", "Displacement");
		String Power = DataCompareMethods.getImpSpecValue(impSpecMap, "Engine", "Power");
		String Transmission = DataCompareMethods.getImpSpecValue(impSpecMap, "Transmission", "Type of Transmission");
		String Mileage = DataCompareMethods.getImpSpecValue(impSpecMap, "Fuel", "Mileage");

		String Fuel = DataCompareMethods.getImpSpecValue(impSpecMap, "Fuel", "Type of Fuel");
		String Tank_Capacity = DataCompareMethods.getImpSpecValue(impSpecMap, "Fuel", "Fuel Tank Capacity");
		String Gear_Box = DataCompareMethods.getImpSpecValue(impSpecMap, "Transmission", "Gear Box");
		String Tyre_Size = DataCompareMethods.getImpSpecValue(impSpecMap, "Wheels", "Tyre Size");
		String Seating_Capacity = DataCompareMethods.getImpSpecValue(impSpecMap, "Weights & Capacity",
				"Seating Capacity");
		String Warranty_years = DataCompareMethods.getImpSpecValue(impSpecMap, "Warranty", "Warranty (Years)");
		String Warranty_km = DataCompareMethods.getImpSpecValue(impSpecMap, "Warranty", "Warranty (Kilometers)");

		System.out.println("engine=" + Engine);
		System.out.println("Power=" + Power);
		System.out.println("Transmission=" + Transmission);
		System.out.println("Mileage=" + Mileage);
		System.out.println("Fuel=" + Fuel);
		System.out.println("Tank_Capacity=" + Tank_Capacity);
		System.out.println("Gear_Box=" + Gear_Box);
		System.out.println("Tyre_Size=" + Tyre_Size);
		System.out.println("Seating_Capacity=" + Seating_Capacity);
		System.out.println("Warranty_years=" + Warranty_years);
		System.out.println("Warranty_km=" + Warranty_km);

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
	        dataMap.put("Engine", "1497 cc");
	        dataMap.put("Power", "113.18 bhp @ 6300 rpm bhp");
	        dataMap.put("Transmission", "Manual");
	        dataMap.put("Mileage", "16.76 kmpl");
	        dataMap.put("Fuel", "Petrol");
	        
	        dataMap.put("", "");
	        dataMap.put("KEY FEATURES", "");        
	        dataMap.put("Tank Capacity", "50 litres");
	        dataMap.put("Gear Box", "6-Speed");
	        dataMap.put("Tyre Size", "205/65 R16");
	        dataMap.put("Seating Capacity", "5");
	        dataMap.put("Warranty (Years)", "5");
	        dataMap.put("Warranty (Kilometers)", "Unlimited");
	        
	        dataMap.put(" ", " ");
	        dataMap.put("PRICE TABLE", "");
	        dataMap.put("Ex-showroom", "₹ 16,35,100");
	        dataMap.put("Insurance", "₹ 98,106");
	        dataMap.put("RTO", "₹ 89,931");
	        dataMap.put("On Road", "₹ 18,39,488");

	        // === Create Excel Workbook and Sheet ===
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet(Brand + Model + Var);

	        int rowIndex = 0;
	        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
	            Row row = sheet.createRow(rowIndex++);
	            Cell keyCell = row.createCell(0);
	            Cell valueCell = row.createCell(1);

	            keyCell.setCellValue(entry.getKey());
	            valueCell.setCellValue(entry.getValue());
	        }

	        // === Auto-size Columns ===
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);

	        // === Write to Excel File ===
	        String filePath = ".\\OutputCarData\\OutputCar.xlsx";
	        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	            workbook.write(fileOut);
	        }
	        workbook.close();

	        System.out.println("Excel file created: " + filePath);
	    
		
	}

}
