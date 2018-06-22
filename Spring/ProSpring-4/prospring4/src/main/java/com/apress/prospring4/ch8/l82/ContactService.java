package com.apress.prospring4.ch8.l82;

import java.util.List;

public interface ContactService {

	List<Contact> findAll();
	List<Contact> findAllWithDetail();
	
	Contact findById(Long id);
	Contact save(Contact contact);
	void delete(Contact contact);

	//Нативный запрос
	List<Contact> findAllByNativeQuery();
	
	//Нативный запрос 2
	List<Contact> findAllByNativeQuery2();
	
	//Использование АРl-интерфейса критериев JPA 2 для запроса с критерием
	List<Contact> findByCriteriaQuery(String firstName, String lastName);
}
