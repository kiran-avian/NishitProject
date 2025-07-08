package com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.testng.asserts.SoftAssert;

public class DataCompareMethods {

	public static void arrayListVsArrayCompare(SoftAssert soft, ArrayList<String> actLast, String[] expLst) {

		ArrayList<String> al = new ArrayList<String>(Arrays.asList(expLst));
		arrayListCompare(soft, actLast, al);
		allUnmatched(actLast, al);
	}

	public static void arrayListCompare(SoftAssert soft, ArrayList<String> actLst, ArrayList<String> expLst) {
		System.out.println("actLst" + actLst);
		System.out.println("expLst" + expLst);
		soft.assertEquals(actLst.toString(), expLst.toString(), "List are not equal");
	}

	public static void arrayListCompareMoreThan2(SoftAssert soft, ArrayList<String>... lists) {
		// Print all lists for debugging
		for (ArrayList<String> list : lists) {
			System.out.println(list);
		}

		// Compare each list with the first one
		for (int i = 1; i < lists.length; i++) {
			soft.assertEquals(lists[0].toString(), lists[i].toString(),
					"List " + (i + 1) + " is not equal to the first list.");
		}
	}

	public static void allUnmatched(ArrayList<String> actlist1, ArrayList<String> explist2) {
		// Create copies of the lists to preserve the original data
		ArrayList<String> unmatchedFromActList = new ArrayList<>(actlist1);
		ArrayList<String> unmatchedFromExpList = new ArrayList<>(explist2);

		// Find unmatched items
		unmatchedFromActList.removeAll(explist2); // Elements in actlist1 but not in explist2
		unmatchedFromExpList.removeAll(actlist1); // Elements in explist2 but not in actlist1

		// Combine unmatched data if needed
		ArrayList<String> allUnmatched = new ArrayList<>(unmatchedFromActList);
		allUnmatched.addAll(unmatchedFromExpList);

		// Print results
		System.out.println("Missing Data(in explist but not in actlist): " + unmatchedFromExpList);
		System.out.println("Extra Data  (in actlist but not in explist): " + unmatchedFromActList);
		// Uncomment to see the combined unmatched data if needed
		// System.out.println("All Unmatched Data: " + allUnmatched);
	}

	public static ArrayList<String> getFieldForwhichErrorNotDisplayed(ArrayList<String> firstArray,
			ArrayList<String> secondArray) {
		ArrayList<String> notFound = new ArrayList<String>();
		for (String secondElement : secondArray) {
			boolean found = false;
			for (String firstElement : firstArray) {
				if (firstElement.startsWith(secondElement)) {
					found = true;
					break;
				}
			}
			if (!found) {
				notFound.add(secondElement);
			}
		}

		if (notFound.isEmpty()) {
			System.out.println("All elements of the second array are starting points in the first array.");
		} else {
			System.out
					.println("The following elements of the second array are not starting points in the first array:");
			for (String element : notFound) {
				System.out.println(element);
			}
		}
		return notFound;
	}

	public static String getImpSpecValue(LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> specMap,
			String sectionKey, String itemKey) {
		if (specMap.containsKey(sectionKey)) {
			ArrayList<LinkedHashMap<String, String>> sectionList = specMap.get(sectionKey);
			for (LinkedHashMap<String, String> item : sectionList) {
				if (item.containsKey(itemKey)) {
					return item.get(itemKey); // return the value of the matching key
				}
			}
		}
		return null; // if not found
	}
	public static void destructure(String[] arr) {
	    for (int i = 0; i < arr.length; i++) {
	        System.out.println("element " + i + " = " + arr[i]);
	    }
	}
}
