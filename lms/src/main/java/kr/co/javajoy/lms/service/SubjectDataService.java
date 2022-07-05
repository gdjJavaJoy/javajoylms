package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.SubjectDataMapper;
import kr.co.javajoy.lms.vo.SubjectData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SubjectDataService {
	@Autowired SubjectDataMapper subjectdataMapper;

	// 강좌자료 리스트 출력
	public Map<String, Object> getSubjectDataListByPage(int currentPage, int rowPerPage, int subjectNo, String searchName) {
		// 디버깅
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage.currentPage : " + currentPage + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage.rowPerPage : " + rowPerPage + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage.subjectNo : " + subjectNo + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage.searchName : " + searchName + CF.RESET);

		// 페이징
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("beginRow", beginRow);
		map.put("subjectNo", subjectNo);
		map.put("searchName", searchName);
		
		List<Map<String, Object>> list = subjectdataMapper.selectSubjectDataListByPage(map);
		int totalCount = subjectdataMapper.selectTotalCount(searchName);
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		// 디버깅
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage list : " + list + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage totalCount : " + totalCount + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage lastPage : " + lastPage + CF.RESET);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}
}
