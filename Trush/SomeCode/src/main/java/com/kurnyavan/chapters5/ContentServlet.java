package com.kurnyavan.chapters5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentServlet extends HttpServlet {

	public String loginUrl = "CockieLoginServlet";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		int length = cookies.length;
		String userName = null;
		String password = null;
		
		for(int i = 0; i < length; i++) {
			Cookie cookie = cookies[i];
			if(cookie.getName().equals("userName"))
				userName = cookie.getValue();
			else if(cookie.getName().equals("password"))
				password = cookie.getValue();
		}
		
		if(userName == null || password == null || !CookieLoginServlet.login(userName, password))
			resp.sendRedirect(loginUrl);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Welcome</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Welcome.");		
		out.println("<body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
