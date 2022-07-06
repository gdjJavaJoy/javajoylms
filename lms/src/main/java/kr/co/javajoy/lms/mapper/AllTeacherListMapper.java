package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Teacher;

@Mapper
public interface AllTeacherListMapper {
	// 강사 리스트 출력
	List<Teacher> AllTeacherList(Map<String, Object> map);
	// 강사 삭제(업데이트)
	int deleteTeacher(String memberId);
	int deleteMemberId(String memberId);
	// 강사 총 수
	int selectTotalCount();
}