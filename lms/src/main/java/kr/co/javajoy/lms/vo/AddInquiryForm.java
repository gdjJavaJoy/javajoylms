package kr.co.javajoy.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddInquiryForm {
	private int boardNo;
	private String memberId;
	private int recevier;
	private	List<String> teacherId;
	private String boardTitle;
	private String boardContent;
	private String privateNo;
	private List<MultipartFile> inquiryFileList;
}
