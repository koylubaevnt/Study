CREATE TABLE contact (
id INT NOT NULL auto_increment, 
first_name VARCHAR(60) NOT NULL, 
last_name VARCHAR(40) NOT NULL, 
birth_date DATE,
UNIQUE UQ_CONTACT_1 (first_name, last_name),
PRIMARY KEY(id)
) ;
CREATE TABLE contact_tel_detail(
id INT NOT NULL auto_increment
, contact_id INT NOT NULL
, tel_type VARCHAR(20) NOT NULL
, tel_number VARCHAR(20) NOT NULL
, UNIQUE UQ_CONTACT_TEL_DETAIL_1 (contact_id, tel_type)
, PRIMARY KEY(ID)
, CONSTRAINT FK_CONTACT_TEL_DETAIL_1 FOREIGN KEY (contact_id)
REFERENCES contact(id)
) ;
