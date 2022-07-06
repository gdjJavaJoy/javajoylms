package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Student;

@Mapper
public interface AllStudentListMapper {
	// 학생 리스트 출력
	List<Student> AllStudentList(Map<String, Object> map);
	// 학생 삭제 (업데이트)
	int deleteStudent(String memberId);
	int deleteMemberId(String memberId);
	// 학생 총 수
	int selectTotalCount();
}