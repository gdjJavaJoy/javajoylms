package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Student;

@Mapper
public interface StudentMapper {
	// 학생 리스트 출력
	List<Student> getStudentList(Map<String, Object> map);
	// 학생 총 수
	int selectTotalCount();
}