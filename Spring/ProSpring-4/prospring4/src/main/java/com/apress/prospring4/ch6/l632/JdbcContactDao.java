package com.apress.prospring4.ch6.l632;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.apress.prospring4.ch6.l63.Contact;
import com.apress.prospring4.ch6.l63.ContactTelDetail;

@Repository("contactDao")
public class JdbcContactDao implements ContactDAO {

	private Log log = LogFactory.getLog(JdbcContactDao.class);
	
	private DataSource dataSource;
	private SellectAllContacts sellectAllContacts;
	private SellectContactByFirstName sellectContactByFirstName;
	private UpdateContact updateContact;
	private InsertContact insertContact;
	private InsertContactTelDetail insertContactTelDetail;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.sellectAllContacts = new SellectAllContacts(dataSource);
		this.sellectContactByFirstName = new SellectContactByFirstName(dataSource);
		this.updateContact = new UpdateContact(dataSource);
		this.insertContact = new InsertContact(dataSource);
	}
	
	@Override
	public String findLastNameById(Long id) {
		return null;
	}

	@Override
	public List<Contact> findAll() {
		return sellectAllContacts.execute();
	}
	
	@Override
	public List<Contact> findAllWithDetail() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		
		String sql = "select c.id, c.first_name, c.last_name, c.birth_date, " +
				"d.id as contact_tel_id, d.tel_type, d.tel_number from contact c " +
				"left join contact_tel_detail d on c.id = d.contact_id";
		return jdbcTemplate.query(sql, (rs) -> {
			Map<Long, Contact> map = new HashMap<>();
			Contact contact = null;
			
			while (rs.next()) {
				Long id = rs.getLong("id");
				contact = map.get(id);
				if(contact == null) {
					contact = new Contact();
					contact.setId(id);
					contact.setFirstName(rs.getString("first_name"));
					contact.setLastName(rs.getString("last_name"));
					contact.setBirthDate(rs.getDate("birth_date"));
					contact.setContactTelDetails(new ArrayList<>());
					map.put(id, contact);
				}
				Long contactTelDetailId = rs.getLong("contact_tel_id");
				if(contactTelDetailId > 0) {
					ContactTelDetail contactTelDetail = new ContactTelDetail();
					contactTelDetail.setId(contactTelDetailId);
					contactTelDetail.setContactId(id);
					contactTelDetail.setTelType(rs.getString("tel_type"));
					contactTelDetail.setTelNumber(rs.getString("tel_number"));
					contact.getContactTelDetails().add(contactTelDetail);
				}
			}
			
			return new ArrayList<>(map.values());
		});
	}
	
	@Override
	public List<Contact> findByFirstName(String firstName) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName", firstName);
		return sellectContactByFirstName.executeByNamedParam(paramMap);
	}

	@Override
	public String findFirstNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Contact contact) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName", contact.getFirstName());
		paramMap.put("lastName", contact.getLastName());
		paramMap.put("birthDate", contact.getBirthDate());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		insertContact.updateByNamedParam(paramMap, keyHolder);
		contact.setId(keyHolder.getKey().longValue());
		
		log.info("New contact inserted with id: " + contact.getId());
		
	}

	@Override
	public void update(Contact contact) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName", contact.getFirstName());
		paramMap.put("lastName", contact.getLastName());
		paramMap.put("birth_date", contact.getBirthDate());
		paramMap.put("id", contact.getId());
		
		updateContact.updateByNamedParam(paramMap);
		
		log.info("Existing contact updated with id: " + contact.getId());
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertWithDetail(Contact contact) {
		this.insertContactTelDetail = new InsertContactTelDetail(dataSource);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName", contact.getFirstName());
		paramMap.put("lastName", contact.getLastName());
		paramMap.put("birthDate", contact.getBirthDate());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		insertContact.updateByNamedParam(paramMap, keyHolder);
		contact.setId(keyHolder.getKey().longValue());
		
		log.info("New contact inserted with id: " + contact.getId());
		
		List<ContactTelDetail> contactTelDetails = contact.getContactTelDetails();
		if(contactTelDetails != null) {
			for(ContactTelDetail contactTelDetail : contactTelDetails) {
				paramMap = new HashMap<>();
				paramMap.put("contactId", contact.getId());
				paramMap.put("telType", contactTelDetail.getTelType());
				paramMap.put("telNumber", contactTelDetail.getTelType());
				insertContactTelDetail.updateByNamedParam(paramMap);
			}
		}
		insertContactTelDetail.flush();
	}


	public DataSource getDataSource() {
		return dataSource;
	}
}
