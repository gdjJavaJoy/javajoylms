package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class SubjectNotice {
	private int subjectNoticeNo;
	private String memberId;
	private String subjectNoticeTitle;
	private String subjectNoticeCount;
	private String createDate;
}
