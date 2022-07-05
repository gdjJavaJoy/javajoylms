package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class SubjectReport {
	private int subjectReportNo;
	private String memberId;
	private String subjectReportTitle;
	private String subjectReportContent;
	private String subjectReportPeriod;
	private String createDate;
}
