package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.Boardfile;
import kr.co.javajoy.lms.vo.Recevier;

@Mapper
public interface InquiryMapper {
	// 문의사항 개시판 
	List<Board> selectInquiryByPage(Map<String,Object> map);
	// 문의사항의 글의 총개수
	int selectInquiryTotalCount();
	// 해당학생을 가르키는 강사 리스트
	List<Map<String,Object>> selectTeacherListBySubject(String memberId);
	
	int insertInquiry(Board board);
	
	int insertBoardFile(Boardfile boardFile);
	
	int insertRecevier(Recevier recevier);
	
}
