package ru.intuit.deepjava.firstIndependentWork.frontend.pages;

import ru.intuit.deepjava.firstIndependentWork.base.UserSession;

public class WaitGamePageGeneration implements PageGenerator {

	@Override
	public String getPage(UserSession userSession) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\n")
		.append("<head>\n")
		.append("<title>Ожидание другого участника игры</title>\n")
		.append("<script>\n")
		.append("function refresh() {\n")
		.append("		document.forms['mainForm'].submit();\n")
		.append("	}\n")
		.append("</script>\n")
		.append("<body onload=\"setTimeout(function(){refresh();}, 5000)\">\n")
		.append("</head>\n")
		.append("<body>\n")
		.append("<form id=\"mainForm\" method=\"POST\">\n")
		.append("<input type=\"hidden\" name=\"sessionId\" value=\"" + userSession.getSessionId() + "\"/>\n")
		.append("<h4>Ожидайте другого участника игры...</h4>\n")
		.append("</form>\n")
		.append("</body>\n")
		.append("</html>");
		return sb.toString();
	}

}
