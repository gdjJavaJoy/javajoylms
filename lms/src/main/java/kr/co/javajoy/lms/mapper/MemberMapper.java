package kr.co.javajoy.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.SignupForm;

@Mapper
public interface MemberMapper {
	// 중복검사
	public List<String> selectMemberId();
	// id로 level 추출
	public String selectMemberLevel(String memberId);
	// 멤버추가
	int insertMemberId(SignupForm signupForm);
	// 관리자추가
	int insertAdmin(SignupForm signupForm);
	// password 추가
	int insertPassword(SignupForm signupForm);
	// 강사 추가
	int insertTeacher(SignupForm signupForm);
	// 학생 추가
	int insertStudent(SignupForm signupForm);

}
