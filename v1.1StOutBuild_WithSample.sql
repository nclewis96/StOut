USE Stout;

DROP TABLE IF EXISTS Course_Outcome;
DROP TABLE IF EXISTS Offering_Outcome;
DROP TABLE IF EXISTS Offering_Student;
DROP TABLE IF EXISTS Student_Outcome;
DROP TABLE IF EXISTS Outcome;
DROP TABLE IF EXISTS Metric;
DROP TABLE IF EXISTS Program_Cutoff;
DROP TABLE IF EXISTS Offering;
DROP TABLE IF EXISTS Semester;
DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Course_Prefix;
DROP TABLE IF EXISTS Program_Permissions;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Permission_Set;
DROP TABLE IF EXISTS Student_Outcome;
DROP TABLE IF EXISTS Semester_Type;
DROP TABLE IF EXISTS Program;
DROP TABLE IF EXISTS Semester_Type;
DROP TABLE IF EXISTS Job_Title;

SET time_zone = "-06:00";


CREATE TABLE `Course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_num` int(11) NOT NULL,
  `prefix_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `program_id` int(11) NOT NULL,
  PRIMARY KEY(`course_id`)
);

CREATE TABLE `Course_Outcome` (
  `course_id` int(11) NOT NULL,
  `outcome_id` int(11) NOT NULL,
  PRIMARY KEY (`course_id`, `outcome_id`)
);

CREATE TABLE `Offering` (
  `offering_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL,
  `section_num` int(11) NOT NULL,
  `locked` BIT,
  PRIMARY KEY(`offering_id`)
);

CREATE TABLE `Users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `job_title_id` int(11) NOT NULL,
  PRIMARY KEY(`user_id`)
);

CREATE TABLE `Semester_Type` (
  `semester_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(100) NOT NULL,
  `startMonth` varchar(100) NOT NULL,
  PRIMARY KEY(`semester_type_id`)
);

CREATE TABLE `Program_Cutoff` (
  `program_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  PRIMARY KEY(`program_id`, `semester_id`)
);

CREATE TABLE `Course_Prefix` (
  `prefix_id` int(11) NOT NULL AUTO_INCREMENT,
  `prefix` varchar(4),
  PRIMARY KEY (`prefix_id`)
);

CREATE TABLE `Job_Title` (
  `job_title_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY(`job_title_id`)
);

CREATE TABLE `Offering_Student` (
  `offering_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `student_name` varchar(100) NOT NULL,
  PRIMARY KEY(`offering_id`, `student_id`)
);

CREATE TABLE `Student_Outcome` (
  `offering_id` int(11) NOT NULL,
  `outcome_id` int(11) NOT NULL ,
  `student_id` int(11) NOT NULL,
  `score` DOUBLE NOT NULL,
  PRIMARY KEY(`student_id`, `outcome_id`, `offering_id`)
);

CREATE TABLE `Semester` (
  `semester_id` int(11) NOT NULL AUTO_INCREMENT,
  `semester_type_id` int(11) NOT NULL,
  `year` varchar(100) NOT NULL,
  PRIMARY KEY(`semester_id`)
);

CREATE TABLE `Outcome` (
  `outcome_id` int(11) NOT NULL AUTO_INCREMENT,
  `metric_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY(`outcome_id`)
);

CREATE TABLE `Permission_Set` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `ptable` varchar(100) NOT NULL,
  `permission_level` int(11) NOT NULL,
  PRIMARY KEY(`permission_id`)
);

CREATE TABLE `Offering_Outcome` (
  `offering_id` int(11) NOT NULL,
  `outcome_id` int(11) NOT NULL,
  `score` DOUBLE NOT NULL,
  PRIMARY KEY(`offering_id`, `outcome_id`)
);

CREATE TABLE `Program_Permissions` (
  `program_permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `program_id` int(11) NOT NULL,
  PRIMARY KEY(`program_permission_id`)
);

CREATE TABLE `Program` (
  `program_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`program_id`)
);

CREATE TABLE `Metric` (
  `metric_id` int(11) NOT NULL AUTO_INCREMENT,
  `program_id` int(11) NOT NULL,
  `name` varchar(100),
  PRIMARY KEY (`metric_id`)
);

ALTER TABLE `Course_Outcome`
	ADD CONSTRAINT `CourseOutCourse` FOREIGN KEY (`course_id`) REFERENCES `Course` (`course_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `CourseOutOutcome` FOREIGN KEY (`outcome_id`) REFERENCES `Outcome` (`outcome_id`) ON UPDATE CASCADE;
    
ALTER TABLE `Offering`
	ADD CONSTRAINT `courseOff` FOREIGN KEY (`course_id`) REFERENCES `Course` (`course_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `instOff` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `semOff` FOREIGN KEY (`semester_id`) REFERENCES `Semester` (`semester_id`) ON UPDATE CASCADE;
    
ALTER TABLE `Course`
	ADD CONSTRAINT `progCourse` FOREIGN KEY (`program_id`) REFERENCES `Program` (`program_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `prefixCourse` FOREIGN KEY (`prefix_id`) REFERENCES `Course_Prefix` (`prefix_id`) ON UPDATE CASCADE;

ALTER TABLE `Users`
	ADD CONSTRAINT `jobTitleUser` FOREIGN KEY (`job_title_id`) REFERENCES `Job_Title` (`job_title_id`) ON UPDATE CASCADE;

ALTER TABLE `Program_Cutoff`
	ADD CONSTRAINT `progProgCut` FOREIGN KEY (`program_id`) REFERENCES `Program` (`program_id`) ON UPDATE CASCADE,
	ADD CONSTRAINT `semProgCut` FOREIGN KEY (`semester_id`) REFERENCES `Semester` (`semester_id`) ON UPDATE CASCADE;
    
ALTER TABLE `Offering_Student`
	ADD CONSTRAINT `offOffStud` FOREIGN KEY (`offering_id`) REFERENCES `Offering` (`offering_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `studOffStud` FOREIGN KEY (`student_id`) REFERENCES `Student_Outcome` (`student_id`) ON UPDATE CASCADE;
    
ALTER TABLE `Student_Outcome`
	ADD CONSTRAINT `offStudOut` FOREIGN KEY (`offering_id`) REFERENCES `Offering` (`offering_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `outStudOut` FOREIGN KEY (`outcome_id`) REFERENCES `Outcome` (`outcome_id`) ON UPDATE CASCADE;

ALTER TABLE `Semester`
	ADD CONSTRAINT `semTypeSemester` FOREIGN KEY (`semester_type_id`) REFERENCES `Semester_Type` (`semester_type_id`) ON UPDATE CASCADE;

ALTER TABLE `Outcome`
	ADD CONSTRAINT `metOutcome` FOREIGN KEY (`metric_id`) REFERENCES `Metric` (`metric_id`) ON UPDATE CASCADE;
	
ALTER TABLE `Offering_Outcome`
	ADD CONSTRAINT `offOffOut` FOREIGN KEY (`offering_id`) REFERENCES `Offering` (`offering_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `outOffOut` FOREIGN KEY (`outcome_id`) REFERENCES `Outcome` (`outcome_id`) ON UPDATE CASCADE;

Alter TABLE `Program_Permissions`
	ADD CONSTRAINT `permsPP` FOREIGN KEY (`permission_id`) REFERENCES `Permission_Set` (`permission_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `userPerms` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `progPerms` FOREIGN KEY (`program_id`) REFERENCES `Program` (`program_id`) ON UPDATE CASCADE;
    
ALTER TABLE `Metric`
	ADD CONSTRAINT `progMetric` FOREIGN KEY (`program_id`) REFERENCES `Program` (`program_id`) ON UPDATE CASCADE;


/* Program_*/
INSERT INTO Program (name) 
VALUE ('Accounting'),
('Allied Health Med Supp'),
('American History'),
('Antrhopology'),
('Applied Health Science'),
('Arabic'),
('Automotive Technology');

/* Prefix */
INSERT INTO Course_Prefix (prefix)
VALUE ('ACTG'),
('AHMS'),
('HSTA'),
('ANTY'),
('AHS'),
('ARAB'),
('AT');

/*Course PK:course_id 
FK: FOREIGN KEY (`program_id`) REFERENCES `Program` (`program_id`) ON UPDATE CASCADE, 
FOREIGN KEY (`prefix_id`) REFERENCES `Course_Prefix` (`prefix_id`) ON UPDATE CASCADE;
*/
INSERT INTO Course (prefix_id,program_id,course_num,title)
VALUES ('1','1','111','Principles of Mangerial Accounting'),
('2','2','222','Advance Medical Coding'),
('3','3','333', 'American History I'),
('4','4','444','Anthropology & the human experience'),
('5','5','555','Advanced Physiology Of Exercise'),
('6','6','666','Elementary Modern Arabic I'),
('7','7','777','Brakes');

/*Metric PK: metric_id
FK: FOREIGN KEY (`program_id`) REFERENCES `Program` (`program_id`) ON UPDATE CASCADE;
*/
INSERT INTO Metric (program_id,name) 
VALUE ('1','first metric'),
('2','first metric'),
('3','first metric'),
('4','first metric'),
('5','first metric'),
('6','first metric'),
('7','first metric');

/*Outcome PK: outcome_id
FK:FOREIGN KEY (`metric_id`) REFERENCES `Metric` (`metric_id`) ON UPDATE CASCADE; */
INSERT INTO Outcome (metric_id, name, description) 
VALUES ('1' , 'Counting Money' , 'Can count the monies with such efficiency such accuracy'),
('2' , 'Cure coding' , 'Can code cures that kill viruses'),
('3' , 'Cure coding' , 'Can code cures that kill viruses'),
('4' , 'Cure coding' , 'Can code cures that kill viruses'),
('5' , 'Cure coding' , 'Can code cures that kill viruses'),
('6' , 'Cure coding' , 'Can code cures that kill viruses'),
('7' , 'Cure coding' , 'Can code cures that kill viruses');

/* Course_outcome:PK course_id , outcome_id
FK:FOREIGN KEY (`course_id`) REFERENCES `Course` (`course_id`) ON UPDATE CASCADE,
   FOREIGN KEY (`outcome_id`) REFERENCES `Outcome` (`outcome_id`) ON UPDATE CASCADE;  */   
INSERT INTO Course_Outcome (course_id, outcome_id)
VALUES ('1','1'),
('2','2'),
('3','3'),
('4','4'),
('5','5'),
('6','6'),
('7','7');

/*Job Title PK: Job_Title_id */
INSERT INTO Job_Title(job_title_id, title)
Value ('1','Faculty'),
('2','Program Director'),
('3','Observer');


/*Users PK:user_id
FK: FOREIGN KEY (`job_title_id`) REFERENCES `Job_Title` (`job_title_id`) ON UPDATE CASCADE;
*/
INSERT INTO Users (job_title_id, name, username) 
VALUES ('3','abdul', 'eggz' ),
('2','Trevor', 'Dataless'),
('2','Nathan', 'Nate'),
('2','Nathan', 'Sisune'),
('1','Jeff', 'Jeff') ;

/*Semester Type PK: symester_type_id*/
INSERT INTO Semester_Type (text, startMonth)
VALUES ('Spring', 'January'),
('Fall', 'September'),
('Summer', 'May');

/* Semster PK: semster_id 
FK:FOREIGN KEY (`semester_type_id`) REFERENCES `Semester_Type` (`semester_type_id`) ON UPDATE CASCADE;*/
INSERT INTO Semester (semester_type_id, year)
VALUES ('1','2017'),
('2','2017'),
('3','2017');


/*Offering PK:offering_id
FK: FOREIGN KEY (`course_id`) REFERENCES `Course` (`course_id`) ON UPDATE CASCADE,
FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`) ON UPDATE CASCADE,RT INTO Offering (offering_id, course_id, user_id, semester_id, section_num)
FOREIGN KEY (`semester_id`) REFERENCES `Semester` (`semester_id`) ON UPDATE CASCADE;ES ('88' ,'1','799','111','02'); */
INSERT INTO Offering (course_id, user_id, semester_id, section_num)
VALUES ('1','1','1','01'),
('2','2','2','01'),
('3','3','3','01'),
('4','4','1','01'),
('5','5','2','01'),
('6','5','3','01'),
('7','5','1','01');

/*Premisions Set PK:premission_id */
INSERT INTO Permission_Set(ptable, permission_level) 
VALUES ('this is the permission table','1'),
('this is the permission table','1'),
('this is the permission table','1'),
('this is the permission table','1'),
('this is the permission table','1'),
('this is the permission table','1'),
('this is the permission table','1');

/* Program Permissions PK program_permission_id ,

FK: FOREIGN KEY (`permission_id`) REFERENCES `Permission_Set` (`permission_id`) ON UPDATE CASCADE,
	FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`) ON UPDATE CASCADE,
    FOREIGN KEY (`program_id`) REFERENCES `Program` (`program_id`) ON UPDATE CASCADE;*/
INSERT INTO Program_Permissions (permission_id, user_id, program_id) 
VALUE ('1' ,'1', '1'),
('2' ,'2', '2'),
('3' ,'3', '3'),
('4' ,'4', '4'),
('5' ,'5', '5');

/*
Offering Outcome
FK:
ADD CONSTRAINT `offOffOut` FOREIGN KEY (`offering_id`) REFERENCES `Offering` (`offering_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `outOffOut` FOREIGN KEY (`outcome_id`) REFERENCES `Outcome` (`outcome_id`) ON UPDATE CASCADE;
*/
INSERT INTO Offering_Outcome (offering_id, outcome_id, score) 
VALUES ('1', '1','87'),
('2', '2','20'),
('3', '3','70'),
('4', '4','60'),
('5', '5','50'),
('6', '6','66'),
('7', '7','45');


/*
ADD CONSTRAINT `offStudOut` FOREIGN KEY (`offering_id`) REFERENCES `Offering` (`offering_id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `outStudOut` FOREIGN KEY (`outcome_id`) REFERENCES `Outcome` (`outcome_id`) ON UPDATE CASCADE;
    */
INSERT INTO Student_Outcome (offering_id, outcome_id, student_id, score) 
VALUES ('1', '1', '1', '90'),
('2', '2', '2', '90'),
('3', '3', '3', '90'),
('4', '4', '4', '90'),
('5', '5', '5', '90'),
('6', '6', '5', '90'),
('7', '7', '5', '90');

/*
FK:
ADD CONSTRAINT `progProgCut` FOREIGN KEY (`program_id`) REFERENCES `Program` (`program_id`) ON UPDATE CASCADE,
	ADD CONSTRAINT `semProgCut` FOREIGN KEY (`semester_id`) REFERENCES `Semester` (`semester_id`) ON UPDATE CASCADE;
    */
INSERT INTO Program_Cutoff ( program_id,semester_id,start_date, end_date)
VALUES ('1','1','20111011', 20121122),
('2','2','20111011', 20121122),
('3','3','20111011', 20121122),
('4','1','20111011', 20121122),
('5','2','20111011', 20121122),
('6','3','20111011', 20121122),
('7','1','20111011', 20121122);