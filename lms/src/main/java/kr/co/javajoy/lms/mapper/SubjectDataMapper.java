package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.SubjectData;

@Mapper
public interface SubjectDataMapper {
	// 강좌자료실 출력
	List<SubjectData> getSubjectDataList(Map<String, Object> map);
	// 커리큘럼 총 수
	int selectTotalCount();
}
