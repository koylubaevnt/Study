package com.apress.prospring4.ch6.l632;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertContact extends SqlUpdate {

	private static final String SQL_INSERT_CONTACT = 
			"insert into contact (first_name, last_name, birth_date) values(:firstName, :lastName, :birthDate)";
	
	public InsertContact(DataSource dataSource) {
		super(dataSource, SQL_INSERT_CONTACT);
		super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
		super.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
		super.declareParameter(new SqlParameter("birthDate", Types.DATE));
		super.setGeneratedKeysColumnNames(new String[] {"id"});
		super.setReturnGeneratedKeys(true);
	}
	
}
