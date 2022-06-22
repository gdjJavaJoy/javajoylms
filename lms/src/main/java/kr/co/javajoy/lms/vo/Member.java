package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class Member {
	private String memberId;
	private String memberPw;
	private String level;
	private String memberActive;
	private String lastLoginDate;
	private String createDate;
	private String deleteDate;
}
