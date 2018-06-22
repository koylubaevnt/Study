package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpecialCharacterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>HTML Tutorial - Changing Line</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("In HTML, you use:");
		//<
		out.println("<br>&lt;");
		//>
		out.println("<br>&gt;");
		//&
		out.println("<br>&amp;");
		//"
		out.println("<br>&quot;");
		//"SPACE
		out.println("<br>Text&nbsp&nbsp&nbspText");
		out.println("In HTML, you use <br> to change line.");
		out.println(encodeHtmlTag("In HTML, you use <br> to change line."));
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
