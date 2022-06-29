package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.AllAdminListMapper;
import kr.co.javajoy.lms.vo.Admin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AllAdminListService{
	@Autowired AllAdminListMapper allAdminListMapper;

	// 관리자 리스트 출력
	public Map<String, Object> AllAdminList(int currentPage, int rowPerPage) {
		// 리스트 출력 페이징 
		int startRow = (currentPage - 1) * rowPerPage;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage); map.put("startRow", startRow);
		
		// Mapper에서 반환 된 값 가공 
		List<Admin> list = allAdminListMapper.AllAdminList(map); int
		totalCount = allAdminListMapper.selectTotalCount(); int lastPage =
		(int)(Math.ceil((double)totalCount / (double)rowPerPage));
		Map<String, Object> returnMap = new HashMap<>(); returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		// 디버깅 
		log.debug(CF.LGN +"AllStudentListController.selecAllStudentList rowPerPage : "+ rowPerPage);
		log.debug(CF.LGN + "AllStudentListController.selecAllStudentList startRow : "+ startRow);
		log.debug(CF.LGN +"AllStudentListController.selecAllStudentList lastPage : " + lastPage );
		log.debug(CF.LGN +"AllStudentListController.selecAllStudentList list.size() : " + list);
		return returnMap;
	}
}
