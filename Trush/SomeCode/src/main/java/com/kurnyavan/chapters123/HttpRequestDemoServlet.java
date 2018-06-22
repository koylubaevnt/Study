package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRequestDemoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Obtaining the Query String</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Query String: " + req.getQueryString() + "<br>");
		out.println("The request's parameters are:<br>");
		Enumeration<String> enumeration = req.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String parameter = (String) enumeration.nextElement();
			out.println(parameter + ": " + req.getParameter(parameter) + "<br>");
		}
		out.println("<form method=get>");
		out.println("<br>First Name: <input type=text name=FirstName>");
		out.println("<br>Last Name: <input type=text name=LastName>");
		out.println("<br><input type=submit value=submit>");
		out.println("</form>");
		
		out.println("<body>");
		out.println("</html>");
	}
	
}
