package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class Notice {

	private int subjectNoticeNo;
	private String memberId;
	private String subjectNoticeTitle;
	private String subjectNoticeContent;
	private String createDate;
}
