package com.manning.gwtia.ch08.shared.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.validation.Validation;

import org.hibernate.validator.HibernateValidator;

@SuppressWarnings("serial")
public class BootstrapValidationServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("BootstrapValidationServlet - init()");
		Validation.byProvider(HibernateValidator.class).configure();
		System.out.println("BootstrapValidationServlet - inited()");
		
	}
	
}
