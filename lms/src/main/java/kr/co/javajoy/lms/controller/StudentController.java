package kr.co.javajoy.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.StudentService;
import kr.co.javajoy.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentController {
	@Autowired StudentService studentService;
	
	@GetMapping("/studentOne")
	public String studentOne(HttpSession session
							,Model model) {
		String loginUser = session.getAttribute("loginUser").toString();
		log.debug(CF.YHJ + "StudentController.getStudentOne.loginUser : " + loginUser); // 디버깅
		
		Student student = studentService.getStudentOne(loginUser);
		log.debug(CF.YHJ + "StudentController.getStudentOne.student : " + student); // 디버깅
		
		model.addAttribute("student",student);
		
		return "/member/student/studentOne";
	}
	
	@GetMapping("/modifyStudentOne")
	public String modifyStudentOne(@RequestParam(value="memberId") String memberId
									,Model model) {
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.memberId : " + memberId); // 디버깅
		
		Student student = studentService.getStudentOne(memberId);
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.student : " + student); // 디버깅
		
		model.addAttribute(student);
		
		return "/member/student/modifyStudentOne";
	}
}
