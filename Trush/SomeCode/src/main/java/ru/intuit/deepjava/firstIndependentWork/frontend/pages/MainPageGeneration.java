package ru.intuit.deepjava.firstIndependentWork.frontend.pages;

import ru.intuit.deepjava.firstIndependentWork.base.UserSession;

public class MainPageGeneration implements PageGenerator {

	@Override
	public String getPage(UserSession userSession) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\n")
		.append("<head>\n")
		.append("<title>Главная страница</title>\n")
		.append("<style>")
		.append(".inline {")
		.append("  display: inline;")
		.append("}")
		.append(".link-button {")
		.append("  background: none;")
		.append("  border: none;")
		.append("  color: blue;")
		.append("  text-decoration: underline;")
		.append("  cursor: pointer;")
		.append("  font-size: 1em;")
		.append("  font-family: serif;")
		.append("}")
		.append(".link-button:focus {")
		.append("  outline: none;")
		.append("}")
		.append(".link-button:active {")
		.append("  color:red;")
		.append("}")
		.append("</style>")
		.append("</head>\n")
		.append("<body>\n")
		.append("<form id=\"mainForm\" method=\"POST\" action=\"/dotgames\">\n")
		.append("<input type=\"hidden\" name=\"sessionId\" value=\"" + userSession.getSessionId() + "\"/>\n")
		.append("<h3>Добро пожаловать " + userSession.getUserName() + "!</h3><br/>\n")
		.append("<h4>Здесь вы можете сыграть в следующие онлайн игры:</h4><br/>\n")
		.append("<ul>\n")
		.append("<li><button type=\"submit\" name=\"submit_param\" value=\"submit_value\" class=\"link-button\">Игра точки</button></li>\n")
		.append("</ul>\n")
		.append("</form>\n")
		.append("</body>\n")
		.append("</html>");
		return sb.toString();
	}

}
