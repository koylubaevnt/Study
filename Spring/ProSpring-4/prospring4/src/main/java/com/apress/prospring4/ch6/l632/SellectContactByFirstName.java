package com.apress.prospring4.ch6.l632;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.apress.prospring4.ch6.l63.Contact;

public class SellectContactByFirstName extends MappingSqlQuery<Contact> {

	private static final String SQL_FIND_BY_FIRST_NAME = 
			"select id, first_name, last_name, birth_date from contact where first_name = :firstName";
	
	public SellectContactByFirstName(DataSource dataSource) {
		super(dataSource, SQL_FIND_BY_FIRST_NAME);
		super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
	}
	
	@Override
	protected Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contact contact = new Contact();
		
		contact.setId(rs.getLong("id"));
		contact.setFirstName(rs.getString("first_name"));
		contact.setLastName(rs.getString("last_name"));
		contact.setBirthDate(rs.getDate("birth_date"));
		return contact;
	}

	
}
