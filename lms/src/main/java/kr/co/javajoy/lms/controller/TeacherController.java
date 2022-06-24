package kr.co.javajoy.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.TeacherService;
import kr.co.javajoy.lms.vo.Career;
import kr.co.javajoy.lms.vo.Language;
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
		List<Career> careerList = teacherService.getTeacherCareer(memberId);
		
		model.addAttribute("teacherOne",teacherOne);
		model.addAttribute("careerList",careerList);
		return "member/teacher/teacherOne";
	}
	@GetMapping("/modifyTeacherOne")
	public String modifyTeacherOne(Model model
								  ,@RequestParam(name="memberId") String memberId) {
		Map<String,Object> map = teacherService.getModifyTeacherList(memberId); // 강사 정보 수정할 때 필요한 list 뽑아오는 메서드 
		// 디버깅 
		log.debug(CF.PSG+"TeacherController.modifyTeacherOne.teacherOne : " +map.get("teacherOne") +CF.RESET);
		log.debug(CF.PSG+"TeacherController.modifyTeacherOne.languageList : " +map.get("languageList") +CF.RESET);
		log.debug(CF.PSG+"TeacherController.modifyTeacherOne.careerList : " +map.get("careerList") +CF.RESET);
		log.debug(CF.PSG+"TeacherController.modifyTeacherOne.teacherLanguageList : " +map.get("teacherLanguageList") +CF.RESET);
		
		model.addAttribute("teacherOne",map.get("teacherOne"));
		model.addAttribute("languageList",map.get("languageList"));
		model.addAttribute("careerList",map.get("careerList"));
		model.addAttribute("teacherLanguageList",map.get("teacherLanguageList"));
		return "member/teacher/modifyTeacherOne";
	}
	
	@GetMapping("/removeCareer")
	public String removeCareer(int careerNo
							  ,HttpSession httpSession) {
		String memberId = (String)httpSession.getAttribute("loginUser");
		log.debug(CF.PSG+"teacherController.removeCareer.memberId: "+ memberId+CF.RESET);
		log.debug(CF.PSG+"teacherController.removeCareer.carrerNo :" + careerNo +CF.RESET);
		
			int row = teacherService.removeCareer(careerNo);
			if (row == 1) {
				log.debug("teacherController.removeCareer  삭제성공");
			} else {
				log.debug("teacherController.removeCareer  삭제실패");
			}
			
		
		return "redirect:/modifyTeacherOne?memberId="+memberId;
	}
}
