package kr.co.javajoy.lms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	// 로그인
	public String loginMember(Map<String, Object> map);
	
	// 로그인 시 lastLoginDate Update
	public int updatelastLoginDate(String memberId);
	
	public int updateDormantMember();
	// 강사 아이디 찾기
	public String findTeacherId(Map<String, Object> map);

	// 학생 아이디 찾기
	public String findStudentId(Map<String, Object> map);

	// 강사 비밀번호 찾기
	public String findTeacherPw(Map<String, Object> map);

	// 학생 비밀번호 찾기
	public String findStudentPw(Map<String, Object> map);
}
