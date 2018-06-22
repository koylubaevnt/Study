package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SimpleServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Extending GeneralServlet</TITLE>");
		out.println("<HEAD>");
		out.println("<BODY>");
		out.println("Extending GeneralServlet makes your code simpler.");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
	}

}
