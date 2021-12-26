CREATE TABLE `note` (
  `id` int AUTO_INCREMENT NOT NULL,
  `userId` int(11) unsigned NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` TEXT DEFAULT NULL,
  `lastUpdateTime` DATETIME DEFAULT now(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

