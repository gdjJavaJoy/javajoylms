package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class Admin {
	private String memberId;
	private String adminPw;
	private String adminName;
	private String adminPhone;
	private String adminAddress;
	private String adminDetailAddress;
	private String adminEmail;
	private String updateDate;
}
