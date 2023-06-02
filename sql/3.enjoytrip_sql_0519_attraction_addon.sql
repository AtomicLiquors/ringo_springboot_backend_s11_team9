
use enjoytrip;

DROP TABLE IF EXISTS attraction_likes;
CREATE TABLE attraction_likes(
	attraction_likes_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    content_id INT NOT NULL,
    CONSTRAINT `unique_attractionlikes_userid_contentid` UNIQUE (user_id, content_id),
    CONSTRAINT `fk_attractionlikes_user_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_attractionlikes_attractionInfo_content_id` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS attraction_rating;
CREATE TABLE attraction_rating(
	attraction_rating_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    content_id INT NOT NULL,
    attraction_rating FLOAT NOT NULL,
    CONSTRAINT `unique_attraction_rating_userid_contentid` UNIQUE (user_id, content_id),
    CONSTRAINT `fk_attraction_rating_user_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_attraction_rating_attractionInfo_content_id` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS plan;
CREATE TABLE IF NOT EXISTS `enjoytrip`.`plan` (
  `plan_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NULL,
  `plan_start_date` DATE NULL,
  `plan_end_date` DATE NULL,
  `plan_thumbnail` VARCHAR(100) NULL,
  PRIMARY KEY (`plan_id`),
   CONSTRAINT `user_id_to_user_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
     ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE = InnoDB;

alter table `enjoytrip`.`plan` ADD `plan_title` VARCHAR(50) AFTER `user_id`;
alter table `enjoytrip`.`plan` ADD `plan_desc` VARCHAR(50) AFTER `plan_title`;


DROP TABLE IF EXISTS plan_detail;
CREATE TABLE IF NOT EXISTS `enjoytrip`.`plan_detail` (
  `plan_detail_id` INT NOT NULL AUTO_INCREMENT,
  `plan_id` INT NOT NULL,
  `content_id` INT NULL,
  `trip_no` INT NULL,
  `trip_date` DATE NULL,
  `arrival_time` TIMESTAMP NULL,
  `isWaypoint` TINYINT NOT NULL,
  PRIMARY KEY (`plan_detail_id`),
  CONSTRAINT `plan_id_to_plan_plan_id_fk`
    FOREIGN KEY (`plan_id`)
    REFERENCES `enjoytrip`.`plan` (`plan_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `content_id_to_attraction_info_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;