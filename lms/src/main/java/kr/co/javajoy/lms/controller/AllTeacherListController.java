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
import kr.co.javajoy.lms.service.AllTeacherListService;
import kr.co.javajoy.lms.service.MemberService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class AllTeacherListController {
	@Autowired AllTeacherListService allTeacherListService;

	@Autowired MemberService memberService;
	// 강사 리스트 URL GetMapping
	@GetMapping("/allTeacherList")
	public String allTeacherList(HttpSession session
								, Model model
								,@RequestParam @Nullable String SearchTeacherName // @NULLABLE = 널값허용 + 검색 파라메터 값 받아오기
								,@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
								,@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
	Map<String, Object> map = allTeacherListService.AllTeacherList(currentPage, rowPerPage, SearchTeacherName);
	String memberId = (String)session.getAttribute("loginUser");
	String level = (String)session.getAttribute("level");
	log.debug(CF.LGN + "AllTeacherListController.allTeacherList.sessionId : " + memberId);
	log.debug(CF.LGN + "AllTeacherListController.allTeacherList.level : " + level);
	model.addAttribute("list", map.get("list"));
	model.addAttribute("currentPage", currentPage);
	model.addAttribute("rowPerPage", rowPerPage);
	model.addAttribute("lastPage", map.get("lastPage"));
	model.addAttribute("SearchTeacherName", SearchTeacherName);
	log.debug(CF.LGN + "AllTeacherListController.allTeacherList.currentPage : " + currentPage);
	log.debug(CF.LGN + "AllTeacherListController.allTeacherList.rowPerPage : " + rowPerPage);
	log.debug(CF.LGN + "AllTeacherListController.allTeacherList.list : " + map.get("list"));
	log.debug(CF.LGN + "AllTeacherListController.allTeacherList.lastPage : " + map.get("lastPage"));
	log.debug(CF.LGN + "AllTeacherListController.allTeacherList.SearchTeacherName : " + SearchTeacherName);
	
	// 세션체크 관리자가 아닌경우 memberIndex로 redirect
	if(level.equals("2") || level.equals("3")) {
		return "redirect:/memberIndex";
	}
		// allTeacherList.jsp로 이동
		return "/member/teacher/allTeacherList";
	}
	
	@GetMapping("/deleteTeacher")
	public String allTeacherList(@RequestParam(name="memberId") String memberId) {
		
		int deleteTeacher = allTeacherListService.deleteTeacher(memberId);
		int deleteMemberId = allTeacherListService.deleteMemberId(memberId);
	      if(deleteTeacher == 1 && deleteMemberId == 1 ) {
	    	  log.debug(CF.LGN + "allTeacherListController.deleteTeacher.deleteTeacher : "+ deleteTeacher); // 성공
	      } else {
	    	  log.debug(CF.LGN + "allTeacherListController.deleteTeacher.deleteMemberId : "+ deleteMemberId); // 실패
	      }
	      return "redirect:/allTeacherList"; 
	}
}