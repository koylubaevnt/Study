package ru.intuit.deepjava.firstIndependentWork.frontend.pages;

import ru.intuit.deepjava.firstIndependentWork.base.UserSession;

public class GamePageGeneration implements PageGenerator {

	@Override
	public String getPage(UserSession userSession) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\n")
		.append("<head>\n")
		.append("<title>Игра</title>\n")
		.append("<script language=\"JavaScript\" type=\"text/javascript\" src=\"static/jquery-1.8.2.min.js\"></script>\n")
		.append("<script src=\"static/dots.js\"></script>\n")
		.append("</head>\n")
		.append("<body>\n")
		.append("<p id=\"turn\">Turn</p>\n")
		.append("<canvas id=\"field\" height=\"500\" width=\"500\" align=\"center\" style=\"border:1px solid blue;\">\n")
		.append("	Html5 canvas is not supported\n")
		.append("</canvas>\n")
		.append("<div style=\"position: absolute; left: -100px;\">\n")
		.append("<img id=\"dot-red\" src=\"static/dot-red.png\">\n")
		.append("<img id=\"dot-blue\" src=\"static/dot-blue.png\">\n")
		.append("<img id=\"dot-red-transparent\" src=\"static/dot-red-transparent.png\">\n")
		.append("<img id=\"dot-blue-transparent\" src=\"static/dot-blue-transparent.png\">\n")
		.append("</div>\n").append("<form id=\"main_form\" method=\"POST\">\n")
		.append("	<input id=\"userId\" type=\"hidden\" name=\"userId\" value=" + userSession.getUserId() + ">\n")
		.append("	<input id=\"sessionId\" type=\"hidden\" name=\"sessionId\" value=" + userSession.getSessionId() + ">\n")
		.append("	<input id=\"myTurn\" type=\"hidden\" name=\"myTurn\" value=" + userSession.isMyTurn() + ">\n")
		.append("	<input id=\"userColor\" type=\"hidden\" name=\"userColor\" value=" + userSession.getColor() + ">\n")
		.append("</form>\n")
		.append("</body>\n")
		.append("</html>");
		return sb.toString();
	}

}
