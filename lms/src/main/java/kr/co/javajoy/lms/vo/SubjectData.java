package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class SubjectData {
	private int subjectDataNo;
	private String memberId;
	private String subjectDataTitle;
	private String subjectDataContent;
	private String updateDate;
}
