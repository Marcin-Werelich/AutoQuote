package com.altagram.autoquote;

import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.altagram.autoquotebeans.AdminDataBean;

public class AdminAutoQuoteUtils {

	public static AdminDataBean setupAdminDataBean(
			TreeMap<String, String> sourceLangList,
			TreeMap<String, String> targetLangList,
			HashMap<String, Double> priceList) {
		
		AdminDataBean adminData = new AdminDataBean();
		adminData.setPriceList(priceList);
		adminData.setSourceLanguageNamesList(sourceLangList);
		adminData.setTargetLanguageNamesList(targetLangList);

		return adminData;
	}
	
	
}
