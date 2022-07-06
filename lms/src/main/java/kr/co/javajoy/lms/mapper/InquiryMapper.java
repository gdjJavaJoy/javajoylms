package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.BoardComment;
import kr.co.javajoy.lms.vo.Receiver;
import kr.co.javajoy.lms.vo.Teacher;

@Mapper
public interface InquiryMapper {
	// 문의사항 개시판 
	List<Map<String,Object>> selectInquiryByPage(Map<String,Object> map);
	// 문의사항의 글의 총개수
	int selectInquiryTotalCount(String searchInquiryTitle);
	// 해당학생을 가르키는 강사 리스트
	List<Map<String,Object>> selectTeacherListBySubject(String memberId);
	// 문의사항입력 
	int insertInquiry(Board board);

	int insertReceiver(Receiver recevier);
	
	List<String> selectReceiverListByBoardNo(int boardNo);
	// 답변 달기 
	int insertInquiryComment(BoardComment boardComment);
	// 선택한 답변  삭제
	
	List<Teacher> selectCheckedReceiverTeacherName(int boardNo);
	
	int updateInquiryBoard(Board board);
	
	int deleteReceiver(int boardNo);
	
	List<Receiver> receiverList();
	
	List<Map<String,Object>> selectInquiryByMemberId (Map<String,Object> map); 
	
	int selectInquiryTotalCountByMemberId(Map<String,Object> map);
	
}
