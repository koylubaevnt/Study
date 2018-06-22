package com.apress.prospring4.ch6.l617;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;

public class JdbcContactDao implements ContactDAO, InitializingBean {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public String findLastNameById(Long id) {
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(dataSource == null) {
			throw new BeanCreationException("Must set dataSource on ContactDao");
		}
	}


}
