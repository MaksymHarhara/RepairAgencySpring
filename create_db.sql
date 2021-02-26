create database service_db;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL  AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `user_account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa2ixh18irxw16xxl1ka3gfth6` (`user_account_id`)
);

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKj9553ass9uctjrmh0gkqsmv0d` (`roles_id`),
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`)
);

CREATE TABLE `request` (
  `id` bigint(20) NOT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `request` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22e2ep54nspx0we4lfrbtgv6q` (`master_id`)
);

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`)
);


INSERT INTO `service_db`.`user` (`id`, `email`,  `first_name`,  `password`, `surname`) VALUES ('1', 'adam@sarif.com', 'Adam','$2a$10$L/LzfxrplFIUl0X5uElGwOuofhk3B9aFwMxC0suYHBD2UUnUoK1vG', 'Jansen');
INSERT INTO `service_db`.`user` (`id`, `email`,  `first_name`,  `password`, `surname`) VALUES ('2', 'arahnid@gmail.com', 'David','$2a$10$L/LzfxrplFIUl0X5uElGwOuofhk3B9aFwMxC0suYHBD2UUnUoK1vG', 'Arahnidov');
INSERT INTO `service_db`.`user` (`id`, `email`,  `first_name`,  `password`, `surname`) VALUES ('3', 'garry@gmail.com', 'Garry', '$2a$10$L/LzfxrplFIUl0X5uElGwOuofhk3B9aFwMxC0suYHBD2UUnUoK1vG', 'Fat');

INSERT INTO `service_db`.`role` (`id`, `name`) VALUES ('1', 'ROLE_MANAGER');
INSERT INTO `service_db`.`role` (`id`, `name`) VALUES ('2', 'ROLE_USER');
INSERT INTO `service_db`.`role` (`id`, `name`) VALUES ('3', 'ROLE_MASTER');

INSERT INTO `service_db`.`user_roles` (`user_id`, `roles_id`) VALUES ('1', '1');
INSERT INTO `service_db`.`user_roles` (`user_id`, `roles_id`) VALUES ('2', '3');
INSERT INTO `service_db`.`user_roles` (`user_id`, `roles_id`) VALUES ('3', '2');
