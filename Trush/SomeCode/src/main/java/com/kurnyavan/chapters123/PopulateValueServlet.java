package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PopulateValueServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = "Duncan \"The Great\" Young";
		String password = "lo&&lota";
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Populate HTML elements</title>");
		out.println("</head>");
		out.println("<h3>You user name and password.</h3>");
		out.println("<form method=post>");
		out.println("<br>User name: <input type=text name=userName value=\"" + encodeHtmlTag(userName) + "\">");
		out.println("<br>Password: <input type=text name=password value=\"" + password + "\">");
		out.println("<body>");
		out.println("<body>");
		out.println("</html>");
	}
	

	public static String encodeHtmlTag(String tag) {
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
