-- create user 'docbook'@'localhost' identified by 'docbook';

-- grant all privileges on * . * to 'docbook'@'localhost';

DROP DATABASE IF EXISTS `doctorcare`;
create database if not exists `doctorcare`;
USE `doctorcare`;
DROP TABLE IF EXISTS specializations;
CREATE TABLE specializations (
     id int not null auto_increment,
     name varchar(255) default null,
     description text default null,
     image varchar(255) default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     clinicId int default null,
     PRIMARY KEY(id)
);
DROP TABLE IF EXISTS sequelizemeta;
CREATE TABLE sequelizemeta (
     id int not null auto_increment,
     name varchar(255) default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
     id int not null auto_increment,
     name varchar(255) default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS users;
CREATE TABLE users (
     id int not null auto_increment,
     name varchar(255) default null,
     email varchar(255) default null,
     password varchar(255) default null,
     address varchar(255) default null,
     phone varchar(255) default null,
     avatar varchar(255) default null,
     gender varchar(255) default null,
     description text default null,
     isActive tinyint default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     roleId int default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS statuses;
CREATE TABLE statuses (
     id int not null auto_increment,
     name varchar(255) default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id)
 );
DROP TABLE IF EXISTS patients;
CREATE TABLE patients (
     id int not null auto_increment,
     doctorId int default null,
     statusId int default null,
     patientId int default null,
     reason varchar(255) default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS sessions;
CREATE TABLE sessions (
     id int not null auto_increment,
     expires datetime default null,
     data text default null,
     createAt datetime default null,
     updatedAt datetime default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS clinics;
CREATE TABLE clinics (
     id int not null auto_increment,
     name varchar(255) default null,
     address varchar(255) default null,
     phone varchar(255) default null,
     introductionHTML text default null,
     introductionMarkdown text default null,
     description text default null,
     image varchar(255) default null,
     workingHours varchar(255) default null,
     importantNotes varchar(255) default null,
     examinationFee decimal(11, 0) default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS supporterlogs;
CREATE TABLE supporterlogs (
     id int not null auto_increment,
     patientId int default null,
     supporterId int default null,
     content varchar(255) default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS places;
CREATE TABLE places (
     id int not null auto_increment,
     name varchar(255) default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS comments;
CREATE TABLE comments (
     id int not null auto_increment,
     doctorId int default null,
     patientId int default null,
     timeBooking varchar(255) default null,
     dateBooking text default null,
     content text default null,
     status tinyint default null,
     consultationFee int default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS posts;
CREATE TABLE posts (
     id int not null auto_increment,
     title varchar(255) default null,
     contentMarkdown text default null,
     contentHTML text default null,
     timeBooking varchar(255) default null,
     dateBooking text default null,
     forDoctorId int default null,
     forSpecializationId int default null,
     forClinicId int default null,
     writerId int default null,
     confirmByDoctor tinyint default null,
     image varchar(255) default null,
     consultationFee int default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS schedules;
CREATE TABLE schedules (
     id int not null auto_increment,
     doctorId int default null,
     date varchar(255) default null,
     time varchar(255) default null,
     maxBooking int default null,
     sumBooking int default null,
     doctorFee int default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS doctor_users;
CREATE TABLE doctor_users (
     id INT NOT NULL AUTO_INCREMENT,
     doctorId INT DEFAULT NULL,
     clinicId INT DEFAULT NULL,
     specializationId INT DEFAULT NULL,
     introduction TEXT DEFAULT NULL,  -- Giới thiệu chung     
	 trainingHistory TEXT DEFAULT NULL,  -- Quá trình đào tạo     
	 achievements TEXT DEFAULT NULL,  -- Các thành tựu đạt được     
	 createAt DATETIME DEFAULT NULL,
     updatedAt DATETIME DEFAULT NULL,
     deletedAt DATETIME DEFAULT NULL,
     PRIMARY KEY(id) 
);
DROP TABLE IF EXISTS extrainfos;
CREATE TABLE extrainfos (
     id int not null auto_increment,
     patientId int default null,
     historyBreath text default null,
     placeId int default null,
     oldForms text default null,
     sendForms text default null,
     moreInfo text default null,
     createAt datetime default null,
     updatedAt datetime default null,
     deletedAt datetime default null,
     PRIMARY KEY(id) 
);

ALTER TABLE patients
     ADD CONSTRAINT fk_doctor_id FOREIGN KEY (doctorId) REFERENCES doctor_users(id),
     ADD CONSTRAINT fk_status_id FOREIGN KEY (statusId) REFERENCES statuses(id),
     ADD CONSTRAINT fk_user_id FOREIGN KEY (patientId) REFERENCES users(id);

ALTER TABLE supporterlogs
     ADD CONSTRAINT fk_patient_id FOREIGN KEY (patientId) REFERENCES patients(id),
     ADD CONSTRAINT fk_supporter_id FOREIGN KEY (supporterId) REFERENCES users(id);

ALTER TABLE comments
     ADD CONSTRAINT fk_patientId_comments FOREIGN KEY (patientId) REFERENCES patients(id),
     ADD CONSTRAINT fk_doctorId_comments FOREIGN KEY (doctorId) REFERENCES doctor_users(id);

ALTER TABLE specializations
     ADD CONSTRAINT fk_clinic_id FOREIGN KEY (clinicId) REFERENCES clinics(id);

ALTER TABLE users
     ADD CONSTRAINT fk_users_role_id FOREIGN KEY (roleId) REFERENCES roles(id);

ALTER TABLE posts
     ADD CONSTRAINT fk_forDoctorId FOREIGN KEY (forDoctorId) REFERENCES doctor_users(id),
     ADD CONSTRAINT fk_forSpecializationId FOREIGN KEY (forSpecializationId) REFERENCES specializations(id),
     ADD CONSTRAINT fk_forClinicId FOREIGN KEY (forClinicId) REFERENCES clinics(id),
     ADD CONSTRAINT fk_writerId FOREIGN KEY (writerId) REFERENCES users(id);

ALTER TABLE schedules
     ADD CONSTRAINT fk_doctor_id_schedules FOREIGN KEY (doctorId) REFERENCES doctor_users(id);

ALTER TABLE doctor_users
     ADD CONSTRAINT fk_doctorId FOREIGN KEY (doctorId) REFERENCES users(id),
     ADD CONSTRAINT fk_clinicId FOREIGN KEY (clinicId) REFERENCES clinics(id),
     ADD CONSTRAINT fk_specializationId FOREIGN KEY (specializationId) REFERENCES specializations(id);

ALTER TABLE extrainfos
     ADD CONSTRAINT fk_patientId FOREIGN KEY (patientId) REFERENCES patients(id),
     ADD CONSTRAINT fk_placeId FOREIGN KEY (placeId) REFERENCES places(id);

INSERT INTO roles VALUES (1, 'ROLE_ADMIN', NOW(), NOW(), null),
						 (2, 'ROLE_DOCTOR', NOW(), NOW(), null),
						 (3, 'ROLE_PATIENT', NOW(), NOW(), null);