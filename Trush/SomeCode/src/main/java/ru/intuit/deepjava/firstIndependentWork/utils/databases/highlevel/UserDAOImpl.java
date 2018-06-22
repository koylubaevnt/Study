package ru.intuit.deepjava.firstIndependentWork.utils.databases.highlevel;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAOImpl implements UsersDAO {

	private Connection connection;
	
	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public UsersDataSet get(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsersDataSet getByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(UsersDataSet dataSet) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) throws SQLException {
		// TODO Auto-generated method stub

	}

}
