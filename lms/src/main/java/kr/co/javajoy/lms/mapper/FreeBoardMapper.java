package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.BoardComment;

@Mapper
public interface FreeBoardMapper {
	// 자유개시판 리스트 출력 
	 List<Map<String,Object>> selectFreeBoardByPage (Map<String,Object> map);
	// 자유개시판 총 글 개수 
	 int selectFreeBoardTotalCount(String searchFreeBoardTitle);
	 // 게시물 댓글 
	 // 게시물 파일 갯수 
	 int addFreeBoard(Board board);
	 
	 int insertFreeBoardComment(BoardComment boardComment);
	 
	 int updateFreeBoard(Board board);
}
