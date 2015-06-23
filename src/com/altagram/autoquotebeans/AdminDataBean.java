package com.altagram.autoquotebeans;

import java.util.HashMap;
import java.util.TreeMap;

public class AdminDataBean {
	
	private HashMap<String, Double> priceList;
	private TreeMap<String, String> allLanguageNamesList;
	private TreeMap<String, String> targetLanguageNamesList;
	private TreeMap<String, String> sourceLanguageNamesList;
	
	public AdminDataBean() {
		
	}

	public HashMap<String, Double> getPriceList() {
		return priceList;
	}

	public TreeMap<String, String> getTargetLanguageNamesList() {
		return targetLanguageNamesList;
	}

	public TreeMap<String, String> getSourceLanguageNamesList() {
		return sourceLanguageNamesList;
	}
	
	public TreeMap<String, String> getAllLanguageNamesList() {
		return allLanguageNamesList;
	}

	public void setPriceList(HashMap<String, Double> priceList) {
		this.priceList = priceList;
	}

	public void setTargetLanguageNamesList(
			TreeMap<String, String> targetLanguageNamesList) {
		this.targetLanguageNamesList = targetLanguageNamesList;
	}

	public void setSourceLanguageNamesList(
			TreeMap<String, String> sourceLanguageNamesList) {
		this.sourceLanguageNamesList = sourceLanguageNamesList;
	}

	public void setAllLanguageNamesList(
			TreeMap<String, String> allLanguageNamesList) {
		this.allLanguageNamesList = allLanguageNamesList;
	}
	
	
}
