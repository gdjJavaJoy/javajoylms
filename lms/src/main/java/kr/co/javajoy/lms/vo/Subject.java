package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class Subject {
	private int subjectNo;
	private String teacherId;
	private String adminId;
	private String subjectName;
	private int subjectStudentMax;
	private String subjectInfo;
	private String subjectStartDate;
	private String subjectFinishDate;
	private String subjectStartTime;
	private String subjectEndTime;
	private String createDate;
	private String updateDate;
}
