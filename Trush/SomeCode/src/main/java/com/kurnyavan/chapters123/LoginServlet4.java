package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet4 extends HttpServlet {

		
	private void sendLoginForm(HttpServletResponse response, boolean withErrorMessage) throws ServerException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		if(withErrorMessage)
			out.println("Login failed. Please try again.<br>");
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
		String password = req.getParameter("password");
		if(login(userName,password)) {
			RequestDispatcher rd = req.getRequestDispatcher("WelcomeServlet");
			rd.forward(req, resp);
		} else {
			sendLoginForm(resp, true);
		}
	}

	private boolean login(String userName, String password) {
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			//Connection connection = DriverManager.getConnection("jdbc:odbc:JavaWeb");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Java/Tools/Servers/Tomcat/apache-tomcat-4.1.40/webapps/myApp/WEB-INF/databases/JavaWeb.accdb");
			System.out.println("got connection");
			
			Statement statement = connection.createStatement();
			String sql = "SELECT userName FROM users WHERE userName='" + 
					fixSQLField(userName) + "' AND password='" + 
					fixSQLField(password) + "'";
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
	
}