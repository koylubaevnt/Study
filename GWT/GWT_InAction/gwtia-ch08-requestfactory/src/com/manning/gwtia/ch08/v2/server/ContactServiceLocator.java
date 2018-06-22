package com.manning.gwtia.ch08.v2.server;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class ContactServiceLocator implements ServiceLocator {

	private static ContactService serviceInstance;
	
	@Override
	public Object getInstance(Class<?> clazz) {
		return ContactServiceLocator.getServiceInstance();
	}

	private static ContactService getServiceInstance() {
		if(serviceInstance == null) {
			serviceInstance = new ContactService();
		}
		return serviceInstance;
		/*
		In practice, your getInstance() might do a little more than create a new service
		instance. For example, if you use Spring, you’ll want to go into the Spring container
		and fetch the service bean. Here’s an example of what that might look like:
		
		 	HttpServletRequest request =
			RequestFactoryServlet.getThreadLocalRequest();
			ServletContext servletCtx = request.getSession().getServletContext();
			ApplicationContext springCtx =
			WebApplicationContextUtils.getWebApplicationContext(servletCtx);
			return springCtx.getBean(ContactService.class)
			
	 	The reason for having to do all of this work is that the ServiceLocator will be
		instantiated by the RequestFactoryServlet and not your Spring (or any other) container. You need to first get a handle on the container before you can get the bean.
		 */
	}
}
