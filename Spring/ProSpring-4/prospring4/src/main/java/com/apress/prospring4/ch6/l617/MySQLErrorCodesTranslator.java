package com.apress.prospring4.ch6.l617;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

public class MySQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {

	@Override
	protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
		if(sqlEx.getErrorCode() == -12345) {
			return new DeadlockLoserDataAccessException(task, sqlEx);
		}
		return super.customTranslate(task, sql, sqlEx);
	}
	
}
