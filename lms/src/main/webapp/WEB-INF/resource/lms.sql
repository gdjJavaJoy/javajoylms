-- --------------------------------------------------------
-- 호스트:                          3.36.49.93
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

-- 내보낼 데이터가 선택되어 있지 않습니다.

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.board_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_comment` (
  `board_comment_no` int(11) NOT NULL AUTO_INCREMENT,
  `board_no` int(11) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `board_comment_content` text NOT NULL,
  `comment_anony` enum('1','2') NOT NULL DEFAULT '1' COMMENT '1. 평범 2. 익명',
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`board_comment_no`) USING BTREE,
  KEY `FK_board_comment_board` (`board_no`),
  CONSTRAINT `FK_board_comment_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='inquiry의 경우 answer의 기능';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.board_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_file` (
  `board_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `board_no` int(11) NOT NULL,
  `board_file_original_name` varchar(50) NOT NULL,
  `board_file_name` varchar(50) NOT NULL,
  `board_file_type` varchar(100) NOT NULL,
  `board_file_size` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`board_file_no`),
  KEY `FK_board_file_board` (`board_no`),
  CONSTRAINT `FK_board_file_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.book 구조 내보내기
CREATE TABLE IF NOT EXISTS `book` (
  `book_no` int(11) NOT NULL AUTO_INCREMENT,
  `book_title` varchar(50) NOT NULL,
  `book_writer` varchar(50) NOT NULL,
  `book_company` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`book_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.career 구조 내보내기
CREATE TABLE IF NOT EXISTS `career` (
  `career_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `career` varchar(50) NOT NULL,
  `detail_career` varchar(50) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`career_no`),
  KEY `FK_career_teacher` (`member_id`),
  CONSTRAINT `FK_career_teacher` FOREIGN KEY (`member_id`) REFERENCES `teacher` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.curriculum_book 구조 내보내기
CREATE TABLE IF NOT EXISTS `curriculum_book` (
  `book_no` int(11) NOT NULL,
  `curriculum_no` int(11) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`book_no`,`curriculum_no`),
  KEY `FK_curriculum_book_curriculum` (`curriculum_no`),
  CONSTRAINT `FK_curriculum_book_book_no` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `FK_curriculum_book_curriculum` FOREIGN KEY (`curriculum_no`) REFERENCES `curriculum` (`curriculum_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.language 구조 내보내기
CREATE TABLE IF NOT EXISTS `language` (
  `language_no` int(11) NOT NULL AUTO_INCREMENT,
  `language_name` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`language_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

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

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.member_photo 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_photo` (
  `member_id` varchar(50) NOT NULL,
  `member_photo_original_name` varchar(50) NOT NULL,
  `member_photo_name` varchar(50) NOT NULL,
  `member_photo_size` bigint(50) NOT NULL,
  `member_photo_type` varchar(20) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `FK_member_photo_member_id` FOREIGN KEY (`member_id`) REFERENCES `member_id` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.password 구조 내보내기
CREATE TABLE IF NOT EXISTS `password` (
  `member_id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

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

-- 내보낼 데이터가 선택되어 있지 않습니다.

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

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.student_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `student_file` (
  `student_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_report_student_no` int(11) NOT NULL,
  `student_file_original_name` varchar(50) NOT NULL,
  `student_file_name` varchar(50) NOT NULL,
  `student_file_type` varchar(50) NOT NULL,
  `student_file_size` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`student_file_no`) USING BTREE,
  KEY `FK_student_file_subject_report_student` (`subject_report_student_no`),
  CONSTRAINT `FK_student_file_subject_report_student` FOREIGN KEY (`subject_report_student_no`) REFERENCES `subject_report_student` (`subject_report_student_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.student_job 구조 내보내기
CREATE TABLE IF NOT EXISTS `student_job` (
  `member_id` varchar(50) NOT NULL,
  `company` varchar(50) NOT NULL,
  `start_salary` int(11) NOT NULL COMMENT '초봉',
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.subject_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_board` (
  `subject_board_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_no` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`subject_board_no`),
  KEY `FK_subject_board_subject` (`subject_no`),
  CONSTRAINT `FK_subject_board_subject` FOREIGN KEY (`subject_no`) REFERENCES `subject` (`subject_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.subject_data 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_data` (
  `subject_data_no` int(11) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `subject_data_title` varchar(50) NOT NULL,
  `subject_data_content` varchar(50) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_data_no`),
  CONSTRAINT `FK_subject_data_subject_board` FOREIGN KEY (`subject_data_no`) REFERENCES `subject_board` (`subject_board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.subject_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_file` (
  `subject_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_file_board_no` int(11) NOT NULL,
  `subject_file_name` varchar(50) NOT NULL,
  `subject_file_original_name` varchar(50) NOT NULL,
  `subject_file_type` varchar(50) NOT NULL,
  `subject_file_size` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_file_no`),
  KEY `FK_subject_file_subject_board` (`subject_file_board_no`),
  CONSTRAINT `FK_subject_file_subject_board` FOREIGN KEY (`subject_file_board_no`) REFERENCES `subject_board` (`subject_board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.subject_notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_notice` (
  `subject_notice_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `subject_notice_title` varchar(50) NOT NULL,
  `subject_notice_content` text NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`subject_notice_no`),
  CONSTRAINT `FK_subject_notice_subject_board` FOREIGN KEY (`subject_notice_no`) REFERENCES `subject_board` (`subject_board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.subject_report 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_report` (
  `subject_report_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `subject_report_title` varchar(50) NOT NULL,
  `subject_report_content` text NOT NULL,
  `subject_report_period` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`subject_report_no`),
  CONSTRAINT `FK_subject_report_subject_board` FOREIGN KEY (`subject_report_no`) REFERENCES `subject_board` (`subject_board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.subject_report_student 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_report_student` (
  `subject_report_student_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_report_no` int(11) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `subject_report_student_title` varchar(50) NOT NULL,
  `subject_report_student_content` text NOT NULL,
  `score` int(11) NOT NULL,
  `status` enum('1','2') NOT NULL DEFAULT '1' COMMENT '1=채점전, 2=채점완료',
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_report_student_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

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

-- 내보낼 데이터가 선택되어 있지 않습니다.

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

-- 내보낼 데이터가 선택되어 있지 않습니다.

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

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

-- 내보낼 데이터가 선택되어 있지 않습니다.

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

-- 내보낼 데이터가 선택되어 있지 않습니다.

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

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
