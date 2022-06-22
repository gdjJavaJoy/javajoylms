package kr.co.javajoy.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Admin;
import kr.co.javajoy.lms.vo.Member;
import kr.co.javajoy.lms.vo.Password;
import kr.co.javajoy.lms.vo.Student;
import kr.co.javajoy.lms.vo.Teacher;

@Mapper
public interface MemberMapper {
	// 중복검사
	public List<String> selectMemberId();
	// id로 level 추출
	public String selectMemberLevel(String memberId);
	// 멤버추가
	public int insertMemberId(Member member);
	// 관리자추가
	public int insertAdmin(Admin admin);
	// password 추가
	public int insertPassword(Password password);
	// 강사 추가
	public int insertTeacher(Teacher teacher);
	// 학생 추가
	public int insertStudent(Student student);
	// 멤버 비밀번호 생성기간(월기준)
	public int selectMemberPwPeriod(String memberId);
	// memberId로 active 추출
	public String selectMemberActive(String memberId);
	// 비활성화 memberActive를 활성화
	public void updateMemberActive(String memberId);
}
