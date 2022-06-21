package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.mapper.NoticeMapper;
import kr.co.javajoy.lms.mapper.NoticefileMapper;
import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.Boardfile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class NoticeService {
	@Autowired NoticeMapper noticeMapper;
	@Autowired NoticefileMapper noticefileMapper;
	
	// 공지사항 리스트
	public Map<String, Object> getNoticeByPage(int currentPage, int rowPerPage) {
		int startRow = (currentPage - 1)*rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		List<Board> list = noticeMapper.selectNoticeByPage(map);
		
		int totalCount = noticeMapper.selectTotalCount();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		return returnMap;
	}
	
	// 공지사항 상세보기
	public Map<String, Object> getNoticeOne(int boardNo) {
		// 쿼리불러오기 조회값 저장
		List<Board> board = noticeMapper.selectNoticeOne(boardNo); 
		log.debug(board +"----------------------------");
		// 파일
		List<Boardfile> boardfile = noticefileMapper.selectNoticefileList(boardNo); 
		log.debug(boardfile +"----------------------------");
		
		Map<String, Object> map = new HashMap<>();
		map.put("board", board);
		map.put("boardfile", boardfile);
	
		return map;
		
	}
}