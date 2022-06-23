package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.SubjectNoticeMapper;
import kr.co.javajoy.lms.vo.SubjectNotice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SubjectNoticeService {
	@Autowired SubjectNoticeMapper subjectNoticeMapper;
	
	// 커리큘럼 리스트 출력
	public Map<String, Object> getSubjectNoticeList(int currentPage, int rowPerPage, int subjectNo) {
		// 리스트 출력 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		map.put("subjectNo", subjectNo);
		// Mapper에서 반환 된 값 가공
		List<SubjectNotice> list = subjectNoticeMapper.getSubjectNoticeList(map);
		int totalCount = subjectNoticeMapper.selectTotalCount();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("subjectNo", subjectNo);
		
		// 디버깅
		log.debug(CF.LGN + "SubjectNoticeController.selectSubjectNotice rowPerPage : " + CF.RESET + rowPerPage);
		log.debug(CF.LGN + "SubjectNoticeController.selectSubjectNotice startRow : " + CF.RESET + startRow);
		log.debug(CF.LGN + "SubjectNoticeController.selectSubjectNotice lastPage : " + CF.RESET + lastPage );
		log.debug(CF.LGN + "SubjectNoticeController.selectSubjectNotice list.size() : " + CF.RESET + list);
		return returnMap;
	}
}