package ru.intuit.deepjava.firstIndependentWork.utils.databases.lowlevel;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import ru.intuit.deepjava.firstIndependentWork.utils.reflection.ReflectionHelper;

public class JDBCExample {

	public static void main(String[] args) throws SQLException {
		Driver driver = (Driver) ReflectionHelper.createInstance("com.mysql.jdbc.Driver");
		DriverManager.registerDriver(driver);
		
		StringBuilder url = new StringBuilder();
		url.
		append("jdbc:mysql://").
		append("locallhost:").
		append("3306/").
		append("lecture_db?").
		append("user=user&").
		append("password=password");
		
		Connection connection = DriverManager.getConnection(url.toString());
		
		

	}

}
