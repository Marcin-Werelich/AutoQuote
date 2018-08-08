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

public class AdminServletPrices extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AdminServletPrices() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		
		TreeMap<String, Double> priceList = DataManagement
				.getPriceList(getServletContext().getResourceAsStream(
						Constants.PRICE_LIST_PATH));

		AdminDataBean adminData = AdminUtils.setupPriceAdminDataBean(priceList);
				
		request.setAttribute("adminData", adminData);

		request.getRequestDispatcher(Constants.ADMIN_PRICES_PAGE_URL).forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
