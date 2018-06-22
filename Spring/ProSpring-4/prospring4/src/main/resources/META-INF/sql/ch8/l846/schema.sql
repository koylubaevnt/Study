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
UNIQUE UQ_CONTACT_AUDIT_1 (first_name, last_name),
PRIMARY KEY(id)
);

CREATE TABLE contact_audit_h (
id INT NOT NULL, 
first_name VARCHAR(60) NOT NULL, 
last_name VARCHAR(40) NOT NULL, 
birth_date DATE,
version INT NOT NULL DEFAULT 0,
created_by VARCHAR(20),
created_date TIMESTAMP,
last_modified_by VARCHAR(20),
last_modified_date TIMESTAMP,
audit_revision INT NOT NULL,
action_type INT,
audit_revision_end INT,
audit_revision_end_ts TIMESTAMP,
UNIQUE UQ_CONTACT_AUDIT_H_1 (first_name, last_name),
PRIMARY KEY(id, audit_revision)
);

CREATE TABLE revinfo (
	revtstmp BIGINT NOT NULL,
	rev INT NOT NULL auto_increment,
	PRIMARY KEY (revtstmp, rev)
);