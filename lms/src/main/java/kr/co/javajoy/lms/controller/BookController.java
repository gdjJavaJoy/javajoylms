package kr.co.javajoy.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.BookService;
import kr.co.javajoy.lms.vo.Book;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookController {
	@Autowired BookService bookService;
	
	@GetMapping("/getBookListByPage")
public String getBookListByPage(Model model
							,@RequestParam(value="currentPage" , defaultValue="1") int currentPage
							,@RequestParam(value="rowPerPage",defaultValue="10") int rowPerPage
							,@RequestParam @Nullable String searchBookTitle) {
	//디버깅 
	log.debug(CF.PSG+"BookController.getBookListByPage currentPage : " +currentPage + CF.RESET);
	log.debug(CF.PSG+"BookController.getBookListByPage rowPerPage : " +rowPerPage + CF.RESET);
	log.debug(CF.PSG+"BookController.getBookListByPage searchBookTitle : " +searchBookTitle + CF.RESET);
	Map<String,Object> map = bookService.getBookListByPage(currentPage, rowPerPage, searchBookTitle);
	
	model.addAttribute("searchBookTitle", searchBookTitle);
	model.addAttribute("list", map.get("list"));
	model.addAttribute("lastPage", map.get("lastPage"));
	model.addAttribute("rowPerPage", map.get("rowPerPage"));
	model.addAttribute("totalCount", map.get("totalCount"));
	model.addAttribute("currentPage", map.get("currentPage"));
	
	return "curriculum/book/getBookListByPage";
	}
	@GetMapping("/addBook")
public String addBook() {
		
	return "curriculum/book/addBook";
	}
	@PostMapping("/addBook")
public String addBook(Book book) {
		log.debug(CF.PSG+"bookController.addBook.Post() book :" +book + CF.RESET);
		int row = bookService.addBook(book);
		
		if (row == 0) {
			log.debug(CF.PSG+"BookController.addBook 책추가 실패"+CF.RESET);
			return "redirect:/addBook";
		}
		
		return "redirect:/getBookListByPage";
	}
	@GetMapping("/modifyBook")
	public String modifyBook(Model model
						,@RequestParam(value="bookNo") int bookNo) {
		
		Book book = bookService.selectBookOne(bookNo);
		
		model.addAttribute("book",book);
		
		return "curriculum/book/modifyBook";
	}
	@PostMapping("/modifyBook")
	public String modifyBook(Book book) {
		int row = bookService.modifyBook(book);
		
		if (row == 0) {
			log.debug(CF.PSG+"BookController.modifyBook 수정실패" + CF.RESET);
			return "redirect:/modifyBook?bookNo="+book.getBookNo();
		}
		
		log.debug(CF.PSG+"BookController.modifyBook 수정성공" + CF.RESET);
		return "redirect:/getBookListByPage";
	}
	
}
