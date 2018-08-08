package com.werelich.autoquote;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class DataManagement {

	public static TreeMap<String, Double> getPriceList(InputStream input)
			throws IOException {

		List<String> lineList = new ArrayList<String>();
		TreeMap<String, Double> priceList = new TreeMap<String, Double>();
		String[] lineSplit;

		Scanner inputReader = new Scanner(new InputStreamReader(
				input));

		while (inputReader.hasNextLine()) {
			lineList.add(inputReader.nextLine());
		}

		for (String line : lineList) {
			if (line != null && !line.matches("")) {
				lineSplit = line.split("\t");
				priceList.put(lineSplit[0] + ">" + lineSplit[1],
						Double.parseDouble(lineSplit[2]));
			}
		}
		inputReader.close();
		return priceList;
	}

	public static double getPrice(String sourceLanguage, String targetLanguage,
			TreeMap<String, Double> priceList) {
		String[] entrySplit;
		for (Entry<String, Double> entry : priceList.entrySet()) {
			entrySplit = entry.getKey().toString().split(">");
			if (entrySplit[0].matches(sourceLanguage)
					&& entrySplit[1].matches(targetLanguage))
				return (double) entry.getValue();
		}
		return 0;
	}

	public static TreeMap<String, String> getFullLanguageNamesList(
			InputStream input) throws IOException {
		List<String> lineList = new ArrayList<String>();
		TreeMap<String, String> langList = new TreeMap<String, String>();
		String[] lineSplit;
		Scanner inputReader = new Scanner(new InputStreamReader(
				input));

		while (inputReader.hasNextLine()) {
			lineList.add(inputReader.nextLine());
		}

		for (String line : lineList) {
			if (line != null) {
				lineSplit = line.split("\t");
				langList.put(lineSplit[1], lineSplit[0]);
			}
		}	
		inputReader.close();
		return langList;
	}

	public static String getFullLanguageName(String languageShortName,
			TreeMap<String, String> langList) {

		for (Entry<String, String> entry : langList.entrySet()) {
			if (entry.getValue().matches(languageShortName))
				return entry.getKey();
		}
		return null;
	}

	
}
