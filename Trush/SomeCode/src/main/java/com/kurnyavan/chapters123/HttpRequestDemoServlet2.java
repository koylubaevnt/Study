package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRequestDemoServlet2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Obtaining Multi-Value Parameters</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Select you favorite music:");
		out.println("<form method=post>");
		out.println("<br><input type=checkbox name=favoriteMusic value=Rock>Rock");
		out.println("<br><input type=checkbox name=favoriteMusic value=Jazz>Jazz");
		out.println("<br><input type=checkbox name=favoriteMusic value=Classical>Classical");
		out.println("<br><input type=checkbox name=favoriteMusic value=Country>Country");
		out.println("<br><input type=submit value=submit>");
		out.println("</form>");
		
		out.println("<body>");
		out.println("</html>");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String[] values = req.getParameterValues("favoriteMusic");
		PrintWriter out = resp.getWriter();
		if(values != null) {
			int length = values.length;
			out.println("You are selected:");
			for(int i = 0; i < length; i++) {
				out.println("<br>" + values[i]);
			}
		}
		out.flush();
	}
}
