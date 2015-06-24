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

public class AdminServletSrcLangs extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AdminServletSrcLangs() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		TreeMap<String, String> sourceLangList = DataManagement
				.getFullLanguageNamesList(getServletContext()
						.getResourceAsStream(Constants.SOURCE_LANG_LIST_PATH));

		TreeMap<String, String> allLangList = DataManagement
				.getFullLanguageNamesList(getServletContext()
						.getResourceAsStream(Constants.ALL_LANG_LIST_PATH));
		
		TreeMap<String, String> temp = new TreeMap();
		
		AdminDataBean adminData = AdminUtils.setupLangAdminDataBean(
				sourceLangList, temp,  allLangList);
					
		request.setAttribute("adminData", adminData);

		request.getRequestDispatcher(Constants.ADMIN_SRC_LANGS_PAGE_URL).forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
