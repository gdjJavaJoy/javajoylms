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
	int insertMemberId(Member member);
	// 관리자추가
	int insertAdmin(Admin admin);
	// password 추가
	int insertPassword(Password password);
	// 강사 추가
	int insertTeacher(Teacher teacher);
	// 학생 추가
	int insertStudent(Student student);

}
