package ru.intuit.deepjava.firstIndependentWork.frontend.pages;

import ru.intuit.deepjava.firstIndependentWork.base.UserSession;

public class AutorizationPageGeneration implements PageGenerator {

	@Override
	public String getPage(UserSession userSession) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\n")
		.append("<head>\n")
		.append("<title>Авторизация</title>\n")
		.append("</head>\n")
		.append("<body>\n")
		.append("<form id=\"mainForm\" method=\"POST\">\n")
		.append("<input type=\"hidden\" name=\"sessionId\" value=\"" + userSession.getSessionId() + "\"/>\n")
		.append("Имя пользователя <input type=\"text\" name=\"userName\" required placeholder=\"Имя пользователя\"/><br/>\n")
		.append("<input type=\"submit\" value=\"Войти\"/>\n")
		.append("</form>\n")
		.append("</body>\n")
		.append("</html>");
		return sb.toString();
	}

}
