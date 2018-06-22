package com.apress.prospring4.ch6.l626;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.apress.prospring4.ch6.l617.ContactDAO;

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


}
