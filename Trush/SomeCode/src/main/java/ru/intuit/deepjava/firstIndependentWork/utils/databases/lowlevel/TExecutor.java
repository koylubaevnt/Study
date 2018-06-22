package ru.intuit.deepjava.firstIndependentWork.utils.databases.lowlevel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TExecutor {

	public <T> T execQuery(Connection connection, String query, TResultHandler<T> resultHandler) {
		T value = null;
		try(Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(query);
			value = resultHandler.handle(resultSet);
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return value;
	}
	
}
