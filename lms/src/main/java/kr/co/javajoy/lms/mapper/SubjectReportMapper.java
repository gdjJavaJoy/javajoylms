package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.SubjectReport;

@Mapper
public interface SubjectReportMapper {
	// 과제 게시판 글 리스트 출력
	List<SubjectReport> selectSubjectReportListByPage(Map<String, Object> map);
	// 과제 게시판 글 총 개수
	int selectTotalCount();
}
