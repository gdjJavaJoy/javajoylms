package kr.co.javajoy.lms.vo;

import java.util.List;

import lombok.Data;

@Data
public class MemberUpdateForm {
	private String memberId;
	private String memberName;
	private String memberGender;
	private String memberPhone;
	private String memberEmail;
	private String memberEducation;
	private String currentMemberAddress;
	private String changeMemberAddress;
	private String memberDetailAddress;
	private List<Integer> languageNo;
}
