package kr.co.javajoy.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SubjectReportForm {
	// subject_board
	private int subjectNo;
	// subject_report
	private String memberId;
	private String subjectReportTitle;
	private String subjectReportContent;
	private String subjectReportPeriod;
	
	private List<MultipartFile> subjectReportFileList;
}
