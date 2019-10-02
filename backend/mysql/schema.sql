DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` INT NOT NULL,
  `hashed_password` CHAR(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `tile`;
CREATE TABLE `tile` (
  `tile_id` INT NOT NULL,
  `ispinned` BOOLEAN DEFAULT 0,
  `end_datetime` DATETIME DEFAULT NULL,
  `create_datetime` DATETIME DEFAULT NULL,
  `title` VARCHAR(255) DEFAULT NULL,
  `message` VARCHAR(8192) DEFAULT NULL,
  `color` INT DEFAULT NULL,
  PRIMARY KEY (`tile_id`)
);


DROP TABLE IF EXISTS `create`;
CREATE TABLE `create` (
  `create_id` INT NOT NULL,
  `tile_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`create_id`),
  FOREIGN KEY (`tile_id`) REFERENCES `tile`(`tile_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
);
