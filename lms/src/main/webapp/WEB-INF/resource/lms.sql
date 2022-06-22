-- --------------------------------------------------------
-- 호스트:                          3.38.95.7
-- 서버 버전:                        10.3.35-MariaDB-1:10.3.35+maria~bionic - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- lms 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `lms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lms`;

-- 테이블 lms.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `member_id` varchar(50) NOT NULL,
  `admin_name` varchar(50) NOT NULL,
  `admin_phone` varchar(50) NOT NULL,
  `admin_address` varchar(200) NOT NULL,
  `admin_detailaddress` varchar(50) NOT NULL,
  `admin_email` varchar(50) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.admin:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`member_id`, `admin_name`, `admin_phone`, `admin_address`, `admin_detailaddress`, `admin_email`, `update_date`) VALUES
	('admin', '유희조', '010-1111-1111', 'ㅁㄴㅇ', 'ㅁㄴㅇ', 'ㅁㄴㅇ', '2022-06-20 13:13:16');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- 테이블 lms.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `board_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `board_category` enum('1','2','3') NOT NULL COMMENT '1. 전체공지사항 2.자유게시판 3. 문의사항',
  `board_title` varchar(50) NOT NULL,
  `board_content` text NOT NULL,
  `private_no` enum('1','2','3') NOT NULL DEFAULT '1' COMMENT '1:기능없음, 2:익명성, 3:비밀글',
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`board_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.board:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`board_no`, `member_id`, `board_category`, `board_title`, `board_content`, `private_no`, `create_date`, `update_date`) VALUES
	(1, 'test', '1', 'test', 'testcontent', '1', '2022-06-20 14:35:03', '2022-06-20 14:35:03'),
	(2, 'test2', '2', 'test2', 'testcontent2', '2', '2022-06-20 16:43:35', '2022-06-20 16:43:35'),
	(3, 'test3', '3', 'test3', 'testcontent3', '3', '2022-06-20 16:43:57', '2022-06-20 16:43:57'),
	(4, 'test4', '1', 'test4', 'testcontent4', '1', '2022-06-20 16:45:18', '2022-06-20 16:45:18'),
	(5, 'test5', '2', 'test5', 'testcontent5', '2', '2022-06-20 16:45:33', '2022-06-20 16:45:33'),
	(6, 'test6', '3', 'test6', 'testcontent6', '3', '2022-06-20 16:46:02', '2022-06-20 16:46:02');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 lms.board_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_comment` (
  `board_comment_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `board_no` int(11) NOT NULL,
  `board_comment_content` text NOT NULL,
  `comment_anony` enum('1','2') NOT NULL DEFAULT '1' COMMENT '1. 평범 2. 익명',
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`board_comment_no`) USING BTREE,
  KEY `FK_board_comment_board` (`board_no`),
  CONSTRAINT `FK_board_comment_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='inquiry의 경우 answer의 기능';

-- 테이블 데이터 lms.board_comment:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `board_comment` ENABLE KEYS */;

-- 테이블 lms.board_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_file` (
  `board_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `board_no` int(11) NOT NULL,
  `board_file_original_name` varchar(50) NOT NULL,
  `board_file_name` varchar(50) NOT NULL,
  `board_file_type` varchar(50) NOT NULL,
  `board_file_size` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`board_file_no`),
  KEY `FK_board_file_board` (`board_no`),
  CONSTRAINT `FK_board_file_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.board_file:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `board_file` ENABLE KEYS */;

-- 테이블 lms.book 구조 내보내기
CREATE TABLE IF NOT EXISTS `book` (
  `book_no` int(11) NOT NULL,
  `book_title` varchar(50) NOT NULL,
  `book_writer` varchar(50) NOT NULL,
  `book_company` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`book_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.book:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- 테이블 lms.career 구조 내보내기
CREATE TABLE IF NOT EXISTS `career` (
  `career_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `career` varchar(50) NOT NULL,
  `detail_career` varchar(50) NOT NULL,
  `update_date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`career_no`),
  KEY `FK_career_teacher` (`member_id`),
  CONSTRAINT `FK_career_teacher` FOREIGN KEY (`member_id`) REFERENCES `teacher` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.career:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `career` DISABLE KEYS */;
/*!40000 ALTER TABLE `career` ENABLE KEYS */;

-- 테이블 lms.curriculum 구조 내보내기
CREATE TABLE IF NOT EXISTS `curriculum` (
  `curriculum_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_no` int(11) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `language_no` int(11) NOT NULL,
  `curriculum_title` varchar(50) NOT NULL,
  `curriculum_content` text NOT NULL,
  `start_day` date NOT NULL,
  `end_day` date NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`curriculum_no`) USING BTREE,
  KEY `FK_curriculum_subject` (`subject_no`),
  CONSTRAINT `FK_curriculum_subject` FOREIGN KEY (`subject_no`) REFERENCES `subject` (`subject_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.curriculum:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` (`curriculum_no`, `subject_no`, `member_id`, `language_no`, `curriculum_title`, `curriculum_content`, `start_day`, `end_day`, `create_date`, `update_date`) VALUES
	(1, 1, 'teacher', 1, 'java문법1', 'java문법을 배우자', '2022-01-01', '2022-06-01', '2022-06-21 11:25:40', '2022-06-21 11:25:43'),
	(2, 1, 'teacher1', 1, 'java알고리즘1', 'java알고리즘을 배우자', '2022-02-01', '2022-07-01', '2022-06-21 12:34:11', '2022-06-21 12:34:14'),
	(3, 1, 'teacher2', 1, 'java웹', ' 웹을배우자', '2022-03-01', '2022-08-01', '2022-06-21 12:37:01', '2022-06-21 12:37:02'),
	(4, 2, 'teacher3', 4, 'C+문법기초', 'C+문법기초를배우자', '2022-04-01', '2022-09-01', '2022-06-21 14:21:35', '2022-06-21 14:21:36'),
	(5, 2, 'teacher4', 4, 'C+알고리즘', 'C+알고리즘을 배우자', '2022-05-01', '2022-10-01', '2022-06-21 14:21:36', '2022-06-21 14:21:37');
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;

-- 테이블 lms.curriculum_book 구조 내보내기
CREATE TABLE IF NOT EXISTS `curriculum_book` (
  `book_no` int(11) NOT NULL,
  `curriculum_no` int(11) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`book_no`,`curriculum_no`),
  KEY `FK_curriculum_book_curriculum` (`curriculum_no`),
  CONSTRAINT `FK_curriculum_book_book` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_curriculum_book_curriculum` FOREIGN KEY (`curriculum_no`) REFERENCES `curriculum` (`curriculum_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.curriculum_book:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `curriculum_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_book` ENABLE KEYS */;

-- 테이블 lms.language 구조 내보내기
CREATE TABLE IF NOT EXISTS `language` (
  `language_no` int(11) NOT NULL AUTO_INCREMENT,
  `language_name` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`language_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.language:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` (`language_no`, `language_name`, `create_date`, `update_date`) VALUES
	(1, 'Java', '2022-06-21 14:46:34', '2022-06-21 14:46:35'),
	(2, 'C#', '2022-06-21 14:46:35', '2022-06-21 14:46:36'),
	(3, 'C+', '2022-06-21 14:46:36', '2022-06-21 14:46:37'),
	(4, 'C++', '2022-06-21 14:46:37', '2022-06-21 14:46:38');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;

-- 테이블 lms.member_id 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_id` (
  `member_id` varchar(50) NOT NULL,
  `member_pw` varchar(50) NOT NULL,
  `level` enum('1','2','3') NOT NULL COMMENT '1=운영자, 2=강사, 3=학생',
  `member_active` enum('1','2','3','4') NOT NULL COMMENT '1=활성, 2=비활성, 3=탈퇴, 4=최초로그인',
  `last_login_date` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  `delete_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.member_id:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `member_id` DISABLE KEYS */;
INSERT INTO `member_id` (`member_id`, `member_pw`, `level`, `member_active`, `last_login_date`, `create_date`, `delete_date`) VALUES
	('admin', '*A4B6157319038724E3560894F7F932C8886EBFCF', '1', '1', '2022-06-20 13:12:17', '2022-06-20 13:12:17', '2022-06-20 13:12:18'),
	('student', '*A4B6157319038724E3560894F7F932C8886EBFCF', '3', '1', '2022-06-20 13:12:45', '2022-06-20 13:12:46', '2022-06-20 13:12:47'),
	('teacher', '*A4B6157319038724E3560894F7F932C8886EBFCF', '2', '1', '2022-06-20 13:12:33', '2022-06-20 13:12:34', '2022-06-20 13:12:35');
/*!40000 ALTER TABLE `member_id` ENABLE KEYS */;

-- 테이블 lms.member_photo 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_photo` (
  `member_id` varchar(50) NOT NULL,
  `member_photo_original_name` varchar(50) NOT NULL,
  `member_photo_name` varchar(50) NOT NULL,
  `member_photo_size` varchar(50) NOT NULL,
  `member_photo_type` varchar(50) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `FK_member_photo_member_id` FOREIGN KEY (`member_id`) REFERENCES `member_id` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.member_photo:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `member_photo` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_photo` ENABLE KEYS */;

-- 테이블 lms.password 구조 내보내기
CREATE TABLE IF NOT EXISTS `password` (
  `member_id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.password:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `password` DISABLE KEYS */;
/*!40000 ALTER TABLE `password` ENABLE KEYS */;

-- 테이블 lms.receiver 구조 내보내기
CREATE TABLE IF NOT EXISTS `receiver` (
  `board_no` int(11) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`board_no`,`receiver`),
  KEY `FK_receiver_teacher` (`receiver`),
  CONSTRAINT `FK_receiver_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_receiver_teacher` FOREIGN KEY (`receiver`) REFERENCES `teacher` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='외래키 생각';

-- 테이블 데이터 lms.receiver:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `receiver` DISABLE KEYS */;
/*!40000 ALTER TABLE `receiver` ENABLE KEYS */;

-- 테이블 lms.student 구조 내보내기
CREATE TABLE IF NOT EXISTS `student` (
  `member_id` varchar(50) NOT NULL,
  `student_name` varchar(50) NOT NULL,
  `student_gender` enum('남','여') NOT NULL,
  `student_phone` varchar(50) NOT NULL,
  `student_address` varchar(200) NOT NULL,
  `student_detailaddress` varchar(50) NOT NULL,
  `student_email` varchar(50) NOT NULL,
  `student_education` enum('고졸','초대졸','대졸') NOT NULL,
  `student_register_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.student:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`member_id`, `student_name`, `student_gender`, `student_phone`, `student_address`, `student_detailaddress`, `student_email`, `student_education`, `student_register_date`, `update_date`) VALUES
	('student', '학생유희조', '남', '01022222222', 'ㅁㄴㅇ', 'ㅁㄴㅇ', 'ㅁㄴㅇ', '대졸', '2022-06-20 13:13:52', '2022-06-20 13:13:52'),
	('student1', '이규남', '남', '01011111111', '테스트', '테스트', '1234', '대졸', '2022-06-20 17:02:48', '2022-06-20 17:02:49'),
	('student2', '박범진', '남', '01033333333', '테스트1', '테스트1', '테스트1', '대졸', '2022-06-20 17:03:29', '2022-06-20 17:03:29'),
	('student3', '원성현', '남', '01044444444', '테스트2', '테스트2', '테스트2', '대졸', '2022-06-20 17:03:59', '2022-06-20 17:04:00'),
	('student4', '박성준', '남', '01055555555', '테스트3', '테스트3', '테스트3', '대졸', '2022-06-20 17:05:38', '2022-06-20 17:05:39');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- 테이블 lms.student_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `student_file` (
  `student_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_report_student_no` int(11) NOT NULL,
  `student_file_original_name` varchar(50) NOT NULL,
  `student_file_name` varchar(50) NOT NULL,
  `studentfile_type` varchar(50) NOT NULL,
  `student_file_size` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`student_file_no`) USING BTREE,
  KEY `FK_student_file_subject_report_student` (`subject_report_student_no`),
  CONSTRAINT `FK_student_file_subject_report_student` FOREIGN KEY (`subject_report_student_no`) REFERENCES `subject_report_student` (`subject_report_student_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.student_file:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `student_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_file` ENABLE KEYS */;

-- 테이블 lms.student_job 구조 내보내기
CREATE TABLE IF NOT EXISTS `student_job` (
  `member_id` varchar(50) NOT NULL,
  `company` varchar(50) NOT NULL,
  `start_salary` int(11) NOT NULL COMMENT '초봉',
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.student_job:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `student_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_job` ENABLE KEYS */;

-- 테이블 lms.subject 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject` (
  `subject_no` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` varchar(50) NOT NULL COMMENT '강좌담당 강사',
  `admin_id` varchar(50) NOT NULL COMMENT '작성자',
  `subject_name` varchar(50) NOT NULL,
  `subject_student_max` int(11) NOT NULL,
  `subject_info` text NOT NULL,
  `subject_start_date` date NOT NULL,
  `subject_finish_date` date NOT NULL,
  `subject_start_time` time NOT NULL,
  `subject_end_time` time NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject:~12 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` (`subject_no`, `teacher_id`, `admin_id`, `subject_name`, `subject_student_max`, `subject_info`, `subject_start_date`, `subject_finish_date`, `subject_start_time`, `subject_end_time`, `create_date`, `update_date`) VALUES
	(1, 'teacher', 'admin', '자바테스트', 99, '자바를 배워봅시다', '2022-06-20', '2022-06-20', '14:00:39', '14:00:44', '2022-06-20 14:00:47', '2022-06-20 14:00:52'),
	(2, 'teacher1', 'admin1', '자바테스트1', 40, '씨샵을 배워봅시다', '2022-06-20', '2022-06-20', '17:27:12', '17:27:12', '2022-06-20 17:27:13', '2022-06-20 17:27:16'),
	(3, 'teacher', '박범진', '떠먹여주는파이썬', 99, '', '2022-06-22', '2022-06-23', '17:22:22', '18:22:22', '2022-06-20 22:36:57', '2022-06-21 15:37:58'),
	(4, 'teacher4', '박범진', '떠먹여주는파이썬2', 99, '', '2022-06-22', '2022-06-23', '17:22:22', '18:22:22', '2022-06-20 22:38:28', '2022-06-21 15:37:52'),
	(5, 'teacher3', '유희조', '치킨과피자', 22, '', '2022-06-22', '2022-06-23', '17:22:22', '18:22:22', '2022-06-20 23:32:08', '2022-06-21 15:37:46'),
	(6, 'teacher', '박성준', '빅데이타', 1, 'test', '2022-06-22', '2022-06-23', '17:22:22', '18:22:22', '2022-06-20 23:33:11', '2022-06-21 15:36:04'),
	(7, 'teacher2', '나', '데이터분석', 22, '', '2022-06-22', '2022-06-23', '17:22:22', '18:22:22', '2022-06-20 23:33:48', '2022-06-21 15:37:39'),
	(8, 'teacher4', '유희조', '노잼 자바', 22, '', '2022-06-22', '2022-06-23', '17:22:22', '18:22:22', '2022-06-20 23:34:38', '2022-06-21 15:37:26'),
	(9, 'teacher2', '박범진', '노잼코딩', 99, '', '2022-06-22', '2022-06-23', '17:22:22', '18:22:22', '2022-06-20 23:35:11', '2022-06-21 15:37:15'),
	(10, 'teacher2', '이규남', '00데이터', 1, '', '2022-06-22', '2022-06-23', '17:22:22', '18:22:22', '2022-06-20 23:35:43', '2022-06-21 15:36:52'),
	(11, 'teacher', '나', '인공지능', 99, 'testtetstetst', '2022-06-25', '2022-06-30', '10:09:00', '21:09:00', '2022-06-21 09:09:52', '2022-06-21 15:34:46'),
	(12, 'teacher', '박범진', '자스', 1, 'ㅅㄷㄴㅅ', '2022-06-15', '2022-06-22', '10:21:00', '10:22:00', '2022-06-21 10:18:21', '2022-06-21 15:46:45');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

-- 테이블 lms.subject_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_board` (
  `subject_board_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_no` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`subject_board_no`),
  KEY `FK_subject_board_subject` (`subject_no`),
  CONSTRAINT `FK_subject_board_subject` FOREIGN KEY (`subject_no`) REFERENCES `subject` (`subject_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject_board:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject_board` DISABLE KEYS */;
INSERT INTO `subject_board` (`subject_board_no`, `subject_no`, `create_date`) VALUES
	(1, 12, '2022-06-21 16:16:22'),
	(2, 12, '2022-06-21 16:16:28'),
	(3, 12, '2022-06-21 16:16:44'),
	(4, 12, '2022-06-21 16:16:53');
/*!40000 ALTER TABLE `subject_board` ENABLE KEYS */;

-- 테이블 lms.subject_data 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_data` (
  `subject_data_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `subject_data_title` varchar(50) NOT NULL,
  `subject_data_content` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`subject_data_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject_data:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_data` ENABLE KEYS */;

-- 테이블 lms.subject_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_file` (
  `subject_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_file_board_no` int(11) NOT NULL,
  `subject_file_original_name` varchar(50) NOT NULL,
  `subject_file_name` varchar(50) NOT NULL,
  `subject_file_type` varchar(50) NOT NULL,
  `subject_file_size` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_file_no`),
  KEY `FK_subject_file_subject_board` (`subject_file_board_no`),
  CONSTRAINT `FK_subject_file_subject_board` FOREIGN KEY (`subject_file_board_no`) REFERENCES `subject_board` (`subject_board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject_file:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject_file` DISABLE KEYS */;
INSERT INTO `subject_file` (`subject_file_no`, `subject_file_board_no`, `subject_file_original_name`, `subject_file_name`, `subject_file_type`, `subject_file_size`, `create_date`, `update_date`) VALUES
	(1, 1, 'test1', 'test1', 'type', 'size', '2022-06-21 16:37:34', '2022-06-21 16:37:35'),
	(2, 1, 'test2', 'test2', 'type2', 'size2', '2022-06-21 16:39:05', '2022-06-21 16:39:06'),
	(3, 1, 'test3', 'test3', 'type3', 'size3', '2022-06-21 16:39:24', '2022-06-21 16:39:25'),
	(4, 2, 'test4', 'test4', 'type4', 'size4', '2022-06-21 16:39:42', '2022-06-21 16:39:44');
/*!40000 ALTER TABLE `subject_file` ENABLE KEYS */;

-- 테이블 lms.subject_notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_notice` (
  `subject_notice_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `subject_notice_title` varchar(50) NOT NULL,
  `subject_notice_content` text NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`subject_notice_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject_notice:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject_notice` DISABLE KEYS */;
INSERT INTO `subject_notice` (`subject_notice_no`, `member_id`, `subject_notice_title`, `subject_notice_content`, `create_date`) VALUES
	(1, 'student1', '테스트1', '테스트1', '2022-06-20 17:30:14');
/*!40000 ALTER TABLE `subject_notice` ENABLE KEYS */;

-- 테이블 lms.subject_report 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_report` (
  `subject_report_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `subject_report_title` varchar(50) NOT NULL,
  `subject_report_content` text NOT NULL,
  `subject_report_period` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`subject_report_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject_report:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject_report` DISABLE KEYS */;
INSERT INTO `subject_report` (`subject_report_no`, `member_id`, `subject_report_title`, `subject_report_content`, `subject_report_period`, `create_date`) VALUES
	(1, 'teacher', 'java 문법과제', 'java 문법과제하세요', '2022-06-21 16:08:00', '2022-06-21 16:08:01'),
	(2, 'teacher1', 'java 알고리즘과제', 'java알고리즘문제푸세요^^', '2022-06-23 16:08:39', '2022-06-21 16:08:40'),
	(3, 'teacher', '웹다오 과제', 'dao만드세요^^', '2022-06-24 16:09:17', '2022-06-21 16:09:18'),
	(4, 'teacher2', '스프링과제', '홈페이지만드세욬', '2022-06-25 16:09:59', '2022-06-21 16:10:03');
/*!40000 ALTER TABLE `subject_report` ENABLE KEYS */;

-- 테이블 lms.subject_report_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_report_comment` (
  `subject_report_comment_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_report_no` int(11) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `subject_report_comment_content` text NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_report_comment_no`),
  KEY `FK_subject_report_comment_subject_report` (`subject_report_no`),
  CONSTRAINT `FK_subject_report_comment_subject_report` FOREIGN KEY (`subject_report_no`) REFERENCES `subject_report` (`subject_report_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject_report_comment:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject_report_comment` DISABLE KEYS */;
INSERT INTO `subject_report_comment` (`subject_report_comment_no`, `subject_report_no`, `member_id`, `subject_report_comment_content`, `create_date`, `update_date`) VALUES
	(1, 1, 'teacher', '과제해라', '2022-06-21 16:11:16', '2022-06-21 16:11:16'),
	(2, 1, 'teacher1', '과제좀하라고', '2022-06-21 16:11:52', '2022-06-21 16:11:53'),
	(3, 1, 'teacher', 'rhkwp123', '2022-06-21 16:12:11', '2022-06-21 16:12:12'),
	(4, 2, 'teacher', '과제^^', '2022-06-21 16:12:24', '2022-06-21 16:12:25'),
	(5, 2, 'teacher2', 'ㅇㅅㅇ', '2022-06-21 16:12:42', '2022-06-21 16:12:44');
/*!40000 ALTER TABLE `subject_report_comment` ENABLE KEYS */;

-- 테이블 lms.subject_report_student 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_report_student` (
  `subject_report_student_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_report_no` int(11) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `subject_report_student_title` varchar(50) NOT NULL,
  `subject_report_student_content` text NOT NULL,
  `score` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_report_student_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject_report_student:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject_report_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_report_student` ENABLE KEYS */;

-- 테이블 lms.subject_student 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_student` (
  `member_id` varchar(50) NOT NULL,
  `subject_no` int(11) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`,`subject_no`),
  KEY `FK_subject_student_subject` (`subject_no`),
  CONSTRAINT `FK_subject_student_student` FOREIGN KEY (`member_id`) REFERENCES `student` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_student_subject` FOREIGN KEY (`subject_no`) REFERENCES `subject` (`subject_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject_student:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject_student` DISABLE KEYS */;
INSERT INTO `subject_student` (`member_id`, `subject_no`, `update_date`) VALUES
	('student', 1, '2022-06-20 17:28:32'),
	('student1', 2, '2022-06-20 17:28:16'),
	('student2', 1, '2022-06-20 17:23:24'),
	('student3', 2, '2022-06-20 17:28:25'),
	('student4', 1, '2022-06-20 17:26:01');
/*!40000 ALTER TABLE `subject_student` ENABLE KEYS */;

-- 테이블 lms.subject_video 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_video` (
  `subject_video_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_no` int(11) NOT NULL,
  `subject_video_title` varchar(50) NOT NULL,
  `subject_video_content` text NOT NULL,
  `subject_video_url` text NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_video_no`),
  KEY `FK_subject_video_subject` (`subject_no`),
  CONSTRAINT `FK_subject_video_subject` FOREIGN KEY (`subject_no`) REFERENCES `subject` (`subject_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.subject_video:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `subject_video` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_video` ENABLE KEYS */;

-- 테이블 lms.survey 구조 내보내기
CREATE TABLE IF NOT EXISTS `survey` (
  `survey_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_no` int(11) NOT NULL,
  `survey_question` varchar(200) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`survey_no`),
  KEY `FK_survey_subject` (`subject_no`),
  CONSTRAINT `FK_survey_subject` FOREIGN KEY (`subject_no`) REFERENCES `subject` (`subject_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.survey:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;

-- 테이블 lms.survey_result 구조 내보내기
CREATE TABLE IF NOT EXISTS `survey_result` (
  `survey_no` int(11) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `result` enum('1','2','3','4','5') NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`survey_no`,`member_id`),
  CONSTRAINT `FK_survey_result_survey` FOREIGN KEY (`survey_no`) REFERENCES `survey` (`survey_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.survey_result:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `survey_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey_result` ENABLE KEYS */;

-- 테이블 lms.teacher 구조 내보내기
CREATE TABLE IF NOT EXISTS `teacher` (
  `member_id` varchar(50) NOT NULL,
  `teacher_name` varchar(50) NOT NULL,
  `teacher_phone` varchar(50) NOT NULL,
  `teacher_address` varchar(200) NOT NULL,
  `teacher_gender` enum('남','여') NOT NULL,
  `teacher_detailaddress` varchar(50) NOT NULL,
  `teacher_email` varchar(50) NOT NULL,
  `teacher_join` date NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.teacher:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`member_id`, `teacher_name`, `teacher_phone`, `teacher_address`, `teacher_gender`, `teacher_detailaddress`, `teacher_email`, `teacher_join`, `update_date`) VALUES
	('teacher', '강사유희조', '01011111111', 'ㅁㄴㅇ', '남', 'ㅁㄴㅇ', 'ㅁㄴㅇ', '2022-06-20', '2022-06-20 13:14:38'),
	('teacher1', '강사이규남', '01022222222', 'ㅁㅁㅁ', '남', 'ㅁㅁㅁ', 'ㅁㅁㅁ', '2022-01-01', '2022-06-21 14:17:02'),
	('teacher2', '강사박성준', '01033333333', 'ㅁㅁㄴ', '남', 'ㅁㅁㄴ', 'ㅁㅁㄴ', '2022-02-01', '2022-06-21 14:17:33'),
	('teacher3', '강사원성현', '01044444444', 'ㅁㄴㄴ', '남', 'ㅁㄴㄴ', 'ㅁㄴㄴ', '2022-03-01', '2022-06-21 14:18:39'),
	('teacher4', '강사박범진', '01055555555', 'ㄴㄴㄴ', '남', 'ㄴㄴㄴ', 'ㄴㄴㄴ', '2022-04-01', '2022-06-21 14:18:40');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

-- 테이블 lms.teacher_language 구조 내보내기
CREATE TABLE IF NOT EXISTS `teacher_language` (
  `language_no` int(11) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`language_no`,`member_id`),
  KEY `FK_teacher_language_teacher` (`member_id`),
  CONSTRAINT `FK_teacher_language_language` FOREIGN KEY (`language_no`) REFERENCES `language` (`language_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_teacher_language_teacher` FOREIGN KEY (`member_id`) REFERENCES `teacher` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 lms.teacher_language:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `teacher_language` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_language` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
