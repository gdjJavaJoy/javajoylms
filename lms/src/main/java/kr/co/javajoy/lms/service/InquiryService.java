package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.InquiryMapper;
import kr.co.javajoy.lms.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class InquiryService {
	@Autowired InquiryMapper inquiryMapper;
	public Map<String,Object> getInquiryByPage(int currentPage, int rowPerPage) {
		//page 출력 or List 
		int beginRow = (currentPage-1)*rowPerPage;
		// 디버깅 
		log.debug(CF.PSG+"InquiryService.getInquiryByPage.beginRow :" + beginRow + CF.RESET);
		log.debug(CF.PSG+"InquiryService.getInquiryByPage.rowPerPage :" +rowPerPage+CF.RESET);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginRow",beginRow);
		map.put("rowPerPage", rowPerPage);
		List<Board> list = inquiryMapper.selectInquiryByPage(map);
		// 전체 총 게시물의 갯수 구하기
		int totalCount = inquiryMapper.selectInquiryTotalCount();
		int lastPage =  (int)(Math.ceil((double)totalCount / (double)rowPerPage)); 
		Map<String ,Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("totalCount", totalCount);
		return returnMap;
	}
	public List<String> getTeacherListBySubject(String memberId) {
		
		List<String> list = inquiryMapper.selectTeacherListBySubject(memberId);
		log.debug(CF.PSG+"InquiryService.getTeacherListBySubject list : " + list + CF.RESET);
		
		return list;
	}
}
