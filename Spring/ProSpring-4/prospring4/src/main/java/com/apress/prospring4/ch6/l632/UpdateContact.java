package com.apress.prospring4.ch6.l632;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateContact extends SqlUpdate {

	private static final String SQL_UPDATE_CONTACT = 
			"update contact set first_name = :firstName, last_name = :lastName, birth_date = :birth_date where id = :id";
	
	public UpdateContact(DataSource dataSource) {
		super(dataSource, SQL_UPDATE_CONTACT);
		super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
		super.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
		super.declareParameter(new SqlParameter("birth_date", Types.DATE));
		super.declareParameter(new SqlParameter("id", Types.INTEGER));
	}
	
}
