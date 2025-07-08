package com.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SortMethods {
	public static LinkedHashMap<String, String> sortByKey(LinkedHashMap<String, String> inputMap) {
	    return inputMap.entrySet()
	            .stream()
	            .sorted(Map.Entry.comparingByKey()) // Sort by key
	            .collect(Collectors.toMap(
	                    Map.Entry::getKey,
	                    Map.Entry::getValue,
	                    (oldVal, newVal) -> oldVal,
	                    LinkedHashMap::new
	            ));
	}

}
