package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class StudentFile {
	private int studentFileNo;
	private int subjectReportStudentNo; 
	private String studentFileName;
	private String studentFileOriginalName;
	private String studentFileType;
	private long studentFileSize;
	private String createDate;
	private String updateDate;
}
