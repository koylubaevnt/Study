package ru.intuit.deepjava.firstIndependentWork.utils.databases.lowlevel;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler {

	void handle(ResultSet resultSet) throws SQLException;
	
}
