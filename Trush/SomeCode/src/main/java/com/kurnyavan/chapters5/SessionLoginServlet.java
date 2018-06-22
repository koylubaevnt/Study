package com.kurnyavan.chapters5;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kurnyavan.utils.StringUtil;

public class SessionLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendLoginForm(resp, false);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		if(login(userName,password)) {
			HttpSession session = req.getSession(true);
			session.setAttribute("loggedIn", "true");
			resp.sendRedirect("Content2Servlet");
		} else {
			sendLoginForm(resp, true);
		}
	}

		
	private void sendLoginForm(HttpServletResponse response, boolean withErrorMessage) throws ServerException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		if(withErrorMessage) {
			out.println("Login failed. Please try again.<br>");
			out.println("If you think you have entered the correct user name" +
			" and password, the cookie setting in your browser might be off." +
			"<br>Click <a href=InfoPage.html>here</a> for information" + 
			" on how to turn it on.<br>");
		}
		out.println("<br>");
		out.println("<br><h2>Login Page</h2>");
		out.println("<br>");
		out.println("<br>Please enter your user name and password.");
		out.println("<br>");
		out.println("<br><form method=post>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>User Name:</td>");
		out.println("<td><input type=text name=userName></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Password:</td>");
		out.println("<td><input type=text name=password></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td align=right colspan=2><input type=submit value=submit></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");		
		out.println("<body>");
		out.println("</html>");
	}
	
	
	public static boolean login(String userName, String password) {
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			//Connection connection = DriverManager.getConnection("jdbc:odbc:JavaWeb");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/Programming/Tools/Servers/apache-tomcat-4.1.40/webapps/myApp/WEB-INF/databases/JavaWeb.accdb");
			System.out.println("got connection");
			
			Statement statement = connection.createStatement();
			String sql = "SELECT userName FROM users WHERE userName='" + 
					StringUtil.fixSQLField(userName) + "' AND password='" + 
					StringUtil.fixSQLField(password) + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				resultSet.close();
				statement.close();
				connection.close();
				return true;
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
}
