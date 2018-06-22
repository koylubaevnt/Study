package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ResponseDemoServlet implements Servlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>ServletResponse</TITLE>");
		out.println("<HEAD>");
		out.println("<BODY>");
		out.println("<b>Demonstrating the ServletResponse object</b>");
		out.println("<br>");
		out.println("<br>Server Port: " + req.getServerPort());
		out.println("<br>Server Name: " + req.getServerName());
		out.println("<br>Protocol: " + req.getProtocol());
		out.println("<br>Character Encoding: " + req.getCharacterEncoding());

		out.println("<br>Content Type: " + req.getContentType());
		out.println("<br>Content Length: " + req.getContentLength());
		out.println("<br>Remote Address: " + req.getRemoteAddr());
		out.println("<br>Remote Host: " + req.getRemoteHost());
		out.println("<br>Scheme: " + req.getScheme());
		
		Enumeration<String> parameters = req.getParameterNames();
		while (parameters.hasMoreElements()) {
			String parameter = (String) parameters.nextElement();
			out.println("<br>Parameter name: " + parameter);
			out.println("<br>Parameter value: " + req.getParameter(parameter));
		}
		
		Enumeration<String> attributes = req.getAttributeNames();
		while (attributes.hasMoreElements()) {
			String attribute = (String) attributes.nextElement();
			out.println("<br>Attribute name: " + attribute);
			out.println("<br>Attribute value: " + req.getAttribute(attribute));
		}
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
