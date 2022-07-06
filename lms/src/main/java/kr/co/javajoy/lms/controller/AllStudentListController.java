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
import kr.co.javajoy.lms.service.AllStudentListService;
import kr.co.javajoy.lms.service.MemberService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class AllStudentListController {
	@Autowired AllStudentListService allStudentListService;

	@Autowired MemberService memberService;
	
	// 학생 리스트 URL GetMapping
	@GetMapping("/allStudentList")
	public String allStudentList(HttpSession session
								,Model model
								,@RequestParam @Nullable String searchStudentName // @NULLABLE = 널값허용 + 검색 파라메터 값 받아오기
								,@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
								,@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
	Map<String, Object> map = allStudentListService.AllStudentList(currentPage, rowPerPage, searchStudentName);
	String memberId = (String)session.getAttribute("loginUser");
	String level = (String)session.getAttribute("level");
	log.debug(CF.LGN + "AllStudentListController.allStudentList.sessionId : " + memberId);
	log.debug(CF.LGN + "AllStudentListController.allStudentList.level : " + level);
	model.addAttribute("list", map.get("list"));
	model.addAttribute("currentPage", currentPage);
	model.addAttribute("rowPerPage", rowPerPage);
	model.addAttribute("lastPage", map.get("lastPage"));
	model.addAttribute("searchStudentName", searchStudentName);
	log.debug(CF.LGN + "AllStudentListController.allStudentList.currentPage : " + currentPage);
	log.debug(CF.LGN + "AllStudentListController.allStudentList.rowPerPage : " + rowPerPage);
	log.debug(CF.LGN + "AllStudentListController.allStudentList.lastPage : " + map.get("lastPage"));
	log.debug(CF.LGN + "AllStudentListController.allStudentList.list : " + map.get("list"));
	log.debug(CF.LGN + "AllStudentListController.allStudentList.currentPage : " + searchStudentName);
	
	// 세션체크 관리자가 아닌경우 memberIndex로 redirect
	if(level.equals("2") || level.equals("3")) {
		return "redirect:/memberIndex";
	} 
		// studentList.jsp로 이동.
		return "allStudentList";
	}
	
	@GetMapping("/deleteStudent")
	public String allStudentList(@RequestParam(name="memberId") String memberId) {
		
		int deleteStudent = allStudentListService.deleteStudent(memberId);
		int deleteMemberId = allStudentListService.deleteMemberId(memberId);
	      if(deleteStudent == 1 && deleteMemberId == 1 ) {
	    	  log.debug(CF.LGN + "allStudentListController.deleteStudent.deleteStudent : "+ deleteStudent); // 성공
	      } else {
	    	  log.debug(CF.LGN + "allStudentListController.deleteStudent.deleteMemberId : "+ deleteMemberId); // 실패
	      }
	      return "redirect:/allStudentList"; 
	}
}