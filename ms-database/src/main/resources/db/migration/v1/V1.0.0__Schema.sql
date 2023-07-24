CREATE TABLE IF NOT EXISTS `app_management`.`users` (
    `idUser` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(75) NOT NULL,
    `email` VARCHAR(75) NOT NULL,
    `password` VARCHAR(20) NULL,
    `active` TINYINT NOT NULL DEFAULT 0,
    `createDate` DATE NOT NULL,
    INDEX `email` (`email` ASC, `active` ASC) VISIBLE,
    PRIMARY KEY (`idUser`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`profiles` (
    `idProfile` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `photo` VARCHAR(45) NULL,
    `description` VARCHAR(45) NULL,
    `interest` VARCHAR(45) NULL,
    `idUser` BIGINT(10) NOT NULL,
    PRIMARY KEY (`idProfile`, `idUser`),
    INDEX `fk_profiles_users_idx` (`idUser` ASC) VISIBLE,
    CONSTRAINT `fk_profiles_users1`
    FOREIGN KEY (`idUser`)
    REFERENCES `app_management`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`roles` (
    `idRol` INT NOT NULL  AUTO_INCREMENT,
    `code` VARCHAR(15) NOT NULL,
    `description` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`idRol`))
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `app_management`.`users_roles` (
    `idUser` BIGINT(10) NOT NULL,
    `idRol` INT NOT NULL,
    PRIMARY KEY (`idUser`, `idRol`),
    INDEX `fk_users_has_roles_roles_idx` (`idRol` ASC) VISIBLE,
    INDEX `fk_users_has_roles_users_idx` (`idUser` ASC) VISIBLE,
    CONSTRAINT `fk_users_has_roles_users`
    FOREIGN KEY (`idUser`)
    REFERENCES `app_management`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_roles_roles`
    FOREIGN KEY (`idRol`)
    REFERENCES `app_management`.`roles` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `app_management`.`status_project` (
     `idStatusProject` INT  NOT NULL AUTO_INCREMENT,
     `code` VARCHAR(10) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`idStatusProject`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`status_task` (
    `idStatusTask` INT NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(10) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`idStatusTask`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`priority` (
    `idPriority` INT  NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(10) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`idPriority`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`task_steps` (
    `idTaskStep` INT NOT NULL AUTO_INCREMENT,
    `order` INT NOT NULL,
    `description` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`idTaskStep`))    
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`projects` (
    `idProject` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(250) NULL,
    `description` LONGTEXT NULL,
    `startDate` DATE NULL,
    `endDate` DATE NULL,
    `idStatusProject` INT NOT NULL,
    `createdAt` DATETIME NOT NULL,
    `updatedAt` DATETIME NULL,
    `deletedAt` DATETIME NULL,
    `createdUser` VARCHAR(10) NOT NULL,
    `updatedUser` VARCHAR(10) NULL,
    `deletedUser` VARCHAR(10) NULL,
    `responsibleUser` BIGINT(10) NOT NULL,
    PRIMARY KEY (`idProject`),
    INDEX `fk_projects_status_idx` (`idStatusProject` ASC) VISIBLE,
    INDEX `fk_projects_users_idx` (`responsibleUser` ASC) VISIBLE,
    CONSTRAINT `fk_projects_status`
    FOREIGN KEY (`idStatusProject`)
    REFERENCES `app_management`.`status_project` (`idStatusProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_projects_users`
    FOREIGN KEY (`responsibleUser`)
    REFERENCES `app_management`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`tasks` (
    `idtask` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `description` LONGTEXT NULL,
    `dueDate` DATE NULL,
    `finishDate` DATE NULL,
    `idPriority` INT NOT NULL,
    `estimatedHours` INT NULL,
    `createdAt` DATETIME NOT NULL,
    `updatedAt` DATETIME NULL,
    `deletedAt` DATETIME NULL,
    `createdUser` VARCHAR(10) NOT NULL,
    `updatedUser` VARCHAR(10) NULL,
    `deletedUser` VARCHAR(10) NULL,
    `idProject` BIGINT(10) NOT NULL,
    `idStatus` INT NOT NULL,
    PRIMARY KEY (`idtask`),
    INDEX `fk_task_priority_idx` (`idPriority` ASC) VISIBLE,
    INDEX `fk_task_projects_idx` (`idProject` ASC) VISIBLE,
    INDEX `fk_TASKS_STATUS_TASK1_idx` (`idStatus` ASC) VISIBLE,
    CONSTRAINT `fk_task_priority`
    FOREIGN KEY (`idPriority`)
    REFERENCES `app_management`.`priority` (`idPriority`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_projects`
    FOREIGN KEY (`idProject`)
    REFERENCES `app_management`.`projects` (`idProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_tasks_status_task1`
    FOREIGN KEY (`idStatus`)
    REFERENCES `app_management`.`status_task` (`idStatusTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`task_assigments` (
    `idTaskAssigment` INT NOT NULL AUTO_INCREMENT,
    `assignedAt` DATETIME NOT NULL,
    `assignedBy` BIGINT(10) NOT NULL,
    `assignedTo` BIGINT(10) NOT NULL,
    `idTask` BIGINT(10) NOT NULL,
    `idTaskStep` INT NOT NULL,
    PRIMARY KEY (`idTaskAssigment`),
    INDEX `fk_task_assigments_users_idx` (`assignedTo` ASC) VISIBLE,
    INDEX `fk_task_assigments_tasks_idx` (`idTask` ASC) VISIBLE,
    INDEX `fk_task_assigments_task_steps_idx` (`idTaskStep` ASC) VISIBLE,
    CONSTRAINT `fk_task_assigments_users1`
    FOREIGN KEY (`assignedBy`)
    REFERENCES `app_management`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_assigments_users2`
    FOREIGN KEY (`assignedTo`)
    REFERENCES `app_management`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_assigments_tasks`
    FOREIGN KEY (`idTask`)
    REFERENCES `app_management`.`tasks` (`idtask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_assigments_task_steps`
    FOREIGN KEY (`idTaskStep`)
    REFERENCES `app_management`.`task_steps` (`idTaskStep`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`comments` (
    `idComment` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `comment` LONGTEXT NOT NULL,
    `createdAt` DATETIME NOT NULL,
    `createdUser` VARCHAR(10) NOT NULL,
    `idtask` BIGINT(10) NOT NULL,
    PRIMARY KEY (`idComment`),
    INDEX `fk_comments_task_idx` (`idtask` ASC) VISIBLE,
    CONSTRAINT `fk_comments_task`
    FOREIGN KEY (`idtask`)
    REFERENCES `app_management`.`tasks` (`idtask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;