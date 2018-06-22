package ru.intuit.deepjava.firstIndependentWork.utils.databases.highlevel;

import java.sql.SQLException;

public interface UsersDAO {

	UsersDataSet get(long id) throws SQLException;
	
	UsersDataSet getByName(String name) throws SQLException;
	
	void add(UsersDataSet dataSet) throws SQLException;
	
	void delete(long id) throws SQLException;
	
}
