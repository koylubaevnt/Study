package com.apress.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.apress.spring.domain.Journal;

public interface JournalRepository extends MongoRepository<Journal, String> {

	public List<Journal> findByTitleContaining(String word);
	
	public List<Journal> findByTitleLike(String word);
	
	public List<Journal> findByCreatedAfter(Date date);
	
}
