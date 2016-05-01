CREATE TABLE `phonebook`.`user` (
  `user_id` BIGINT(20) NOT NULL,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `fullName` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `phonebook`.`contact` (
  `contact_id` BIGINT(20) NOT NULL,
  `address` VARCHAR(255) NULL,
  `email` VARCHAR(45) NULL,
  `homePhoneNumber` VARCHAR(45) NULL,
  `mobilePhoneNumber` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `patronym` VARCHAR(45) NOT NULL,
  `user_id` BIGINT(20) NULL,
  PRIMARY KEY (`contact_id`),
  INDEX `dfwdqwd_idx` (`user_id` ASC),
  CONSTRAINT `dfwdqwd`
    FOREIGN KEY (`user_id`)
    REFERENCES `phonebook`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;