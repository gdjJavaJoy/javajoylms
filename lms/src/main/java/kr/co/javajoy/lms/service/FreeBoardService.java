package kr.co.javajoy.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.javassist.runtime.Cflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.BoardMapper;
import kr.co.javajoy.lms.mapper.FreeBoardMapper;
import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.BoardComment;
import kr.co.javajoy.lms.vo.BoardForm;
import kr.co.javajoy.lms.vo.Boardfile;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class FreeBoardService {
	@Autowired FreeBoardMapper freeBoardMapper;
	@Autowired BoardMapper boardMapper;
	public Map<String,Object> getFreeBoardByPage(int currentPage, int rowPerPage, String searchFreeBoardTitle) {
		//page 출력  
		int beginRow = (currentPage-1)*rowPerPage;
		// 디버깅 
		log.debug(CF.PSG+"FreeBoardService.getFreeBoardByPage.beginRow :" + beginRow + CF.RESET);
		log.debug(CF.PSG+"FreeBoardService.getFreeBoardByPage.rowPerPage :" + rowPerPage + CF.RESET);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("searchFreeBoardTitle", searchFreeBoardTitle);
		List<Map<String,Object>> list = freeBoardMapper.selectFreeBoardByPage(map);
		//개시물의 총 갯수 구하기 
		int totalCount = freeBoardMapper.selectFreeBoardTotalCount(searchFreeBoardTitle);
		int lastPage =  (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("totalCount", totalCount);
		return returnMap;
	}
	public Map<String,Object> getFreeBoardOne(int boardNo) {
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> board = boardMapper.selectBoardOne(boardNo);
		List<Boardfile> boardFile = boardMapper.selectBoardfileList(boardNo);
		List<Map<String,Object>> boardComment = boardMapper.selectBoardComment(boardNo);
		int fileCount = boardMapper.selectBoardCountByBoardNo(boardNo);
		log.debug(CF.PSG+"FreeBoardService.getrFreeBoardOne board :" +board+CF.RESET);
		log.debug(CF.PSG+"FreeBoardService.getrFreeBoardOne boardfile :" +boardFile+CF.RESET);
		log.debug(CF.PSG+"FreeBoardService.getrFreeBoardOne boardComment :" +boardComment+CF.RESET);
		log.debug(CF.PSG+"FreeBoardService.getrFreeBoardOne fileCount :" +fileCount+CF.RESET);
		
		map.put("board", board);
		map.put("boardFile", boardFile);
		map.put("boardComment", boardComment);
		map.put("fileCount", fileCount);
		
		return map;
	}
	public int addFreeBoard(BoardForm boardForm, String path) {
		// 디버깅 
		log.debug(CF.PSG+"FreeBoardService.addFreeBoard path :" + path + CF.RESET);
		log.debug(CF.PSG+"FreeBoardService.addFreeBoard boardForm : " + boardForm+CF.RESET);
		
		Board board = new Board();
		board.setBoardTitle(boardForm.getBoardTitle());
		board.setBoardContent(boardForm.getBoardContent());
		board.setMemberId(boardForm.getMemberId());
		board.setPrivateNo(boardForm.getPrivateNo());
		int row = freeBoardMapper.addFreeBoard(board);
		
		log.debug(CF.PSG+"생성된 BoardNo :" + board.getBoardNo()+CF.RESET);
		log.debug(CF.PSG+"FreeBoardService.addFreeBoard 파일 " +boardForm.getBoardfileList()+CF.RESET);
	// 파일 추가했을때 
	if (boardForm.getBoardfileList() != null && row == 1 && boardForm.getBoardfileList().get(0).getSize()>0) {
		log.debug(CF.PSG+"FreeBoardService.addFreeBoard 파일 첨부됨 " +CF.RESET);
	for(MultipartFile mf : boardForm.getBoardfileList()) {
		Boardfile boardfile = new Boardfile();
		
		String originalName = mf.getOriginalFilename();
		String ext = originalName.substring(originalName.lastIndexOf("."));
		String fileName = UUID.randomUUID().toString();
		
		fileName = fileName+ext;
		boardfile.setBoardNo(board.getBoardNo());
		boardfile.setBoardFileName(fileName);
		boardfile.setBoardFileOriginalName(originalName);
		boardfile.setBoardFileSize(mf.getSize());
		boardfile.setBoardFileType(mf.getContentType());
		
		boardMapper.insertBoardFile(boardfile);
		try {
			mf.transferTo(new File(path+fileName));
		} catch(Exception e) {
			e.printStackTrace();
			// 새로운 예외 발생시켜야지만 @Transactional 작동함 
			throw new RuntimeException(); //RuntimeException은 예외처리 하지않아도 컴파일 됨
			}
		}
	
	}
		
		return row; 
	}
public int removeFreeBoard(int boardNo,String path) {
	int row = 0;
	
	List<String> fileNameList = boardMapper.selectFiletNameList(boardNo);
	
	for (String boardFileName : fileNameList) {
		File file = new File(path+boardFileName);
		if(file.exists()) {
			file.delete();
			}
		}
	  boardMapper.deleteBoardFileByBoardNo(boardNo);
	  boardMapper.deleteBoardCommentByBoardNo(boardNo);
row = boardMapper.deleteBoard(boardNo);

return row;
	}
public int addFreeBoardComment(BoardComment boardComment) {
	int row = freeBoardMapper.insertFreeBoardComment(boardComment);
	
	if (row == 1) {
		log.debug(CF.PSG+"FreeBoardService.addFreeBoardComment 답변 추가 성공 "+CF.RESET);
		} else {
		log.debug(CF.PSG+"FreeBoardService.addFreeBoardComment 답변 추가 실패 "+CF.RESET);
		}
	return row;
	}
public int removeFreeBoardCommentByBoardCommentNo(int boardCommentNo) {
	int row = boardMapper.deleteCommentByBoardCommentNo(boardCommentNo);
	if (row == 1) {
		log.debug(CF.PSG+"FreeBoardService.removeFreeBoardCommentByBoardCommentNo 삭제성공 " +CF.RESET);
	} else {
		log.debug(CF.PSG+"FreeBoardService.removeFreeBoardCommentByBoardCommentNo 삭제실패 " +CF.RESET);
		}
	return row;
	}
public Map<String,Object> getModifyFreeBoardOne(int boardNo) {
	Map<String,Object> map = new HashMap<>();
	Map<String,Object> board = boardMapper.selectBoardOne(boardNo);
	List<Boardfile> boardFile = boardMapper.selectBoardfileList(boardNo);
	int fileCount = boardMapper.selectBoardCountByBoardNo(boardNo);
	
	log.debug(CF.PSG+"FreeBoardService.getModifyFreeBoardOne  boardfile :" + boardFile +CF.RESET);
	log.debug(CF.PSG+"InquiryService.getModifyFreeBoardOne board :" + board +CF.RESET);
	
	map.put("boardFile", boardFile);
	map.put("board", board);
	map.put("fileCount",fileCount);
	return map;
	}
public 	int modifyFreeBoard(BoardForm boardForm,String path) {
	log.debug(CF.PSG+"FreeBoardService.modifyFreeBoard.BoardForm :" + boardForm + CF.RESET);
	Board board = new Board();
	int boardNo = boardForm.getBoardNo();
	log.debug(CF.PSG+"FreeBoardService.modifyFreeBoard.boardNo :" + boardNo + CF.RESET);
	board.setBoardNo(boardNo);
	board.setBoardTitle(boardForm.getBoardTitle());
	board.setBoardContent(boardForm.getBoardContent());
	board.setPrivateNo(boardForm.getPrivateNo());
	int row = freeBoardMapper.updateFreeBoard(board);
	
	log.debug(CF.PSG+"FreeBoardService.modifyFreeBoard 파일 : " +boardForm.getBoardfileList() + CF.RESET);
	if (boardForm.getBoardfileList() != null && row == 1 && boardForm.getBoardfileList().get(0).getSize() >0) {
			log.debug(CF.PSG+"FreeBoardService.modifyFreeboard 파일 첨부됨 "+CF.RESET);
		for(MultipartFile mf : boardForm.getBoardfileList()) {
			Boardfile boardfile = new Boardfile();
			
			String originalName = mf.getOriginalFilename();
			String ext = originalName.substring(originalName.lastIndexOf("."));
			
			String fileName = UUID.randomUUID().toString();
			
			fileName = fileName+ext;
			boardfile.setBoardNo(boardNo);
			boardfile.setBoardFileName(fileName);
			boardfile.setBoardFileOriginalName(originalName);
			boardfile.setBoardFileSize(mf.getSize());
			boardfile.setBoardFileType(mf.getContentType());
			boardMapper.insertBoardFile(boardfile);
			try {
				mf.transferTo(new File(path+fileName));
			} catch (Exception e) {
				e.printStackTrace();
				// 새로운 예외 발생시켜야지만 @Transactional 작동함 
				throw new RuntimeException(); // RuntimeException은 예외처리 하지않아도 컴파일 
			}
		}
	}
	return row;
}

public int removeFreeBoardfileByBoardFileNo(int boardFileNo,String path) {
		int row = 0;
		log.debug(CF.PSG+"removeFreeBoardfileByBoardFileNo path :" +path + CF.RESET);
		String fileName = boardMapper.selectBoardFileNameByBoardFileNo(boardFileNo);
		// 저장되어있는 파일 삭제
		File file = new File(path+fileName);
		if(file.exists()) {
			file.delete();
		}
		
		row = boardMapper.deleteBoardfile(boardFileNo);
		
		return row; 
	}
public Map<String,Object> getFreeBoardByMemberId (int currentPage, int rowPerPage, String searchFreeBoardTitle, String memberId) {
		log.debug(CF.PSG+"FreeBoardSerivce.sessionId : " + memberId+CF.RESET);
		//page 출력  
		int beginRow = (currentPage-1)*rowPerPage;
		// 디버깅 
		log.debug(CF.PSG+"FreeBoardService.getFreeBoardByMemberId.beginRow :" + beginRow + CF.RESET);
		log.debug(CF.PSG+"FreeBoardService.getFreeBoardByMemberId.rowPerPage :" + rowPerPage + CF.RESET);
		log.debug(CF.PSG+"FreeBoardService.getFreeBoardByMemberId.searchFreeBoardTitle :" + searchFreeBoardTitle + CF.RESET);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginRow",beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("searchFreeBoardTitle", searchFreeBoardTitle);
		map.put("memberId", memberId);
		List<Map<String,Object>> list = freeBoardMapper.selectFreeBoardByMemberId(map);
		// 게시물 총 갯수 구하기 
		int totalCount = freeBoardMapper.selectFreeBoardTotalCountByMemberId(map);
		int lastPage =  (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		log.debug(CF.PSG+"FreeBoardService.getFreeBoardByMemberId totalCount: " +totalCount+ CF.RESET);
		log.debug(CF.PSG+"FreeBoardService.getFreeBoardByMemberId totalCount: " +lastPage+ CF.RESET);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("totalCount", totalCount);

		
		return returnMap;
	}
}
