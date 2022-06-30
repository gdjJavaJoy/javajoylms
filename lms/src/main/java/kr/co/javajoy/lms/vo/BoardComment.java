package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class BoardComment {
	private int boardCommentNo;
	private String memberId;
	private int boardNo;
	private String boardCommentContent;
	private String commentAnony;
	private String createDate;
	private String updateDate;
}
