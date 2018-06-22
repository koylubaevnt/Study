package ru.intuit.deepjava.firstIndependentWork.utils.databases.lowlevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;


public class Executor {

	public int execUpdate(Connection connection, String update) {
		int updated = 0;
		try(Statement statement = connection.createStatement()) {
			statement.executeUpdate(update);
			updated = statement.getUpdateCount();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return updated;
	}
	
	public int execUpdate(Connection connection, String[] updates) {
		int updated = 0;
		try {
			connection.setAutoCommit(false);
			for(String update : updates) {
				Statement statement = connection.createStatement();
				statement.executeUpdate(update);
				updated += statement.getUpdateCount();
			}
		} catch (SQLException e) {
			updated = 0;
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		return updated;
	}
	
	public int execUpdateUsers(Connection connection, Map<Integer, String> idToName) {
		int updated = 0;
		String update = "insert into users(id, user_name) values(?,?)";
		try(PreparedStatement statement = connection.prepareStatement(update)) {
			for(Integer id : idToName.keySet()) {
				statement.setInt(1, id);
				statement.setString(2, idToName.get(id));
				statement.executeUpdate();
				updated += statement.getUpdateCount();
			}
		} catch (SQLException e) {
			updated = 0;
			e.printStackTrace();
		} 
		return updated;
	}
	
	public void execQuery(Connection connection, String query, ResultHandler resultHandler) {
		try(Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(query);
			resultHandler.handle(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
