-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: studentdb
-- Source Schemata: studentdb
-- Created: Thu Apr 20 23:10:11 2023
-- Workbench Version: 8.0.32
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema studentdb
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `studentdb` ;
CREATE SCHEMA IF NOT EXISTS `studentdb` ;

-- ----------------------------------------------------------------------------
-- Table studentdb.change_requests
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentdb`.`change_requests` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_type` VARCHAR(10) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `changable_field_1` VARCHAR(50) NOT NULL,
  `changable_field_2` VARCHAR(50) NOT NULL,
  `changable_field_3` VARCHAR(50) NOT NULL,
  `status` VARCHAR(20) NULL DEFAULT 'pending',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table studentdb.courses
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentdb`.`courses` (
  `Course_ID` INT NOT NULL,
  `Course_Name` VARCHAR(45) NOT NULL,
  `Branch` VARCHAR(45) NOT NULL,
  `Branch_Code` VARCHAR(25) NULL DEFAULT NULL,
  `No_of_Semesters` INT NOT NULL,
  PRIMARY KEY (`Course_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table studentdb.fees
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentdb`.`fees` (
  `Course_ID` INT NULL DEFAULT NULL,
  `semester1` VARCHAR(10) NULL DEFAULT NULL,
  `semester2` VARCHAR(10) NULL DEFAULT NULL,
  `semester3` VARCHAR(10) NULL DEFAULT NULL,
  `semester4` VARCHAR(10) NULL DEFAULT NULL,
  `semester5` VARCHAR(10) NULL DEFAULT NULL,
  `semester6` VARCHAR(10) NULL DEFAULT NULL,
  `semester7` VARCHAR(10) NULL DEFAULT NULL,
  `semester8` VARCHAR(10) NULL DEFAULT NULL,
  INDEX `Course_ID` (`Course_ID` ASC) VISIBLE,
  CONSTRAINT `fees_ibfk_1`
    FOREIGN KEY (`Course_ID`)
    REFERENCES `studentdb`.`courses` (`Course_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table studentdb.marks
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentdb`.`marks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Roll_No` INT NULL DEFAULT NULL,
  `subject_id` INT NULL DEFAULT NULL,
  `marks` INT NULL DEFAULT NULL,
  `letter_grade` CHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `Roll_No` (`Roll_No` ASC) VISIBLE,
  INDEX `subject_id` (`subject_id` ASC) VISIBLE,
  CONSTRAINT `marks_ibfk_1`
    FOREIGN KEY (`Roll_No`)
    REFERENCES `studentdb`.`students` (`Roll_No`),
  CONSTRAINT `marks_ibfk_3`
    FOREIGN KEY (`subject_id`)
    REFERENCES `studentdb`.`subjects` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 73
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table studentdb.results_total
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentdb`.`results_total` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Roll_No` INT NOT NULL,
  `course_id` INT NOT NULL,
  `semester` INT NOT NULL,
  `total` INT NOT NULL,
  `letter_grade` VARCHAR(10) NOT NULL,
  `sgpa` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unique_result` (`Roll_No` ASC, `course_id` ASC, `semester` ASC) VISIBLE,
  INDEX `course_id` (`course_id` ASC) VISIBLE,
  CONSTRAINT `results_total_ibfk_1`
    FOREIGN KEY (`Roll_No`)
    REFERENCES `studentdb`.`students` (`Roll_No`),
  CONSTRAINT `results_total_ibfk_2`
    FOREIGN KEY (`course_id`)
    REFERENCES `studentdb`.`courses` (`Course_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table studentdb.students
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentdb`.`students` (
  `Roll_No` INT NOT NULL,
  `First_name` VARCHAR(45) NOT NULL,
  `Last_name` VARCHAR(45) NOT NULL,
  `Fathers_name` VARCHAR(45) NULL DEFAULT NULL,
  `Email_id` VARCHAR(45) NOT NULL,
  `Date_of_birth` DATE NOT NULL,
  `Address` VARCHAR(200) NULL DEFAULT NULL,
  `Contact_no` VARCHAR(20) NULL DEFAULT NULL,
  `Course_ID` INT NOT NULL,
  PRIMARY KEY (`Email_id`),
  UNIQUE INDEX `Roll_No_UNIQUE` (`Roll_No` ASC) VISIBLE,
  UNIQUE INDEX `Email_id_UNIQUE` (`Email_id` ASC) VISIBLE,
  INDEX `fkey-courseID_idx` (`Course_ID` ASC) VISIBLE,
  CONSTRAINT `fkey-courseID`
    FOREIGN KEY (`Course_ID`)
    REFERENCES `studentdb`.`courses` (`Course_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 123
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table studentdb.subjects
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentdb`.`subjects` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NULL DEFAULT NULL,
  `semester` INT NULL DEFAULT NULL,
  `subjectName` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `course_id` (`course_id` ASC, `semester` ASC, `subjectName` ASC) VISIBLE,
  CONSTRAINT `subjects_ibfk_1`
    FOREIGN KEY (`course_id`)
    REFERENCES `studentdb`.`courses` (`Course_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 241
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table studentdb.teachers
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentdb`.`teachers` (
  `Teacher_ID` INT NOT NULL AUTO_INCREMENT,
  `First_name` VARCHAR(45) NULL DEFAULT NULL,
  `Last_name` VARCHAR(45) NULL DEFAULT NULL,
  `Designation` VARCHAR(45) NULL DEFAULT NULL,
  `Email_id` VARCHAR(45) NOT NULL,
  `Date_of_birth` DATE NOT NULL,
  `Address` VARCHAR(200) NULL DEFAULT NULL,
  `Contact_no` VARCHAR(45) NULL DEFAULT NULL,
  `Course_ID` INT NOT NULL,
  PRIMARY KEY (`Teacher_ID`),
  INDEX `fkey_teacherCourse_idx` (`Course_ID` ASC) VISIBLE,
  CONSTRAINT `fkey_teacherCourse`
    FOREIGN KEY (`Course_ID`)
    REFERENCES `studentdb`.`courses` (`Course_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1018
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table studentdb.users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentdb`.`users` (
  `user_id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  `user_type` ENUM('student', 'teacher', 'admin') NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Trigger studentdb.set_letter_grade
-- ----------------------------------------------------------------------------
DELIMITER $$
USE `studentdb`$$
CREATE DEFINER=`admin`@`%` TRIGGER `set_letter_grade` BEFORE INSERT ON `marks` FOR EACH ROW BEGIN
    DECLARE grade CHAR(1);
    IF NEW.marks >= 90 THEN
        SET grade = 'O';
    ELSEIF NEW.marks >= 80 THEN
        SET grade = 'E';
    ELSEIF NEW.marks >= 70 THEN
        SET grade = 'A';
    ELSEIF NEW.marks >= 60 THEN
        SET grade = 'B';
    ELSEIF NEW.marks >= 40 THEN
        SET grade = 'C';
    ELSE
        SET grade = 'F';
    END IF;
    SET NEW.letter_grade = grade;
END;

-- ----------------------------------------------------------------------------
-- Trigger studentdb.set_letter_grade_total
-- ----------------------------------------------------------------------------
DELIMITER $$
USE `studentdb`$$
CREATE DEFINER=`admin`@`%` TRIGGER `set_letter_grade_total` BEFORE INSERT ON `results_total` FOR EACH ROW BEGIN
    DECLARE grade CHAR(1);
    DECLARE avg_marks FLOAT;
    SET avg_marks=new.total/4.0;
    
    IF avg_marks >= 90 THEN
        SET grade = 'O';
    ELSEIF avg_marks >= 80 THEN
        SET grade = 'E';
    ELSEIF avg_marks >= 70 THEN
        SET grade = 'A';
    ELSEIF avg_marks >= 60 THEN
        SET grade = 'B';
    ELSEIF avg_marks >= 40 THEN
        SET grade = 'C';
    ELSE
        SET grade = 'F';
    END IF;
    SET NEW.letter_grade = grade;
    Set New.sgpa=avg_marks/10;
END;

-- ----------------------------------------------------------------------------
-- Trigger studentdb.results_total_BEFORE_UPDATE
-- ----------------------------------------------------------------------------
DELIMITER $$
USE `studentdb`$$
CREATE DEFINER=`admin`@`%` TRIGGER `results_total_BEFORE_UPDATE` BEFORE UPDATE ON `results_total` FOR EACH ROW BEGIN
DECLARE grade CHAR(1);
    DECLARE avg_marks FLOAT;
    SET avg_marks = NEW.total / 4.0;
    
    IF avg_marks >= 90 THEN
        SET grade = 'O';
    ELSEIF avg_marks >= 80 THEN
        SET grade = 'E';
    ELSEIF avg_marks >= 70 THEN
        SET grade = 'A';
    ELSEIF avg_marks >= 60 THEN
        SET grade = 'B';
    ELSEIF avg_marks >= 40 THEN
        SET grade = 'C';
    ELSE
        SET grade = 'F';
    END IF;
    SET NEW.letter_grade = grade;
    SET NEW.sgpa = avg_marks / 10;
END;

-- ----------------------------------------------------------------------------
-- Trigger studentdb.add_user_student
-- ----------------------------------------------------------------------------
DELIMITER $$
USE `studentdb`$$
CREATE DEFINER=`admin`@`%` TRIGGER `add_user_student` AFTER INSERT ON `students` FOR EACH ROW INSERT INTO users (user_id, password)
VALUES (NEW.email_id, DATE_FORMAT(NEW.date_of_birth, '%d%m%Y'));

-- ----------------------------------------------------------------------------
-- Trigger studentdb.delete_user_student
-- ----------------------------------------------------------------------------
DELIMITER $$
USE `studentdb`$$
CREATE DEFINER=`admin`@`%` TRIGGER `delete_user_student` BEFORE DELETE ON `students` FOR EACH ROW DELETE FROM users WHERE user_id = OLD.email_id;

-- ----------------------------------------------------------------------------
-- Trigger studentdb.add_user_teacher
-- ----------------------------------------------------------------------------
DELIMITER $$
USE `studentdb`$$
CREATE DEFINER=`admin`@`%` TRIGGER `add_user_teacher` AFTER INSERT ON `teachers` FOR EACH ROW INSERT INTO users (user_id, password)
VALUES (NEW.email_id, DATE_FORMAT(NEW.date_of_birth, '%d%m%Y'));

-- ----------------------------------------------------------------------------
-- Trigger studentdb.delete_user_teacher
-- ----------------------------------------------------------------------------
DELIMITER $$
USE `studentdb`$$
CREATE DEFINER=`admin`@`%` TRIGGER `delete_user_teacher` BEFORE DELETE ON `teachers` FOR EACH ROW DELETE FROM users WHERE user_id = OLD.email_id;
SET FOREIGN_KEY_CHECKS = 1;
