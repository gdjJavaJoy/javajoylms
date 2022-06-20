package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Subject;

@Mapper
public interface SubjectMapper {
	List<Subject> selectSubjectByPage(Map<String, Object> map);
	int selectTotalCount();
}
