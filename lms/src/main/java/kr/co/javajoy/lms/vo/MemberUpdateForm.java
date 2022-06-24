package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class MemberUpdateForm {
	private String memberId;
	private String memberName;
	private String memberGender;
	private String memberPhone;
	private String memberEamil;
	private String memberEducation;
	private String currentMemberAddress;
	private String changeMemberAddress;
	private String memberDetailAddress;
}
