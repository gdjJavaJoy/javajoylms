package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private String memberId;
	private String boardCategory;
	private String boardTitle;
	private String boardContent;
	private String privateNo;
	private String createDate;
	private String updateDate;
	
}