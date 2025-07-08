package com.dataProviders.beDP;


import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.dataProviders.ExcelDPMethods;

public class ModelDP {

	static String path = ".\\TestData\\ExcelModelDP.xlsx";
	public static int FR, LR;
	public static String Sheet;
	public static void setParam(String sh,int FirstRow, int LastRow) {
		Sheet=sh;
		FR = FirstRow;
		LR = LastRow;
	}
	@DataProvider(name = "ModelDS")

	public static String[][] brandDS() throws IOException {
		//return ExcelDPMethods.allData(path,"AddBrand");
		return ExcelDPMethods.selectiveData(path,Sheet,FR,LR);
	}


}
