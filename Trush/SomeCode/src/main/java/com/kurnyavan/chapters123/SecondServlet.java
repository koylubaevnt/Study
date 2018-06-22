package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		Enumeration<String> enums = req.getAttributeNames();
		while (enums.hasMoreElements()) {
			String attrinbute = (String) enums.nextElement();
			out.println(attrinbute + ": " + req.getAttribute(attrinbute) + "<br>");
		}
		out.println("==============<br>");
		enums = req.getParameterNames();
		while (enums.hasMoreElements()) {
			String parameter = (String) enums.nextElement();
			out.println(parameter + ": " + req.getParameter(parameter) + "<br>");
		}
	}
	
}
