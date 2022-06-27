package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class MemberPhoto {
	private String memberId;
	private String memberPhotoOriginalName;
	private String memberPhotoName;
	private long memberPhotoSize;
	private String memberPhotoType;
	private String updateDate;
	
}
