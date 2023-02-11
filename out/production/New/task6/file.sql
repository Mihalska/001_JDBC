CREATE TABLE `task_6`.`users` (
                                  `id_users` INT NOT NULL AUTO_INCREMENT,
                                  `fname` VARCHAR(45) NOT NULL,
                                  `phon` VARCHAR(45) NOT NULL,
                                  PRIMARY KEY (`id_users`),
                                  UNIQUE INDEX `phon_UNIQUE` (`phon` ASC) VISIBLE);
