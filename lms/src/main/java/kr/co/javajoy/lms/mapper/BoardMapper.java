package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Boardfile;

@Mapper
public interface BoardMapper {
	
	int insertBoardFile(Boardfile boardFile);
	
	Map<String,Object> selectBoardOne(int boardNo);

	List<Boardfile> selectBoardfileList(int boardNo);
	
	int selectBoardCountByBoardNo(int boardNo);
	
	List<Map<String,Object>> selectBoardComment(int boardNo);
	
	int deleteCommentByBoardCommentNo(int boardCommentNo);
	
	int deleteBoard(int boardNo);
	
	int deleteBoardFileByBoardNo(int boardNo);
	
	int deleteBoardCommentByBoardNo(int boardNo);
	
	List<String> selectFiletNameList(int boardNo);
	
	int deleteBoardfile(int boardFileNo);
	
	String selectBoardFileNameByBoardFileNo(int boardFileNo);
}
