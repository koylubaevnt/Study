package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>The GET method</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("The servlet has received a GET. Now, click the button below.");
		out.println("<br>");
		out.println("<form method=post>");
		out.println("<input type=submit value=submit>");
		out.println("</form><br>");
		
		Enumeration<String> enumeration = req.getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String header = (String) enumeration.nextElement();
			out.println(header + ": " + req.getHeader(header) + "<br>");
		}
		
		out.println("<body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>The POST method</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("The servlet has received a POST. Thank you.");
		out.println("<body>");
		out.println("</html>");
	}
}
