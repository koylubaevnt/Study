package com.apress.prospring4.ch11.l1101;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="car")
public class Car {

	private Long id;
	private String licensePlate;
	private String manufacturer;
	private DateTime manufactureDate;
	private int age;
	private int version;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="license_plate")
	public String getLicensePlate() {
		return licensePlate;
	}
	
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	@Column(name="manufacturer")
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@Column(name="manufacture_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getManufactureDate() {
		return manufactureDate;
	}
	
	public void setManufactureDate(DateTime manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	
	@Column(name="age")
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	@Version
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Car [licensePlate=" + licensePlate + ", manufacturer=" + manufacturer + ", manufactureDate="
				+ manufactureDate + ", age=" + age + "]";
	}
	
	
}
