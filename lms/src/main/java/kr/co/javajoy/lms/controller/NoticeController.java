package kr.co.javajoy.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.service.NoticeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	@Autowired private NoticeService noticeService;
	
	@GetMapping("/getNoticeByPage")
	public String getNoticeByPage(Model model
			,@RequestParam(name = "currentPage", defaultValue = "1") int currentPage
			,@RequestParam(name = "rowPerPager", defaultValue = "10") int rowPerPage) {
		
		Map<String, Object> map = noticeService.getNoticeByPage(currentPage, rowPerPage);
		model.addAttribute("list",map.get("list"));
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		log.debug(currentPage+"-------------------------------------------");
		return "board/getNoticeByPage";
	}
	@GetMapping("/getNoticeOne")
	public String getNoticeOne(Model model
			,HttpServletRequest request
			,@RequestParam(name = "boardNo") int boardNo) {
		log.debug(boardNo + "--------------------------");
		String path = request.getServletContext().getRealPath("/upload/");
		
		Map<String, Object> map = noticeService.getNoticeOne(boardNo);
		model.addAttribute("path", path);
		model.addAttribute("board",map.get("board"));
		model.addAttribute("boardfile",map.get("boardfile"));
		return "board/getNoticeOne";
	}
	
}