package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Subject;

@Mapper
public interface SubjectMapper {
	// 강좌 입력
	int insertSubject(Subject subject);
	// 강좌 리스트(운영자 용) 출력
	List<Subject> selectSubjectByPage(Map<String, Object> map);
	// 강좌 총 수
	int selectTotalCount();
}
