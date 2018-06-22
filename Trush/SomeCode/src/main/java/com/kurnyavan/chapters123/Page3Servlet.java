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

public class Page3Servlet extends HttpServlet {

	String page1Url = "Page1Srvlet";
	String firstName;
	String lastName;
	String userName;
	String password;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(page1Url);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		firstName = req.getParameter("firstName");
		lastName = req.getParameter("lastName");
		userName = req.getParameter("userName");
		password = req.getParameter("password");
		if(firstName == null || lastName == null ||
				userName == null || password == null)
			resp.sendRedirect(page1Url);
		displayValues(resp);
	}
	
	private void displayValues(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Page 3</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h2>Page 3 (Finish)</h2>");
		out.println("<br>");
		out.println("<br>");
		out.println("Here are the values you have entered.");
		out.println("<br>");
		out.println("<br>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>First Name: &nbsp;</td>");
		out.println("<td>" + encodeHtmlTag(firstName) + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Last Name: &nbsp;</td>");
		out.println("<td>" + encodeHtmlTag(lastName) + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>User Name: &nbsp;</td>");
		out.println("<td>" + encodeHtmlTag(userName) + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Password: &nbsp;</td>");
		out.println("<td>" + encodeHtmlTag(password) + "</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		
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
