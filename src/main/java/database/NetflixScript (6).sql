-- MySQL Script generated by MySQL Workbench
-- Tue Feb 26 15:08:40 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema NetflixDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `NetflixDB` ;

-- -----------------------------------------------------
-- Schema NetflixDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `NetflixDB` DEFAULT CHARACTER SET utf8 ;
USE `NetflixDB` ;

-- -----------------------------------------------------
-- Table `NetflixDB`.`LANGUAGE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`LANGUAGE` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`LANGUAGE` (
  `language_id` INT NOT NULL AUTO_INCREMENT,
  `language_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`language_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`POSITION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`POSITION` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`POSITION` (
  `position_id` INT NOT NULL AUTO_INCREMENT,
  `position_desc` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`position_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`ACCOUNT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`ACCOUNT` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`ACCOUNT` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `position` INT NOT NULL,
  `password` VARCHAR(75) NOT NULL,
  `firstname` VARCHAR(100) NOT NULL,
  `lastname` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  `rate` DOUBLE NOT NULL,
  `portfolio` VARCHAR(150) NULL,
  `isactive` TINYINT NOT NULL,
  `image_path` VARCHAR(200) NULL,
  INDEX `position_id_idx` (`position` ASC),
  PRIMARY KEY (`user_id`),
  CONSTRAINT `position_id`
    FOREIGN KEY (`position`)
    REFERENCES `NetflixDB`.`POSITION` (`position_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `NetflixDB`.`REPORT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`REPORT` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`REPORT` (
  `report_id` INT NOT NULL AUTO_INCREMENT,
  `report_ref` VARCHAR(150) NOT NULL,
  `creation_date` DATE NOT NULL,
  `u_id` INT NOT NULL,
  PRIMARY KEY (`report_id`),
  INDEX `fk_REPORT_ACCOUNT1_idx` (`u_id` ASC),
  CONSTRAINT `fk_REPORT_ACCOUNT1`
    FOREIGN KEY (`u_id`)
    REFERENCES `NetflixDB`.`ACCOUNT` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`SKILLSET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`SKILLSET` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`SKILLSET` (
  `skillset_id` INT NOT NULL AUTO_INCREMENT,
  `skillset_desc` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`skillset_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`MESSAGE_GROUP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`MESSAGE_GROUP` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`MESSAGE_GROUP` (
  `message_group_id` INT NOT NULL AUTO_INCREMENT,
  `chatlog_ref` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`message_group_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`TITLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`TITLE` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`TITLE` (
  `title_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `is_active` TINYINT NOT NULL,
  `priority` TINYINT NOT NULL,
  `design_info` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`title_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`ARTWORK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`ARTWORK` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`ARTWORK` (
  `artwork_id` INT NOT NULL AUTO_INCREMENT,
  `title_id` INT NOT NULL,
  `artwork_name` VARCHAR(100) NOT NULL,
  `artwork_ref` VARCHAR(100) NOT NULL,
  `rating` INT NOT NULL,
  PRIMARY KEY (`artwork_id`),
  INDEX `title_id_idx` (`title_id` ASC),
  CONSTRAINT `title_id`
    FOREIGN KEY (`title_id`)
    REFERENCES `NetflixDB`.`TITLE` (`title_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`FEEDBACK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`FEEDBACK` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`FEEDBACK` (
  `feedback_id` INT NOT NULL AUTO_INCREMENT,
  `artwork_id` INT NOT NULL,
  `feedback_desc` VARCHAR(500) NOT NULL,
  `feedback_date` DATE NOT NULL,
  `isread_date` DATE NOT NULL,
  `isread` TINYINT NOT NULL,
  PRIMARY KEY (`feedback_id`),
  INDEX `fk_FEEDBACK_ARTWORK1_idx` (`artwork_id` ASC),
  CONSTRAINT `fk_FEEDBACK_ARTWORK1`
    FOREIGN KEY (`artwork_id`)
    REFERENCES `NetflixDB`.`ARTWORK` (`artwork_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`STYLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`STYLE` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`STYLE` (
  `style_id` INT NOT NULL,
  `style_desc` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`style_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`GENRE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`GENRE` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`GENRE` (
  `genre_id` INT NOT NULL AUTO_INCREMENT,
  `genre_desc` VARCHAR(75) NOT NULL,
  PRIMARY KEY (`genre_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`ASSET_TYPE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`ASSET_TYPE` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`ASSET_TYPE` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `type_desc` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`ASSET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`ASSET` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`ASSET` (
  `type_id` INT NOT NULL,
  `title_id` INT NOT NULL,
  `asset_id` INT NOT NULL AUTO_INCREMENT,
  `type_ref` VARCHAR(45) NOT NULL,
  INDEX `fk_ASSET_ASSET_TYPE1_idx` (`type_id` ASC),
  INDEX `fk_ASSET_TITLE1_idx` (`title_id` ASC),
  PRIMARY KEY (`asset_id`),
  CONSTRAINT `fk_ASSET_ASSET_TYPE1`
    FOREIGN KEY (`type_id`)
    REFERENCES `NetflixDB`.`ASSET_TYPE` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ASSET_TITLE1`
    FOREIGN KEY (`title_id`)
    REFERENCES `NetflixDB`.`TITLE` (`title_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`BACKUP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`BACKUP` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`BACKUP` (
  `backup_id` INT NOT NULL AUTO_INCREMENT,
  `backup_ref` VARCHAR(100) NOT NULL,
  `backup_date` DATE NOT NULL,
  PRIMARY KEY (`backup_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`ROUND_ARTWORK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`ROUND_ARTWORK` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`ROUND_ARTWORK` (
  `round_id` INT NOT NULL AUTO_INCREMENT,
  `round_number` INT NOT NULL,
  `round_description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`round_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`TITLE_has_ACCOUNT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`TITLE_has_ACCOUNT` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`TITLE_has_ACCOUNT` (
  `TITLE_title_id` INT NOT NULL,
  `ACCOUNT_user_id` INT NOT NULL,
  PRIMARY KEY (`TITLE_title_id`, `ACCOUNT_user_id`),
  INDEX `fk_TITLE_has_ACCOUNT_ACCOUNT1_idx` (`ACCOUNT_user_id` ASC),
  INDEX `fk_TITLE_has_ACCOUNT_TITLE1_idx` (`TITLE_title_id` ASC),
  CONSTRAINT `fk_TITLE_has_ACCOUNT_TITLE1`
    FOREIGN KEY (`TITLE_title_id`)
    REFERENCES `NetflixDB`.`TITLE` (`title_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TITLE_has_ACCOUNT_ACCOUNT1`
    FOREIGN KEY (`ACCOUNT_user_id`)
    REFERENCES `NetflixDB`.`ACCOUNT` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`STYLE_has_ARTWORK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`STYLE_has_ARTWORK` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`STYLE_has_ARTWORK` (
  `STYLE_style_id` INT NOT NULL,
  `ARTWORK_artwork_id` INT NOT NULL,
  PRIMARY KEY (`STYLE_style_id`, `ARTWORK_artwork_id`),
  INDEX `fk_STYLE_has_ARTWORK_ARTWORK1_idx` (`ARTWORK_artwork_id` ASC),
  INDEX `fk_STYLE_has_ARTWORK_STYLE1_idx` (`STYLE_style_id` ASC),
  CONSTRAINT `fk_STYLE_has_ARTWORK_STYLE1`
    FOREIGN KEY (`STYLE_style_id`)
    REFERENCES `NetflixDB`.`STYLE` (`style_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STYLE_has_ARTWORK_ARTWORK1`
    FOREIGN KEY (`ARTWORK_artwork_id`)
    REFERENCES `NetflixDB`.`ARTWORK` (`artwork_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`GENRE_has_TITLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`GENRE_has_TITLE` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`GENRE_has_TITLE` (
  `GENRE_genre_id` INT NOT NULL,
  `TITLE_title_id` INT NOT NULL,
  PRIMARY KEY (`GENRE_genre_id`, `TITLE_title_id`),
  INDEX `fk_GENRE_has_TITLE_TITLE1_idx` (`TITLE_title_id` ASC),
  INDEX `fk_GENRE_has_TITLE_GENRE1_idx` (`GENRE_genre_id` ASC),
  CONSTRAINT `fk_GENRE_has_TITLE_GENRE1`
    FOREIGN KEY (`GENRE_genre_id`)
    REFERENCES `NetflixDB`.`GENRE` (`genre_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GENRE_has_TITLE_TITLE1`
    FOREIGN KEY (`TITLE_title_id`)
    REFERENCES `NetflixDB`.`TITLE` (`title_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`ACCOUNT_has_MESSAGE_GROUP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`ACCOUNT_has_MESSAGE_GROUP` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`ACCOUNT_has_MESSAGE_GROUP` (
  `ACCOUNT_user_id` INT NOT NULL,
  `MESSAGE_GROUP_message_group_id` INT NOT NULL,
  PRIMARY KEY (`ACCOUNT_user_id`, `MESSAGE_GROUP_message_group_id`),
  INDEX `fk_ACCOUNT_has_MESSAGE_GROUP_MESSAGE_GROUP1_idx` (`MESSAGE_GROUP_message_group_id` ASC),
  INDEX `fk_ACCOUNT_has_MESSAGE_GROUP_ACCOUNT1_idx` (`ACCOUNT_user_id` ASC),
  CONSTRAINT `fk_ACCOUNT_has_MESSAGE_GROUP_ACCOUNT1`
    FOREIGN KEY (`ACCOUNT_user_id`)
    REFERENCES `NetflixDB`.`ACCOUNT` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ACCOUNT_has_MESSAGE_GROUP_MESSAGE_GROUP1`
    FOREIGN KEY (`MESSAGE_GROUP_message_group_id`)
    REFERENCES `NetflixDB`.`MESSAGE_GROUP` (`message_group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`ACCOUNT_has_SKILLSET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`ACCOUNT_has_SKILLSET` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`ACCOUNT_has_SKILLSET` (
  `ACCOUNT_user_id` INT NOT NULL,
  `SKILLSET_skillset_id` INT NOT NULL,
  PRIMARY KEY (`ACCOUNT_user_id`, `SKILLSET_skillset_id`),
  INDEX `fk_ACCOUNT_has_SKILLSET_SKILLSET1_idx` (`SKILLSET_skillset_id` ASC),
  INDEX `fk_ACCOUNT_has_SKILLSET_ACCOUNT1_idx` (`ACCOUNT_user_id` ASC),
  CONSTRAINT `fk_ACCOUNT_has_SKILLSET_ACCOUNT1`
    FOREIGN KEY (`ACCOUNT_user_id`)
    REFERENCES `NetflixDB`.`ACCOUNT` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ACCOUNT_has_SKILLSET_SKILLSET1`
    FOREIGN KEY (`SKILLSET_skillset_id`)
    REFERENCES `NetflixDB`.`SKILLSET` (`skillset_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`ACCOUNT_has_LANGUAGE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`ACCOUNT_has_LANGUAGE` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`ACCOUNT_has_LANGUAGE` (
  `ACCOUNT_user_id` INT NOT NULL,
  `LANGUAGE_language_id` INT NOT NULL,
  PRIMARY KEY (`ACCOUNT_user_id`, `LANGUAGE_language_id`),
  INDEX `fk_ACCOUNT_has_LANGUAGE_LANGUAGE1_idx` (`LANGUAGE_language_id` ASC),
  INDEX `fk_ACCOUNT_has_LANGUAGE_ACCOUNT1_idx` (`ACCOUNT_user_id` ASC),
  CONSTRAINT `fk_ACCOUNT_has_LANGUAGE_ACCOUNT1`
    FOREIGN KEY (`ACCOUNT_user_id`)
    REFERENCES `NetflixDB`.`ACCOUNT` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ACCOUNT_has_LANGUAGE_LANGUAGE1`
    FOREIGN KEY (`LANGUAGE_language_id`)
    REFERENCES `NetflixDB`.`LANGUAGE` (`language_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`ARTWORK_has_ROUND_ARTWORK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`ARTWORK_has_ROUND_ARTWORK` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`ARTWORK_has_ROUND_ARTWORK` (
  `ARTWORK_artwork_id` INT NOT NULL,
  `ROUND_ARTWORK_round_id` INT NOT NULL,
  PRIMARY KEY (`ARTWORK_artwork_id`, `ROUND_ARTWORK_round_id`),
  INDEX `fk_ARTWORK_has_ROUND_ARTWORK_ROUND_ARTWORK1_idx` (`ROUND_ARTWORK_round_id` ASC),
  INDEX `fk_ARTWORK_has_ROUND_ARTWORK_ARTWORK1_idx` (`ARTWORK_artwork_id` ASC),
  CONSTRAINT `fk_ARTWORK_has_ROUND_ARTWORK_ARTWORK1`
    FOREIGN KEY (`ARTWORK_artwork_id`)
    REFERENCES `NetflixDB`.`ARTWORK` (`artwork_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ARTWORK_has_ROUND_ARTWORK_ROUND_ARTWORK1`
    FOREIGN KEY (`ROUND_ARTWORK_round_id`)
    REFERENCES `NetflixDB`.`ROUND_ARTWORK` (`round_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`RECOVERY_USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`RECOVERY_USER` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`RECOVERY_USER` (
  `Email` VARCHAR(100) NOT NULL,
  `time_stamp` DATETIME NOT NULL,
  `recovery_id` VARCHAR(7) NOT NULL,
  PRIMARY KEY (`Email`))
ENGINE = InnoDB
KEY_BLOCK_SIZE = 2;


-- -----------------------------------------------------
-- Table `NetflixDB`.`DROPBOX_TOKEN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`DROPBOX_TOKEN` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`DROPBOX_TOKEN` (
  `TOKEN_ID` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`TOKEN_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetflixDB`.`SHARED_LINK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetflixDB`.`SHARED_LINK` ;

CREATE TABLE IF NOT EXISTS `NetflixDB`.`SHARED_LINK` (
  `shared_link_id` INT NOT NULL,
  `shared_link` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`shared_link_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- Test Values
-- Numbers are just placeholders, the real numbers should be like 1000, 2000, 3000, etc. 

CREATE EVENT 
delete_row
    ON SCHEDULE 
      EVERY 30 MINUTE
        DO 
delete from RECOVERY_USER 
WHERE time_stamp < time_stamp +INTERVAL 30 MINUTE;

--POSITION
insert into position (position_id,position_desc)
values (10,'Admin');

insert into position (position_id,position_desc)
values (11,'Freelancer');

insert into position (position_id,position_desc)
values (12,'Design Lead');

insert into position (position_id,position_desc)
values (13,'Coordinator');



--5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8 == password
--USER
insert into account (user_id,position,password,firstname,lastname,email,location,rate,portfolio,isactive,image_path)
values (12,10,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','admin','admin','testadmin@email.com','Canada',10,'www.test.com',1,'no');

insert into account (user_id,position,password,firstname,lastname,email,location,rate,portfolio,isactive,image_path)
values (13,12,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Alex','Jones','testlead@email.com','Canada',10,'www.test.com',1,'no');

insert into account (user_id,position,password,firstname,lastname,email,location,rate,portfolio,isactive,image_path)
values (14,13,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','toor','rot','testcoor@email.com','Canada',10,'www.test.com',1,'no');

insert into account (user_id,position,password,firstname,lastname,email,location,rate,portfolio,isactive,image_path)
values (15,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free1','Hill','testfree1@email.com','Canada',10,'www.test.com',1,'no');

insert into account (user_id,position,password,firstname,lastname,email,location,rate,portfolio,isactive,image_path)
values (16,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free2','Butt','testfree2@email.com','Canada',10,'www.test.com',1,'no');

insert into account (user_id,position,password,firstname,lastname,email,location,rate,portfolio,isactive,image_path)
values (17,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free3','Vasiliou','testfree3@email.com','Canada',10,'www.test.com',1,'no');

insert into account (user_id,position,password,firstname,lastname,email,location,rate,portfolio,isactive,image_path)
values (18,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free4','Low','testfree4@email.com','Canada',10,'www.test.com',1,'no');

insert into account (user_id,position,password,firstname,lastname,email,location,rate,portfolio,isactive,image_path)
values (19,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free5','Kadarov','testfree5@email.com','Canada',10,'www.test.com',1,'no');



-- 
-- 
-- --LANGUAGE
insert into language (language_id,language_name)
values (1,'ENGLISH');

insert into language (language_id,language_name)
values (2,'FRENCH');

insert into language (language_id,language_name)
values (3,'SPANISH');





-- --SKILLSET
insert into skillset (skillset_id,skillset_desc)
values (1,'My skillset.');

insert into skillset (skillset_id,skillset_desc)
values (2,'My skillset.');

insert into skillset (skillset_id,skillset_desc)
values (3,'My skillset.');

insert into ACCOUNT_has_SKILLSET (ACCOUNT_user_id,SKILLSET_skillset_id)
values (12,1);

insert into ACCOUNT_has_SKILLSET (ACCOUNT_user_id,SKILLSET_skillset_id)
values (13,1);

insert into ACCOUNT_has_SKILLSET (ACCOUNT_user_id,SKILLSET_skillset_id)
values (14,1);





-- --MESSAGE GROUP
insert into message_group (message_group_id,chatlog_ref)
values (1,'The chatlog reference.');

insert into message_group (message_group_id,chatlog_ref)
values (2,'The chatlog reference.');

insert into message_group (message_group_id,chatlog_ref)
values (3,'The chatlog reference.');





-- --USER MESSAGE GROUP BRIDGE
insert into ACCOUNT_has_MESSAGE_GROUP (MESSAGE_GROUP_message_group_id,ACCOUNT_user_id)
values (1,12);

insert into ACCOUNT_has_MESSAGE_GROUP (MESSAGE_GROUP_message_group_id,ACCOUNT_user_id)
values (2,13);

insert into ACCOUNT_has_MESSAGE_GROUP (MESSAGE_GROUP_message_group_id,ACCOUNT_user_id)
values (3,14);





-- --ROUND ARTWORK BRIDGE
insert into round_artwork(round_id,round_number,round_description)
values (1,1,'Not finished');

insert into round_artwork(round_id,round_number,round_description)
values (2,2,'Waiting');

insert into round_artwork(round_id,round_number,round_description)
values (3,3,'Completed');






-- --STYLE
insert into style (style_id,style_desc)
values (1,'The first style.');

insert into style (style_id,style_desc)
values (2,'The second style.');

insert into style (style_id,style_desc)
values (3,'The third style.');






-- --GENRE
insert into genre (genre_id,genre_desc)
values (1,'Comedy');

insert into genre (genre_id,genre_desc)
values (2,'Action');

insert into genre (genre_id,genre_desc)
values (3,'Drama');






-- --TITLE
insert into title (title_id,name,start_date,end_date,is_active,priority,design_info)
values (1,'Devilman Crybaby',CURDATE() - INTERVAL 14 DAY,CURDATE() + INTERVAL 8 DAY,1,1,'Design info.');

insert into title (title_id,name,start_date,end_date,is_active,priority,design_info)
values (2,'Superman',sysdate(),sysdate(),1,1,'Design info.');

insert into title (title_id,name,start_date,end_date,is_active,priority,design_info)
values (3,'dhstry',sysdate(),sysdate(),1,1,'Design info.');






-- --USER TITLE BRIDGE
insert into TITLE_has_ACCOUNT (ACCOUNT_user_id,TITLE_title_id)
values (12,1);

insert into TITLE_has_ACCOUNT (ACCOUNT_user_id,TITLE_title_id)
values (13,1);

insert into TITLE_has_ACCOUNT (ACCOUNT_user_id,TITLE_title_id)
values (14,1);

insert into TITLE_has_ACCOUNT (ACCOUNT_user_id,TITLE_title_id)
values (15,1);

insert into TITLE_has_ACCOUNT (ACCOUNT_user_id,TITLE_title_id)
values (16,1);

insert into TITLE_has_ACCOUNT (ACCOUNT_user_id,TITLE_title_id)
values (17,1);

insert into TITLE_has_ACCOUNT (ACCOUNT_user_id,TITLE_title_id)
values (18,1);

insert into TITLE_has_ACCOUNT (ACCOUNT_user_id,TITLE_title_id)
values (19,1);






-- --TITLE GENRE BRIDGE
insert into GENRE_has_TITLE (GENRE_genre_id,TITLE_title_id)
values (1,1);




-- --ARTWORK
insert into artwork(artwork_id,title_id,artwork_name,artwork_ref,rating)
values (1,1,'The first artwork','The first artwork reference',75);

insert into artwork(artwork_id,title_id,artwork_name,artwork_ref,rating)
values (2,2,'The second artwork','The second artwork reference',75);

insert into artwork(artwork_id,title_id,artwork_name,artwork_ref,rating)
values (3,3,'The third artwork','The third artwork reference',75);





-- --ARTWORK STYLE
insert into STYLE_has_ARTWORK (STYLE_style_id,ARTWORK_artwork_id)
values (1,1);

insert into STYLE_has_ARTWORK (STYLE_style_id,ARTWORK_artwork_id)
values (2,2);

insert into STYLE_has_ARTWORK (STYLE_style_id,ARTWORK_artwork_id)
values (3,3);






-- --ROUND
insert into ARTWORK_has_ROUND_ARTWORK (ROUND_ARTWORK_round_id,ARTWORK_artwork_id)
values (1,1);

insert into ARTWORK_has_ROUND_ARTWORK (ROUND_ARTWORK_round_id,ARTWORK_artwork_id)
values (2,2);

insert into ARTWORK_has_ROUND_ARTWORK (ROUND_ARTWORK_round_id,ARTWORK_artwork_id)
values (3,3);






-- --ASSET TYPE
insert into asset_type (type_id,type_desc)
values (1,'Type 1');

insert into asset_type (type_id,type_desc)
values (2,'Type 2');

insert into asset_type (type_id,type_desc)
values (3,'Type 3');





-- --ASSET
insert into asset (type_id,title_id,asset_id,type_ref)
values (1,1,1,'A type reference.');

insert into asset (type_id,title_id,asset_id,type_ref)
values (2,2,2,'A type reference.');

insert into asset (type_id,title_id,asset_id,type_ref)
values (3,3,3,'A type reference.');






-- --BACKUP
insert into backup (backup_id,backup_ref,backup_date)
values (1,'Backup 1',sysdate());

insert into backup (backup_id,backup_ref,backup_date)
values (2,'Backup 2',sysdate());

insert into backup (backup_id,backup_ref,backup_date)
values (3,'Backup 3',sysdate());





-- --LANGUAGE
insert into ACCOUNT_has_LANGUAGE (ACCOUNT_user_id,LANGUAGE_language_id)
values(12,1);

insert into ACCOUNT_has_LANGUAGE (ACCOUNT_user_id,LANGUAGE_language_id)
values(13,2);

insert into ACCOUNT_has_LANGUAGE (ACCOUNT_user_id,LANGUAGE_language_id)
values(14,3);

insert into ACCOUNT_has_LANGUAGE (ACCOUNT_user_id,LANGUAGE_language_id)
values(15,3);

insert into ACCOUNT_has_LANGUAGE (ACCOUNT_user_id,LANGUAGE_language_id)
values(16,3);

insert into ACCOUNT_has_LANGUAGE (ACCOUNT_user_id,LANGUAGE_language_id)
values(17,3);

insert into ACCOUNT_has_LANGUAGE (ACCOUNT_user_id,LANGUAGE_language_id)
values(18,3);

insert into ACCOUNT_has_LANGUAGE (ACCOUNT_user_id,LANGUAGE_language_id)
values(19,3);










-- --FEEDBACK
-- -- isread 1 = true, 0 = false
insert into feedback (feedback_id,artwork_id,feedback_desc,feedback_date,isread_date,isread)
values (1,1,'It is great!',sysdate(),sysdate(),1);

insert into feedback (feedback_id,artwork_id,feedback_desc,feedback_date,isread_date,isread)
values (2,2,'It is great!',sysdate(),sysdate(),1);

insert into feedback (feedback_id,artwork_id,feedback_desc,feedback_date,isread_date,isread)
values (3,3,'It is great!',sysdate(),sysdate(),0);