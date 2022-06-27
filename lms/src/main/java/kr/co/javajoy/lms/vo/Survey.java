package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class Survey {
	private int surveyNo;
	private int subjectNo;
	private String surveyQuestion;
	private String createDate;
	private String updateDate;
}
