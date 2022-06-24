package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class SubjectReport {
	// subject_board
	private int subjectBoardNo;
	private int subjectNo; // LEFT JOIN subject_board
	private String subjectName; // LEFT JOIN subject
	private String createDate;
	// subject_report
	private int subjectReportNo;
	private String memberId;
	private String subjectReportTitle;
	private String subjectReportContent;
	private String subjectReportPeriod;
	// private String createDate;
}
