package com.altagram.admin_autoquote;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.altagram.autoquote.Constants;
import com.altagram.autoquote.DataManagement;
import com.altagram.autoquotebeans.AdminDataBean;

public class AdminServletTgtLangs extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AdminServletTgtLangs() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		TreeMap<String, Double> priceList = DataManagement
//				.getPriceList(getServletContext().getResourceAsStream(
//						Constants.PRICE_LIST_PATH));
		
		DataManagement priceListManagement = new DataManagement(getServletContext().getResourceAsStream(
				Constants.PRICE_LIST_PATH), Constants.DATAMANAGEMENT_PRICELIST);

		TreeMap<String, String> temp = new TreeMap();

//		TreeMap<String, String> targetLangList = DataManagement
//				.getFullLanguageNamesList(getServletContext()
//						.getResourceAsStream(Constants.TARGET_LANG_LIST_PATH));
		

		DataManagement targetLangListManagement = new DataManagement(getServletContext()
				.getResourceAsStream(Constants.TARGET_LANG_LIST_PATH), Constants.DATAMANAGEMENT_LANGS);
		
//		TreeMap<String, String> allLangList = DataManagement
//				.getFullLanguageNamesList(getServletContext()
//						.getResourceAsStream(Constants.ALL_LANG_LIST_PATH));
		
		DataManagement allLangListManagement = new DataManagement(getServletContext()
				.getResourceAsStream(Constants.ALL_LANG_LIST_PATH), Constants.DATAMANAGEMENT_LANGS);

		AdminDataBean adminData = AdminUtils.setupAdminDataBean(
				temp, targetLangListManagement.getFullLanguageNamesList(), allLangListManagement.getFullLanguageNamesList(), priceListManagement.getPriceList());
		
		
		
		request.setAttribute("adminData", adminData);

		request.getRequestDispatcher(Constants.ADMIN_TGT_LANGS_PAGE_URL).forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
