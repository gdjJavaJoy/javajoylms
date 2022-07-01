package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.BoardComment;
import kr.co.javajoy.lms.vo.Boardfile;
import kr.co.javajoy.lms.vo.Receiver;

@Mapper
public interface InquiryMapper {
	// 문의사항 개시판 
	List<Map<String,Object>> selectInquiryByPage(Map<String,Object> map);
	// 문의사항의 글의 총개수
	int selectInquiryTotalCount();
	// 해당학생을 가르키는 강사 리스트
	List<Map<String,Object>> selectTeacherListBySubject(String memberId);
	
	int insertInquiry(Board board);
	
	int insertBoardFile(Boardfile boardFile);
	
	int insertReceiver(Receiver recevier);
	
	Map<String,Object> selectInquiryBoardOne(int boardNo);
	
	List<Boardfile> selectInquiryBoardfileOne(int boardNo);
	
	int selectBoardCountByBoardNo(int boardNo);
	
	List<Map<String,Object>> selectInquiryComment(int boardNo);
	
	int deleteInquiry(int boardNo);
	
	int deleteAllInquiryFile(int boardNo);
	
	int deleteAllInquiryComment(int boardNo);
	
	List<String> selectFiletNameList(int boardNo);
	// 
	List<String> selectReceiverListByBoardNo(int boardNo);
	// 답변 달기 
	int insertboardComment(BoardComment boardComment);
	// 선택한 답변  삭제
	int deleteInquiryCommentByBoardCommentNo(int boardCommentNo);
	
	
}
