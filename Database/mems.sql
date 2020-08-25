-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.18 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for mems
CREATE DATABASE IF NOT EXISTS `mems` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mems`;

-- Dumping structure for table mems.appointment
CREATE TABLE IF NOT EXISTS `appointment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `ward_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg90ck1kd0p4rbamwc22jd2oql` (`patient_id`),
  KEY `FK6wh2pds75affdwn5esdi17nm2` (`ward_id`),
  CONSTRAINT `FK6wh2pds75affdwn5esdi17nm2` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`),
  CONSTRAINT `FKg90ck1kd0p4rbamwc22jd2oql` FOREIGN KEY (`patient_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.appointment: ~25 rows (approximately)
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` (`id`, `date`, `time`, `patient_id`, `ward_id`) VALUES
	(1, '2020-02-20', '20:00', 6, 1),
	(2, '2020-02-12', '08:00', 6, 2),
	(3, '2020-01-10', '10:00', 6, 1),
	(4, '2020-02-20', '10:00', 7, 1),
	(5, '2020-02-20', '18:00', 7, 1),
	(6, '2020-03-30', '13:00', 7, 2),
	(7, '2020-03-08', '08:00', 8, 1),
	(8, '2020-04-20', '20:00', 8, 2),
	(9, '2020-04-02', '06:00', 8, 1),
	(10, '2020-02-12', '10:00', 9, 1),
	(11, '2020-08-20', '20:00', 9, 1),
	(12, '2020-02-20', '20:00', 9, 2),
	(13, '2020-02-20', '17:00', 10, 1),
	(14, '2020-04-22', '16:00', 10, 1),
	(15, '2020-06-10', '10:00', 10, 2),
	(16, '2020-02-02', '11:00', 10, 1),
	(17, '2020-01-03', '10:00', 10, 1),
	(18, '2020-01-15', '08:00', 10, 2),
	(19, '2020-01-20', '22:00', 9, 1),
	(20, '2020-01-13', '13:00', 9, 1),
	(21, '2020-01-10', '10:00', 8, 2),
	(22, '2020-01-05', '10:00', 8, 2),
	(23, '2020-01-23', '10:00', 8, 2),
	(24, '2020-01-21', '21:00', 6, 1),
	(25, '2020-01-27', '10:00', 6, 2);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;

-- Dumping structure for table mems.databasechangelog
CREATE TABLE IF NOT EXISTS `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.databasechangelog: ~0 rows (approximately)
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;

-- Dumping structure for table mems.databasechangeloglock
CREATE TABLE IF NOT EXISTS `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.databasechangeloglock: ~0 rows (approximately)
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
	(1, b'0', NULL, NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;

-- Dumping structure for table mems.examination
CREATE TABLE IF NOT EXISTS `examination` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `status` varchar(15) NOT NULL,
  `time` varchar(255) DEFAULT NULL,
  `appointment_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `result_id` bigint(20) DEFAULT NULL,
  `ward_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7dqgrq2dnuomi11x7x3vhl5dl` (`appointment_id`),
  KEY `FK4ouqobe5b719kcyx3llr0u65r` (`patient_id`),
  KEY `FKkwkymj5g25kq46r19y16gpeeu` (`result_id`),
  KEY `FKkdsw9c5t4egvho6okt1u77rp4` (`ward_id`),
  CONSTRAINT `FK4ouqobe5b719kcyx3llr0u65r` FOREIGN KEY (`patient_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK7dqgrq2dnuomi11x7x3vhl5dl` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`),
  CONSTRAINT `FKkdsw9c5t4egvho6okt1u77rp4` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`),
  CONSTRAINT `FKkwkymj5g25kq46r19y16gpeeu` FOREIGN KEY (`result_id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.examination: ~25 rows (approximately)
/*!40000 ALTER TABLE `examination` DISABLE KEYS */;
INSERT INTO `examination` (`id`, `date`, `status`, `time`, `appointment_id`, `patient_id`, `result_id`, `ward_id`) VALUES
	(1, '2020-02-20', 'open', '20:00', 1, 6, NULL, 1),
	(2, '2020-02-12', 'processed', '08:00', 2, 6, NULL, 2),
	(3, '2020-01-10', 'closed', '10:00', 3, 6, 9, 1),
	(4, '2020-02-20', 'open', '10:00', 4, 7, NULL, 1),
	(5, '2020-02-20', 'open', '18:00', 5, 7, NULL, 1),
	(6, '2020-03-30', 'open', '13:00', 6, 7, NULL, 2),
	(7, '2020-03-08', 'open', '08:00', 7, 8, NULL, 1),
	(8, '2020-04-20', 'open', '20:00', 8, 8, NULL, 2),
	(9, '2020-04-02', 'open', '06:00', 9, 8, NULL, 1),
	(10, '2020-02-12', 'processed', '10:00', 10, 9, NULL, 1),
	(11, '2020-08-20', 'open', '20:00', 11, 9, NULL, 1),
	(12, '2020-02-20', 'open', '20:00', 12, 9, NULL, 2),
	(13, '2020-02-20', 'open', '17:00', 13, 10, NULL, 1),
	(14, '2020-04-22', 'open', '16:00', 14, 10, NULL, 1),
	(15, '2020-06-10', 'open', '10:00', 15, 10, NULL, 2),
	(16, '2020-02-02', 'closed', '11:00', 16, 10, 1, 1),
	(17, '2020-01-03', 'closed', '10:00', 17, 10, 2, 1),
	(18, '2020-01-15', 'closed', '08:00', 18, 10, 5, 2),
	(19, '2020-01-20', 'pending', '22:00', 19, 9, NULL, 1),
	(20, '2020-01-13', 'closed', '13:00', 20, 9, 4, 1),
	(21, '2020-01-10', 'closed', '10:00', 21, 8, 6, 2),
	(22, '2020-01-05', 'closed', '10:00', 22, 8, 8, 2),
	(23, '2020-01-23', 'pending', '10:00', 23, 8, NULL, 2),
	(24, '2020-01-21', 'closed', '21:00', 24, 6, 3, 1),
	(25, '2020-01-27', 'closed', '10:00', 25, 6, 7, 2);
/*!40000 ALTER TABLE `examination` ENABLE KEYS */;

-- Dumping structure for table mems.logs
CREATE TABLE IF NOT EXISTS `logs` (
  `id` varchar(255) NOT NULL,
  `modifying_date` datetime DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.logs: ~7 rows (approximately)
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` (`id`, `modifying_date`, `operation`, `table_name`, `user`) VALUES
	('1c33df60-8989-4059-b7dd-39c8447b5b76', '2020-02-10 21:51:25', 'create', 'Ward', 'admin'),
	('1e6a5485-78b8-4e2b-8674-090a8d8083b4', '2020-02-11 20:11:34', 'create', 'Ward', 'stod'),
	('4716dece-5601-4304-8b71-4fb0d6eff3c0', '2020-02-11 16:46:48', 'create', 'Ward', 'admin'),
	('52433a3b-3c83-4241-aa94-b509eda740d9', '2020-02-11 20:08:26', 'create', 'Ward', 'admin'),
	('c3258b18-b8fb-4265-af4e-49955dff870f', '2020-02-11 16:46:32', 'create', 'Ward', 'admin'),
	('d2faf123-8cd9-4ba3-8ad1-667fd7c8c51b', '2020-02-11 16:47:58', 'create', 'Ward', 'admin'),
	('e2b79413-205d-4257-b153-2b4d65660cab', '2020-02-11 16:46:45', 'create', 'Ward', 'admin');
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;

-- Dumping structure for table mems.result
CREATE TABLE IF NOT EXISTS `result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `ward_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg2efq8dps1qkmoom6upg7pgce` (`patient_id`),
  KEY `FKqbhtmc9monpxydcx972adlehs` (`ward_id`),
  CONSTRAINT `FKg2efq8dps1qkmoom6upg7pgce` FOREIGN KEY (`patient_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqbhtmc9monpxydcx972adlehs` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.result: ~9 rows (approximately)
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` (`id`, `date`, `description`, `time`, `patient_id`, `ward_id`) VALUES
	(1, '2020-02-02', 'Everything looks good.', '11:00', 10, 1),
	(2, '2020-01-03', 'Everything looks good.', '10:00', 10, 1),
	(3, '2020-01-21', 'Everithing looks good.', '21:00', 6, 1),
	(4, '2020-01-13', 'The "sue" is a little higher than normal. There is a chance of infection in the organism.', '13:00', 9, 1),
	(5, '2020-01-15', 'Everything looks normal.', '08:00', 10, 2),
	(6, '2020-01-10', 'Abnormal growth is observed. There is a mass in the brain that is too big to be adipose tissue. Abnormal fluid with bleeding around it which are signs of belignant tumor. The blood vessels contract and thrombise, which is sign for high risk of aneurysm. Operation is recomended as soon as possible.', '10:00', 8, 2),
	(7, '2020-01-27', 'Organs, cross vessels, bones and joints of normal size, shape, appearance and location.  There are no unusual expressions, such as tumors.  No bleeding, abnormal fluid, blood clots in the waist that are flowing or swollen in the blood vessels. There are no signs of inflammation or infection.', '10:00', 6, 2),
	(8, '2020-01-05', 'Observation of mid-sagittal cut through the lumbar spine. There are two main osseous structures that are clearly visible on the mid-sagittal view of a vertebra: the square-shaped vertebral body of the vertebra and the spinous process of the posterior arch. The junction of the spinous process with the laminae make up the anterior border of the posterior arch which also forms the back of the central canal. This is covered with ligamentum flavum. The extremely hyperintense space between the vertebral bodies and posterior arch is the thecal sac, which of course is filled with CSF. This structure may completely fill the vertebral canal, which is the space between the posterior vertebral body (PB), pedicles, and lamina as represented by the black line I have drawn.', '10:00', 8, 2),
	(9, '2020-01-10', 'Top height result of the "wbc", "rbc" and "hb". These can indicate heart problems.', '10:00', 6, 1);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;

-- Dumping structure for table mems.result_blood
CREATE TABLE IF NOT EXISTS `result_blood` (
  `alt` double NOT NULL,
  `ast` double NOT NULL,
  `hb` double NOT NULL,
  `hct` double NOT NULL,
  `mcv` double NOT NULL,
  `plt` double NOT NULL,
  `rbc` double NOT NULL,
  `sue` double NOT NULL,
  `wbc` double NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKl7v1wxdcllsh9rwwcmo2imfd4` FOREIGN KEY (`id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.result_blood: ~5 rows (approximately)
/*!40000 ALTER TABLE `result_blood` DISABLE KEYS */;
INSERT INTO `result_blood` (`alt`, `ast`, `hb`, `hct`, `mcv`, `plt`, `rbc`, `sue`, `wbc`, `id`) VALUES
	(40, 30, 15, 0.405, 85, 350, 5, 5, 7.09, 1),
	(40, 35, 13.85, 0.42, 85, 300, 5.5, 7.3, 8.6, 2),
	(20, 15, 13, 0.4, 87, 276, 5.8, 1, 7, 3),
	(30, 30, 14, 0.31, 95, 200, 5, 20, 6, 4),
	(48, 38, 47, 0.405, 95, 410, 8.2, 9.8, 18.9, 9);
/*!40000 ALTER TABLE `result_blood` ENABLE KEYS */;

-- Dumping structure for table mems.result_irm
CREATE TABLE IF NOT EXISTS `result_irm` (
  `img` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKati9gg9ch7mr7brsnm855fjl5` FOREIGN KEY (`id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.result_irm: ~4 rows (approximately)
/*!40000 ALTER TABLE `result_irm` DISABLE KEYS */;
INSERT INTO `result_irm` (`img`, `id`) VALUES
	('foot7865.jpg', 5),
	('brain52647.png', 6),
	('brain9878.jpg', 7),
	('spine52647.jpg', 8);
/*!40000 ALTER TABLE `result_irm` ENABLE KEYS */;

-- Dumping structure for table mems.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.role: ~2 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `authority`) VALUES
	(1, 'ADMIN'),
	(2, 'PATIENT'),
	(3, 'DOCTOR');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table mems.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `egn` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `is_account_non_expired` bit(1) NOT NULL,
  `is_account_non_locked` bit(1) NOT NULL,
  `is_credentials_non_expired` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `ward_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l3gw64gxibqdtfp5dm5vutxru` (`egn`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FK4qrgk559jbe4eicm18prsl8hy` (`ward_id`),
  CONSTRAINT `FK4qrgk559jbe4eicm18prsl8hy` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.user: ~10 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `egn`, `email`, `first_name`, `is_account_non_expired`, `is_account_non_locked`, `is_credentials_non_expired`, `is_enabled`, `last_name`, `password`, `username`, `ward_id`) VALUES
	(1, '00000000', 'admin@gmail.com', 'Admin', b'1', b'1', b'1', b'1', 'Admin', '$2a$10$PGS64SYvSR/qU46zVgrXUewpENcGLhULPq6BFKy.8XjI1iKuv5scq', 'admin', NULL),
	(2, '9405061213', 'simeon.todorov@gmail.com', 'Simeon', b'1', b'1', b'1', b'1', 'Todorov', '$2a$10$G6TJgC3QSFf8v7Btjkn47.MaCSK9/S3G2ILzEJsu/2pxI.Pkft9PC', 'stod', NULL),
	(3, '9711073782', 'sofia.mihaleva@gmail.com', 'Sofia', b'1', b'1', b'1', b'1', 'Mihaleva', '$2a$10$HnfONqRx/mmgb4Zv9J2jWeTc6N5BP5l9xPNEsFia6Gf9Sb/Ao8gne', 'sof.mih', 1),
	(4, '9705165632', 'pavlina.koleva@gmail.com', 'Pavlina', b'1', b'1', b'1', b'1', 'Koleva', '$2a$10$cAZvdDJw4anZfZ25.53.ZuvLfn2eT.ETg0r1cSgze1Q/aZr/3fr86', 'pav.kol', 1),
	(5, '9708202456', 'antonia.todorova@gmail.com', 'Antonia', b'1', b'1', b'1', b'1', 'Todorova', '$2a$10$1F5UJ03xa02DaZ34EJY0iO/8Jsd587myEHRVYVHpQV511JfDIeihO', 'atod', 2),
	(6, '9707149878', 'diana.yordanova@gmail.com', 'Diana', b'1', b'1', b'1', b'1', 'Yordanova', '$2a$10$gpz9enC0OHZRXGCOx.UUd.DlwZzw8ry4KPv6JnliHOuy8kSAVO25O', 'didka', NULL),
	(7, '9702254687', 'georgi.bonev@gmail.com', 'Georgi', b'1', b'1', b'1', b'1', 'Bonev', '$2a$10$8FFbo0swWuSnvQNRW4WXMuCy4GAmOFxCo4akTGCCb87L4iVqdRHvi', 'bonev', NULL),
	(8, '8403152647', 'gergana.petrova@abv.bg', 'Gergana', b'1', b'0', b'1', b'1', 'Petrova', '$2a$10$S7XDIMOLREv3zaMhkfYfROhfd3cL5d34eI3d18qHy3DqNUJc8uqlG', 'gerip', NULL),
	(9, '7204041267', 'ivan.stoilov@abv.bg', 'Ivan', b'1', b'1', b'1', b'1', 'Stoilov', '$2a$10$oAo5M4cf7YOqcVEb.AQ4he0T5Ahbr70cdtfFB7BflMDNK/gKgDvgq', 'ivansto', NULL),
	(10, '7812157865', 'martin.nikolov@gmail.com', 'Martin', b'1', b'1', b'1', b'1', 'Nikolov', '$2a$10$LRs880l8rwtZJ1m/AARvYOmZvIcK3LorRArZz/T2dO0nD3DsP2J2W', 'marto', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table mems.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.user_role: ~10 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 1),
	(6, 2),
	(7, 2),
	(8, 2),
	(9, 2),
	(10, 2),
	(3, 3),
	(4, 3),
	(5, 3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

-- Dumping structure for table mems.ward
CREATE TABLE IF NOT EXISTS `ward` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_number` varchar(20) NOT NULL,
  `ward_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mems.ward: ~2 rows (approximately)
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
INSERT INTO `ward` (`id`, `room_number`, `ward_name`) VALUES
	(1, 'B1', 'Blood'),
	(2, '101', 'Irm');
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
