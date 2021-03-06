package kr.co.javajoy.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.NoticeMapper;
import kr.co.javajoy.lms.mapper.NoticefileMapper;
import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.BoardForm;
import kr.co.javajoy.lms.vo.Boardfile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class NoticeService {
	@Autowired NoticeMapper noticeMapper;
	@Autowired NoticefileMapper noticefileMapper;
	
	// 공지사항 리스트
	public Map<String, Object> getNoticeByPage(int currentPage, int rowPerPage, String searchNoticeTitle) {
		int startRow = (currentPage - 1)*rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		map.put("searchNoticeTitle", searchNoticeTitle);
		
		log.debug(CF.WSH + "NoticeService.getNoticeByPage.rowPerPage : "+rowPerPage);
		log.debug(CF.WSH + "NoticeService.getNoticeByPage.startRow : "+startRow);
		log.debug(CF.WSH + "NoticeService.getNoticeByPage.searchNoticeTitle : "+searchNoticeTitle);
		
		// Mapper에서 반환된 값
		List<Board> list = noticeMapper.selectNoticeByPage(map);
		// 전체 총 게시물 수 구하기
		int totalCount = noticeMapper.selectTotalCount(searchNoticeTitle);
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		log.debug(CF.WSH + "NoticeService.getNoticeByPage.totalCount : "+totalCount);
		log.debug(CF.WSH + "NoticeService.getNoticeByPage.lastPage : "+lastPage);

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("totalCount", totalCount);
		
		
		return returnMap;
	}
	
	// 공지사항 상세보기
	// 수정 (Get)
	public Map<String, Object> getNoticeOne(int boardNo, String searchNoticeTitle) {
		// 쿼리불러오기 조회값 저장
		List<Board> board = noticeMapper.selectNoticeOne(boardNo); 
		log.debug(CF.WSH + "NoticeService.getNoticeOne.board : "+board);
		
		// 파일
		List<Boardfile> boardfile = noticefileMapper.selectNoticefileList(boardNo); 
		log.debug(CF.WSH + "NoticeService.getNoticeOne.boardfile : "+boardfile);
		
		// 파일 갯수 확인
		int fileTotalCount = noticefileMapper.selectBoardfileCnt(boardNo);
		log.debug(CF.WSH + "NoticeService.getNoticeOne.fileTotalCount : "+fileTotalCount);
		
		// 게시글 번호받기 
		int totalCount = noticeMapper.selectTotalCount(searchNoticeTitle);
		
		Map<String, Object> map = new HashMap<>();
		map.put("board", board);
		map.put("boardfile", boardfile);
		map.put("fileTotalCount", fileTotalCount);
		map.put("totalCount", totalCount);
		return map;
	}
	
	
	// 공지사항 추가하기
	public int addNotice(BoardForm boardForm, String path) {
		log.debug(CF.WSH + "NoticeService.addNotice.boardForm : "+ boardForm);
		log.debug(CF.WSH + "NoticeService.addNotice.path : "+ path);
		
		// jsp에서 적은 파일을 Multipartfile(BoardForm)에 저장했던걸 다시 Board에 저장함
		Board board = new Board();
		board.setMemberId(boardForm.getMemberId());
		board.setBoardTitle(boardForm.getBoardTitle());
		board.setBoardContent(boardForm.getBoardContent());
		board.getBoardNo();
		log.debug(CF.WSH + "NoticeService.addNotice.board : "+ board);
		
		// DB에 값을 넣는 쿼리호출
		int row = noticeMapper.insertNotice(board);
		log.debug(CF.WSH + "NoticeService.addNotice.boardNo : "+ board.getBoardNo());
		
		if(boardForm.getBoardfileList() != null && boardForm.getBoardfileList().get(0).getSize() > 0  && row == 1) {
			log.debug(CF.WSH + "NoticeService.addNotice.파일확인 : "+"첨부된 파일이 있습니다.");
			for(MultipartFile mf : boardForm.getBoardfileList()) {
				Boardfile boardfile = new Boardfile();
				String originName = mf.getOriginalFilename(); 
				String ext = originName.substring(originName.lastIndexOf("."));
				// originName에서 마지막.(점)위치
				String filename = UUID.randomUUID().toString();
				// 저장 시 중복되지 않고 저장하기 위해 UUID API를 사용하여 이름 랜덤 지정
				filename = filename.replace("-", ""); 
				// -를 공백으로
				filename = filename + ext;
				boardfile.setBoardNo(board.getBoardNo());
				boardfile.setBoardFileOriginalName(originName);
				boardfile.setBoardFileName(filename);
				boardfile.setBoardFileType(mf.getContentType());
				boardfile.setBoardFileSize(mf.getSize()); // long타입 // 파일의 배열 사이즈
				log.debug(CF.WSH + "NoticeService.addNotice.boardfile : "+ boardfile);
				noticefileMapper.insertNoticefile(boardfile);
				try {
					mf.transferTo(new File(path+filename));
				} catch (Exception e) {
					e.printStackTrace();
					// 예외 발생 시 @Transactional 작동 
					throw new RuntimeException(); // RuntimeException 예외처리 안해도 컴파일 됨
				}
			}
		}
		return row;
	}
	// 삭제
	public void removefileNotice (int boardfileNo, String path) {
		log.debug(CF.WSH + "NoticeService.removefileNotice.boardfileNo : " + boardfileNo + CF.WSH);
		log.debug(CF.WSH + "NoticeService.removefileNotice.path : " + path + CF.WSH);
		
			List<String> boardfileList = noticefileMapper.selectNoticefileNameListByBoardfileNo(boardfileNo);
			log.debug(CF.WSH + "NoticeService.removefileNotice.boardfileList : "+ boardfileList);
			for(String bfl : boardfileList ) {
				File f = new File(path+bfl);
				if(f.exists())
					f.delete();
			}
	// 행삭제
	noticefileMapper.deleteNoticefileOne(boardfileNo);
	}
	public void removeNotice(int boardNo, String path) {
			List<String> remove = noticefileMapper.selectNoticefileNameList(boardNo);
			for(String re : remove) {
				File f = new File(path+re);
				if(f.exists())
					f.delete();
			}
	// boaardNo를 이용한 행삭제
	noticefileMapper.deleteNoticefileList(boardNo);
	noticeMapper.deleteNotice(boardNo);
	}
	// 수정 (Post)
	public void modifyNotice(Board board, BoardForm boardForm, String path) {
		
		// jsp에서 적은 파일을 Multipartfile(BoardForm)에 저장했던걸 다시 Board에 저장함
		board.setMemberId(boardForm.getMemberId());
		board.setBoardTitle(boardForm.getBoardTitle());
		board.setBoardContent(boardForm.getBoardContent());
		board.getBoardNo();
		log.debug(CF.WSH + "NoticeService.addNotice.board : "+ board);
		
		// DB에 값을 넣는 쿼리호출
		int row = noticeMapper.updateNotice(board);
		log.debug(CF.WSH + "NoticeService.modifyNotice.row : "+ board);
		
		if(boardForm.getBoardfileList() != null && boardForm.getBoardfileList().get(0).getSize() > 0  && row == 1) {
			log.debug(CF.WSH + "NoticeService.addNotice.파일확인 : "+"첨부된 파일이 있습니다.");
			for(MultipartFile mf : boardForm.getBoardfileList()) {
				Boardfile boardfile = new Boardfile();
				String originName = mf.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf("."));
				String filename = UUID.randomUUID().toString();
				filename = filename.replace("-", ""); // -를 공백으로
				filename = filename + ext;
				boardfile.setBoardNo(board.getBoardNo());   
				boardfile.setBoardFileOriginalName(originName);
				boardfile.setBoardFileName(filename);
				boardfile.setBoardFileType(mf.getContentType());
				boardfile.setBoardFileSize(mf.getSize()); // long타입 // 파일의 배열 사이즈
				log.debug(CF.WSH + "NoticeService.addNotice.boardfile : "+ boardfile);
				noticefileMapper.insertNoticefile(boardfile);
				try {
					mf.transferTo(new File(path+filename));
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		}
		
	}
}


		
		


	
