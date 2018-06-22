package com.apress.prospring4.ch8.l837;

import java.util.List;

public interface ContactAuditService {

	List<ContactAudit> findAll();
	ContactAudit findById(long id);
	ContactAudit save(ContactAudit contactAudit);
	
}
