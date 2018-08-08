package com.werelich.admin_autoquote;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.werelich.autoquote.Constants;
import com.werelich.autoquote.DataManagement;
import com.werelich.autoquotebeans.AdminDataBean;

public class AdminServlet extends HttpServlet {
	//private static final long serialVefind /home/rsionUID = 1L;


	public AdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

//		
//		
//		HashMap<String, Double> priceList = DataManagement
//				.getPriceList(getServletContext().getResourceAsStream(
//						Constants.PRICE_LIST_PATH));
//
//		TreeMap<String, String> sourceLangList = DataManagement
//				.getFullLanguageNamesList(getServletContext()
//						.getResourceAsStream(Constants.SOURCE_LANG_LIST_PATH));
//
//		TreeMap<String, String> targetLangList = DataManagement
//				.getFullLanguageNamesList(getServletContext()
//						.getResourceAsStream(Constants.TARGET_LANG_LIST_PATH));
//		
//		TreeMap<String, String> allLangList = DataManagement
//				.getFullLanguageNamesList(getServletContext()
//						.getResourceAsStream(Constants.ALL_LANG_LIST_PATH));
//
//		AdminDataBean adminData = AdminUtils.setupAdminDataBean(
//				sourceLangList, targetLangList, allLangList, priceList);
//		
//		
//		
//		request.setAttribute("adminData", adminData);

		request.getRequestDispatcher(Constants.ADMIN_RESPONSE_PAGE_URL).forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
