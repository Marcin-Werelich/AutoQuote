package com.werelich.admin_autoquote;

import java.util.TreeMap;

import com.werelich.autoquotebeans.AdminDataBean;

import java.util.TreeMap;

public class AdminUtils {

	public static AdminDataBean setupAdminDataBean(
			TreeMap<String, String> sourceLangList,
			TreeMap<String, String> targetLangList,
			TreeMap<String, String> allLangList,
			TreeMap<String, Double> priceList) {
		
		AdminDataBean adminData = new AdminDataBean();
		adminData.setPriceList(priceList);
		adminData.setSourceLanguageNamesList(sourceLangList);
		adminData.setTargetLanguageNamesList(targetLangList);
		adminData.setAllLanguageNamesList(allLangList);
		return adminData;
	}
	
	public static AdminDataBean setupAdminDataBean(
			TreeMap<String, String> sourceLangList,
			TreeMap<String, String> targetLangList,
			TreeMap<String, Double> priceList) {
		
		AdminDataBean adminData = new AdminDataBean();
		adminData.setPriceList(priceList);
		adminData.setSourceLanguageNamesList(sourceLangList);
		adminData.setTargetLanguageNamesList(targetLangList);
		return adminData;
	}
	
	public static AdminDataBean setupLangAdminDataBean(
			TreeMap<String, String> sourceLangList,
			TreeMap<String, String> targetLangList,
			TreeMap<String, String> allLangList) {
		
		AdminDataBean adminData = new AdminDataBean();
		adminData.setAllLanguageNamesList(allLangList);
		adminData.setSourceLanguageNamesList(sourceLangList);
		adminData.setTargetLanguageNamesList(targetLangList);
		return adminData;
	}
	
	public static AdminDataBean setupPriceAdminDataBean(
			TreeMap<String, Double> priceList) {
		
		AdminDataBean adminData = new AdminDataBean();
		adminData.setPriceList(priceList);

		return adminData;
	}
	
	
}
