DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`password` varchar(255) NOT NULL,
PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`id` int(11) NOT NULL,
`name` varchar(255) NOT NULL,
PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`user_id` int(11) NOT NULL,
`role_id` int(11) NOT NULL,
PRIMARY KEY (`user_id`,`role_id`)
) ;

CREATE TABLE `note` (
  `id` int AUTO_INCREMENT NOT NULL,
  `userId` int(11) unsigned NOT NULL,
  `content` TEXT DEFAULT NULL,
  `lastUpdateTime` DATETIME DEFAULT now(),
  PRIMARY KEY (`id`)
) ;

