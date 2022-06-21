package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class Curriculum {
	private int curriculumNo;
	private int subjectNo;
	private String memberId;
	private int languageNo;
	private String curriculumTitle;
	private String curriculumContent;
	private String startDay;
	private String endDay;
	private String createDate;
	private String updateDate;
}
