package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.SubjectReport;
import kr.co.javajoy.lms.vo.SubjectReportComment;

@Mapper
public interface SubjectReportMapper {
	// 과제 게시판 글 총 개수
	int selectTotalCount();
	
	// 과제 게시판 글 리스트 출력
	List<SubjectReport> selectSubjectReportListByPage(Map<String, Object> map);
	
	// 과제 게시판 글 상세보기
	List<SubjectReport> selectSubjectReportOne(int subjectBoardNo);
	
	// 과제 게시판 글 상세보기의 파일 이름 리스트 출력
	List<String> selectSubjectReportFileNameList(int subjectBoardNo);
	
	// 과제 게시판 글 상세보기의 파일 리스트 출력
	List<String> selectSubjectReportFileList(int subjectBoardNo);
	
	// 과제 게시판 각 글의 댓글 리스트
	List<SubjectReportComment> selectCommentListByPage(Map<String, Object> map);
	
	// 각 글의 총 댓글 수
	int selectCommentTotalCountByNotice(int subjectBoardNo);
}
