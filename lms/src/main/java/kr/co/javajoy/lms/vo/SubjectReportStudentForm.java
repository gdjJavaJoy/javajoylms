package kr.co.javajoy.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SubjectReportStudentForm {

	private int subjectReportStudentNo;
	private int subjectReportNo;
	private String memberId;
	private String subjectReportStudentTitle;
	private String subjectReportStudentContent;
	private int score;
	private String status;
		
	private List<MultipartFile> studentFileList;
}
