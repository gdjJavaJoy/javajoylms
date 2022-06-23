package kr.co.javajoy.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Subject;

@Mapper
public interface StudentMapper {
	// 학생이 들었던 또는 듣고있는 subject리스트 출력
	public List<Subject> studentIndex(String memerId);
	
}
