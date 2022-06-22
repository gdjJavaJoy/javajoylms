package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class SubjectReportComment {
	private int subjectReportCommentNo;
	private int subjectReportNo;
	private String memberId;
	private String subjectReportCommentContent;
	private String createDate;
	private String updateDate;
}
