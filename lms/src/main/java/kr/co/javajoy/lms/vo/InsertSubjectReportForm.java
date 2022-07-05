package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class InsertSubjectReportForm {
	private int subjectBoardNo;
	private int subjectNo; 
	private int subjectReportNo;
	private String memberId;
	private String subjectReportTitle;
	private String subjectReportContent;
	private String subjectReportPeriod;
	private String createDate;
}
