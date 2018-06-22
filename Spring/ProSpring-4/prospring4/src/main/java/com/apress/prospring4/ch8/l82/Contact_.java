package com.apress.prospring4.ch8.l82;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Contact.class)
public abstract class Contact_ {
	public static volatile SingularAttribute<Contact, Long> id;
	public static volatile SingularAttribute<Contact, String> firstName;
	public static volatile SingularAttribute<Contact, String> lastName;
	public static volatile SingularAttribute<Contact, Date> birthDate;
	public static volatile SingularAttribute<Contact, Integer> version;
	public static volatile SetAttribute<Contact, ContactTelDetail> contactTelDetails;
	public static volatile SetAttribute<Contact, Hobby> hobbies;
	/*
	 * Зависимость дя генерации классов метамоделей JАR-файл
	 * 		hibernatejpamodelgen-1.3.О.Final.jar
	 * Описание
	 * Главная библиотека для генерации классов метамоделей. Вы
	 * найдете нужный файл в hibernate-jpamodelgen-1.3.О.Final
	 * после распаковки загруженного пакета или посредством инструмента 
	 * построения, такого как Maven
	 * */
}
