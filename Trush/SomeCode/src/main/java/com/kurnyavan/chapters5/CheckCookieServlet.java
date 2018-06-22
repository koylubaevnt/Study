package com.kurnyavan.chapters5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		if(req.getParameter("flag") == null) {
			Cookie cookie = new Cookie("browserSetting", "on");
			resp.addCookie(cookie);
			String nextUrl = req.getRequestURI() + "?flag=1";
			out.println("<META HTTP-EQUIV=\"Refresh\" CONTENT=\"0; URL=" + nextUrl + "\">");
		} else {
			Cookie[] cookies = req.getCookies();
			if(cookies != null) {
				boolean cookieFound = false;
				for(int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if(cookie.getName().equals("browserSetting") && cookie.getValue().equals("on")) {
						cookieFound = true;
						break;
					}
				}
				if(cookieFound)
					out.println("Your browser's cookie setting is on.");
				else {
					out.println("Your browser does not support cookies or" +
							" the cookie setting is off.");
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
