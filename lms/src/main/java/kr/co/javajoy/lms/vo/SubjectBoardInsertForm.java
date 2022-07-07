package kr.co.javajoy.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SubjectBoardInsertForm {
	private int subjectBoardNo;
	private int subjectNo;
	private int subjectNoticeNo;
	private String memberId;
	private String subjectBoardTitle;
	private String subjectBoardContent;
	
	private List<MultipartFile> subjectBoardFileList;
}
