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
import kr.co.javajoy.lms.mapper.InquiryMapper;
import kr.co.javajoy.lms.vo.AddInquiryForm;
import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.BoardComment;
import kr.co.javajoy.lms.vo.Boardfile;
import kr.co.javajoy.lms.vo.Receiver;
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
		List<Map<String,Object>> list = inquiryMapper.selectInquiryByPage(map);
		// 전체 총 게시물의 갯수 구하기
		int totalCount = inquiryMapper.selectInquiryTotalCount();
		int lastPage =  (int)(Math.ceil((double)totalCount / (double)rowPerPage)); 
		Map<String ,Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("totalCount", totalCount);
		return returnMap;
	}
	public List<Map<String,Object>> getTeacherListBySubject(String memberId) {
		
		List<Map<String,Object>> map = inquiryMapper.selectTeacherListBySubject(memberId);
		log.debug(CF.PSG+"InquiryService.getTeacherListBySubject list : " + map + CF.RESET);
		
		return map;
	}
	public int addInquiry(AddInquiryForm addInquiryForm, String path) {
		// 보드에 값 받아오기 
		log.debug(CF.PSG+"InquiryService.addInquiry path :"+ path + CF.RESET);
		log.debug(CF.PSG+"InquiryService.addInquiry addInquiryForm:"+ addInquiryForm + CF.RESET);
		 
		Board board = new Board();
		board.setBoardTitle(addInquiryForm.getBoardTitle());
		board.setBoardContent(addInquiryForm.getBoardContent());
		board.setPrivateNo(addInquiryForm.getPrivateNo());
		board.setMemberId(addInquiryForm.getMemberId());
		int row = inquiryMapper.insertInquiry(board);
		// insert 후  입력된 autoincrement 값 
		log.debug(CF.PSG+"InquiryService.addInquiry 생성된 NoticeId :"+ board.getBoardNo() + CF.RESET);
		
		int recevierNo = addInquiryForm.getRecevier();
		
		if (recevierNo== 2 && row == 1) { // 강사에게 글을 쓸때 
			Receiver receiver = new Receiver();
			List<String>  list= addInquiryForm.getTeacherId();
			log.debug(CF.PSG + "InquiryService.addInquiry 체크한 강사 수 :" +list.size() +CF.RESET);
		for(int i=0; i<list.size(); i++) {// 체크된 강사 만큼 
			log.debug(CF.PSG+"InquiryService.addInquiry 체크한InquiryService 강사 :" + list.get(i)+CF.RESET );
			receiver.setBoardNo(board.getBoardNo());
			receiver.setReceiver(list.get(i));
			inquiryMapper.insertReceiver(receiver);
			}
		}
		log.debug(CF.PSG+"InquiryService.addInquiry 파일 :" +addInquiryForm.getInquiryFileList() + CF.RESET);
		if (addInquiryForm.getInquiryFileList() != null  && row == 1 && addInquiryForm.getInquiryFileList().get(0).getSize() > 0) { // 파일을 첨부했을 때 
				log.debug(CF.PSG +"InquiryService.addInquiry 파일 첨부됨 " + CF.RESET);
			for(MultipartFile mf : addInquiryForm.getInquiryFileList()) {
				Boardfile boardfile = new Boardfile();
				
				String originalName = mf.getOriginalFilename(); // 파일 오리지널이름 
				String ext = originalName.substring(originalName.lastIndexOf("."));
				
				String fileName = UUID.randomUUID().toString(); // 파일 저장할때 중복되지않는 이름 사용 UUID API
				
				fileName = fileName+ext;
				boardfile.setBoardNo(board.getBoardNo());
				boardfile.setBoardFileName(fileName);
				boardfile.setBoardFileOriginalName(originalName);
				boardfile.setBoardFileSize(mf.getSize());
				boardfile.setBoardFileType(mf.getContentType());
				if (mf.getContentType().equals("image/jpeg") || mf.getContentType().equals("image/jpg") || mf.getContentType().equals("image/png")) {
				inquiryMapper.insertBoardFile(boardfile);
				try {
					mf.transferTo(new File(path+fileName));
				} catch (Exception e) {
					e.printStackTrace();
					// 새로운 예외 발생시켜야지만 @Transactional 작동함 
					throw new RuntimeException(); //RuntimeException은 예외처리 하지않아도 컴파일 됨
						}
					}
				}
			}
		
		return row;
	}
	public Map<String,Object> getInquiryOne(int boardNo) {
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> board = inquiryMapper.selectInquiryBoardOne(boardNo);
		List<Boardfile> boardFile = inquiryMapper.selectInquiryBoardfileOne(boardNo);
		List<Map<String,Object>> boardComment = inquiryMapper.selectInquiryComment(boardNo);
		List<String> receiver = inquiryMapper.selectReceiverListByBoardNo(boardNo);
		int fileCount = inquiryMapper.selectBoardCountByBoardNo(boardNo);
		
			log.debug(CF.PSG+"InquiryService.getInquiryOne Inquiry boardfile :" + boardFile +CF.RESET);
			log.debug(CF.PSG+"InquiryService.getInquiryOne Inquiry board :" + board +CF.RESET);
			log.debug(CF.PSG+"InquiryService.getInquiryOne Inquiry boardComment :" + boardComment +CF.RESET);
		
		map.put("receiver", receiver);
		map.put("fileCount", fileCount);
		map.put("board", board);
		map.put("boardFile", boardFile);
		map.put("boardComment", boardComment);
		
		return map;
	}
	public int removeInquiry(int boardNo,String path) {
		int row = 0;
		
		List<String> fileNameList = inquiryMapper.selectFiletNameList(boardNo);
		
		for (String boardFileName : fileNameList) {
			File file = new File(path+boardFileName);
			if(file.exists()) {
				file.delete();
			}
		}
				inquiryMapper.deleteAllInquiryFile(boardNo);
				inquiryMapper.deleteAllInquiryComment(boardNo);
		  row = inquiryMapper.deleteInquiry(boardNo);
		
		return row;
	}
	public int addInquiryComment(BoardComment boardComment) {
		int row = inquiryMapper.insertboardComment(boardComment);
		
		if (row == 1) {
			log.debug(CF.PSG+"InquiryService.addInquiryComment 답변 추가 성공 " + CF.RESET);
		} else {
			log.debug(CF.PSG+"InquiryService.addInquiryComment 답변 추가 실패 " + CF.RESET);
		}
		return row;
	}
	
	public int removeInquiryCommentByBoardCommentNo(int boardCommentNo) {
		
		int row = inquiryMapper.deleteInquiryCommentByBoardCommentNo(boardCommentNo);
		
	if(row == 1) {
		log.debug(CF.PSG+"InquiryService.removeInquiryCommentByBoardCommentNo 삭제 성공 "+CF.RESET);
		} else {
		log.debug(CF.PSG+"InquiryService.removeInquiryCommentByBoardCommentNo 삭제 실패 "+CF.RESET);
		}
		return row;
	}
}
