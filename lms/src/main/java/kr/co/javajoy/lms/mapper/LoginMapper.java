package kr.co.javajoy.lms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	// 로그인
	public String loginMember(Map<String, Object> map);
	
	// 로그인 시 lastLoginDate Update
	public int updatelastLoginDate(String memberId);
	
	// 휴먼 계정 처리(1년 이상 미접속)
	public int updateDormantMember();
	
	// 강사 아이디 찾기
	public String findTeacherId(Map<String, Object> map);

	// 학생 아이디 찾기
	public String findStudentId(Map<String, Object> map);

	// 강사 비밀번호 찾기
	public int findTeacherPw(Map<String, Object> map);

	// 학생 비밀번호 찾기
	public int findStudentPw(Map<String, Object> map);
	
	// 비밀번호를 1111, active를 4(최초로그인 상태)로 바꾸어서 무조건 비밀번호 변경창으로 이동하기 위해 처리
	int updateFindPwMember(String memberId);
}
