package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.AllStudentListMapper;
import kr.co.javajoy.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AllStudentListService {
	@Autowired AllStudentListMapper allStudentListMapper;
	
	// 학생 리스트 출력
	public Map<String, Object> AllStudentList(int currentPage, int rowPerPage, String s_studentName) {
		// 리스트 출력 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		map.put("s_studentName", s_studentName);
		
		// Mapper에서 반환 된 값 가공
		List<Student> list = allStudentListMapper.AllStudentList(map);
		int totalCount = allStudentListMapper.selectTotalCount();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		// 디버깅
		log.debug(CF.LGN + "AllStudentListController.selecAllStudentList rowPerPage : "+ rowPerPage);
		log.debug(CF.LGN + "AllStudentListController.selecAllStudentList startRow : " + startRow);
		log.debug(CF.LGN + "AllStudentListController.selecAllStudentList lastPage : " + lastPage );
		log.debug(CF.LGN + "AllStudentListController.selecAllStudentList list.size() : " + list);
		return returnMap;
	}
}
