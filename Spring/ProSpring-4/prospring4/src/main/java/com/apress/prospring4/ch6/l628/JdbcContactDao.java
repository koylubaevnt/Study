package com.apress.prospring4.ch6.l628;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.apress.prospring4.ch6.l63.Contact;
import com.apress.prospring4.ch6.l63.ContactDAO;

public class JdbcContactDao implements ContactDAO, InitializingBean {

	private DataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public String findLastNameById(Long id) {
		String sql = "select first_name from contact where id = :contactId";
		
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("contactId", id);
		
		return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(dataSource == null) {
			throw new BeanCreationException("Must set dataSource on ContactDao");
		}
		
		if(namedParameterJdbcTemplate == null) {
			throw new BeanCreationException("Null NamedParameterJdbcTemplate on ContactDao");
		}
		
	}

	@Override
	public List<Contact> findAll() {
		String sql = "select id, first_name, last_name, birth_date from contact";
		return namedParameterJdbcTemplate.query(sql, new ContactMapper());
	}

	private static final class ContactMapper implements RowMapper<Contact> {

		@Override
		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
			Contact contact = new Contact();
			contact.setId(rs.getLong("id"));
			contact.setFirstName(rs.getString("first_name"));
			contact.setLastName(rs.getString("last_name"));
			contact.setBirthDate(rs.getDate("birth_date"));
			return contact;
		}
		
	}
	
	@Override
	public List<Contact> findByFirstName(String firstName) {
		//то же самое что findAll, но с использованием лямбда-выражения
		String sql = "select id, first_name, last_name, birth_date from contact";
		return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
			Contact contact = new Contact();
			contact.setId(rs.getLong("id"));
			contact.setFirstName(rs.getString("first_name"));
			contact.setLastName(rs.getString("last_name"));
			contact.setBirthDate(rs.getDate("birth_date"));
			return contact;
		});
	}

	@Override
	public String findFirstNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}


}
