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
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SubjectReportService {
	@Autowired SubjectReportMapper subjectReportMapper;
	
	// 과제 게시판 글 리스트 출력
	public Map<String, Object> getSubjectReportListByPage(int currentPage, int rowPerPage, int subjectNo) {
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.currentPage" +currentPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.rowPerPage" +rowPerPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.subjectNo" +subjectNo);		
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
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.startRow" +startRow);		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.totalCount" +totalCount);		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.lastPage" +lastPage);	
		
		// 리스트 결과값 해쉬 맵에 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("subjectNo", subjectNo);
		
		return returnMap;
	}
}
















































































