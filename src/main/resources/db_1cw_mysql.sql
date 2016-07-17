SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


DELIMITER $$
USE `db_1cw_local`$$
CREATE FUNCTION `func_inc_var_session`(count int(11)) RETURNS int(11)
begin
  SET @var := IFNULL(@var,0);
  SET @var := IF(@var = count,
				0,
				@var + 1);
  return @var;
end;$$

DELIMITER ;


CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`advert` (
  `advert_id` INT(11) NOT NULL ,
  `account_id` INT(11) NOT NULL ,
  PRIMARY KEY (`advert_id`) ,
  INDEX `fk_advert_account1_idx` (`account_id` ASC) ,
  CONSTRAINT `fk_advert_account1`
    FOREIGN KEY (`account_id` )
    REFERENCES `db_1cw_local`.`account` (`account_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`authorities` (
  `username` VARCHAR(50) NOT NULL ,
  `authority` VARCHAR(50) NOT NULL ,
  UNIQUE INDEX `ix_auth_username` (`username` ASC, `authority` ASC) ,
  CONSTRAINT `fk_authorities_users`
    FOREIGN KEY (`username` )
    REFERENCES `db_1cw_local`.`users` (`username` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`group_authorities` (
  `group_id` BIGINT(20) NOT NULL ,
  `authority` VARCHAR(50) NOT NULL ,
  INDEX `fk_group_authorities_group` (`group_id` ASC) ,
  CONSTRAINT `fk_group_authorities_group`
    FOREIGN KEY (`group_id` )
    REFERENCES `db_1cw_local`.`groups` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`group_members` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(50) NOT NULL ,
  `group_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_group_members_group` (`group_id` ASC) ,
  CONSTRAINT `fk_group_members_group`
    FOREIGN KEY (`group_id` )
    REFERENCES `db_1cw_local`.`groups` (`id` ))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`groups` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `group_name` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`persistent_logins` (
  `username` VARCHAR(64) NOT NULL ,
  `series` VARCHAR(64) NOT NULL ,
  `token` VARCHAR(64) NOT NULL ,
  `last_used` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`series`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`tags` (
  `tag_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `tag_name` VARCHAR(200) NOT NULL ,
  `md5_checksum` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`tag_id`) ,
  INDEX `indx_tag_name` (`tag_name` ASC) ,
  INDEX `indx_md5` (`md5_checksum` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`users` (
  `username` VARCHAR(50) NOT NULL ,
  `password` VARCHAR(50) NOT NULL ,
  `enabled` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`username`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`listing_category` (
  `listing_category_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `category_name` VARCHAR(75) NOT NULL ,
  `category_description` VARCHAR(200) NULL DEFAULT NULL ,
  PRIMARY KEY (`listing_category_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`advert_registry` (
  `advert_registry_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `advert_member_id` INT(11) NOT NULL ,
  PRIMARY KEY (`advert_registry_id`) ,
  INDEX `fk_advert_registry_advert1_idx` (`advert_member_id` ASC) ,
  CONSTRAINT `fk_advert_registry_advert1`
    FOREIGN KEY (`advert_member_id` )
    REFERENCES `db_1cw_local`.`advert` (`advert_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`schedule` (
  `schedule_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `advert_id` INT(11) NOT NULL ,
  PRIMARY KEY (`schedule_id`) ,
  INDEX `fk_schedule_advert1_idx` (`advert_id` ASC) ,
  CONSTRAINT `fk_schedule_advert1`
    FOREIGN KEY (`advert_id` )
    REFERENCES `db_1cw_local`.`advert` (`advert_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`time_slot` (
  `time_slot_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `schedule_id` INT(11) NOT NULL ,
  `is_blocked` TINYINT(4) NULL DEFAULT NULL ,
  `time_of_day` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`time_slot_id`) ,
  INDEX `fk_time_slot_schedule1_idx` (`schedule_id` ASC) ,
  CONSTRAINT `fk_time_slot_schedule1`
    FOREIGN KEY (`schedule_id` )
    REFERENCES `db_1cw_local`.`schedule` (`schedule_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`column_advert_content` (
  `column_advert_content_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `heading` VARCHAR(75) NOT NULL ,
  `body` VARCHAR(400) NULL DEFAULT NULL ,
  `advert_id` INT(11) NOT NULL ,
  PRIMARY KEY (`column_advert_content_id`) ,
  INDEX `fk_column_advert_content_advert1_idx` (`advert_id` ASC) ,
  CONSTRAINT `fk_column_advert_content_advert1`
    FOREIGN KEY (`advert_id` )
    REFERENCES `db_1cw_local`.`advert` (`advert_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`advert_registry_has_listing_category` (
  `advert_registry_id` INT(11) NOT NULL ,
  `listing_category_id` INT(11) NOT NULL ,
  `advert_registry_has_listing_category_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `advert_registry_has_listing_categorycol` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`advert_registry_has_listing_category_id`) ,
  INDEX `fk_advert_registry_has_listing_category_listing_category1_idx` (`listing_category_id` ASC) ,
  INDEX `fk_advert_registry_has_listing_category_advert_registry1_idx` (`advert_registry_id` ASC) ,
  CONSTRAINT `fk_advert_registry_has_listing_category_advert_registry1`
    FOREIGN KEY (`advert_registry_id` )
    REFERENCES `db_1cw_local`.`advert_registry` (`advert_registry_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_advert_registry_has_listing_category_listing_category1`
    FOREIGN KEY (`listing_category_id` )
    REFERENCES `db_1cw_local`.`listing_category` (`listing_category_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`advert_has_tags` (
  `advert_id` INT(11) NOT NULL ,
  `tag_id` INT(11) NOT NULL ,
  `advert_has_tags_id` INT(11) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`advert_has_tags_id`) ,
  INDEX `fk_advert_has_tags_tags1_idx` (`tag_id` ASC) ,
  INDEX `fk_advert_has_tags_advert1_idx` (`advert_id` ASC) ,
  CONSTRAINT `fk_advert_has_tags_advert1`
    FOREIGN KEY (`advert_id` )
    REFERENCES `db_1cw_local`.`advert` (`advert_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_advert_has_tags_tags1`
    FOREIGN KEY (`tag_id` )
    REFERENCES `db_1cw_local`.`tags` (`tag_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`account` (
  `account_id` INT(11) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`account_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`account_details` (
  `account_details_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `account_holder_firstname` VARCHAR(75) NULL DEFAULT NULL ,
  `account_holder_lastname` VARCHAR(150) NULL DEFAULT NULL ,
  `account_holder_email` VARCHAR(200) NULL DEFAULT NULL ,
  `account_holder_mobile` VARCHAR(45) NULL DEFAULT NULL ,
  `account_id` INT(11) NOT NULL ,
  PRIMARY KEY (`account_details_id`) ,
  INDEX `fk_account_details_account1_idx` (`account_id` ASC) ,
  CONSTRAINT `fk_account_details_account1`
    FOREIGN KEY (`account_id` )
    REFERENCES `db_1cw_local`.`account` (`account_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`account_users` (
  `account_users_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `account_id` INT(11) NOT NULL ,
  `username` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`account_users_id`) ,
  INDEX `fk_account_users_account1_idx` (`account_id` ASC) ,
  INDEX `fk_account_users_users1_idx` (`username` ASC) ,
  CONSTRAINT `fk_account_users_account1`
    FOREIGN KEY (`account_id` )
    REFERENCES `db_1cw_local`.`account` (`account_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_users_users1`
    FOREIGN KEY (`username` )
    REFERENCES `db_1cw_local`.`users` (`username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`spend_limit` (
  `spend_limit_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `advert_id` INT(11) NULL DEFAULT NULL ,
  `account_id` INT(11) NULL DEFAULT NULL ,
  `spend_limit_type` VARCHAR(45) NULL DEFAULT NULL ,
  `spend_limit_value` DECIMAL(3) NULL DEFAULT 0 ,
  PRIMARY KEY (`spend_limit_id`) ,
  INDEX `fk_spend_limit_advert1_idx` (`advert_id` ASC) ,
  INDEX `fk_spend_limit_account1_idx` (`account_id` ASC) ,
  CONSTRAINT `fk_spend_limit_advert1`
    FOREIGN KEY (`advert_id` )
    REFERENCES `db_1cw_local`.`advert` (`advert_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_spend_limit_account1`
    FOREIGN KEY (`account_id` )
    REFERENCES `db_1cw_local`.`account` (`account_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`account_registrations` (
  `account_registrations_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `registration_datetime` DATETIME NULL DEFAULT NULL ,
  `completion_datetime` DATETIME NULL DEFAULT NULL ,
  `account_id` INT(11) NOT NULL ,
  PRIMARY KEY (`account_registrations_id`) ,
  INDEX `fk_account_registrations_account1_idx` (`account_id` ASC) ,
  CONSTRAINT `fk_account_registrations_account1`
    FOREIGN KEY (`account_id` )
    REFERENCES `db_1cw_local`.`account` (`account_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `db_1cw_local`.`advert_scores` (
  `advert_scores_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `advert_score_value` DECIMAL(3) NOT NULL ,
  `advert_has_tags_id` INT(11) NULL DEFAULT NULL ,
  `advert_score_type` INT(11) NOT NULL ,
  `spend_limit_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`advert_scores_id`) ,
  INDEX `fk_advert_scores_advert_has_tags1_idx` (`advert_has_tags_id` ASC) ,
  INDEX `fk_advert_scores_spend_limit1_idx` (`spend_limit_id` ASC) ,
  CONSTRAINT `fk_advert_scores_advert_has_tags1`
    FOREIGN KEY (`advert_has_tags_id` )
    REFERENCES `db_1cw_local`.`advert_has_tags` (`advert_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_advert_scores_spend_limit1`
    FOREIGN KEY (`spend_limit_id` )
    REFERENCES `db_1cw_local`.`spend_limit` (`spend_limit_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;


-- -----------------------------------------------------
-- Placeholder table for view `db_1cw_local`.`advert_basic_score`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_1cw_local`.`advert_basic_score` (`advert_registry_id` INT, `advert_member_id` INT, `spend_limit_value` INT);

-- -----------------------------------------------------
-- Placeholder table for view `db_1cw_local`.`default_scorecard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_1cw_local`.`default_scorecard` (`rank` INT);


USE `db_1cw_local`;

-- -----------------------------------------------------
-- View `db_1cw_local`.`advert_basic_score`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_1cw_local`.`advert_basic_score`;
USE `db_1cw_local`;
CREATE  OR REPLACE VIEW `db_1cw_local`.`advert_basic_score` AS
select  ar.*, s.spend_limit_value from advert_registry ar
	join advert a on a.advert_id = ar.advert_member_id
	left join spend_limit s on s.advert_id = a.advert_id 
	order by s.spend_limit_value desc;


USE `db_1cw_local`;

-- -----------------------------------------------------
-- View `db_1cw_local`.`default_scorecard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_1cw_local`.`default_scorecard`;
USE `db_1cw_local`;
CREATE  OR REPLACE VIEW `db_1cw_local`.`default_scorecard` AS
select func_inc_var_session(v1.count) rank, v2.* from (
select count(*) count, abs.* from advert_basic_score abs) v1
join (select  * from advert_basic_score) v2;
DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
