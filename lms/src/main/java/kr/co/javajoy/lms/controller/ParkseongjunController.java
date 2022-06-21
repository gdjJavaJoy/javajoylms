package kr.co.javajoy.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.vo.SignupForm;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ParkseongjunController {
	@GetMapping("/addMember")
	public String addMember() {
		
		return "login/addMember";
	}
	
	@PostMapping("/addMember")
	public String addMember(SignupForm signUpForm) {
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.gender :" + signUpForm.getGender()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.Education :" + signUpForm.getEducation()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.MemberJoin :" + signUpForm.getMemberJoin()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.getMemberAddr :" + signUpForm.getMemberAddress()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.getMemberAddr :" + signUpForm.getMemberActive()+CF.RESET);
		
		return "redirect:/addMember";
	}
}
