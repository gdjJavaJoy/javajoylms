

package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.StudentListMapper;
import kr.co.javajoy.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StudentListService {
	@Autowired StudentListMapper studentListMapper;
	
	// 학생 리스트 출력
	public Map<String, Object> getStudentList(int currentPage, int rowPerPage, int subjectNo) {
		// 리스트 출력 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		map.put("subjectNo", subjectNo);
		// Mapper에서 반환 된 값 가공
		List<Student> list = studentListMapper.getStudentList(map);
		int totalCount = studentListMapper.selectTotalCount();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("subjectNo", subjectNo);
		
		// 디버깅
		log.debug(CF.LGN + "StudentListController.selecStudentList rowPerPage : " + CF.RESET + rowPerPage);
		log.debug(CF.LGN + "StudentListController.selecStudentList startRow : " + CF.RESET + startRow);
		log.debug(CF.LGN + "StudentListController.selecStudentList lastPage : " + CF.RESET + lastPage );
		log.debug(CF.LGN + "StudentListController.selecStudentList list.size() : " + CF.RESET + list);
		return returnMap;
	}
}
