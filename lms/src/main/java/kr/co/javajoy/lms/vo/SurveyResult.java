package kr.co.javajoy.lms.vo;

import java.util.List;

import lombok.Data;

@Data
public class SurveyResult {
	private int surveyNo;
	private String memberId;
	private String result;
	private String createDate;
	private String updateDate;
	
	private List<SurveyResult> surveyResultList;
}
