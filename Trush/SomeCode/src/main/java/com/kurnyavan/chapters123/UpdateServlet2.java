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

public class UpdateServlet2 extends HttpServlet {

	private String keyword = "";
	
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
		sendUpdateForm(req, resp);
		sendPageFooter(resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendPageHeader(resp);
		updateRecord(req, resp);
		sendPageFooter(resp);
	}
	
	private void sendPageHeader(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Updating Record</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		
	}
	
	private void sendUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
		String id = request.getParameter("id");
		PrintWriter out = response.getWriter();
		out.println("<br><h2>Update Form</h2>");
		out.println("<br>Please edit the first name, last name or password.");
		out.println("<br>");
		try {
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Java/Tools/Servers/Tomcat/apache-tomcat-4.1.40/webapps/myApp/WEB-INF/databases/JavaWeb.accdb");
			Statement statement = connection.createStatement();
			String sql = "SELECT firstName, lastName, userName, password FROM users WHERE id = " + id;
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String firstName = resultSet.getString(1);
				String lastName = resultSet.getString(2);
				String userName = resultSet.getString(3);
				String password = resultSet.getString(4);
				//System.out.println("sendUpdateForm " + password);
				out.println("<br><form method=post>");
				out.println("<input type=hidden name=id value=" + id + ">");
				out.println("<table>");
				out.println("<tr>");
				out.println("<td>First Name</td>");
				out.println("<td><input type=text name=firstName value=\"" + encodeHtmlTag(firstName) + "\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Last Name</td>");
				out.println("<td><input type=text name=lastName value=\"" + encodeHtmlTag(lastName) + "\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>User Name</td>");
				out.println("<td>" + encodeHtmlTag(userName) + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Password</td>");
				out.println("<td><input type=password name=password value=\"" + encodeHtmlTag(password) + "\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td><input type=reset></td>");
				out.println("<td><input type=submit></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			out.println(e.toString());
		}
	}
	
	private void updateRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		//System.out.println("updateRecord " + password);
		PrintWriter out = response.getWriter();
		
		try {
			String sql = "UPDATE users " + 
					"SET firstName='" + fixSQLField(firstName) + "'," + 
						"lastName='" + fixSQLField(lastName) + "'," +
						"password='" + fixSQLField(password) + "'" +
						" WHERE id=" + id;
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Java/Tools/Servers/Tomcat/apache-tomcat-4.1.40/webapps/myApp/WEB-INF/databases/JavaWeb.accdb");
			Statement statement = connection.createStatement();
			int i = statement.executeUpdate(sql);
			if(i == 1)
				out.println("Record updated");
			else
				out.println("Error updating record");
			statement.close();
			connection.close();
		} catch (Exception e) {
			out.println(e.toString());
		}
		out.println("<a href=SearchServlet2>Go back</a> to the Search page");
	}
	
	private void sendPageFooter(HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
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
