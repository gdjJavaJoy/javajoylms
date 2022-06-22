package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.SubjectReportMapper;
import kr.co.javajoy.lms.vo.SubjectReport;
import kr.co.javajoy.lms.vo.SubjectReportComment;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SubjectReportService {
	@Autowired SubjectReportMapper subjectReportMapper;
	
	// 과제 게시판 글 리스트 출력
	public Map<String, Object> getSubjectReportListByPage(int currentPage, int rowPerPage, int subjectNo) {
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.currentPage" + currentPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.rowPerPage" + rowPerPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.subjectNo" + subjectNo);		
		// 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", startRow);
		map.put("rowPerPage", rowPerPage);
		map.put("subjectNo", subjectNo);
		// 1) 컨트롤러에서 넘오온 변수값 가공 후 맵퍼 호출
		List<SubjectReport> list = subjectReportMapper.selectSubjectReportListByPage(map);
		// 2) 맵퍼에서 반환된 값을 가공 후, 컨트롤러에 변환
		int totalCount =  subjectReportMapper.selectTotalCount(); // 과제 게시판 글 총 수
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage)); 
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.startRow" + startRow);		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.totalCount" + totalCount);		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.lastPage" + lastPage);	
		
		// 리스트 결과값 해쉬 맵에 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("subjectNo", subjectNo);
		
		return returnMap;
	}
	
	// 과제 게시판 글 상세보기 + 파일 이름 리스트 출력
	public Map<String, Object> getSubjectReportAndFileNameListAndCommentList (Map<String, Object> map) {
		// 댓글 페이징 데이터
		int commentCurrentPage = (int)map.get("commentCurrentPage");
		int rowPerPage = (int)map.get("rowPerPage");
		int startRow = (commentCurrentPage - 1) * rowPerPage;
		int subjectBoardNo = (int)map.get("subjectBoardNo");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("subjectBoardNo", subjectBoardNo);
	
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.subjectBoardNo :" + subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.startRow :" + startRow);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.rowPerPage :" + rowPerPage);
		
		// 선택된 과게 게시판 글 상세보기
		List<SubjectReport> subjectReport = subjectReportMapper.selectSubjectReportOne(subjectBoardNo);
		// 선택된 과제 게시판 글의 파일 리스트 
		List<String> subjectFileList = subjectReportMapper.selectSubjectReportFileList(subjectBoardNo);
		// 선택된 과제 게시판 댓글 리스트
		List<SubjectReportComment> commentList = subjectReportMapper.selectCommentListByPage(paramMap);
		// 상세보기 + 파일 리스트 저장
		Map<String, Object> returnMap = new HashMap<>();
		// 댓글 페이징 코드
		int commentTotalCount = subjectReportMapper.selectCommentTotalCountByNotice(subjectBoardNo);
		int commentLastPage = commentTotalCount / (int)(map.get("rowPerPage"));
		if(commentTotalCount % (int)(map.get("rowPerPage")) != 0) {
			commentLastPage +=1;
		}
		// 상세보기 값 + 파일 리스트 + 댓글 리스트 데이터 저장
		returnMap.put("subjectReport", subjectReport);
		returnMap.put("subjectFileList", subjectFileList);
		returnMap.put("commentList", commentList);
		returnMap.put("commentLastPage", commentLastPage);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.subjectReport :" + subjectReport);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.subjectFileList :" + subjectFileList);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.commentList :" + commentList);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.commentLastPage :" + commentLastPage);
		
		return returnMap;
	}
}
















































































