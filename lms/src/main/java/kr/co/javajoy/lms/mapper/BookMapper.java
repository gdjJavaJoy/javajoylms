package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Book;

@Mapper
public interface BookMapper {
	// 책 리스트 출력
	List<Book> selectBookListByPage(Map<String,Object> map);
	//책의 총 갯수 출력
	int selectBookListCount(String searchBookTitle);
	// 책 추가 
	int insertBook(Book book);
	// 책 수정 
	int updateBook(Book board);
	
	Book selectBookOne(int boardNo);
}
