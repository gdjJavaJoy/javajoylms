package kr.co.javajoy.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.AllAdminListService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class AllAdminListController {
	@Autowired AllAdminListService allAdminListService;

	// 학생 리스트 URL GetMapping
	@GetMapping("/allAdminList")
	public String allAdminList(HttpSession session
								, Model model
								,@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
								,@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
	Map<String, Object> map = allAdminListService.AllAdminList(currentPage, rowPerPage);
	String memberId = (String)session.getAttribute("loginUser");
	String level = (String)session.getAttribute("level");
	log.debug(CF.LGN + "AllAdminListController.allAdminList.sessionId : " + memberId);
	log.debug(CF.LGN + "AllAdminListController.allAdminList.level : " + level);
	model.addAttribute("list", map.get("list"));
	model.addAttribute("currentPage", currentPage);
	model.addAttribute("lastPage", map.get("lastPage"));
	log.debug(CF.LGN + "AllAdminListController.allAdminList.currentPage : " + currentPage);
	log.debug(CF.LGN + "AllAdminListController.allAdminList.lastPage : " + rowPerPage);
	
	// 세션체크 관리자가 아닌경우 memberIndex로 redirect
	if(level.equals("2") || level.equals("3")) {
		return "redirect:/memberIndex";
	}
		// allAdminList.jsp로 이동
		return "allAdminList";
	}
}