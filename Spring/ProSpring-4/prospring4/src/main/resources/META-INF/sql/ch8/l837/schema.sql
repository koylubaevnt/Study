CREATE TABLE contact_audit (
id INT NOT NULL auto_increment, 
first_name VARCHAR(60) NOT NULL, 
last_name VARCHAR(40) NOT NULL, 
birth_date DATE,
version INT NOT NULL DEFAULT 0,
created_by VARCHAR(20),
created_date TIMESTAMP,
last_modified_by VARCHAR(20),
last_modified_date TIMESTAMP,
UNIQUE UQ_CONTACT_1 (first_name, last_name),
PRIMARY KEY(id)
);