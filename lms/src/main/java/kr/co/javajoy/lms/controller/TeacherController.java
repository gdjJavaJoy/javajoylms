package kr.co.javajoy.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.TeacherService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	@GetMapping("/teacherOne")
	public String teacherOne(HttpSession session
							,Model model) {
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		// 디버깅 
		log.debug(CF.PSG+"TeacherController.teacherOne.memberId :" + memberId + CF.RESET);
		log.debug(CF.PSG+"TeacherController.teacherOne.level :" + level + CF.RESET);
		Map<String,Object> teacherOne = teacherService.getTeacherOne(memberId);
		
		model.addAttribute("teacherOne",teacherOne);
		
		return "teacher/teacherOne";
	}
}
