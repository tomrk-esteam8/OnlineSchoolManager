CREATE TABLE `student`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `firstname`    VARCHAR(255) NOT NULL,
    `lastname`     VARCHAR(255) NOT NULL,
    `bank_account` VARCHAR(255) DEFAULT NULL,
    `email`        VARCHAR(255) DEFAULT NULL,
    `phone_number` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `teacher`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `firstname`    VARCHAR(255) NOT NULL,
    `lastname`     VARCHAR(255) NOT NULL,
    `email`        VARCHAR(255) DEFAULT NULL,
    `phone_number` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `course`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(255) NOT NULL,
    `cost`         DECIMAL(6, 2) unsigned DEFAULT NULL,
    `teacher_cost` DECIMAL(6, 2)          DEFAULT NULL,
    `teacher_id`   BIGINT                 DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (teacher_id) REFERENCES teacher (id)
);

CREATE TABLE `payment`
(
    `id`         BIGINT NOT NULL AUTO_INCREMENT,
    `student_id` BIGINT NOT NULL,
    `cost`       DECIMAL(6, 2) unsigned DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (student_id) REFERENCES student (id)
);

CREATE TABLE `salary`
(
    `id`         BIGINT NOT NULL AUTO_INCREMENT,
    `teacher_id` BIGINT NOT NULL,
    `value`      DECIMAL(6, 2) unsigned DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (teacher_id) REFERENCES teacher (id)
);

CREATE TABLE `term`
(
    `id`           BIGINT   NOT NULL AUTO_INCREMENT,
    `course_id`    BIGINT   NOT NULL,
    `teacher_id`   BIGINT   NOT NULL,
    `student_id`   BIGINT   NOT NULL,
    `date`         DATETIME NOT NULL,
    `cost_teacher` DECIMAL(6, 2) unsigned DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher (id)
);
