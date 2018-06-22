package com.kurnyavan.chapters123;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.SingleThreadModel;

public class SingleThreadedServlet extends GenericServlet {

	@Override
	public synchronized void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int counter = 0;
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("counter.txt"));
			counter = Integer.parseInt(reader.readLine());
		} catch (Exception e) {
			
		} finally {
			if(reader != null)
				reader.close();
		}
		
		counter++;
		
		try {
			TimeUnit.SECONDS.sleep(6);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("counter.txt"));
			writer.write(Integer.toString(counter));
		} catch (Exception e) {
			
		} finally {
			if(writer != null)
				writer.close();
		}
		
		try {
			PrintWriter out = res.getWriter();
			out.println("You are visitor number " + counter);
		} catch (Exception e) {
			
		}

	}

}
