package kr.co.javajoy.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.MemberMapper;
import kr.co.javajoy.lms.service.MemberService;
import kr.co.javajoy.lms.vo.SignupForm;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ParkseongjunController {
	@GetMapping("/addMember")
	public String addMember() {
		
		return "login/addMember";
	}
	@Autowired MemberService memberService;
	@PostMapping("/addMember")
	public String addMember(SignupForm signUpForm) {
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.gender :" + signUpForm.getGender()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.Education :" + signUpForm.getEducation()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.MemberJoin :" + signUpForm.getMemberJoin()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.getMemberAddr :" + signUpForm.getMemberAddress()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.memberPw :" + signUpForm.getMemberPw()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.Active :" + signUpForm.getMemberActive()+CF.RESET);
		int row = memberService.addMember(signUpForm);
		
		if (row==1) {
			log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember 추가성공"+CF.RESET);
			return "redirect:/login";
		} else {
			log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember 추가실패"+CF.RESET);
		return "redirect:/addMember";
		}
	}
	@GetMapping("/modifyPw")
	public String modifyPw(HttpSession session) {
		String memberId = (String) session.getAttribute("loginUser"); // 세션에 있는 loginUser 값 변수에 저장 
		String active = memberService.getMemberActive(memberId); // memberId를 사용해서 level값 받아오기
		
		//디버깅
		log.debug(CF.PSG+"ParkseongjunController.modifyPw.memberId : " + memberId+ CF.PSG);
		log.debug(CF.PSG+"ParkseongjunController.modifyPw.active : " + active + CF.PSG);
		
		
		return "index";
	}
	
}
