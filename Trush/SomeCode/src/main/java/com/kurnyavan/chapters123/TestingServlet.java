package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestingServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Servlet Testing</TITLE>");
		out.println("<HEAD>");
		out.println("<BODY>");
		out.println("Welcome to the Servlet Testing Center");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
	}
	
}
