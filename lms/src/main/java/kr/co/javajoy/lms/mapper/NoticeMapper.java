package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.Notice;

@Mapper
public interface NoticeMapper {
	// Notice 리스트
	List<Board> selectNoticeByPage(Map<String, Object>map);
	// 전체 row
	int selectTotalCount();
	// Notice One
	List<Board>selectNoticeOne(int boardNo);
	// add Notice
	int insertNotice(Board board);
	// 삭제
	int deleteNotice(int boardNo);
	
	

}
