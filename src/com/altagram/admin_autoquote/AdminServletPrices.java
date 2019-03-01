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

public class AdminServletPrices extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AdminServletPrices() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DataManagement dataManagement = new DataManagement(getServletContext().getResourceAsStream(
						Constants.PRICE_LIST_PATH), Constants.DATAMANAGEMENT_PRICELIST);
		

		AdminDataBean adminData = AdminUtils.setupPriceAdminDataBean(dataManagement.getPriceList());
				
		request.setAttribute("adminData", adminData);

		request.getRequestDispatcher(Constants.ADMIN_PRICES_PAGE_URL).forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
