package kr.co.javajoy.lms.vo;


import lombok.Data;

@Data
public class SubjectVideo {
	private int subjectVideoNo;
	private int subjectNo;
	private String subjectVideoTitle;
	private String subjectVideoContent;
	private String subjectVideoUrl;
	private String createDate;
	private String updateDate;
	
}
