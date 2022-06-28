package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class SubjectFile {
	private int subjectFileNo;
	private int subjectBoardNo; // LEFT JOIN
	private int subjectFileBoardNo;
	private String subjectFileOriginalName;
	private String subjectFileName;
	private String subjectFileType;
	private long subjectFileSize;
	private String createDate;
	private String updateDate;
}
