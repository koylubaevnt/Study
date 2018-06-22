package com.apress.prospring4.ch8.l82;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
	
	final static String ALL_CONTACT_NATIVE_QUERY =
			"select id, first_name, last_name, birth_date, version from contact";
	
	private Log log = LogFactory.getLog(ContactServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Contact> findAll() {
		List<Contact> contacts = em.createNamedQuery("Contact.findAll", Contact.class).getResultList();
		return contacts;
	}

	@Override
	public List<Contact> findAllWithDetail() {
		List<Contact> contacts = em.createNamedQuery("Contact.findAllWithDetail", Contact.class).getResultList();
		return contacts;
	}

	@Override
	public Contact findById(Long id) {
		TypedQuery<Contact> query = em.createNamedQuery("Contact.findById", Contact.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public Contact save(Contact contact) {
		if(contact.getId() == null) {
			log.info("Inserting new contact");
			em.persist(contact);
		} else {
			log.info("Updating new contact");
			em.merge(contact);
		}
		log.info("Contact saved with id: " + contact.getId());
		return contact;
	}

	@Override
	public void delete(Contact contact) {
		Contact mergedContact = em.merge(contact);
		em.remove(mergedContact);
		log.info("Contact with id: " + contact.getId() + " deleted succesfully");
	}

	@Override
	public List<Contact> findAllByNativeQuery() {
		return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, Contact.class).getResultList();
	}

	@Override
	public List<Contact> findAllByNativeQuery2() {
		return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, "contactResult").getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Contact> findByCriteriaQuery(String firstName, String lastName) {
		log.info("Finding contact for firstName: " + firstName + " and lastName: " + lastName);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
		Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
		contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
		contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);
		criteriaQuery.select(contactRoot).distinct(true);
		Predicate predicate = cb.conjunction();
		if(firstName != null) {
			Predicate p = cb.equal(contactRoot.get(Contact_.firstName), firstName);
			predicate = cb.and(predicate, p);
		}
		if(lastName != null) {
			Predicate p = cb.equal(contactRoot.get(Contact_.lastName), lastName);
			predicate = cb.and(predicate, p);
		}
		criteriaQuery.where(predicate);
		return em.createQuery(criteriaQuery).getResultList();
	}	
}
