package com.apress.prospring4.ch6.l632;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

public class InsertContactTelDetail extends BatchSqlUpdate {

	private static final String SQL_INSERT_CONTACT_TEL = 
			"insert into contact_tel_detail (contact_id, tel_type, tel_number) values(:contactId, :telType, :telNumber)";
	private static final int BATCH_SIZE = 10;
	
	public InsertContactTelDetail(DataSource dataSource) {
		super(dataSource, SQL_INSERT_CONTACT_TEL);
		super.declareParameter(new SqlParameter("contactId", Types.INTEGER));
		super.declareParameter(new SqlParameter("telType", Types.VARCHAR));
		super.declareParameter(new SqlParameter("telNumber", Types.VARCHAR));
		
		setBatchSize(BATCH_SIZE);
	}
	
}
