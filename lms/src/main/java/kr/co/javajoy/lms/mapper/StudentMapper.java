package kr.co.javajoy.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Student;

@Mapper
public interface StudentMapper {
	// 학생 상세보기
	public Student selectStudentOne(String memberId);
}
