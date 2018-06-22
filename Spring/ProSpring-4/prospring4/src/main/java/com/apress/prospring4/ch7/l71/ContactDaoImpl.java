package com.apress.prospring4.ch7.l71;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("contactDao")
@Transactional
public class ContactDaoImpl implements ContactDao {

	private static final Log LOG = LogFactory.getLog(ContactDaoImpl.class);
	
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Contact> findAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from Contact c").list();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Contact> findAllWithDetail() {
		return sessionFactory.getCurrentSession()
				.getNamedQuery("Contact.findAllWithDetail").list();
	}

	@Override
	public Contact findById(Long id) {
		return (Contact) sessionFactory.getCurrentSession()
				.getNamedQuery("Contact.findById")
				.setParameter("id", id)
				.uniqueResult();
	}

	@Override
	public Contact save(Contact contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		LOG.info("Contact saved with id: " + contact.getId());
		return contact;
	}

	@Override
	public void delete(Contact contact) {
		sessionFactory.getCurrentSession().delete(contact);
		LOG.info("Contact deleted with id: " + contact.getId());
	}

}
