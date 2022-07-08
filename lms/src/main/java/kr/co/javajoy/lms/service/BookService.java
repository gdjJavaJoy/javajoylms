package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.BookMapper;
import kr.co.javajoy.lms.vo.Book;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {
	@Autowired BookMapper bookMapper;

public Map<String,Object> getBookListByPage(int currentPage, int rowPerPage, String searchBookTitle) {
	int beginRow = (currentPage-1)*rowPerPage;
	log.debug(CF.PSG+"BookService.getBookListByPage beginRow : " + beginRow + CF.RESET);
	log.debug(CF.PSG+"BookService.getBookListByPage rowPerPage : " + rowPerPage + CF.RESET);
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("beginRow",beginRow);
	map.put("rowPerPage",rowPerPage);
	map.put("searchBookTitle",searchBookTitle);
	List<Book> list = bookMapper.selectBookListByPage(map);
	// 전체 총 게시물의 갯수 구하기 
	int totalCount = bookMapper.selectBookListCount(searchBookTitle);
	int lastPage = (int)(Math.ceil((double)totalCount /(double)rowPerPage));
	Map<String,Object> returnMap = new HashMap<>();
	returnMap.put("list",list);
	returnMap.put("lastPage", lastPage);
	returnMap.put("totalCount", totalCount);
	return returnMap;
	}
public int addBook(Book book) {
		log.debug(CF.PSG+"BookService.addBook book :" + book + CF.RESET);
		
		int row = bookMapper.insertBook(book);
	
	if(row == 1) {
		log.debug(CF.PSG+"BookService.addBook 책추가 성공"+ CF.RESET);
		} else {
		log.debug(CF.PSG+"BookService.addBook 책추가 실패"+ CF.RESET);
		}
	return row; 
	}
public Book selectBookOne(int bookNo) {
	log.debug(CF.PSG+"BookService.selectBookOne bookNo :" +bookNo + CF.RESET);
	Book book = new Book();
	book = bookMapper.selectBookOne(bookNo);
	
	return book;
	}
public int modifyBook(Book book) {
	log.debug(CF.PSG+"BookService.modifyBook bookNo : " +book + CF.RESET);
	
	int row = bookMapper.updateBook(book);
	
	if (row == 0) {
		log.debug(CF.PSG+"BookService.modifyBook 수정 실패" + CF.RESET);
	} else {
		log.debug(CF.PSG+"BookService.modifyBook 수정 성공" + CF.RESET);
	}
	return row;
	}
}
