package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class Boardfile {
	private int boardFileNo;
	private int boardNo;
	private String boardFileOriginalName;
	private String boardFileName;
	private String boardFileType;
	private long boardFileSize;
	private String createDate;
	private String updateDate;
	
}
