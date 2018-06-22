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

public class SearchServlet2 extends HttpServlet {

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
		sendSearchForm(resp);
		sendPageFooter(resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		keyword = req.getParameter("keyword");
		sendPageHeader(resp);
		sendSearchForm(resp);
		sendSearchResult(resp);
		sendPageFooter(resp);
	}
	
	private void sendPageHeader(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Displaying Selected Record(s)</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		
	}
	
	private void sendSearchForm(HttpServletResponse response) throws ServerException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<br><h2>Search Form</h2>");
		out.println("<br>Please enter the first name, last name or part of any.");
		out.println("<br>");
		out.println("<br><form method=post>");
		out.println("Name:<input type=text name=keyword value=\"" + encodeHtmlTag(keyword) + "\">");
		out.println("<input type=submit>");
		out.println("</form>");		
		out.println("<br>");
		out.println("<br>");
	}
	
	private void sendSearchResult(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		try {
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>First Name</th>");
			out.println("<th>Last Name</th>");
			out.println("<th>User Name</th>");
			out.println("<th>Password</th>");
			out.println("<th></th>");
			out.println("<th></th>");
			out.println("<th></th>");
			out.println("</tr>");
			
			try {
				Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Java/Tools/Servers/Tomcat/apache-tomcat-4.1.40/webapps/myApp/WEB-INF/databases/JavaWeb.accdb");
				Statement statement = connection.createStatement();
				String sql = "SELECT id, firstName, lastName, userName, password FROM users WHERE firstName LIKE '%" + fixSQLField(keyword) + "%'" +
						"OR lastName LIKE '%" + fixSQLField(keyword) + "%'";				
				ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
					String id = resultSet.getString(1);
					out.println("<tr>");
					out.println("<td>" + encodeHtmlTag(resultSet.getString(2)) + "</td>");
					out.println("<td>" + encodeHtmlTag(resultSet.getString(3)) + "</td>");
					out.println("<td>" + encodeHtmlTag(resultSet.getString(4)) + "</td>");
					out.println("<td>" + encodeHtmlTag(resultSet.getString(5)) + "</td>");
					out.println("<td><a href=DeleteServlet?id=" + id + ">Delete</a></td>");
					out.println("<td><a href=UpdateServlet?id=" + id + ">Update</a></td>");
					out.println("<td><a href=UpdateServlet2?id=" + id + ">Update</a></td>");
					out.println("</tr>");
				}
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				
			}
			out.println("</table>");
		} catch (Exception e) {
			
		}
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
