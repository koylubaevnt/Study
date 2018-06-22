package ru.intuit.deepjava.firstIndependentWork.frontend.pages;

import ru.intuit.deepjava.firstIndependentWork.base.UserSession;

public interface PageGenerator {
	
		String getPage(UserSession userSession);

}
