package kr.co.javajoy.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.javajoy.lms.CF;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentController {
	
	@GetMapping("memberIndex")
	public String sudentIndex(HttpSession session) {
		log.debug(CF.YHJ + "StudentController.studentIndex.loginUser : " + session.getAttribute("loginUser") + CF.RESET);
		
		return "member/memberIndex";
	}
}
