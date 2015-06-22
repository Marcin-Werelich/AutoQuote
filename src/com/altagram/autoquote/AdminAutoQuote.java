package com.altagram.autoquote;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.altagram.autoquotebeans.AdminDataBean;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class AdminAutoQuote extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AdminAutoQuote() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		
		HashMap<String, Double> priceList = AutoQuoteUtils
				.getPriceList(getServletContext().getResourceAsStream(
						Constants.PRICE_LIST_PATH));

		TreeMap<String, String> sourceLangList = AutoQuoteUtils
				.getFullLanguageNamesList(getServletContext()
						.getResourceAsStream(Constants.SOURCE_LANG_LIST_PATH));

		TreeMap<String, String> targetLangList = AutoQuoteUtils
				.getFullLanguageNamesList(getServletContext()
						.getResourceAsStream(Constants.TARGET_LANG_LIST_PATH));

		AdminDataBean adminData = AdminAutoQuoteUtils.setupAdminDataBean(
				sourceLangList, targetLangList, priceList);

		request.setAttribute("adminData", adminData);

		request.getRequestDispatcher(Constants.ADMIN_RESPONSE_PAGE_URL).forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
