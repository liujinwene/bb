-- DROP TABLE IF EXISTS `bb_commo_cat`;
CREATE TABLE `bb_commo` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(128) NOT NULL COMMENT 'commodity its name',
  `price` DECIMAL(10,2) NOT NULL COMMENT 'commodity its price',
  `delete_flag` TINYINT(4) NOT NULL,
  `create_time` BIGINT(20) NOT NULL,
  `pic` VARCHAR(512) DEFAULT NULL COMMENT 'commodity its picture',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT 'commodity its description',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- DROP TABLE IF EXISTS `bb_commo_cat`;
CREATE TABLE `bb_commo_cat` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  `pos` INTEGER(11) DEFAULT NULL,
  `create_time` BIGINT(20) NOT NULL,
  `remark` VARCHAR(256) DEFAULT NULL,
  `start_time` BIGINT(20) DEFAULT NULL,
  `end_time` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- DROP TABLE IF EXISTS `bb_commo_cat_contact`;
CREATE TABLE `bb_commo_cat_contact` (
  `id` BIGINT(20) NOT NULL,
  `cat_id` BIGINT(20) NOT NULL COMMENT 'bb_commo_cat.id',
  `commo_id` BIGINT(20) NOT NULL COMMENT 'bb_commo.id',
  `create_time` BIGINT(20) NOT NULL,
  `pos` INTEGER(11) DEFAULT NULL COMMENT 'commo display position',
  PRIMARY KEY (`id`),
  UNIQUE KEY `catId_commoId` (`cat_id`,`commo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


















