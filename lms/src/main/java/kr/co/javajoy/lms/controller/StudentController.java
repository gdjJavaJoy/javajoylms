package kr.co.javajoy.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.StudentService;
import kr.co.javajoy.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentController {
	@Autowired StudentService studentService;
	
	@GetMapping("memberIndex")
	public String sudentIndex(HttpSession session
							,Model model) {
		String memberId = (String) session.getAttribute("loginUser");
		String level = (String) session.getAttribute("level");
		// 디버깅
		log.debug(CF.YHJ + "StudentController.memberIndex.loginUser : " + session.getAttribute("loginUser") + CF.RESET);
		log.debug(CF.YHJ + "StudentController.memberIndex.level : " + session.getAttribute("level") + CF.RESET);

		List<Subject> memberList = studentService.memberIndex(memberId,level);
		
		log.debug(CF.YHJ + "StudentController.memberIndex.memberList : " + memberList + CF.RESET); // 디버깅
		
		model.addAttribute("memberList",memberList);
		
		return "member/memberIndex";
	}
}
