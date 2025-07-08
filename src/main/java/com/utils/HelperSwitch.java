package com.utils;

public class HelperSwitch {
	public String getExcelpathForImport(String moduleName) {
		String path = "D:\\EclipseWorkSpace\\CarOnPhoneBackEnd\\TestData\\AdminTestData\\Imports\\";

		switch (moduleName) {
		case "Brand":
			return path = path + "";
		case "LanchedEV":
			return path = path + "";
		default:
			return path = "Invalid case";
		}
		
	}
}