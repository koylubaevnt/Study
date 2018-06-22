package com.kurnyavan.chapters123;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kurnyavan.utils.StringUtil;

public class MultipleFormsServlet extends HttpServlet {

	String firstName;
	String lastName;
	String userName;
	String password;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendPage1(resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		firstName = req.getParameter("firstName");
		lastName = req.getParameter("lastName");
		userName = req.getParameter("userName");
		password = req.getParameter("password");
		
		if(page == null) {
			sendPage1(resp);
			return;
		}
		
		if(page.equals("1")) {
			if(firstName == null || lastName == null)
				sendPage1(resp);
			else
				sendPage2(resp);
		} else if (page.equals("2")) {
			if(firstName == null || lastName == null || userName == null || password == null)
				sendPage1(resp);
			else
				displayValues(resp);
		}
	}

	private void sendPage1(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Page 1</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h2>Page 1</h2>");
		out.println("<br>");
		out.println("<br>");
		out.println("<br>Please enter your first name and last name.");
		out.println("<br>");
		out.println("<br>");
		out.println("<br><form method=post>");
		out.println("<input type=hidden name=page value=1>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>First Name&nbsp;</td>");
		out.println("<td><input type=text name=firstName></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Last Name&nbsp;</td>");
		out.println("<td><input type=text name=lastName></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><input type=reset></td>");
		out.println("<td><input type=submit></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}
	
	private void sendPage2(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Page 2</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h2>Page 2</h2>");
		out.println("<br>");
		out.println("<br>");
		out.println("<br>Please enter your user name and password.");
		out.println("<br>");
		out.println("<br>");
		out.println("<br><form method=post>");
		out.println("<input type=hidden name=page value=2>");
		out.println("<input type=hidden name=firstName value=\"" + StringUtil.encodeHtmlTag(firstName) + "\">");
		out.println("<input type=hidden name=lastName value=\"" + StringUtil.encodeHtmlTag(lastName) + "\">");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>User Name&nbsp;</td>");
		out.println("<td><input type=text name=userName></td>");
		out.println("</tr>");
		out.println("<td>Password&nbsp;</td>");
		out.println("<td><input type=password name=password></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><input type=reset></td>");
		out.println("<td><input type=submit></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
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
		out.println("<td>" + StringUtil.encodeHtmlTag(firstName) + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Last Name: &nbsp;</td>");
		out.println("<td>" + StringUtil.encodeHtmlTag(lastName) + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>User Name: &nbsp;</td>");
		out.println("<td>" + StringUtil.encodeHtmlTag(userName) + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Password: &nbsp;</td>");
		out.println("<td>" + StringUtil.encodeHtmlTag(password) + "</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		
	}
	
}
