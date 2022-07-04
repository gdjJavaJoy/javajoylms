package kr.co.javajoy.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.AllAdminListService;
import kr.co.javajoy.lms.service.MemberService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class AllAdminListController {
	@Autowired AllAdminListService allAdminListService;
	
	@Autowired MemberService memberService;
	// 관리자 리스트 URL GetMapping
	@GetMapping("/allAdminList")
	public String allAdminList(HttpSession session
								, Model model
								,@RequestParam @Nullable String searchAdminName // @NULLABLE = 널값허용 + 검색 파라메터 값 받아오기
								,@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
								,@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
	Map<String, Object> map = allAdminListService.AllAdminList(currentPage, rowPerPage, searchAdminName);
	String memberId = (String)session.getAttribute("loginUser");
	String level = (String)session.getAttribute("level");
	log.debug(CF.LGN + "AllAdminListController.allAdminList.sessionId : " + memberId);
	log.debug(CF.LGN + "AllAdminListController.allAdminList.level : " + level);
	model.addAttribute("list", map.get("list"));
	model.addAttribute("currentPage", currentPage);
	model.addAttribute("rowPerPage", rowPerPage);
	model.addAttribute("lastPage", map.get("lastPage"));
	model.addAttribute("searchAdminName", searchAdminName);
	log.debug(CF.LGN + "AllAdminListController.allAdminList.currentPage : " + currentPage);
	log.debug(CF.LGN + "AllAdminListController.allAdminList.rowPerPage : " + rowPerPage);
	log.debug(CF.LGN + "AllAdminListController.allAdminList.lastPage : " + map.get("lastPage"));
	log.debug(CF.LGN + "AllAdminListController.allAdminList.list : " + map.get("list"));
	log.debug(CF.LGN + "AllAdminListController.allAdminList.currentPage : " + searchAdminName);
	
	// 세션체크 관리자가 아닌경우 memberIndex로 redirect
	if(level.equals("2") || level.equals("3")) {
		return "redirect:/memberIndex";
	}
		// allAdminList.jsp로 이동
		return "allAdminList";
	}
}