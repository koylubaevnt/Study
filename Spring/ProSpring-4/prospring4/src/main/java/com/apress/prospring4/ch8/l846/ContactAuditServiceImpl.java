package com.apress.prospring4.ch8.l846;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service("contactAuditServiceEnvers")
@Repository
@Transactional
public class ContactAuditServiceImpl implements ContactAuditService {

	@Autowired
	private ContactAuditRepository contactAuditRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
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

	@Transactional(readOnly=true)
	@Override
	public ContactAudit findAuditByRevision(Long id, int revision) {
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
		return auditReader.find(ContactAudit.class, id, revision);
	}

}
