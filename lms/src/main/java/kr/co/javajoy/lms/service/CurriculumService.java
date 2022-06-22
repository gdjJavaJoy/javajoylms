package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.CurriculumMapper;
import kr.co.javajoy.lms.vo.Curriculum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CurriculumService {
	@Autowired CurriculumMapper curriculumMapper;
	
	// 커리큘럼 리스트 출력
	public Map<String, Object> getCurriculumList(int currentPage, int rowPerPage) {
		// 리스트 출력 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		
		// Mapper에서 반환 된 값 가공
		List<Curriculum> list = curriculumMapper.getCurriculumList(map);
		int totalCount = curriculumMapper.selectTotalCount();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		// 디버깅
		log.debug(CF.LGN + "StudentListController.selecStudentList rowPerPage : " + CF.RESET + rowPerPage);
		log.debug(CF.LGN + "StudentListController.selecStudentList startRow : " + CF.RESET + startRow);
		log.debug(CF.LGN + "StudentListController.selecStudentList lastPage : " + CF.RESET + lastPage );
		log.debug(CF.LGN + "StudentListController.selecStudentList list.size() : " + CF.RESET + list);
		return returnMap;
	}
}
