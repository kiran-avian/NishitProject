package com.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormaterMethods {
	 public static String DateFormatToyyyyMMdd(String inputDate) {
	        // Define the formatter for the input date
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");

	        // Parse the input date string into a LocalDate
	        LocalDate date = LocalDate.parse(inputDate, inputFormatter);

	        // Define the formatter for the output date
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        // Format the date into the desired output format and return
	        return date.format(outputFormatter);
	    }
}
