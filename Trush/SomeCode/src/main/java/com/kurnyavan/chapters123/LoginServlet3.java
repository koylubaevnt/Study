package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet3 extends HttpServlet {

		
	private void sendLoginForm(HttpServletResponse response, boolean withErrorMessage) throws ServerException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		if(withErrorMessage)
			out.println("Login failed. Please try again.<br>");
		out.println("<br>");
		out.println("<br>Please enter your user name and password.");
		out.println("<br><form method=post>");
		out.println("<br>User Name: <input type=text name=userName>");
		out.println("<br>Password: <input type=text name=password>");
		out.println("<br><input type=submit value=submit>");
		out.println("</form>");		
		out.println("<body>");
		out.println("</html>");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendLoginForm(resp, false);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String passwprd = req.getParameter("password");
		if(userName != null && passwprd != null &&
				userName.equals("jamesb") && passwprd.equals("007")) {
			RequestDispatcher rd = req.getRequestDispatcher("WelcomeServlet");
			rd.forward(req, resp);
		} else {
			sendLoginForm(resp, true);
		}
	}
	
}
