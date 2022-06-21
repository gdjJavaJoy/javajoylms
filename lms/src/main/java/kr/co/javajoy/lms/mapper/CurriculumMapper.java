package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Curriculum;

@Mapper
public interface CurriculumMapper {
	// 커리큘럼 리스트 출력
	List<Curriculum> getCurriculumList(Map<String, Object> map);
	// 커리큘럼 총 수
	int selectTotalCount();
}