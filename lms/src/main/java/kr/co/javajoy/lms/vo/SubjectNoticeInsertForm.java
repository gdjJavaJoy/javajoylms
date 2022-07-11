package kr.co.javajoy.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SubjectNoticeInsertForm {
	private int subjectBoardNo;
	private int subjectNo;
	private int subjectNoticeNo;
	private String memberId;
	private String subjectNoticeTitle;
	private String subjectNoticeContent;
	private String createDate;
	
	private List<MultipartFile> subjectNoticeFileList;
}

