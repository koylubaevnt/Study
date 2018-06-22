package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SQLToolServlet extends HttpServlet {

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
		sendSqlForm(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendSqlForm(req, resp);
	}

	private void sendSqlForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>SQL Tool Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<br><h2>SQL Tool</h2>");
		out.println("<br>Please type your SQL statement in the following box.");
		out.println("<br>");
		out.println("<br><form method=post>");
		out.println("<textarea name=sql cols=80 rows=8>");
		String sql = req.getParameter("sql");
		if(sql != null)
			out.println(sql);
		out.println("</textarea>");
		out.println("<br>");
		out.println("<input type=submit value=Execute>");
		out.println("</form>");
		out.println("<br>");
		out.println("<hr>");
		out.println("<br>");
		if(sql != null)
			executeSql(sql.trim(), resp);
		out.println("</body>");
		out.println("</html>");
		
	}

	private void executeSql(String sql, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		try {
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Java/Tools/Servers/Tomcat/apache-tomcat-4.1.40/webapps/myApp/WEB-INF/databases/JavaWeb.accdb");
			Statement statement = connection.createStatement();
			
			if(sql.toUpperCase().startsWith("SELECT")) {
				out.println("<table border=1>");
				ResultSet resultSet = statement.executeQuery(sql);
				ResultSetMetaData rsdm = resultSet.getMetaData();
				
				int columnCount = rsdm.getColumnCount();
				out.println("<tr>");
				for(int i = 1; i <= columnCount; i++)
					out.println("<td><b>" + rsdm.getColumnName(i) + "</b></td>\n");
				out.println("</tr>");
				while (resultSet.next()) {
					out.println("<tr>");
					for(int i = 1; i <= columnCount; i++)
						out.println("<td>" + resultSet.getString(i) + "</td>");
					out.println("</tr>");
				}
				resultSet.close();
				out.println("</table>");
			} else {
				int i = statement.executeUpdate(sql);
				out.println("Record(s) affected: " + i);
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			out.println("<b>Error<b>");
			out.println("<br>");
			out.println(e.toString());
		}
		
	}
	
	
}
