package com.apress.prospring4.ch10.l101;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("myBeanValidationService2")
public class MyBeanValidationService2 {

	@Autowired
	private Validator validator;
	
	public Set<ConstraintViolation<Customer2>> validateCustomer(Customer2 customer) {
		return validator.validate(customer);
	}
}
