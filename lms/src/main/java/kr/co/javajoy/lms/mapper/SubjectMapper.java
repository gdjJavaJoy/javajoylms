package kr.co.javajoy.lms.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Subject;

@Mapper
public interface SubjectMapper {
	// 강좌 입력
	int insertSubject(Subject subject);
	// 강사 리스트
	ArrayList<String> selectTeacherId();
	// 강좌 리스트(운영자 용) 출력
	List<Subject> selectSubjectByPage(Map<String, Object> map);
	// 강좌 총 수
	int selectTotalCount();
	// 강좌 상세보기
	Subject selectSubjectOne(int subjectNo);
	// 강좌 수정
	int updateSubject(Subject subject);
	// 강좌 삭제(미구현)
	
}
