package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie c1 = new Cookie("userName", "Helen");
		Cookie c2 = new Cookie("password", "Keppler");
		resp.addCookie(c1);
		resp.addCookie(c2);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Cookie Test</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<br>Please click the button to see the cookies sent to you.");
		out.println("<br>");
		out.println("<form method=post>");
		out.println("<input type=submit>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Cookie Test</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Here are all the headers.</h2>");
		
		Enumeration<String> enums = req.getHeaderNames();
		while (enums.hasMoreElements()) {
			String header = (String) enums.nextElement();
			out.print("<b>" + header + "</b>: ");
			out.print(req.getHeader(header) + "<br>");
		}
		out.println("<br><br><h2>And, here are all the cookies.</h2>");
		Cookie[] cookies = req.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			out.println("<b>Cookie Name:</b> " + cookie.getName() + "<br>");
			out.println("<b>Cookie Value:</b> " + cookie.getValue() + "<br>");
		}
			out.println("</body>");
		out.println("</html>");
	}
}
