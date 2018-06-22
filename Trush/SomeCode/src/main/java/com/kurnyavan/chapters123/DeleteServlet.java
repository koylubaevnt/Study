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

public class DeleteServlet extends HttpServlet {

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
		int recordAffected = 0;
		try {
			String id = req.getParameter("id");
			String sql = "DELETE FROM users WHERE id = " + id;
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Java/Tools/Servers/Tomcat/apache-tomcat-4.1.40/webapps/myApp/WEB-INF/databases/JavaWeb.accdb");
			Statement statement = connection.createStatement();
			recordAffected = statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (Exception e) {
			
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Deleting A Record</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		if(recordAffected==1)
			out.println("<p>Record deleted.</p>");
		else
			out.println("<p>Error deleteing record.</p>");
		out.println("<a href=SearchServlet2>Go back</a> to the Search page");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
}
