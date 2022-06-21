package kr.co.javajoy.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.SignupForm;

@Mapper
public interface MemberMapper {
	 public List<String> selectMemberId();
	 
	 public String selectMemberLevel(String memberId);
	  int insertMemberId(SignupForm signupForm);
	  int insertAdmin(SignupForm signupForm);
	  int insertPassword(SignupForm signupForm);
	  int insertTeacher(SignupForm signupForm);
	  int insertStudent(SignupForm signupForm);

		 
}
