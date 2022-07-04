package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class SubjectReportStudent {
	private int subjectReportStudentNo;
	private int subjectReportNo;
	private String memberId;
	private String subjectReportStudentTitle;
	private String subjectReportStudentContent;
	private int score;
	private String status;
	private String createDate;
	private String updateDate;
}
