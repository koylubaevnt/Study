package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Included Request Parameters</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<b>Included Request Parameters</b><br>");
		RequestDispatcher rd = req.getRequestDispatcher("/Second?name=budi");
		rd.include(req, resp);		
		out.println("<body>");
		out.println("</html>");
	}
	
}