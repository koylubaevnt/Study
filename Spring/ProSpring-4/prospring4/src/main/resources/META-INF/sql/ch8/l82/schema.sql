CREATE TABLE contact (
id INT NOT NULL auto_increment, 
first_name VARCHAR(60) NOT NULL, 
last_name VARCHAR(40) NOT NULL, 
birth_date DATE,
version INT NOT NULL DEFAULT 0,
UNIQUE UQ_CONTACT_1 (first_name, last_name),
PRIMARY KEY(id)
);

CREATE TABLE hobby (
hobby_id VARCHAR(20) NOT NULL,
PRIMARY KEY (hobby_id));

CREATE TABLE contact_tel_detail(
id INT NOT NULL auto_increment,
contact_id INT NOT NULL,
tel_type VARCHAR(20) NOT NULL,
tel_number VARCHAR(20) NOT NULL,
version INT NOT NULL DEFAULT 0,
UNIQUE UQ_CONTACT_TEL_DETAIL_1 (contact_id, tel_type),
PRIMARY KEY(ID),
CONSTRAINT FK_CONTACT_TEL_DETAIL_1 FOREIGN KEY (contact_id)
REFERENCES contact(id)
) ;

CREATE TABLE contact_hobby_detail (
contact_id INT NOT NULL,
hobby_id VARCHAR(20) NOT NULL,
PRIMARY KEY(contact_id, hobby_id),
CONSTRAINT fk_contact_hobby_detail_1 FOREIGN KEY (contact_id)
REFERENCES contact(id) ON DELETE CASCADE,
CONSTRAINT fk_contact_hobby_detail_2 FOREIGN KEY (hobby_id)
REFERENCES hobby(hobby_id));