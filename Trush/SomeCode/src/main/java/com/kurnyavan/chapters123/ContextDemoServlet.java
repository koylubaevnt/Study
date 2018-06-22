package com.kurnyavan.chapters123;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ContextDemoServlet implements Servlet {

	ServletConfig servletConfig;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		servletConfig = config;
	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		ServletContext servletContext = servletConfig.getServletContext();
		Enumeration attributes = servletContext.getAttributeNames();
		while (attributes.hasMoreElements()) {
			String attribute = (String) attributes.nextElement();
			System.out.println("Attribute name: " + attribute);
			System.out.println("Attribute value: " + servletContext.getAttribute(attribute));
		}

		System.out.println("Major version: " + servletContext.getMajorVersion());
		System.out.println("Minor version: " + servletContext.getMinorVersion());
		System.out.println("Server info: " + servletContext.getServerInfo());
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
