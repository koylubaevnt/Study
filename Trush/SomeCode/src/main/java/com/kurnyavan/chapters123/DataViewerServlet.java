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

public class DataViewerServlet extends HttpServlet {
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
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Display All Users</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<br><h2>Display All Users</h2>");
		out.println("<br>Please enter the user details.");
		out.println("<br>");
		out.println("<br>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>First Name</th>");
		out.println("<th>Last Name</th>");
		out.println("<th>User Name</th>");
		out.println("<th>Password</th>");
		out.println("</tr>");
		
		String sql = "SELECT firstName, lastName, userName, password FROM users";
		try {
			Connection connection =  DriverManager.getConnection("jdbc:ucanaccess://C:/Java/Tools/Servers/Tomcat/apache-tomcat-4.1.40/webapps/myApp/WEB-INF/databases/JavaWeb.accdb");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				out.println("<tr>");
				out.println("<td>" + encodeHtmlTag(resultSet.getString(1)) + "</td>");
				out.println("<td>" + encodeHtmlTag(resultSet.getString(2)) + "</td>");
				out.println("<td>" + encodeHtmlTag(resultSet.getString(3)) + "</td>");
				out.println("<td>" + encodeHtmlTag(resultSet.getString(4)) + "</td>");
				out.println("</tr>");
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			
		}
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
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
