package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Page1Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendPage1(resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendPage1(resp);
	}
	
	private void sendPage1(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Page 1</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h2>Page 1</h2>");
		out.println("<br>");
		out.println("<br>");
		out.println("<br>Please edit the first name and last name.");
		out.println("<br>");
		out.println("<br>");
		out.println("<br><form method=post action=Page2Servlet>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>First Name&nbsp;</td>");
		out.println("<td><input type=text name=firstName></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Last Name&nbsp;</td>");
		out.println("<td><input type=text name=lastName></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><input type=reset></td>");
		out.println("<td><input type=submit></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}
	
	public static String fixSQLField(String value) {
		if(value == null)
			return null;
		int length = value.length();
		StringBuffer buffer = new StringBuffer((int) (length * 1.1));
		for(int i = 0; i < length; i++) {
			char c = value.charAt(i);
			if(c =='\'')
				buffer.append("''");
			else
				buffer.append(c);
		}
		return buffer.toString();
	}
	
	private static String encodeHtmlTag(String tag) {
		if(tag == null)
			return null;
		int length = tag.length();
		StringBuffer encodedTag = new StringBuffer(2 * length);
		for(int i = 0; i < length; i++) {
			char c = tag.charAt(i);
			if(c == '<')
				encodedTag.append("&lt;");
			else if(c == '>')
				encodedTag.append("&gt;");
			else if(c == '&')
				encodedTag.append("&amp;");
			else if(c == '"')
				encodedTag.append("&quot;");
			else if(c == ' ')
				encodedTag.append("&nbsp;");
			else
				encodedTag.append(c);
		}
		return encodedTag.toString();
	}
}
