package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Admin;
import kr.co.javajoy.lms.vo.Language;
import kr.co.javajoy.lms.vo.Member;
import kr.co.javajoy.lms.vo.MemberPhoto;
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
	// 탈퇴한 회원 추출
	public List<String> selectResignedMemberId();
	public int selectMemberPw(Map<String, Object> map);
	int updateMemberPassword(Password password);
	// 학생이 들었던 또는 듣고있는 subject리스트 출력
	public List<Map<String, Object>> studentIndex(String memerId);
	// 강사가 수강했던 또는 수강 중인 subject리스트 출력
	public List<Map<String, Object>> teacherIndex(String memberId);
	// languageList
	public List<Language> selectLanguageList();
	// 해당하는 member의 memberPhoto 사진 cnt 하는 메서드
	int selectMemberPhotoCnt(String memberId);
	// deleteMemberPhoto db 행 삭제
	int deleteMemberPhoto(String memberId);
	// 사진 추가 쿼리
	int insertMemberPhoto(MemberPhoto memberPhoto);
	// 사진이름가져오는 메서드
	List<String> selectPhotoNameByMemberId(String memberId);
	
}
