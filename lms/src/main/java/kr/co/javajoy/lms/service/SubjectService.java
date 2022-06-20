package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.mapper.SubjectMapper;
import kr.co.javajoy.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SubjectService {
@Autowired SubjectMapper subjectMapper;
	
	// 강좌 리스트(운영자용) 출력
	public Map<String, Object> getSubjectByPage(int currentPage, int rowPerPage) {
		// 리스트 출력 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		
		// Mapper에서 반환 된 값 가공
		List<Subject> list = subjectMapper.selectSubjectByPage(map);
		int totalCount = subjectMapper.selectTotalCount();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		// 디버깅
		log.debug("SubjectListController.selecSubjectListByPage rowPerPage : " + rowPerPage);
		log.debug("SubjectListController.selecSubjectListByPage startRow : " + startRow);
		log.debug("SubjectListController.selecSubjectListByPage lastPage : " + lastPage );
		log.debug("SubjectListController.selecSubjectListByPage list.size() : " + list.size());
		
		return returnMap;
	}
}
