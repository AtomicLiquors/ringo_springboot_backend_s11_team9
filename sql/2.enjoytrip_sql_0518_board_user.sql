
use enjoytrip;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`user_no` int NOT NULL PRIMARY KEY auto_increment,
  `user_id` varchar(20) NOT NULL UNIQUE,
  `user_pw` varchar(64) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_regtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_email` varchar(45) NOT NULL,
  `user_address_basic` varchar(45) NOT NULL,
  `user_address_detail` varchar(45) DEFAULT NULL,
  `user_grade` int DEFAULT '0',
  `user_img` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
alter table user ADD token VARCHAR(1000);
ALTER TABLE user MODIFY COLUMN user_img VARCHAR(1000);

DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `board_id` int NOT NULL AUTO_INCREMENT,
  `board_subject` varchar(50) DEFAULT NULL,
  `board_content` text,
  `user_id` varchar(20) DEFAULT NULL,
  `board_regtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `board_views` int DEFAULT '0',
  `board_type` varchar(20) NOT NULL,
  PRIMARY KEY (`board_id`),
  KEY `board_user_userid_fk_idx` (`user_id`),
  CONSTRAINT `fk_board_user_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS relationship;
CREATE TABLE relationship (
  relationship_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id VARCHAR(20),
  target_id VARCHAR(20),
  user_status VARCHAR(20),
  CONSTRAINT `chk_user_status` CHECK (user_status IN ('follow', 'block', 'hide')),
  CONSTRAINT `fk_relationship_user_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_relationship_user_targetid` FOREIGN KEY (`target_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS board_comments;
CREATE TABLE board_comments (
  board_comments_id INT PRIMARY KEY AUTO_INCREMENT,
  board_id INT,
  board_comments_content TEXT,
  user_id VARCHAR(20),
  board_comments_regtime TIMESTAMP default current_timestamp,
  CONSTRAINT `fk_boardcomments_board_boardid` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS board_likes;
CREATE TABLE board_likes(
	board_likes_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    board_id INT,
    board_is_liked BOOLEAN default true,
    CONSTRAINT `fk_boardlikes_user_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_boardlikes_board_boardid` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE ON UPDATE CASCADE
);