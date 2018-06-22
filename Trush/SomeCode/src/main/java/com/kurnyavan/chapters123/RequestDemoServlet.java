package com.kurnyavan.chapters123;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RequestDemoServlet implements Servlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("Server Port: " + req.getServerPort());
		System.out.println("Server Name: " + req.getServerName());
		System.out.println("Protocol: " + req.getProtocol());
		System.out.println("Character Encoding: " + req.getCharacterEncoding());

		System.out.println("Content Type: " + req.getContentType());
		System.out.println("Content Length: " + req.getContentLength());
		System.out.println("Remote Address: " + req.getRemoteAddr());
		System.out.println("Remote Host: " + req.getRemoteHost());
		System.out.println("Scheme: " + req.getScheme());
		
		Enumeration<String> parameters = req.getParameterNames();
		while (parameters.hasMoreElements()) {
			String parameter = (String) parameters.nextElement();
			System.out.println("Parameter name: " + parameter);
			System.out.println("Parameter value: " + req.getParameter(parameter));
		}
		
		Enumeration<String> attributes = req.getAttributeNames();
		while (attributes.hasMoreElements()) {
			String attribute = (String) attributes.nextElement();
			System.out.println("Attribute name: " + attribute);
			System.out.println("Attribute value: " + req.getAttribute(attribute));
		}
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
