package com.apress.prospring4.ch6.l63;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlainContactDao implements ContactDAO {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/prospring4_ch6",
				"prospring4", "prospring4");
				
	}
	
	private void closeConnection(Connection connection) {
		if(connection == null) return;
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Contact> findAll() {
		List<Contact> result = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = 
					connection.prepareStatement("select * from contact");
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Contact contact = new Contact();
				contact.setId(resultSet.getLong("id"));
				contact.setFirstName(resultSet.getString("first_name"));
				contact.setLastName(resultSet.getString("last_name"));
				contact.setBirthDate(resultSet.getDate("birth_date"));
				
				result.add(contact);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Contact> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findLastNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findFirstNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Contact contact) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = 
					connection.prepareStatement("insert into contact (first_name, last_name, birth_date) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, contact.getFirstName());
			statement.setString(2, contact.getLastName());
			statement.setDate(3, contact.getBirthDate());
			statement.execute();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				contact.setId(generatedKeys.getLong(1));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public void update(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = 
					connection.prepareStatement("delete from contact where id = ?");
			statement.setLong(1, id);
			statement.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

}
