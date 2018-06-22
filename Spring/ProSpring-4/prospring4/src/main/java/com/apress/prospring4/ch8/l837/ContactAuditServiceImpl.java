package com.apress.prospring4.ch8.l837;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service("contactAuditService")
@Repository
@Transactional
public class ContactAuditServiceImpl implements ContactAuditService {

	@Autowired
	private ContactAuditRepository contactAuditRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<ContactAudit> findAll() {
		return Lists.newArrayList(contactAuditRepository.findAll());
	}

	@Override
	public ContactAudit findById(long id) {
		return contactAuditRepository.findOne(id);
	}

	@Override
	public ContactAudit save(ContactAudit contactAudit) {
		return contactAuditRepository.save(contactAudit);
	}

}
