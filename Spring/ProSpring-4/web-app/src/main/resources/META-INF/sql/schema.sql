CREATE TABLE contact (
id INT NOT NULL auto_increment, 
first_name VARCHAR(60) NOT NULL, 
last_name VARCHAR(40) NOT NULL, 
birth_date DATE,
description varchar(2000),
photo BLOB,
version INT NOT NULL DEFAULT 0,
UNIQUE UQ_CONTACT_1 (first_name, last_name),
PRIMARY KEY(id)
);