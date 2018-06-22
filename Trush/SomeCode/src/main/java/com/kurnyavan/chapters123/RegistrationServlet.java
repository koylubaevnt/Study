package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {

	private String firstName = "";
	private String lastName = "";
	private String userName = "";
	private String password = "";
	
	@Override
	public void init() throws ServletException {
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			System.out.println("JDBC driver loaded");
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendPageHeader(resp);
		sendRegistrationForm(req, resp, false);
		sendPageFooter(resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendPageHeader(resp);
		
		firstName = req.getParameter("firstName");
		lastName = req.getParameter("lastName");
		userName = req.getParameter("userName");
		password = req.getParameter("password");
		
		boolean error = false;
		String message = null;
		try {
			//Connection connection = DriverManager.getConnection("jdbc:odbc:JavaWeb");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Java/Tools/Servers/Tomcat/apache-tomcat-4.1.40/webapps/myApp/WEB-INF/databases/JavaWeb.accdb");
			System.out.println("got connection");
			
			Statement statement = connection.createStatement();
			
			String sql = "SELECT userName FROM users WHERE userName='" + fixSQLField(userName) + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				resultSet.close();
				message = "The user name <b>" + encodeHtmlTag(userName) + "</b> has been taken. Please select another name.";
				error = true;
			} else {
				resultSet.close();
				sql = "INSERT INTO users(firstName, lastName, userName, password)" +
						"VALUES('" + fixSQLField(firstName) + "'," +
						"'" + fixSQLField(lastName) + "'," +
						"'" + fixSQLField(userName) + "'," +
						"'" + fixSQLField(password) + "')";
				int i = statement.executeUpdate(sql);
				if(i == 1) {
					message = "Successfully added one user.";
				}
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			message = "Error: " + e.toString();
		}
		if(message != null) {
			PrintWriter out = resp.getWriter();
			out.println("<b>" + message + "</b><br>");
			out.println("<hr><br>");
		}
		if (error)
			sendRegistrationForm(req, resp, true);
		else
			sendRegistrationForm(req, resp, false);
		sendPageFooter(resp);
	}
	
	private void sendPageFooter(HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

	private void sendRegistrationForm(HttpServletRequest req, HttpServletResponse resp, boolean displayPreviousValues) throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<br><h2>Registration Page</h2>");
		out.println("<br>Please enter the user details.");
		out.println("<br>");
		out.println("<br><form method=post>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>First Name</td>");
		out.println("<td><input type=text name=firstName");
		if(displayPreviousValues)
			out.println("VALUE=\"" + encodeHtmlTag(firstName) + "\"");
		out.println("></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Last Name</td>");
		out.println("<td><input type=text name=lastName");
		if(displayPreviousValues)
			out.println("VALUE=\"" + encodeHtmlTag(lastName) + "\"");
		out.println("></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>User Name</td>");
		out.println("<td><input type=text name=userName");
		if(displayPreviousValues)
			out.println("VALUE=\"" + encodeHtmlTag(userName) + "\"");
		out.println("></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Password</td>");
		out.println("<td><input type=text name=password");
		if(displayPreviousValues)
			out.println("VALUE=\"" + encodeHtmlTag(password) + "\"");
		out.println("></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><input type=reset></td>");
		out.println("<td><input type=submit></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<br>");
		out.println("<br>");
	}

	private void sendPageHeader(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Registration Page</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		
	}

	private static String fixSQLField(String value) {
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
