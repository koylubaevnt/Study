CREATE TABLE car (
id INT NOT NULL auto_increment, 
license_plate VARCHAR(20) NOT NULL, 
manufacturer VARCHAR(20) NOT NULL, 
manufacture_date DATE NOT NULL,
age INT NOT NULL DEFAULT 0,
version INT NOT NULL DEFAULT 0,
UNIQUE UQ_CAR_1 (license_plate),
PRIMARY KEY(id)
);
