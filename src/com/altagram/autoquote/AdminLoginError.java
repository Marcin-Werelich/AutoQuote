package com.altagram.autoquote;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class AdminLoginError extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AdminLoginError() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher(Constants.ADMIN_LOGIN_ERROR_RESPONSE_PAGE_URL).forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		String username = request.getParameter("username").toString();
//		String password = request.getParameter("password").toString();
//		
//		if (username.matches(Constants.ADMIN_USER_NAME) && password.matches(Constants.ADMIN_PASSWORD)) 
//			response.sendRedirect(Constants.ADMIN_REDIRECT_URL);
//		
//		else
//			request.getRequestDispatcher(Constants.ADMIN_LOGIN_ERROR_RESPONSE_PAGE_URL).forward(request,
//					response);
	}
}
