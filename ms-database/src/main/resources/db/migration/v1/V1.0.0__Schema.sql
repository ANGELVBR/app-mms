CREATE TABLE IF NOT EXISTS `app_management`.`users` (
    `id_user` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(75) NOT NULL,
    `email` VARCHAR(75) NOT NULL,
    `password` VARCHAR(20) NULL,
    `active` TINYINT NOT NULL DEFAULT 0,
    `create_date` DATE NOT NULL,
    INDEX `email` (`email` ASC, `active` ASC) VISIBLE,
    PRIMARY KEY (`id_user`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`profiles` (
    `id_profile` BIGINT NOT NULL AUTO_INCREMENT,
    `photo` VARCHAR(45) NULL,
    `description` VARCHAR(45) NULL,
    `interest` VARCHAR(45) NULL,
    `id_user` BIGINT NOT NULL,
    PRIMARY KEY (`id_profile`, `id_user`),
    INDEX `fk_profiles_users_idx` (`id_user` ASC) VISIBLE,
    CONSTRAINT `fk_profiles_users1`
    FOREIGN KEY (`id_user`)
    REFERENCES `app_management`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`roles` (
    `id_rol` INT NOT NULL  AUTO_INCREMENT,
    `code` VARCHAR(15) NOT NULL,
    `description` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id_rol`))
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `app_management`.`users_roles` (
    `id_user` BIGINT NOT NULL,
    `id_rol` INT NOT NULL,
    PRIMARY KEY (`id_user`, `id_rol`),
    INDEX `fk_users_has_roles_roles_idx` (`id_rol` ASC) VISIBLE,
    INDEX `fk_users_has_roles_users_idx` (`id_user` ASC) VISIBLE,
    CONSTRAINT `fk_users_has_roles_users`
    FOREIGN KEY (`id_user`)
    REFERENCES `app_management`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_roles_roles`
    FOREIGN KEY (`id_rol`)
    REFERENCES `app_management`.`roles` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `app_management`.`status_project` (
     `id_status_project` INT  NOT NULL AUTO_INCREMENT,
     `code` VARCHAR(20) NOT NULL,
     `name` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id_status_project`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`status_task` (
    `id_status_task` INT NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(20) NOT NULL,
    `name` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id_status_task`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`priority` (
    `id_priority` INT  NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(20) NOT NULL,
    `name` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id_priority`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`projects` (
    `id_project` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(250) NULL,
    `description` LONGTEXT NULL,
    `startDate` DATE NULL,
    `endDate` DATE NULL,
    `id_status_project` INT NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NULL,
    `deleted_at` DATETIME NULL,
    `created_user` VARCHAR(10) NOT NULL,
    `updated_user` VARCHAR(10) NULL,
    `deleted_user` VARCHAR(10) NULL,
    `responsible_user` BIGINT NOT NULL,
    PRIMARY KEY (`id_project`),
    INDEX `fk_projects_status_idx` (`id_status_project` ASC) VISIBLE,
    INDEX `fk_projects_users_idx` (`responsible_user` ASC) VISIBLE,
    CONSTRAINT `fk_projects_status`
    FOREIGN KEY (`id_status_project`)
    REFERENCES `app_management`.`status_project` (`id_status_project`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_projects_users`
    FOREIGN KEY (`responsible_user`)
    REFERENCES `app_management`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`tasks` (
    `id_task` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `description` LONGTEXT NULL,
    `due_date` DATE NULL,
    `finish_date` DATE NULL,
    `id_priority` INT NOT NULL,
    `estimated_Hours` INT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NULL,
    `deleted_at` DATETIME NULL,
    `created_user` VARCHAR(10) NOT NULL,
    `updated_user` VARCHAR(10) NULL,
    `deleted_user` VARCHAR(10) NULL,
    `id_project` BIGINT NOT NULL,
    `id_status` INT NOT NULL,
    PRIMARY KEY (`id_task`),
    INDEX `fk_task_priority_idx` (`id_priority` ASC) VISIBLE,
    INDEX `fk_task_projects_idx` (`id_project` ASC) VISIBLE,
    INDEX `fk_tasks_status_task1_idx` (`id_status` ASC) VISIBLE,
    CONSTRAINT `fk_task_priority`
    FOREIGN KEY (`id_priority`)
    REFERENCES `app_management`.`priority` (`id_priority`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_projects`
    FOREIGN KEY (`id_project`)
    REFERENCES `app_management`.`projects` (`id_project`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_tasks_status_task1`
    FOREIGN KEY (`id_status`)
    REFERENCES `app_management`.`status_task` (`id_status_task`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`task_assigments` (
    `id_task_assigment` INT NOT NULL AUTO_INCREMENT,
    `assigned_at` DATETIME NOT NULL,
    `assigned_by` BIGINT NOT NULL,
    `assigned_to` BIGINT NOT NULL,
    `id_task` BIGINT NOT NULL,
    `id_status_assigment` INT NOT NULL,
    PRIMARY KEY (`id_task_assigment`),
    INDEX `fk_task_assigments_users_idx` (`assigned_to` ASC) VISIBLE,
    INDEX `fk_task_assigments_tasks_idx` (`id_task` ASC) VISIBLE,
    INDEX `fk_task_assigments_status_task_idx` (`id_status_assigment` ASC) VISIBLE,
    CONSTRAINT `fk_task_assigments_users1`
    FOREIGN KEY (`assigned_by`)
    REFERENCES `app_management`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_assigments_users2`
    FOREIGN KEY (`assigned_to`)
    REFERENCES `app_management`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_assigments_tasks`
    FOREIGN KEY (`id_task`)
    REFERENCES `app_management`.`tasks` (`id_task`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_assigments_status_task`
    FOREIGN KEY (`id_status_assigment`)
    REFERENCES `app_management`.`status_task` (`id_status_task`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `app_management`.`comments` (
    `id_comment` BIGINT NOT NULL AUTO_INCREMENT,
    `comment` LONGTEXT NOT NULL,
    `created_at` DATETIME NOT NULL,
    `created_user` VARCHAR(10) NOT NULL,
    `id_task` BIGINT NOT NULL,
    PRIMARY KEY (`id_comment`),
    INDEX `fk_comments_task_idx` (`id_task` ASC) VISIBLE,
    CONSTRAINT `fk_comments_task`
    FOREIGN KEY (`id_task`)
    REFERENCES `app_management`.`tasks` (`id_task`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;