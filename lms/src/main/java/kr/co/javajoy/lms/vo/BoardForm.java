package kr.co.javajoy.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardForm {
	private String memberId;
	private String boardCategory;
	private String boardTitle;
	private String boardContent;
	private String privateNo;
	
	
	private List<MultipartFile> boardfileList;
}
